package me.kvq.supertrailspro.utils;

import java.util.Base64;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.authlib.properties.PropertyMap;

import me.kvq.supertrailspro.SuperTrailsPro;
import me.kvq.supertrailspro.packetlib.ReflectionUtils;

public class CustomSkull {
	
	private ItemStack item;
	//"http://textures.minecraft.net/texture/"
	public CustomSkull(String headID) {
		try {
			item = createSkull("http://textures.minecraft.net/texture/" + headID);
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			SuperTrailsPro.getLogManager().error("Unable to generate custom head" , e);
			item = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		}
	}
	
	public CustomSkull(String headID, ItemStack fallbackItem) {
		try {
			item = createSkull("http://textures.minecraft.net/texture/" + headID);
		}  catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			SuperTrailsPro.getLogManager().error("Unable to generate custom head" , e);
			item = fallbackItem;
		}
	
	}
	
	private ItemStack createSkull(String url) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		 ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		 ItemMeta meta = head.getItemMeta();
		 ReflectionUtils.setValue(meta, "profile", generateProfile(url));
		 head.setItemMeta(meta); return head;
		 
	}
	
	private GameProfile generateProfile(String url) {
		GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        PropertyMap propertyMap = profile.getProperties();
        byte[] base64 = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
        propertyMap.put("textures", new Property("textures", new String(base64))); 
        return profile;
	}
	
	public ItemStack getItem() {
		return item;
	}

}
