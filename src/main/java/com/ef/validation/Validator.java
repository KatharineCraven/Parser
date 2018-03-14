package com.ef.validation;

public class Validator {

	public Validator() {

	}

	/**
	 * Validates command line arguments.
	 * 
	 * @param args
	 * @return
	 * @throws Exception
	 */
	public boolean validateArgs(String args[]) throws Exception {

		if (args.length == 4) {
			for (int i = 0; i < 4; i++) {
				if (!validateIndividualParam(args[i])) {
					throw new Exception(
							"Invalid argument format. Expected: --accesslog= --startDate= --duration= --threshold=");
				}
			}
			return vaildateParamsWithFile(args[0], args[1], args[2], args[3]);
		} else {
			throw new Exception("Invalid number of arguments. startDate, duration, and threshold required.");
		}

	}

	/**
	 * Validates the number, name, and order of command line arguments, including
	 * accesslog.
	 * 
	 * @param accesslog
	 * @param startDate
	 * @param duration
	 * @param threshold
	 * @return
	 * @throws Exception
	 */
	private boolean vaildateParamsWithFile(String accesslog, String startDate, String duration, String threshold)
			throws Exception {
		if (!startDate.trim().substring(2, startDate.indexOf("=")).equals("startDate")) {
			throw new Exception("Missing expected accesslog parameter.");
		}

		return validateParamNames(startDate, duration, threshold);
	}

	/**
	 * Validates the number, name, and order of command line arguments without
	 * accesslog.
	 * 
	 * @param startDate
	 * @param duration
	 * @param threshold
	 * @return
	 * @throws Exception
	 */
	private boolean validateParamNames(String startDate, String duration, String threshold) throws Exception {
		if (!startDate.trim().substring(2, startDate.indexOf("=")).equals("startDate")) {
			throw new Exception("Missing expected startDate parameter.");
		}

		if (!duration.trim().substring(2, duration.indexOf("=")).equals("duration")) {
			throw new Exception("Missing expected duration parameter.");
		}

		if (!threshold.trim().substring(2, threshold.indexOf("=")).equals("threshold")) {
			throw new Exception("Missing expected threshold parameter.");
		}

		return true;
	}

	/**
	 * Validates the formatting of the command line arguments.
	 * 
	 * @param s
	 * @return
	 * @throws Exception
	 */
	private boolean validateIndividualParam(String s) throws Exception {
		s = s.trim();

		if (validateDashes(s) && validateEquals(s)) {
			return true;

		}
		return false;
	}

	/**
	 * Validates that there are dashes before every parameter.
	 * 
	 * @param s
	 * @return
	 */
	private boolean validateDashes(String s) {
		if (s.substring(0, 2).equals("--")) {
			return true;
		}

		return false;
	}

	/**
	 * Validates that there is an equals sign and a value after it.
	 * 
	 * @param s
	 * @return
	 */
	private boolean validateEquals(String s) {
		if (s.contains("=")) {
			if (s.indexOf("=") != s.length()) {
				return true;
			}
		}
		return false;
	}
}
