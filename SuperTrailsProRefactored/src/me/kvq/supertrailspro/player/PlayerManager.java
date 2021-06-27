package me.kvq.supertrailspro.player;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;

public class PlayerManager {
	
	private HashMap<Player,STPlayer> players = new HashMap<>();
	
	private static Storage storage;
	
	public PlayerManager() {
		
	}
	
	public void loadFromJSON(UUID uuid, String json) {
		
	}
	
	public static void loadFromJSON(UUID uuid, String json, String event_json) {
		System.out.println(uuid + " " + json);
	}
	
	public static void preparePlayer(UUID uuid) {
		storage.loadPlayer(uuid);
	}
	
	public static void savePlayer(STPlayer stplayer) {
		storage.savePlayer(stplayer);
	}


}
