package com.assignment.theatreSeating;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.assignment.theatreSeating.exception.UnableToProcessRequestException;
import com.assignment.theatreSeating.impl.TheatreSeatingServiceImpl;

/**
 * Test Class for ticket Service
 * 
 * @author A.Bhattacharya
 *
 */
public class TheatreSeatingApplicationTests {

	private TheatreSeatingService seatingService;

	private  int totalSeats;

	public final String NAME = "Smith";

	@Before
	public void setUp() {
		try {
			seatingService = new TheatreSeatingServiceImpl(getLayout());
		} catch (Exception ignore) {
			
		}
	}

	@Test
	public void testNumberofAvailableSeats() {
		Assert.assertEquals(totalSeats, seatingService.numSeatsAvailable());
	}

	@Test
	public void testreservation() {
		try {
			String result = seatingService.findAndReserveSeats(2,NAME);
			Assert.assertNotNull(result);
			Assert.assertEquals(result,NAME+" Row 1 Section 1");
			Assert.assertEquals(totalSeats - 2, seatingService.numSeatsAvailable());
		} catch (UnableToProcessRequestException ignore) {
			// TODO Auto-generated catch block
		}
	}

	@Test
	public void testHoldSeatsMoreThanAvailable() {
		try {
			String result = seatingService.findAndReserveSeats(totalSeats+1, NAME);
			Assert.assertNotNull(result);
			Assert.assertEquals(result,NAME+" Sorry, we can't handle your party.");
		} catch (UnableToProcessRequestException ignore) {
			// TODO Auto-generated catch block
		}
	}

	@Test
	public void testReservationInvalidNoOfSeatsThrowsUPRE() {
		try {
			seatingService.findAndReserveSeats(0, NAME);
		} catch (UnableToProcessRequestException e) {
			// TODO Auto-generated catch block
			Assert.assertEquals(e.getMessage(), "Number of seats requested cannot be 0");
		}
	}

	@Test
	public void testHoldSeatsWithInvalidNameThrowsUPRE() {
		try {
			seatingService.findAndReserveSeats(2, null);
		} catch (UnableToProcessRequestException e) {
			// TODO Auto-generated catch block
			Assert.assertEquals(e.getMessage(), "Invalid name");
		}
	}

	@Test
	public void testReservationSlpit() {
		try {
			String result = seatingService.findAndReserveSeats(12, "Smith");
			Assert.assertNotNull(result);
			Assert.assertEquals(result,NAME + " Call to split party");
		} catch (UnableToProcessRequestException ignore) {
			// TODO Auto-generated catch block
		}
	}



	private String getLayout() {
		String layout = "6 6" + System.lineSeparator() + "3 5 5 3" + System.lineSeparator() + "4 6 6 4"
				+ System.lineSeparator() + "2 8 8 2" + System.lineSeparator() + "6 6";
		return layout;
	}
}
