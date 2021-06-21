package me.kvq.supertrailspro;

import java.util.Optional;
import java.util.stream.Stream;

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
	
	public static boolean atleast(Version v) {
		return current.newerOrEqual(v);
	}
	
	public static Version getOlder() {
		return Version.values()[0];
	}
	
	public static Version getNewest() {
		return Version.values()[Version.values().length-1];
	}
	
	public static String getVersionFromPackage() {
	    String packageName = SuperTrailsPro.getPlugin().getServer().getClass().getPackage().getName();
	    return packageName.substring(packageName.lastIndexOf('.') + 1);
	}
	
	public static Optional<Version> findFromConfig(String version) {
		return Stream.of(Version.values()).filter(vers -> vers.getOldValue().equalsIgnoreCase(version) ).findFirst();
	}
	
}
