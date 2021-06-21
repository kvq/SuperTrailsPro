package me.kvq.supertrailspro.packetlib;

import java.awt.Color;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface PacketManager {
	
	
	public ParticlePacket createPacket(ParticleEffect effect, Location location);
	
	public ParticlePacket createPacket(ParticleEffect effect, Location location,ParticleData data);

	public FakeItemPacket createPacket(ItemStack is);
	
	public FakeItemPacket createPacket(Material m);
	
	public ParticleData createItemData(ItemStack item);
	
	public ParticleData createItemData(Material m);
	
	public ParticleData createColorData(Color c);
	
	public ParticleData createColorData(int red,int green,int blue);
	
	public void sendToPlayer(Packet packet,Player p);
	
	public void sendToNearbyPlayers(Packet packet, Location l);
	

}
