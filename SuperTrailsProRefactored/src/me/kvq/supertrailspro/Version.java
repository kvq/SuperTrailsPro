package me.kvq.supertrailspro;

public enum Version {

	MC8(0,"v1_8","S18"),MC9(1,"v1_9","S19"),MC10(2,"v1_10","S110"),
	MC1(3,"v1_11","S111"),MC12(4,"v1_12","S112"),MC13(5,"v1_13","S113"),
	MC14(6,"v1_14","S114"),MC15(7,"v1_15","S115"),MC16(8,"v1_16","S116"),
	MC17(8,"v1_17","S117"), MC18(9,"v1_18","S118"), MC(10,"v1_19","S119");
	
	private int id = -1;
	private String key, oldkey;
	
	Version(int id,String key,String config){
		this.id = id;
		this.key = key; this.oldkey = config;
	}
	
	public int getVersionID(){
		return this.id;
	}
	
	public String getKey() {
		return this.key;
	}
	
	public String getOldValue() {
		return oldkey;
	}
	
	public boolean newerOrEqual(Version v) {
		return this.id >= v.getVersionID();
	}
	
	public boolean newerThan(Version v) {
		return this.id > v.getVersionID();
	}
	
	public boolean olderThan(Version v) {
		return this.id < v.getVersionID();
	}
	
	
	
}
