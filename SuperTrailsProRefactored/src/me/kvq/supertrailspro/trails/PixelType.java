package me.kvq.supertrailspro.trails;

public enum PixelType {

	Red((byte)1),
	Green((byte)2),
	Blue((byte)3),
	Flame((byte)4),
	Witch((byte)5),
	Drip((byte)6),
	Crit((byte)7),
	
	None((byte)0);
	
	byte b;
	private PixelType(byte b) {
		this.b = b;
	}
	
	public byte getByte() {
		return b;
	}
	
}
