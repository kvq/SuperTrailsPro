package me.kvq.supertrailspro.player;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.kvq.supertrailspro.exceptions.ObjectNotFoundException;

public class NotLoadedPlayer extends STPlayer {

	public NotLoadedPlayer(Player p) {
		player = p; uuid = p.getUniqueId();
	}
	
	public NotLoadedPlayer(UUID uuid) throws ObjectNotFoundException {
		Player p = Bukkit.getPlayer(uuid);
		if (p == null) throw new ObjectNotFoundException("player", uuid.toString());
		player = p; this.uuid = uuid;
	}

	@Override
	public void save() {
		return;
	}

}
