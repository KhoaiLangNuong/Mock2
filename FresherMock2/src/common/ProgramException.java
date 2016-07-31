package common;

/**
 * ProgramException.java
 *
 * Version 1.0
 *
 * Date: 21/07/2016
 *
 * Copyright 
 *
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 21/07/2016       	NguyetNT6        Create
 */

public class ProgramException extends Exception {
	private static final long serialVersionUID = 1L;

	public ProgramException() {
	}

	public ProgramException(String message) {
		super(message);
	}
}
