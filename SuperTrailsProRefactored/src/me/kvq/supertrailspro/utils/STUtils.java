package me.kvq.supertrailspro.utils;

import java.lang.reflect.Method;
import java.util.Optional;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.kvq.supertrailspro.ServerVersion;
import me.kvq.supertrailspro.SuperTrailsPro;
import me.kvq.supertrailspro.Version;

public class STUtils {
	
	private static STLog log = SuperTrailsPro.getLogManager();
	public static final int MAX_DISTANCE = 25;

	public static ItemStack removeFlags(ItemStack is) {
		ItemMeta meta = is.getItemMeta();
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.addItemFlags(ItemFlag.values());
		is.setItemMeta(meta);
		return is;
	}
	
	
	@SuppressWarnings("deprecation")
	public static Material getDirrectMaterial(String name) {
		
		if (Character.isDigit(name.charAt(0))) return Material.getMaterial(Integer.parseInt(name));
		
		Material ma = Material.getMaterial(name);
		if (ma!=null) return ma;
		try {
			Class<?> clz = Class.forName("org.bukkit.Material");
			Method m = clz.getDeclaredMethod("getMaterial", String.class);
			Object o = m.invoke(null, name);
			return (Material)o;
		} catch (Exception e) {
			log.error(e);
		}
		return Material.STONE;
		
	}
	
	private static FileConfiguration config = SuperTrailsPro.getPlugin().getConfig();
	public static FileConfiguration getConfig() {
		return config;
	}
	
	public static String getConfigString(String key) {
		return config.getString(key);
	}
	
	public static int getConfigInt(String key) {
		return config.getInt(key);
	}
	
	public static double getConfigDouble(String key) {
		return config.getDouble(key);
	}
	
	@SuppressWarnings("deprecation")
	public static ItemStack readItemStack(String from){
		try {
		if (!Character.isDigit(from.charAt(0))) {
			return readNewItemStack(from);
		}
		String itemdata[] = from.split(":");
		int id = Integer.parseInt(itemdata[0]);
		byte data = 0; 
		if (itemdata.length > 1) data = Byte.parseByte(itemdata[1]);
		
		return new ItemStack(Material.getMaterial(id),1,data);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Some item id you've entered use incorrect format > " +from);
			return null;
		}
	}
	
	public static Optional<Version> readVersion(String version_string){
		Optional<Version> ov =  ServerVersion.findFromConfig(version_string);
		if (!ov.isPresent()) {
			log.error("Version value " + version_string + " is incorrect");
		}
		
		return ov;
	}
	
	public static ItemStack readNewItemStack(String s) {
		s = s.toLowerCase();
		String str[] = s.split(s.contains(",") ? "," :":");
		String name = str[0];
		if (s.startsWith("customhead=")) {
			return getCustomSkull(s.split("=")[1],new ItemStack(Material.SKULL_ITEM)).getItem();
		} 
		
		name = name.toUpperCase();
		byte b = 0; if (str.length>1) {b = Byte.parseByte(str[1]); }
		
		Material m = getDirrectMaterial(name);
		if (m == null) return null;
		return new ItemStack(m, 1, b);
		
	}
	
	public static CustomSkull getCustomSkull(String ID) {
		return new CustomSkull(ID);
	}
	
	public static CustomSkull getCustomSkull(String ID,ItemStack fallback) {
		return new CustomSkull(ID,fallback);
	}
	
}
