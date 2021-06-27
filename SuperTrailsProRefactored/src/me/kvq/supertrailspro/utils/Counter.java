package me.kvq.supertrailspro.utils;

public class Counter {
	
	private int value, min_value,max_value;

	public Counter(int value,int min,int max) {
		this.value = value; this.min_value = min;
		this.max_value = max;
	}
	
	public Counter() {
		this(0, 0, Integer.MAX_VALUE);
	}
	
	public Counter(int value) {
		this(value,value,Integer.MAX_VALUE);
	}
	
	public Counter(int min, int max) {
		this(min,min,max);
	}
	
	public void add(int i) {//15 + 1
		value+=i; // 16
		if (value>max_value) value= value - max_value + min_value - 1;
 		// 16-15+10=11
	}
	
	public void add() {
		add(1);
	}
	
	public int get() {
		return value;
	}
	
	public int getAndAdd() {
		int current = value; add();
		return current;
	}
}
