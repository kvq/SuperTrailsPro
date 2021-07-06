package me.kvq.supertrailspro.trails;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import me.kvq.supertrailspro.ServerVersion;
import me.kvq.supertrailspro.SuperTrailsPro;
import me.kvq.supertrailspro.Version;
import me.kvq.supertrailspro.trails.fx.TrailFX;
import me.kvq.supertrailspro.utils.Counter;
import me.kvq.supertrailspro.utils.STLog;
import me.kvq.supertrailspro.utils.STUtils;

public class TrailsManager {
	
	private HashMap<Integer, Trail> trails = new HashMap<>();
	private HashMap<String, WingsPattern> patterns = new HashMap<>();
	private HashMap<String, TrailFX> trailfxs = new HashMap<>();
	
	private STLog log = SuperTrailsPro.getLogManager();	
	
	public TrailsManager() {
		this.initialize();
	}
	
	private void initialize() {
		initializeParticles();
		initializeBlocks();
		initializeRains();
		initializeFXs();
		initializeWings();
		initializePatterns();
	}
	
	private void registerTrail(Trail t) {
		trails.put(t.getID(), t);
	}
	
	public void registerPattern(String name, WingsPattern pattern) {
		patterns.put(name, pattern);
	}
	
	public Optional<Trail> getFromID(int id) {
		return Optional.ofNullable(trails.get(id));
	}
	
	private void initializeParticles() {
		for (ParticleTrailsEnum enums : ParticleTrailsEnum.values()) {
			registerTrail(new TrailParticle(enums.getId(), enums.getName(), enums.getEffect(), enums.getItem(), enums.getVersion()));
		}	
	}
	
	private void initializeBlocks() {
		ConfigurationSection fc = STUtils.getConfig().getConfigurationSection("BlockTrails"); 
		if (fc == null) return;
		Counter id = new Counter(100);
		for (String key : fc.getKeys(false)){
			int type = fc.getInt(key + ".Type");
			Optional<Version> ov = STUtils.readVersion(fc.getString(key + ".Version"));
			if (!ov.isPresent() || !ServerVersion.atleast(ov.get())) continue;
			Version v = ov.get(); 
			
			String setString[] = fc.getString(key + ".Set").split(",");
			Material m[] = new Material[setString.length/2];
			byte b[] = new byte[setString.length/2];
			
			ItemStack item = STUtils.readItemStack(fc.getString(key + ".Item"));
			int slot = fc.getInt(key + ".Slot");
			
			
			for (int place = 0; place < setString.length; place++) {
				int num = place/2;
				
				if (place % 2 == 0) {
					m[num] = STUtils.getDirrectMaterial(setString[place]);
				} else {
					b[num] = (byte) Byte.parseByte(setString[place]);
				}
			}
			
			
			registerTrail(new TrailBlocks(id.getAndAdd(), key, m, b, type, item, slot,v));
		}	
		
	}
	
	private void initializeRains() {
		registerTrail(new TrailRain());
	}
	
	private void initializeFXs() {
		
	}
	
	private void initializeWings() {
		Counter counter = new Counter(300);
		File folder = new File(SuperTrailsPro.getPlugin().getDataFolder() + "/wings/models");
		folder.mkdirs();
		ConfigurationSection selection = STUtils.getConfig().getConfigurationSection("Wings");
		
		for (String key : selection.getKeys(false)){
			
			String path = selection.getString(key+".File");File file = new File(folder,path);
			if (!file.exists() || !file.isFile()) continue;
			byte[][] buffer;
			try {
			buffer = WingsParser.parseImage(file);
			} catch (IOException exe) {
				log.error("Unable to load wings: " + key,exe);
				continue;
			}
			
			int slot = selection.getInt(key + ".Slot");
			String stringitem = selection.getString(key + ".Item");
			ItemStack item = STUtils.readItemStack(stringitem);
			boolean movement = selection.getBoolean(key + ".Movement");

			String stringfxs = selection.getString(key + ".PostFX");
			TrailFX[] fxs = parseFX(stringfxs);
						
			registerTrail(new TrailWings(counter.getAndAdd(), key, item, fxs, slot, buffer, movement));
			
		}
	}
	
	private void initializePatterns() {
		File folder = new File(SuperTrailsPro.getPlugin().getDataFolder() + "/wings/patterns");
		folder.mkdirs();
		ConfigurationSection selection = STUtils.getConfig().getConfigurationSection("WingsPatterns");
		
		for (String key : selection.getKeys(false)){
			String path = selection.getString(key+".File");File file = new File(folder,path);
			if (!file.exists() || !file.isFile()) continue;
			BufferedImage img = null;
			try {
				img = WingsParser.loadPattern(file);
			} catch (IOException e) {
				log.error("Unable to load pattern: " + key,e);
			}
			
			int slot = selection.getInt(key + ".Slot");
			String stringit = selection.getString(key + ".Item");
			ItemStack it = STUtils.readItemStack(stringit);
			String fxs = selection.getString(key + ".PostFX");
			
			WingsPattern pattern = new WingsPattern(key, img, slot, it);
			registerPattern(key, pattern);
			
		}
	}
	
	
	private TrailFX[] parseFX(String fxs) {
		if (fxs == null) return new TrailFX[0];
		fxs = fxs.replaceAll(" ", "");
		if (fxs == "") return new TrailFX[0];
		
		List<TrailFX> fxlist = new ArrayList<>();
		
		for (String fxname : fxs.split(",")) {
			Optional<TrailFX> fx = findFX(fxname);
			fx.ifPresent(f -> fxlist.add(f));
		}
		
		return fxlist.toArray(new TrailFX[fxlist.size()]);
	}
	
	private Optional<TrailFX> findFX(String name) {
		if (name == null) return Optional.empty();
		return Optional.ofNullable(trailfxs.get(name));
	}
	
	
}
