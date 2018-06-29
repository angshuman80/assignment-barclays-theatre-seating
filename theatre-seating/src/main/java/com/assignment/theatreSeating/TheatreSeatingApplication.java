package com.assignment.theatreSeating;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.assignment.theatreSeating.bo.Row;
import com.assignment.theatreSeating.bo.Section;
import com.assignment.theatreSeating.exception.UnableToProcessRequestException;
import com.assignment.theatreSeating.impl.TheatreSeatingServiceImpl;

/**
 * This is the entry point of the application This class is responsible for user
 * interaction to perform various features of ticketing service Like 1) Get the
 * number of available seats 2) Block the seats and get the temporary ticket id
 * 3) Reserve the seats using ticket Id and emailId
 * 
 * @author A.Bhattacharya
 *
 */
public class TheatreSeatingApplication {

	private static final Logger logger = Logger.getLogger(TheatreSeatingApplication.class);

	public static void main(String[] args) {

		BasicConfigurator.configure();
		TheatreSeatingApplication application = new TheatreSeatingApplication();
		application.startUserInteraction();
	}

	/**
	 * This method is to initiate User Interaction
	 */
	private void startUserInteraction() {

		Scanner scanner = new Scanner(System.in);

		try {
			reservationProcess(scanner, setup());
		} finally {
			scanner.close();
		}

	}

	/**
	 * Setting up as per the sample input
	 * 
	 * @return
	 */
	private TheatreSeatingService setup() {
		List<Row> rows = new LinkedList<Row>();
		for (int i = 1; i <= 5; i++) {
			if (i == 1 || i == 5) {
				for (int j = 1; j <= 2; j++) {
					if (j == 1 || j == 2) {
						Section section = new Section(j, 6);
						Row row = new Row(i, section);
						rows.add(row);
					}
				}
			}
			if (i == 2) {
				for (int j = 1; j <= 4; j++) {
					if (j == 1 || j == 4) {
						Section section = new Section(j, 3);
						Row row = new Row(i, section);
						rows.add(row);
					}
					if (j == 2 || j == 3) {
						Section section = new Section(j, 5);
						Row row = new Row(i, section);
						rows.add(row);
					}
				}
			}
			if (i == 3) {
				for (int j = 1; j <= 4; j++) {
					if (j == 1 || j == 4) {
						Section section = new Section(j, 4);
						Row row = new Row(i, section);
						rows.add(row);
					}
					if (j == 2 || j == 3) {
						Section section = new Section(j, 6);
						Row row = new Row(i, section);
						rows.add(row);
					}
				}
			}
			if (i == 4) {
				for (int j = 1; j <= 4; j++) {
					if (j == 1 || j == 4) {
						Section section = new Section(j, 2);
						Row row = new Row(i, section);
						rows.add(row);
					}
					if (j == 2 || j == 3) {
						Section section = new Section(j, 8);
						Row row = new Row(i, section);
						rows.add(row);
					}
				}
			}
		}

		return new TheatreSeatingServiceImpl(rows);

	}

	/**
	 * Start user interactive reservation process
	 * @param scanner
	 * @param theatreSeatingService
	 */
	private void reservationProcess(Scanner scanner, TheatreSeatingService theatreSeatingService) {
		while (true) {
			System.out.println("Do you want to see number of available seats Y/N:");
			String availSeatResp = scanner.next();
			if (availSeatResp.equalsIgnoreCase("Y")) {
				logger.info("number of available seats are :" + theatreSeatingService.numSeatsAvailable());
			}

			System.out.println("Do you want Reserve seats Y/N:");

			String reserveResp = scanner.next();
			// Reserve Seats
			if (reserveResp.equalsIgnoreCase("Y")) {
				System.out.println("Provide No of seats:");
				int seatNum = scanner.nextInt();
				System.out.println("Provide user name:");
				String name = scanner.next();
				try {
					String result = theatreSeatingService.findAndReserveSeats(seatNum, name);
					logger.info(result);
				} catch (UnableToProcessRequestException e) {
					// TODO Auto-generated catch block
					logger.info(e.getMessage());
				}
			}

			System.out.println("Do you want Continue with Reservation process Y/N:");
			String continueResp = scanner.next();
			if (continueResp.equalsIgnoreCase("N")) {
				scanner.close();
				System.exit(0);
			}
		}

	}

}
