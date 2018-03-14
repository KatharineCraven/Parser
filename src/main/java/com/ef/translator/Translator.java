package com.ef.translator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ef.datatypes.Duration;

public class Translator {

	String formatStandard;
	String formatLog;

	public Translator() {
		formatStandard = "yyyy-MM-dd.HH:mm:ss";
		formatLog = "yyyy-MM-dd HH:mm:ss.SSS";
	}

	public String getParamValue(String fullParam) {
		return fullParam.substring(fullParam.indexOf("=") + 1).trim();
	}

	public Date getStartDate(String unparsedDate) throws ParseException {
		// gets the date after the equals sign in the parameter

		String sDate = getParamValue(unparsedDate);

		SimpleDateFormat sdf = new SimpleDateFormat(formatStandard);
		Date startDate = sdf.parse(sDate);

		return startDate;
	}

	public Duration getDuration(String unparsedDuration) {
		String duration = getParamValue(unparsedDuration);
		return Duration.valueOf(duration.toUpperCase());
	}

	public int getThreshold(String unparsedThreshold) {
		String threshold = getParamValue(unparsedThreshold);
		return Integer.parseInt(threshold);
	}

	public Date getDate(String sDate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(formatLog);
		Date date = null;
		date = sdf.parse(sDate);

		return date;

	}
}
