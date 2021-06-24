package me.kvq.supertrailspro.trails;

import org.bukkit.inventory.ItemStack;

import me.kvq.supertrailspro.ServerVersion;
import me.kvq.supertrailspro.Version;
import me.kvq.supertrailspro.annotations.ConfigValue;
import me.kvq.supertrailspro.player.STPlayer;

public abstract class Trail implements PotencialGuiItem {

	protected int id;
	protected String name;
	protected Version v;
	protected boolean supported = false;
	protected ItemStack item;
	protected int slot;
	
	public Trail(int id,String n,Version v,ItemStack item,int slot){
		this(id, n, item,slot);
		
		supported = ServerVersion.atleast(v); 
	}

	public Trail(int id,String n,ItemStack item,int slot){
		this.id = id;
		name = n;
		supported = true;
		this.item = item;
		this.slot = slot;
	}
	
	public boolean isSupported(){
		return supported;
	}
	
	public String getName(){
		return name;
	}
	
	public Version getVersion(){
		return v;
	}
	
	public int getId(){
		return id;
	}
	
	public TrailType getType(){
		return TrailType.Empty;
	}
	
	public String getPermission() {
		return null;
	}
	
	public ItemStack getItem() {
		return item;
	}
	
	public int getSlot() {
		return slot;
	}

	abstract public void spawn(STPlayer player);
}
