package me.kvq.supertrailspro;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import me.kvq.supertrailspro.exceptions.UnsupportedVersionException;
import me.kvq.supertrailspro.utils.STConsoleLogger;
import me.kvq.supertrailspro.utils.STLog;

public class SuperTrailsPro extends JavaPlugin{
	
	private static Plugin plugin;
	private ServerVersion versionmanager;
	private STLog logger;
	
	@Override
	public void onEnable() {
		
		try {
		plugin = this;
		logger = new STConsoleLogger(true, true, true, false);
		
		versionmanager = new ServerVersion();
		
		
		
		} catch (UnsupportedVersionException ex) {
			logger.error(ex);
		} catch (Exception ex) {
			logger.error(ex);
		}
	}
	
	@Override
	public void onDisable() {
		
	}

	public static Plugin getPlugin() {
		return plugin;
	}

	public ServerVersion getVersionmanager() {
		return versionmanager;
	}

	public STLog getLogManager() {
		return logger;
	}
	
}
