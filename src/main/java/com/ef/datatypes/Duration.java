package com.ef.datatypes;

public enum Duration {
	HOURLY("hourly"), DAILY("daily");

	private String value;

	private Duration(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return getValue();
	}
}
