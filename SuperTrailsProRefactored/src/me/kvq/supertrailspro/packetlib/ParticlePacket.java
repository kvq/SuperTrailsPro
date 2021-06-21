package me.kvq.supertrailspro.packetlib;

import org.bukkit.Location;

public interface ParticlePacket extends Packet {

	public ParticleEffect getEffect();
	
	public Location getLocation();
	
	public ParticleData getData();
	
}
