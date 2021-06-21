package me.kvq.supertrailspro.exceptions;

public class ObjectNotFoundException extends Exception {
	

	private static final long serialVersionUID = 6235451881566766869L;

	public ObjectNotFoundException(String msg) {
		super(msg);
	}
	
	public ObjectNotFoundException(String objectname, String id) {
		super("Cannot find "+ objectname + " with id " + id);
	}

}
