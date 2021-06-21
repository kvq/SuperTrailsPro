package me.kvq.supertrailspro.packetlib.versionhooks.legacy;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import me.kvq.supertrailspro.packetlib.versionhooks.ParticleDataContainer;

public class LegacyItemPDC extends ParticleDataContainer {

	private String data;
	
	@SuppressWarnings("deprecation")
	public LegacyItemPDC(ItemStack is) {
		super(0, 0, 0,  0.1F, 3);
		
		data = "_" + is.getTypeId() + "_" + is.getData().getData();
	}
	
	public LegacyItemPDC(Material is) {
		super(0, 0, 0,  0.1F, 3);
		
		data = "_" + is.getId() + "_" + "0";
	}

	@Override
	public boolean containsCustomData() {
		return data!=null;
	}

	@Override
	public Object getData() {
		return data;
	}

}
