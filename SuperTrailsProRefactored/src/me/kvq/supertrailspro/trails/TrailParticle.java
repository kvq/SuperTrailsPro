package me.kvq.supertrailspro.trails;

import org.bukkit.inventory.ItemStack;

import me.kvq.supertrailspro.Version;
import me.kvq.supertrailspro.packetlib.ParticleEffect;
import me.kvq.supertrailspro.player.STPlayer;
import me.kvq.supertrailspro.utils.STUtils;

public class TrailParticle extends Trail {

	protected ParticleEffect pef;
	protected ItemStack i;

	protected int slot;
	
	public TrailParticle(int id,String n,ParticleEffect p,ItemStack i,Version v){
		super(id,n,v);
		prepare(p, i);
	}
	
	public TrailParticle(int id,String n,ParticleEffect p,ItemStack i){
		super(id,n);
		prepare(p, i);
	}
	
	private void prepare(ParticleEffect effect, ItemStack item) {
		this.pef = effect;
		this.i = item;
		if (name == null) return;
		slot = STUtils.getConfigInt("ParticleTrails." + getName() + ".Slot");
	}
	
	
	public ItemStack getIS(){
		return i;
	}
		
	public ParticleEffect getEff(){
		return pef;
	}
	
	public int getSlot() {
		return slot;
	}
	
	@Override
	public TrailType getType(){
		return TrailType.Particle;
	}
	
	@Override
	public String getPermission() {
		return "trails.particle." + this.name;
	}

	@Override
	public void spawn(STPlayer player) {
		// TODO Auto-generated method stub
		
	}
}
