package me.kvq.supertrailspro;

import me.kvq.supertrailspro.exceptions.ObjectNotLoadedException;
import me.kvq.supertrailspro.exceptions.UnsupportedVersionException;

public class ServerVersion {

	private static Version current;
	
	public ServerVersion() throws UnsupportedVersionException {
		detectVersion();
	}
	
	public static void detectVersion() throws UnsupportedVersionException {
		for (Version v : Version.values()) {
			if (v.getKey().contains(getVersionFromPackage())) {
				current = v;
				return;
			}
		}
		throw new UnsupportedVersionException(getVersionFromPackage());
	}
	
	public static Version getCurrentVersion() {
		return current;
	}
	
	public static String getVersionFromPackage() {
	    final String packageName = SuperTrailsPro.getPlugin().getServer().getClass().getPackage().getName();
	    return packageName.substring(packageName.lastIndexOf('.') + 1);
	}
	
}
