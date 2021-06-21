package me.kvq.supertrailspro.packetlib.versionhooks;

import me.kvq.supertrailspro.packetlib.ParticleData;

public abstract class ParticleDataContainer implements ParticleData {

	float offsetX,offsetY,offsetZ,speed; int amount;
	
	public ParticleDataContainer(float offsetX,float offsetY,float offsetZ,float speed,int amount) {
		this.offsetX = offsetX; this.offsetY = offsetY; this.offsetZ = offsetZ;
		this.speed = speed;
		this.amount = amount;
	}
	
	@Override
	public float getOffsetX() {
		return offsetX;
	}

	@Override
	public float getOffsetY() {
		return offsetY;
	}

	@Override
	public float getOffsetZ() {
		return offsetZ;
	}

	@Override
	public float getSpeed() {
		return speed;
	}

	@Override
	public int getAmount() {
		return amount;
	}

}
