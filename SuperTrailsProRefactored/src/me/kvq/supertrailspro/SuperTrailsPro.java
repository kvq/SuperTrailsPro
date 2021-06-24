package me.kvq.supertrailspro;

import org.bukkit.plugin.java.JavaPlugin;

import me.kvq.supertrailspro.commands.SuperTrailsCmd;
import me.kvq.supertrailspro.exceptions.UnsupportedVersionException;
import me.kvq.supertrailspro.player.PlayerManager;
import me.kvq.supertrailspro.trails.TrailsManager;
import me.kvq.supertrailspro.utils.STConsoleLogger;
import me.kvq.supertrailspro.utils.STLog;

public class SuperTrailsPro extends JavaPlugin{
	
	private static SuperTrailsPro plugin;
	private ServerVersion versionmanager;
	private static STLog logger;
	private PlayerManager playerManager;
	private static TrailsManager trailsManager;
	
	public static void main(String[] args) { return;}
	
	@Override
	public void onEnable() {
		
		try {
		plugin = this;
		logger = new STConsoleLogger(true, true, true, false);
		
		versionmanager = new ServerVersion();
		
		
		registerCommands(); registerEvents();
		
		trailsManager = new TrailsManager();
		playerManager = new PlayerManager();
		
		setHeadless(true);
		
		} catch (UnsupportedVersionException ex) {
			logger.error(ex);
		} catch (Exception ex) {
			logger.error(ex);
		}
	}
	
	@Override
	public void onDisable() {
		
	}

	public static SuperTrailsPro getPlugin() {
		return plugin;
	}

	public ServerVersion getVersionmanager() {
		return versionmanager;
	}

	public static STLog getLogManager() {
		return logger;
	}
	
	public void registerCommands() {
		this.getCommand("supertrails").setExecutor(new SuperTrailsCmd());
	}
	
	public void registerEvents() {
//		EventManager;
	}
	
	public static PlayerManager getPlayerManager() {
		return SuperTrailsPro.getPlugin().playerManager;
	}
	
	private void setHeadless(boolean v) {
		System.setProperty("java.awt.headless", v ? "true" : "false");
	}
	
	public static TrailsManager getTrailsManager() {
		return trailsManager;
	}
	
}
