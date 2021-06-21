package me.kvq.supertrailspro.packetlib.versionhooks;

import org.bukkit.Location;

import me.kvq.supertrailspro.packetlib.ParticleData;
import me.kvq.supertrailspro.packetlib.ParticleEffect;
import me.kvq.supertrailspro.packetlib.ParticlePacket;

public class ParticlePacketContainer implements ParticlePacket{

	private ParticleEffect effect;
	private Location l;
	private Object packet;
	private ParticleData data;
	
	public ParticlePacketContainer(ParticleEffect effect, Location location, Object packet, ParticleData data) {
		this.effect = effect;
		this.l = location;
		this.packet = packet;
		this.data = data;
	}
	
	
	@Override
	public ParticleEffect getEffect() {
		return effect;
	}

	@Override
	public Location getLocation() {
		return l;
	}

	@Override
	public Object getPacket() {
		return packet;
	}


	@Override
	public ParticleData getData() {
		return data;
	}
	
}
