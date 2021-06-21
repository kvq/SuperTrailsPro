package me.kvq.supertrailspro.player;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.kvq.supertrailspro.SuperTrailsPro;
import me.kvq.supertrailspro.exceptions.InvalidDataException;
import me.kvq.supertrailspro.exceptions.ObjectNotFoundException;

public class GamePlayer extends STPlayer {

	public GamePlayer(Player p) {
		player = p; uuid = p.getUniqueId();
	}
	
	public GamePlayer(UUID uuid) throws ObjectNotFoundException {
		Player p = Bukkit.getPlayer(uuid);
		if (p == null) throw new ObjectNotFoundException("player", uuid.toString());
		player = p; this.uuid = uuid;
	}
	
	public void initializeData(String data_json, String event_json) {
		this.data = new DataContainer();
		
		try {	
			if (data_json!=null)this.data.initializeData(data_json);
			if (event_json!=null)this.data.initializeEventData(event_json);
		} catch (InvalidDataException e) {
			SuperTrailsPro.getLogManager().error(e);
		}
	}

	@Override
	public void save() {
		SuperTrailsPro.getPlayerManager().savePlayer(this);
	}

}
