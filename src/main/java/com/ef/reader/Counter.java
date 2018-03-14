package com.ef.reader;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.ef.datatypes.Duration;
import com.ef.datatypes.Record;

public class Counter {

	public Counter() {

	}

	/**
	 * Gets records that go over the threshold in the specified amount of time.
	 * 
	 * @param allRecords
	 * @param startDate
	 * @param duration
	 * @param threshold
	 * @return
	 */
	public ArrayList<String> getRelevantRecords(ArrayList<Record> allRecords, Date startDate, Duration duration,
			int threshold) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		Date endDate = startDate;

		switch (duration) {
		case HOURLY:
			cal.add(Calendar.HOUR, 1);
			endDate = cal.getTime();
			break;
		case DAILY:
			cal.add(Calendar.DATE, 1);
			endDate = cal.getTime();
			break;
		default:
			endDate = null;
			System.out.println("duration not valid");
		}

		ArrayList<Record> recordsInDuration = recordsInDateRange(allRecords, startDate, endDate);

		return getRecordsOverThreshold(recordsInDuration, threshold);
	}

	private ArrayList<Record> recordsInDateRange(ArrayList<Record> allRecords, Date startDate, Date endDate) {
		List<Record> recordsInDate = allRecords.stream()
				.filter(record -> (startDate.before(record.getDate()) && endDate.after(record.getDate())))
				.collect(Collectors.toList());
		return new ArrayList<Record>(recordsInDate);
	}

	/**
	 * Gets list of ips which are over the threshold.
	 * 
	 * @param records
	 * @param threshold
	 * @return
	 */
	private ArrayList<String> getRecordsOverThreshold(ArrayList<Record> records, int threshold) {
		ArrayList<String> recordsOverThreshold = new ArrayList<String>();

		// get unique ips
		List<String> ipList = records.stream().map(ip -> ip.getIp()).distinct().collect(Collectors.toList());

		for (String ip : ipList) {

			if (getCallsByIp(records, ip) > threshold) {
				recordsOverThreshold.add(ip);
			}

		}
		return recordsOverThreshold;
	}

	/**
	 * Gets the number of calls made by the IP.
	 * 
	 * @param remainingRecords
	 * @param ip
	 * @return
	 */
	private int getCallsByIp(ArrayList<Record> remainingRecords, String ip) {
		return remainingRecords.stream().filter(record -> record.getIp().equals(ip)).collect(Collectors.toList())
				.size();
	}

}
