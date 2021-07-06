package me.kvq.supertrailspro.database;

import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.UUID;

import javax.swing.DebugGraphics;

import org.bukkit.configuration.file.FileConfiguration;

import me.kvq.supertrailspro.SuperTrailsPro;
import me.kvq.supertrailspro.event.EventManager;
import me.kvq.supertrailspro.player.PlayerManager;
import me.kvq.supertrailspro.player.STPlayer;
import me.kvq.supertrailspro.player.Storage;
import me.kvq.supertrailspro.utils.STLog;
import me.kvq.supertrailspro.utils.STUtils;
import pro.husk.mysql.MySQL;

public class STDB implements Storage {
	
	public final static int DEFAULT_PORT = 3306;
	protected Database db;
	private FileConfiguration config;
	protected boolean working = false;
	private DatabaseWorker worker;
	protected STLog log = SuperTrailsPro.getLogManager();
	
	public STDB() throws ClassNotFoundException, SQLException {
		this(STUtils.getConfig());
	}
	
	public STDB(FileConfiguration config) throws ClassNotFoundException, SQLException {

		this.config = config;
		String ip = readconfig("ip"), dbname = readconfig("database"),
				login = readconfig("login"), password = readconfig("password");
		int port = config.getInt("MYSQL.port");
	
		setupDB(ip, dbname, login, password, port);
	}
	
	public STDB(String ip, String dbname, String login,String password,int port) throws ClassNotFoundException, SQLException {
		setupDB(ip, dbname, login, password, port);
	}
	
	private void setupDB(String ip, String dbname, String login,String password,int port) throws ClassNotFoundException, SQLException {
		
			db = new STMySQL(ip, port, dbname, login, password);

	
		
		this.working = true;
		this.worker = new DatabaseWorker();
		prepareDB();
	}
	
	
	public void prepareDB() {
		this.worker.addQueue(
				new DBAction("CREATE TABLE IF NOT EXISTS `supertrailspro` (`Player` varchar(64),`Data` varchar(256),`Event` varchar(256))"));
	}
	
	public String readconfig(String value) {
		return config.getString("MYSQL." + value);
	}

	@Override
	public void loadPlayer(UUID uuid) {
		DBAction load = 
				new DBAction("SELECT * FROM `supertrailspro` WHERE Player = '" + uuid.toString() + "';", 
						
				result -> {
					String data = result.getString("Data");
					String event_json = result.getString("Event");
					
					PlayerManager.loadFromJSON(uuid, data, event_json);
				} );
		this.worker.addQueue(load);
	}

	@Override
	public void savePlayer(STPlayer player) {
		
		String uuid = player.getUUID().toString(), json_data = player.getJSONData(), event_json = player.getJSONEventData();
		DBAction save = 
				new DBAction("INSERT INTO `supertrailspro` (`Player`,`Data`, `Event`) "
								+ "VALUES ('"+uuid+"', '" + json_data +"', '" + event_json + "') "
								+ "ON DUPLICATE KEY UPDATE `Data` = `"+json_data +"` , `Event` =  `"+event_json+"`;");
		this.worker.addQueue(save);
	}
	
	@Override
	public void clearPlayer(STPlayer player) {
		clearPlayer(player.getUUID());
		
	}

	@Override
	public void clearPlayer(UUID uuid) {
		DBAction clear = new DBAction("DELETE FROM `supertrailspro` WHERE Player =`"+uuid+"`;", r->{});
		this.worker.addQueue(clear);
	}
	
	
	class DatabaseWorker extends Thread{
		
		private Queue<DBAction> actions = new ArrayDeque<>();
		
		public DatabaseWorker() {
			this.start();
		}
		
		@Override
		public void run() {
			
			while (working) {
				DBAction action;
				synchronized (this.actions)	{action = this.actions.poll();}
				try {
				if (action!=null) db.execute(action);
				
				if (actions.peek()==null)this.wait();
				
				
				} catch (InterruptedException e) {
					log.error(e);
				} catch (SQLException e) {
					log.error(e);
					if (action!=null) {
						
						if (!action.retry(this)) log.error("SQL Error occured. Unable to retrieve user data from database."); 
					}
				}
			
			}
		}
		
		
		public void addQueue(DBAction action) {
			synchronized (this.actions) {
			this.actions.add(action);
			}
			this.notifyAll();
		}
		
	}

}
