package me.kvq.supertrailspro.player;

import java.util.Optional;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.json.simple.parser.ParseException;

import me.kvq.supertrailspro.SuperTrailsPro;
import me.kvq.supertrailspro.exceptions.InvalidDataException;
import me.kvq.supertrailspro.trails.Trail;
import me.kvq.supertrailspro.trails.TrailType;
import me.kvq.supertrailspro.trails.TrailsManager;
import me.kvq.supertrailspro.utils.STJson;

public class DataContainer {
	
	private Trail trail;
	//private TrailSettings settings; 
	//settings.set("value",) settings.get("value");
	private EventDataContainer event;
	private TrailsManager manager = SuperTrailsPro.getTrailsManager();
	private int mode = 0;
	private int lang = 0;
	
	private int[] wingsColors = new int[] {0,0,0};
	private String pattern;	
	private ItemStack rain;
	
	

	public Optional<Trail> getTrail() {
		if (trail.getType() == TrailType.Empty) return Optional.empty();
		return Optional.ofNullable(trail);
	}
	
	public void setTrail(Trail trail) {
		this.trail = trail;
	}
	
	public void setTrail(int id) {
		manager.getFromID(id).ifPresent(trail -> setTrail(trail));
	}
	
	public EventDataContainer getEvent() {
		return event;
	}
	
	public void initializeData(String json) throws InvalidDataException{
		
		try {
			STJson j = new STJson(json);
			
			j.readInt("id", id -> setTrail(id))
			.readInt("mode", mode -> setMode(mode))
			.readInt("w1", w1 -> setWingsColor(0, w1))
			.readInt("w2", w2 -> setWingsColor(1, w2))
			.readInt("w3", w3 -> setWingsColor(2, w3))
			.readString("pattern", pattern -> setPattern(pattern));
			
			rain = itemFromJson(j.getSubJson("rain"));
//			Collectors.tomap
			
		} catch (ParseException e) {
			throw new InvalidDataException(json);
		}
		
		
//		JSONObject obj = new JSONObject();
//		obj.put("id", trail);
//		obj.put("mode", mode);
//		obj.put("w1",W1);
//		obj.put("w2",W2);
//		obj.put("w3",W3);
//		obj.put("pattern",pattern);
//		
//		if (rain != null) {
//		JSONObject rainobj = new JSONObject();
//		int typeid = STUtils.idFix(rain.getTypeId());
//		byte data = rain.getData().getData();
//		rainobj.put("itemID", typeid);
//		rainobj.put("dataID", data);
//		obj.put("rain", rainobj);
		
	}
	
	public void initializeEventData(String json) throws InvalidDataException{
		
	}
	
	private ItemStack itemFromJson(STJson subjson) {
		if (subjson == null) return null;
		
		Material m = Material.getMaterial(subjson.readInt("itemID"));
		byte data = (byte) subjson.readInt("dataID");
		
		return new ItemStack(m,data);
	}
	
	public boolean isEmpty() {
		return false;
	}
	
	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	public int getLang() {
		return lang;
	}

	public void setLang(int lang) {
		this.lang = lang;
	}

	public int[] getWingsColors() {
		return wingsColors;
	}
	
	public void setWingsColor(int number, int color) {
		if (number < 3) {
			wingsColors[number] = color;
		}
	}

	public void setWingsColors(int[] wingsColors) {
		this.wingsColors = wingsColors;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public ItemStack getRain() {
		return rain;
	}

	public void setRain(ItemStack rain) {
		this.rain = rain;
	}

}
