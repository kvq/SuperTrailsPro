package me.kvq.supertrailspro.trails;

import org.bukkit.inventory.ItemStack;

import me.kvq.supertrailspro.player.STPlayer;
import me.kvq.supertrailspro.trails.fx.TrailFX;

public class TrailWings extends Trail {

	private byte buffer[][];
	private boolean hasColorRed,hasColorBlue,hasColorGreen;
	private TrailFX fx[];
	
	public TrailWings(int id, String n,ItemStack item,TrailFX fx[],int slot, byte buffer[][], boolean movement) {
		super(id, n,item,slot); this.fx = fx;
		checkColors();
	}
	
	private void checkColors() {
		for (byte[] subb: buffer) {
			for (byte b: subb) {
				if (b == 1) hasColorRed = true;
				if (b == 2) hasColorGreen = true;
				if (b == 3) hasColorBlue = true;
			}
		}
	}

	public byte[][] getBuffer() {
		return buffer;
	}

	public boolean hasColorRed() {
		return hasColorRed;
	}

	public boolean hasColorBlue() {
		return hasColorBlue;
	}

	public boolean hasColorGreen() {
		return hasColorGreen;
	}

	@Override
	public void spawn(STPlayer player) {
		// TODO Auto-generated method stub
		
	}

}
