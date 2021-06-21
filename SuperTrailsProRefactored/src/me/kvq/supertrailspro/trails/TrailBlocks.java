package me.kvq.supertrailspro.trails;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import me.kvq.supertrailspro.Version;
import me.kvq.supertrailspro.player.STPlayer;
import me.kvq.supertrailspro.utils.Counter;

public class TrailBlocks extends Trail{

	private Material m[];
	private byte[] b;
	private int type = 0; //0 = only solid blocks, 1 = everywhere
	public Counter tick = new Counter(m.length-1);
	
	private ItemStack is;
	private int slot;
	
	public TrailBlocks(int id,String s,Material m[],byte b[],int type,ItemStack is,int slot,Version v){
		super(id,s,v);
		this.m = m;
		this.b = b;
		this.type = type;
		this.is = is;
		this.slot = slot+1;
	}
	
	public TrailBlocks(int id,String s,Material m[],byte b[],int type,ItemStack is,int slot){
		super(id,s);
		this.m = m;
		this.b = b;
		this.type = type;
		this.is = is;
		this.slot = slot+1;
	}
	
	public Material[] getMaterials(){
		return m;
	}
	
	public byte[] getBytes(){
		return b;
	}
	
	public int getMode(){
		return type;
	}

	@Override
	public TrailType getType(){
		return TrailType.Block;
	}
	
	public int getBlocksType() {
		return type;
	}
	
	@Override
	public String getPermission() {
		return "trails.block." + this.name;
	}
	
	public ItemStack getItem() {
		return is;
	}
	
	public int getSlot() {
		return slot;
	}
	
	public void nextTick() {
		tick.add();
	}

	@Override
	public void spawn(STPlayer player) {
		// TODO Auto-generated method stub
		
	}
}
