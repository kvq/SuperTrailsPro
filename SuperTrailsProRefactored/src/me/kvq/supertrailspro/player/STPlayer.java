package me.kvq.supertrailspro.player;

import org.bukkit.Location;

public interface STPlayer {
	
	public void sendMessage();
	
	public Location getLocation();
	
	public boolean hasPermission(String s);

}
