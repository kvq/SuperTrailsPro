package me.kvq.supertrailspro.packetlib;

public interface ParticleData {
	
	public boolean containsCustomData();
	public Object getData();
	
	public float getOffsetX();
	public float getOffsetY();
	public float getOffsetZ();
	
	public float getSpeed();
	
	public int getAmount();
	
	

}
