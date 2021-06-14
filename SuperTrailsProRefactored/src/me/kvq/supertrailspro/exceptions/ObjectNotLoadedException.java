package me.kvq.supertrailspro.exceptions;

public class ObjectNotLoadedException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3542535367452730513L;
	
	public ObjectNotLoadedException() {
		super("Object wasn't loaded yet.");
	}

}
