package me.kvq.supertrailspro.database;

import java.util.UUID;

import me.kvq.supertrailspro.player.STPlayer;

public interface IDB {

	public void loadPlayer(UUID uuid);
	
	public void savePlayer(STPlayer player);
	
}
