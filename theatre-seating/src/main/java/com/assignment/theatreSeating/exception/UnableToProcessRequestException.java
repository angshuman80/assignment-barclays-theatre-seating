package com.assignment.theatreSeating.exception;

/**
 * This is a custom Exception class
 * The Exception will be raised when we cannot reserve seats
 * due to business reasons
 * @author A.Bhattacharya
 *
 */
public class UnableToProcessRequestException extends Exception {
	
	public UnableToProcessRequestException(String message){
		super(message);
	}
	
	public UnableToProcessRequestException(){
		super();
	}

}
