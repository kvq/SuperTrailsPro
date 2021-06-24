package me.kvq.supertrailspro.utils;

public interface STLog {

	public void debug(String str);
	public void debug(String str,ConsoleColor color);
	
	public void error(String str);
	public void error(String str,ConsoleColor color);
	public void error(Exception e);
	public void error(String str, Exception e);
	
	public void info(String str);
	public void info(String str, ConsoleColor color);
	
}
