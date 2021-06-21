package me.kvq.supertrailspro.packetlib.versionhooks.legacy;

import java.awt.Color;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.kvq.supertrailspro.SuperTrailsPro;
import me.kvq.supertrailspro.packetlib.FakeItemPacket;
import me.kvq.supertrailspro.packetlib.Packet;
import me.kvq.supertrailspro.packetlib.PacketManager;
import me.kvq.supertrailspro.packetlib.ParticleData;
import me.kvq.supertrailspro.packetlib.ParticleEffect;
import me.kvq.supertrailspro.packetlib.ParticlePacket;
import me.kvq.supertrailspro.packetlib.ReflectionUtils;
import me.kvq.supertrailspro.packetlib.ReflectionUtils.PackageType;
import me.kvq.supertrailspro.packetlib.versionhooks.ParticlePacketContainer;
import me.kvq.supertrailspro.utils.STLog;
import me.kvq.supertrailspro.utils.STUtils;

public class LegacyPM implements PacketManager {

	private Class<?> packetClass; 
	private Constructor<?> packetConstructor;
	private Class<?> enumparticle;
	private STLog log = SuperTrailsPro.getLogManager();
	
	private Method getHandle;
	private Field playerConnection;
	private Method sendPacket;
	
	public LegacyPM() {
		try {
		packetClass = PackageType.MINECRAFT_SERVER.getClass("PacketPlayOutWorldParticles");
		packetConstructor = ReflectionUtils.getConstructor(packetClass);
		enumparticle = PackageType.MINECRAFT_SERVER.getClass("EnumParticle");
		
		getHandle = ReflectionUtils.getMethod("CraftPlayer", PackageType.CRAFTBUKKIT_ENTITY, "getHandle");
		playerConnection = ReflectionUtils.getField("EntityPlayer", PackageType.MINECRAFT_SERVER, false, "playerConnection");
		sendPacket = ReflectionUtils.getMethod(playerConnection.getType(), "sendPacket", PackageType.MINECRAFT_SERVER.getClass("Packet"));
		
		} catch (Exception e) {
			log.error(e);
		}
	}
	
	@Override
	public ParticlePacket createPacket(ParticleEffect effect, Location location, ParticleData data) {
		try {
			Object packet = packetConstructor.newInstance();
			ReflectionUtils.setValue(packet, "a", enumparticle.getEnumConstants()[effect.getID()]);
			ReflectionUtils.setValue(packet, true, "j", true);
			if (data!=null && data.containsCustomData()) {
				ReflectionUtils.setValue(packet, true, "k", (String) data.getData());
			}
			
			ReflectionUtils.setValue(packet, true, "b", (float) location.getX());
			ReflectionUtils.setValue(packet, true, "c", (float) location.getY());
			ReflectionUtils.setValue(packet, true, "d", (float) location.getZ());
			ReflectionUtils.setValue(packet, true, "e", data.getOffsetX());
			ReflectionUtils.setValue(packet, true, "f", data.getOffsetY());
			ReflectionUtils.setValue(packet, true, "g", data.getOffsetZ());
			ReflectionUtils.setValue(packet, true, "h", data.getSpeed());
			ReflectionUtils.setValue(packet, true, "i", data.getAmount());

			return new ParticlePacketContainer(effect, location, packet, data);
		} catch (Exception e) {
			log.error(e);
		}
		
		return null;
	}
	
	@Override
	public ParticlePacket createPacket(ParticleEffect effect, Location location) {
		return createPacket(effect, location, null);
	}

	

	@Override
	public FakeItemPacket createPacket(ItemStack is) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FakeItemPacket createPacket(Material m) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sendToPlayer(Packet packet, Player p) {
		try {
			sendPacket.invoke(playerConnection.get(getHandle.invoke(p)), packet.getPacket());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void sendToNearbyPlayers(Packet packet, Location l) {
		Bukkit.getOnlinePlayers().stream()
				.filter(t -> (t.getWorld() == l.getWorld() && t.getLocation().distance(l) < STUtils.MAX_DISTANCE))
				.forEach(t -> sendToPlayer(packet, t));

	}

	@Override
	public ParticleData createItemData(ItemStack item) {
		return new LegacyItemPDC(item);
	}

	@Override
	public ParticleData createItemData(Material m) {
		return new LegacyItemPDC(m);
	}

	@Override
	public ParticleData createColorData(Color c) {
		return createColorData(c.getRed(),c.getBlue(),c.getGreen());
	}

	@Override
	public ParticleData createColorData(int red, int green, int blue) {
		return new LegacyColorPDC(red, green, blue);
	}

}
