package me.kvq.supertrailspro.packetlib.versionhooks.legacy;

import me.kvq.supertrailspro.packetlib.versionhooks.ParticleDataContainer;

public class LegacyColorPDC extends ParticleDataContainer {

	public LegacyColorPDC(int red, int green, int blue) {
		super((float) red / 255F, (float) green / 255F, (float) blue / 255F, 0, 1);
	}

	@Override
	public boolean containsCustomData() {
		return false;
	}

	@Override
	public Object getData() {
		return null;
	}

}
