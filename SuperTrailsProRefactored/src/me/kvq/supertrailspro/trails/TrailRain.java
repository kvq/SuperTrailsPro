package me.kvq.supertrailspro.trails;

import me.kvq.supertrailspro.player.STPlayer;

public class TrailRain extends Trail{
	
	public TrailRain(){
		super(201,null,null);
	}
	
	@Override
	public TrailType getType(){
		return TrailType.Rain;
	}
	
	@Override
	public String getPermission() {
		return "trails.rains";
	}
	
	@Override
	public void spawn(STPlayer player) {
		// TODO Auto-generated method stub
		
	}
}
