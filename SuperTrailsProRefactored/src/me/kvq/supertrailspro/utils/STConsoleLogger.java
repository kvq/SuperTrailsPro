package me.kvq.supertrailspro.utils;

public class STConsoleLogger implements STLog{

	
	boolean console,info,error,debug;
	public STConsoleLogger(boolean console,boolean info,boolean error,boolean debug) {
		this.console = console; this.info = info; this.error = error; this.debug = debug;
	}
	
	@Override
	public void debug(String str) {
		debug(str,ConsoleColor.YELLOW);
		
	}

	@Override
	public void debug(String str, ConsoleColor color) {
		if (debug) sendConsoleMessage("[STDEBUG] " + str , color);
		
	}

	@Override
	public void error(String str) {
		error(str, ConsoleColor.RED);
		
	}
	
	@Override
	public void error(Exception e) {
		error(e.getMessage());
		e.printStackTrace();
		
	}

	@Override
	public void error(String str, ConsoleColor color) {
		if (error)  sendConsoleMessage("[SuperTrailsPro] ERROR ->" + str , color);
		
	}

	@Override
	public void info(String str) {
		info(str, ConsoleColor.WHITE);
		
	}

	@Override
	public void info(String str, ConsoleColor color) {
		if (info) sendConsoleMessage("[SuperTrailsPro] " + str , color);
	}

	private void sendConsoleMessage(String str) {
		System.out.println(str);
	}
	
	private void sendConsoleMessage(String str, ConsoleColor color) {
		sendConsoleMessage(color.getColorCode() + str);
	}

	@Override
	public void error(String str, Exception e) {
		error(str);
		error(e);
		
	}
	
}
