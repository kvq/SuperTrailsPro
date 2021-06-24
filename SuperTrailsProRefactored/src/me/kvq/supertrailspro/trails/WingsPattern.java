package me.kvq.supertrailspro.trails;

import java.awt.image.BufferedImage;

import org.bukkit.inventory.ItemStack;

public class WingsPattern implements PotencialGuiItem {

	private String name;
	private BufferedImage image;
	
	private int slot;
	private ItemStack item;
	

	public WingsPattern(String name,BufferedImage img,int slot, ItemStack item) {
		this.name = name; this.image = img; this.slot = slot;
		this.item = item;
	}

	public String getName() {
		return name;
	}

	public BufferedImage getImage() {
		return image;
	}
	
	public int getSlot() {
		return slot;
	}

	public ItemStack getItem() {
		return item;
	}
	
}
