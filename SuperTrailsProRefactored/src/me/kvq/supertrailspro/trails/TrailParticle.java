package me.kvq.supertrailspro.trails;

import org.bukkit.inventory.ItemStack;

import me.kvq.supertrailspro.Version;
import me.kvq.supertrailspro.packetlib.ParticleEffect;
import me.kvq.supertrailspro.player.STPlayer;
import me.kvq.supertrailspro.utils.STUtils;

public class TrailParticle extends Trail {

	protected ParticleEffect pef;
	
	private float speed, offsetX,offsetY,offsetZ;
	private double Y;
	private int amount; 

	public TrailParticle(int id,String n,ParticleEffect p,ItemStack i,Version v){
		super(id,n,v,i,0);
		prepare(p, i);
	}
	
	public TrailParticle(int id,String n,ParticleEffect p,ItemStack i){
		super(id,n,i,0);
		prepare(p, i);
	}
	
	private void prepare(ParticleEffect effect, ItemStack item) {
		this.pef = effect;
		if (name == null) return;
		this.slot = getConfigInt("Slot");
		this.speed = (float) getConfigDouble("Speed");
		this.Y = getConfigDouble("Y");
		
		this.offsetX = (float) getConfigDouble("OffsetX");
		this.offsetY = (float) getConfigDouble("OffsetY");
		this.offsetZ = (float) getConfigDouble("OffsetZ");
	
		this.amount = getConfigInt("Amount");
	}
	
	public ParticleEffect getEff(){
		return pef;
	}
	
	
	@Override
	public TrailType getType(){
		return TrailType.Particle;
	}
	
	@Override
	public String getPermission() {
		return "trails.particle." + this.name;
	}
	
	private double getConfigDouble(String value) {
		return STUtils.getConfigDouble("ParticleTrails." + getName() + "." + value);
	}
	
	private int getConfigInt(String value) {
		return STUtils.getConfigInt("ParticleTrails." + getName() + "." + value);
	}
	
	public ParticleEffect getPef() {
		return pef;
	}

	public float getSpeed() {
		return speed;
	}

	public float getOffsetX() {
		return offsetX;
	}

	public float getOffsetY() {
		return offsetY;
	}

	public float getOffsetZ() {
		return offsetZ;
	}

	public double getY() {
		return Y;
	}

	public int getAmount() {
		return amount;
	}

	@Override
	public void spawn(STPlayer player) {
		// TODO Auto-generated method stub
		
	}
}
