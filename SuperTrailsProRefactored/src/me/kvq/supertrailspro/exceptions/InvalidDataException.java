package me.kvq.supertrailspro.exceptions;

public class InvalidDataException extends Exception{

	private static final long serialVersionUID = -7187935669873398511L;

	public InvalidDataException(String raw) {
		super("An error has occured while reading data. Raw data -> " +  raw);
	}

}
