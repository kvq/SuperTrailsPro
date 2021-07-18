package me.kvq.supertrailspro.player;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.kvq.supertrailspro.SuperTrailsPro;
import me.kvq.supertrailspro.exceptions.InvalidDataException;
import me.kvq.supertrailspro.exceptions.ObjectNotFoundException;

public class GamePlayer extends STPlayer {

	public GamePlayer(Player p) {
		super(p);
		player = p; uuid = p.getUniqueId();	
	}
	
	public GamePlayer(UUID uuid) throws ObjectNotFoundException {
		super(null,uuid,null);
		this.player = Bukkit.getPlayer(uuid);
		if (this.player == null) throw new ObjectNotFoundException("player", uuid.toString());
	}
	
	public void initializeData(String data_json, String event_json) {
		this.data = new DataContainer(this);
		
		try {	
			if (data_json!=null)this.data.fromJson(data_json);
			if (event_json!=null)this.data.eventFromJson(event_json);
		} catch (InvalidDataException e) {
			SuperTrailsPro.getLogManager().error(e);
		}
	}

	@Override
	public void save() {
		SuperTrailsPro.getPlayerManager().savePlayer(this);
	}

}
