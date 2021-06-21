package me.kvq.supertrailspro.player;

import java.util.Optional;

import me.kvq.supertrailspro.exceptions.InvalidDataException;
import me.kvq.supertrailspro.trails.Trail;
import me.kvq.supertrailspro.trails.TrailType;

public class DataContainer {
	
	private Trail trail;
	//private TrailSettings settings; 
	//settings.set("value",) settings.get("value");
	private EventDataContainer event;
	
	
	public Optional<Trail> getTrail() {
		if (trail.getType() == TrailType.Empty) return Optional.empty();
		return Optional.ofNullable(trail);
	}
	
	public void setTrail(Trail trail) {
		this.trail = trail;
	}
	
	public EventDataContainer getEvent() {
		return event;
	}
	
	public void initializeData(String json) throws InvalidDataException{
		
	}
	
	public void initializeEventData(String json) throws InvalidDataException{
		
	}
	
	public boolean isEmpty() {
		return false;
		
	}

}
