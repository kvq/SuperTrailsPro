package me.kvq.supertrailspro.trails;

import java.util.HashMap;
import java.util.Optional;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import com.google.common.util.concurrent.SettableFuture;

import me.kvq.supertrailspro.ServerVersion;
import me.kvq.supertrailspro.SuperTrailsPro;
import me.kvq.supertrailspro.Version;
import me.kvq.supertrailspro.utils.Counter;
import me.kvq.supertrailspro.utils.STLog;
import me.kvq.supertrailspro.utils.STUtils;

public class TrailsManager {
	
	private HashMap<Integer, Trail> trails = new HashMap<>();
	private STLog log = SuperTrailsPro.getLogManager();	
	
	public TrailsManager() {
		this.initialize();
	}
	
	private void initialize () {
		initializeParticles();
		initializeBlocks();
		initializeRains();
	}
	
	private void registerTrail(Trail t) {
		trails.put(t.getId(), t);
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
	
	private void initializeWings() {
		
	}
	
	
}
