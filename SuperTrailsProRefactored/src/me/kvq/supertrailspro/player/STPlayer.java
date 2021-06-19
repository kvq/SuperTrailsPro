package me.kvq.supertrailspro.player;

import java.util.UUID;

import org.bukkit.Location;

public interface STPlayer {
	
	public void sendMessage();
	
	public Location getLocation();
	
	public boolean hasPermission(String s);
	
	public UUID getUUID();
	
	public String getJSONData();
	
	public String getJSONEventData();
	
	public void save();

}
