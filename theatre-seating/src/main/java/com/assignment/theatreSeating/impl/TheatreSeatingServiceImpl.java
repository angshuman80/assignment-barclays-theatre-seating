package com.assignment.theatreSeating.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;

import com.assignment.theatreSeating.TheatreSeatingService;
import com.assignment.theatreSeating.bo.Row;
import com.assignment.theatreSeating.bo.Section;
import com.assignment.theatreSeating.exception.UnableToProcessRequestException;
/**
 * Implementation class for Ticket Service
 * 
 * @author A.Bhattacharya
 *
 */
public class TheatreSeatingServiceImpl implements TheatreSeatingService {

	/*
	 * This map keeps tracks of all available seats.
	 */
	private Map<Integer, List<Section>> theatreMap = new HashMap<Integer, List<Section>>();

	// Keep track of available seats that are not on hold or reserved
	private AtomicInteger numSeatsAvailable = new AtomicInteger(0);

	private static final Logger logger = Logger.getLogger(TheatreSeatingServiceImpl.class);

	private int maxSeatCntInSection;
	private int totalNoOfRows;

	//Keep tracks of all the sections
	private Set<Integer> sectionSet = new HashSet<Integer>();
	

	/*
	 * Act as mutex for critical section .Only one thread can hold the seats
	 * other has to wait for the chance to get the lock
	 */

	private Lock lock = new ReentrantLock(true);

	/**
	 * Constructor to initialize the venue
	 * 
	 * @param rows
	 *            - No of rows in venue
	 * @param cols
	 *            - No of seats in a row
	 * @param seconds
	 *            - Time to hold the seats
	 */
	public TheatreSeatingServiceImpl(List<Row> rows) {
		if(rows!=null && !rows.isEmpty()){
			for (Row row : rows) {
				if(row!=null && row.getSection()!=null){
					initializeVenue(row);
				}
			}
		}
	}

	/**
	 * This method initializes the venue one by one
	 * for a Row
	 * @param row - Row containing sections
	 * 
	 */
	private void initializeVenue(Row row) {

		int seatCnt = row.getSection().getCount();

		if (theatreMap.containsKey(row.getRowId())) {
			List<Section> sections = theatreMap.get(row.getRowId());
			sections.add(row.getSection());
			theatreMap.put(row.getRowId(), sections);
		} else {
			List<Section> sections = new LinkedList<Section>();
			sections.add(row.getSection());
			theatreMap.put(row.getRowId(), sections);
			totalNoOfRows++;
		}

		sectionSet.add(seatCnt);

		if (maxSeatCntInSection < seatCnt) {
			maxSeatCntInSection = seatCnt;
		}

		numSeatsAvailable.addAndGet(seatCnt);
	}

	@Override
	public int numSeatsAvailable() {
		return numSeatsAvailable.get();
	}

	@Override
	public String findAndReserveSeats(int numSeats, String name) throws UnableToProcessRequestException {

		if (validateRequest(numSeats, name)) {
			lock.lock();
			String result = findBestSeats(numSeats, name);
			lock.unlock();

			return result;

		}
		return null;
	}

	private boolean validateRequest(int numSeats, String name) throws UnableToProcessRequestException {
		if (numSeats == 0) {
			logger.error("Number of seats requested cannot be 0");
			throw new UnableToProcessRequestException("Number of seats requested cannot be 0");
		}

		if (name == null || name.isEmpty()) {
			logger.error("Invalid name");
			throw new UnableToProcessRequestException("Invalid name");
		}

		return true;
	}

	/**
	 * This method returns the best available seat
	 * 
	 */
	private String findBestSeats(int numSeats, String name) {

		String result;
		if (numSeats > numSeatsAvailable.get()) {
			result = name + " Sorry, we can't handle your party.";
			return result;
		}

		if (numSeats > maxSeatCntInSection) {
			result = name + " Call to split party";
			return result;
		}

		for (int i = 1; i <= totalNoOfRows; i++) {
			List<Section> sections = theatreMap.get(i);
			for (Section section : sections) {
				int seatCnt = section.getCount();
				if (seatCnt != 0 && seatCnt >= numSeats && (isSection(seatCnt, numSeats))
						|| isAfit(seatCnt, numSeats)) {

					return name + returnSeats(section, numSeats, i);

				}
			}

		}

		/*
		 * Only if the above algorithm does not meet the requirement return what
		 * is available. Fallback
		 */
		for (int i = 1; i <= totalNoOfRows; i++) {
			List<Section> sections = theatreMap.get(i);
			for (Section section : sections) {
				int seatCnt = section.getCount();
				if (seatCnt != 0 && seatCnt >= numSeats) {
					
					return name + returnSeats(section, numSeats, i);

				}
			}

		}

		return null;
	}

	private String returnSeats(Section section,int numSeats,int rowId){
		
		section.setCount(section.getCount() - numSeats);
		numSeatsAvailable.set(numSeatsAvailable.get() - numSeats);

		return  " Row " + rowId + " Section " + section.getSectionId();
		
	}
	/**
	 * This method returns whether after substracting the 
	 * numSeats from seatCnt in a section it remains as a
	 * valid section in input seating arrangement
	 * @param seatCnt - No of seats left in a sction
	 * @param numSeats
	 * @return
	 */
	private boolean isSection(int seatCnt, int numSeats) {
		return sectionSet.contains(seatCnt - numSeats);
	}

	/**
	 * The number of seats requested is a fit for a section
	 * @param seatCnt
	 * @param numSeats
	 * @return
	 */
	private boolean isAfit(int seatCnt, int numSeats) {
		return (seatCnt - numSeats == 0);
	}
}
