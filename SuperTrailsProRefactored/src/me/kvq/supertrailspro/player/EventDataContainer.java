package me.kvq.supertrailspro.player;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.json.simple.parser.ParseException;

import me.kvq.supertrailspro.SuperTrailsPro;
import me.kvq.supertrailspro.utils.STJson;

public class EventDataContainer {
	
	private int[] unlocked = new int[] {};
	private int boxes = 0;
	private int tempBoxes = 0;
	private int craftableMaterials = 0;
	private STPlayer player;
	
	
	
	public EventDataContainer(STPlayer player) {
		this.player = player;
	}

	public EventDataContainer(STPlayer player, String json) {
		this(player);
		fromJson(json);
	}
	
	public boolean isUnlocked(int id) {
		for (int i : unlocked) {
			if (i == 490 ||i == id) return true;
		}
		return false;
	}
	
	public int[] getUnlocked() {
		return unlocked;
	}
	
	public void unlockAll() {
		unlocked = new int[] {490};
	}
	
	public int getBoxes() {
		return boxes;
	}
	
	public int getTempBoxes() {
		return tempBoxes;
	}
	
	public int getTotalBoxes() {
		return tempBoxes+boxes;
	}
	
	public void addBox() {
		addBoxes(1);
	}
	
	public void addBoxes(int amount) {
		boxes+=amount;
	}
	
	private void setBoxes(int boxes) {
		this.boxes = boxes;
	}
	
	private void setTempBoxes(int tempBoxes) {
		this.tempBoxes = tempBoxes;
	}
	
	private void setUnlocked(int[] ints) {
		unlocked = ints;
	}
	
	public String toJson() {
		
		STJson json = new STJson();
		List<Integer> ints = Arrays.stream(getUnlocked()).boxed().collect(Collectors.toList());
		json.setList("u", ints);
		json.setInt("c", boxes);
		json.setInt("p", craftableMaterials);
			
			
		return json.toString();
	}
	
	private String getPlayerName() {
		if (player == null) return "Unknown";
		return player.getPlayerName();
	}
	
	public void fromJson(String stringJson) {
		
		try {
			STJson json = new STJson(stringJson);
			json.getList("u", arr -> {arr.stream().mapToInt(i->(int)i).toArray();});
			json.readInt("c", c -> { setBoxes(c); });
			json.readInt("p", p -> { setTempBoxes(p); });
		} catch (ParseException e) {
			SuperTrailsPro.getLogManager().error("Unable to read event data of player " + getPlayerName(),e);
		}
		
		
	}
}
