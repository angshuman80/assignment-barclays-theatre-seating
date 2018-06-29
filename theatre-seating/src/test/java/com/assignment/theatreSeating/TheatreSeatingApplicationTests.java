package com.assignment.theatreSeating;

import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.assignment.theatreSeating.bo.Row;
import com.assignment.theatreSeating.bo.Section;
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
		seatingService = initializeVenue();
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



	private TheatreSeatingService initializeVenue() {
		List<Row> rows = new LinkedList<Row>();
		for (int i = 1; i <= 5; i++) {
			if (i == 1 || i == 5) {
				for (int j = 1; j <= 2; j++) {
					if (j == 1 || j == 2) {
						Section section = new Section(j, 6);
						Row row = new Row(i, section);
						rows.add(row);
						totalSeats=totalSeats+6;
					}
				}
			}
			if (i == 2) {
				for (int j = 1; j <= 4; j++) {
					if (j == 1 || j == 4) {
						Section section = new Section(j, 3);
						Row row = new Row(i, section);
						rows.add(row);
						totalSeats=totalSeats+3;
					}
					if (j == 2 || j == 3) {
						Section section = new Section(j, 5);
						Row row = new Row(i, section);
						rows.add(row);
						totalSeats=totalSeats+5;
					}
				}
			}
			if (i == 3) {
				for (int j = 1; j <= 4; j++) {
					if (j == 1 || j == 4) {
						Section section = new Section(j, 4);
						Row row = new Row(i, section);
						rows.add(row);
						totalSeats=totalSeats+4;
					}
					if (j == 2 || j == 3) {
						Section section = new Section(j, 6);
						Row row = new Row(i, section);
						rows.add(row);
						totalSeats=totalSeats+6;
					}
				}
			}
			if (i == 4) {
				for (int j = 1; j <= 4; j++) {
					if (j == 1 || j == 4) {
						Section section = new Section(j, 2);
						Row row = new Row(i, section);
						rows.add(row);
						totalSeats=totalSeats+2;
					}
					if (j == 2 || j == 3) {
						Section section = new Section(j, 8);
						Row row = new Row(i, section);
						rows.add(row);
						totalSeats=totalSeats+8;
					}
				}
			}
		}

		return new TheatreSeatingServiceImpl(rows);
	}
}
