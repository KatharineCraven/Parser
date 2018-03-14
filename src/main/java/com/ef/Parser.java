package com.ef;

import com.ef.manager.Manager;

public class Parser {

	/**
	 * Reads log file and returns ips which have made calls over the threshold.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Manager manager = new Manager();
		for (String ip : manager.getRelevantIps(args)) {
			System.out.println(ip);
		}
	}
}
