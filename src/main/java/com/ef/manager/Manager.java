package com.ef.manager;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import com.ef.datatypes.Duration;
import com.ef.datatypes.Record;
import com.ef.reader.Counter;
import com.ef.reader.Reader;
import com.ef.translator.Translator;
import com.ef.validation.Validator;

public class Manager {

	private Validator validator;
	private Translator translator;
	private Reader reader;
	private Counter counter;

	public Manager() {
		validator = new Validator();
		translator = new Translator();
		reader = new Reader();
		counter = new Counter();
	}

	/**
	 * After validating parameters, reads file and then gets IPs over threshold.
	 * 
	 * @param args
	 * @return
	 */
	public ArrayList<String> getRelevantIps(String args[]) {

		try {
			if (validator.validateArgs(args)) {
				String accessLog = translator.getParamValue(args[0].trim());
				Date startdate = translator.getStartDate(args[1].trim());
				Duration duration = translator.getDuration(args[2].trim());
				int threshold = translator.getThreshold(args[3].trim());

				ArrayList<Record> allRecords = reader.read(accessLog);
				return counter.getRelevantRecords(allRecords, startdate, duration, threshold);
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
