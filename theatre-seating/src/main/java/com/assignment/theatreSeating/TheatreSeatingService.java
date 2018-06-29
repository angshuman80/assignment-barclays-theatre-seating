package com.assignment.theatreSeating;

import com.assignment.theatreSeating.exception.UnableToProcessRequestException;
/**
 * This interface exposes methods to check available seats
 * and find and reserve seats
 * @author A.Bhattacharya
 *
 */

public interface TheatreSeatingService {
	
	/**
	* The number of seats in the venue
	* @return the number of tickets available in the venue
	*/
	 int numSeatsAvailable();
	/**
	* Find and reserve best available seats for a customer
	*
	* @param numSeats the number of seats to find and reserve
	* @param name unique identifier for the customer
	* @return a String with name and section with row reserved
	*/
	String findAndReserveSeats(int numSeats, String name) throws UnableToProcessRequestException;
	
	

}
