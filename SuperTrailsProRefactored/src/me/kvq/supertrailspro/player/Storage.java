package me.kvq.supertrailspro.player;

import java.util.UUID;

public interface Storage {
	
	public void loadPlayer(UUID uuid);
	
	public void savePlayer(STPlayer player);
	
	public void clearPlayer(STPlayer player);
	public void clearPlayer(UUID uuid);

}
