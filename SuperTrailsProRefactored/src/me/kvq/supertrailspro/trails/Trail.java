package me.kvq.supertrailspro.trails;

import me.kvq.supertrailspro.ServerVersion;
import me.kvq.supertrailspro.Version;
import me.kvq.supertrailspro.player.STPlayer;

public abstract class Trail {

	protected int id;
	protected String name;
	protected Version v;
	protected boolean supported = false;
	
	public Trail(int id,String n,Version v){
		this.id = id;
		name = n;
		this.v = v;
		supported = ServerVersion.atleast(v);
	}
	
	public Trail(int id,String n){
		this.id = id;
		name = n;
		supported = true;
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
	
	abstract public void spawn(STPlayer player);
}
