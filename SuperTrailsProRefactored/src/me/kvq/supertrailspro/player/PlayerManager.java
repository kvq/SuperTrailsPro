package me.kvq.supertrailspro.player;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;

public class PlayerManager {
	
	private HashMap<Player,STPlayer> players = new HashMap<>();
	
	private Storage storage;
	
	public PlayerManager() {
		
	}
	
	public void loadFromJSON(UUID uuid, String json) {
		
	}
	
	public void loadFromJSON(UUID uuid, String json, String event_json) {
		
	}
	
	public void preparePlayer(UUID uuid) {
		storage.loadPlayer(uuid);
	}
	
	public void savePlayer(STPlayer stplayer) {
		storage.savePlayer(stplayer);
	}


}
