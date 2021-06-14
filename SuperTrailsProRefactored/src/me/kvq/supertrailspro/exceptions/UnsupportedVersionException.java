package me.kvq.supertrailspro.exceptions;

public class UnsupportedVersionException extends Exception {

	private static final long serialVersionUID = 7064599645510009733L;
	private String versionKey;
	
	public UnsupportedVersionException(String versionKey) {
		super("Current game server version ("+versionKey.replaceAll("_", ".")+") not supported by your version of this plugin."); 
		this.versionKey = versionKey;
	}

	public String getVersionKey() {
		return this.versionKey;
	}
	
}
