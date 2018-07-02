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
		try {
			application.startUserInteraction();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This method is to initiate User Interaction
	 * 
	 * @throws Exception
	 */
	private void startUserInteraction() throws Exception {

		Scanner scanner = new Scanner(System.in);

		try {
			String layout = "6 6" + System.lineSeparator() + "3 5 5 3" + System.lineSeparator() + "4 6 6 4"
					+ System.lineSeparator() + "2 8 8 2" + System.lineSeparator() + "6 6";
			TheatreSeatingService seatingService = new TheatreSeatingServiceImpl(layout);
			reservationProcess(scanner, seatingService);
		} finally {
			scanner.close();
		}

	}

	/**
	 * Start user interactive reservation process
	 * 
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
