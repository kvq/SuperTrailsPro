package me.kvq.supertrailspro;

public enum Version {

	MC8(0,"v1_8"),MC9(1,"v1_9"),MC10(2,"v1_10"),
	MC1(3,"v1_11"),MC12(4,"v1_12"),MC13(5,"v1_13"),
	MC14(6,"v1_14"),MC15(7,"v1_15"),MC16(8,"v1_16"),
	MC17(8,"v1_17"), MC18(9,"v1_18"), MC(10,"v1_19");
	
	private int id = -1;
	private String key = "";
	
	Version(int id,String key){
		this.id = id;
		this.key = key;
	}
	
	public int getVersionID(){
		return this.id;
	}
	
	public String getKey() {
		return this.key;
	}
	
}
