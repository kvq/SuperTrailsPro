package me.kvq.supertrailspro.packetlib;

import org.bukkit.inventory.ItemStack;

public interface FakeItemPacket extends Packet {
	
	public ItemStack getItem();
	
	public long getSpawnTime();
	
	public int getID();

}
