package me.kvq.supertrailspro.player;

import java.util.Optional;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import me.kvq.supertrailspro.trails.Trail;

public abstract class STPlayer {
	
	protected Player player;
	protected UUID uuid;
	protected DataContainer data;
	protected String name;
	
	public STPlayer(Player player) {
		this(player, player.getUniqueId(), player.getName());
	}
	
	public STPlayer(Player player, UUID uuid, String name) {
		this.player = player; this.uuid = uuid; this.name = name;
		setEmptyContainers();
	}
	
	private void setEmptyContainers() {
		data = new DataContainer(this);
	}

	public void sendMessage(String msg) {
		if (!playerExists()) return;
		player.sendMessage(msg);
	}

	public Optional<Location> getLocation() {
		if (!playerExists()) return Optional.empty();
		return Optional.of(player.getLocation()); 
	}

	public boolean hasPermission(String s) {
		if (!playerExists()) return false;
		return player.hasPermission(s);
	}

	public UUID getUUID() {
		return uuid;
	}

	public String getJSONData() {
		return data.toJson();
	}

	public String getJSONEventData() {
		return data.getEvent().toJson();
	}
	
	private boolean playerExists() {
		return player != null && player.isOnline();
	}

	public Optional<Trail> getTrail() {
		if (!playerExists()) return Optional.empty();
		return data.getTrail();
	}
	
	public boolean isEmpty() {
		 if (data==null) return true;
		 return data.isEmpty();
	}
	
	public String getPlayerName() {
		return name == null ? "Unknown" : name;
	}
	
	abstract public void save();

}
