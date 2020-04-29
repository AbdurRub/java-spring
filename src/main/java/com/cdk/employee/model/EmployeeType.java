package com.cdk.employee.model;

public enum EmployeeType {
	REGULAR("R"), PART_TIME("P"), NONE("");
	private String value;

	private EmployeeType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static EmployeeType getEmployeeTypeValue(String code) {
		if (code == null) {
			return EmployeeType.NONE;
		}
		switch (code) {
		case "R":
			return EmployeeType.REGULAR;
		case "P":
			return EmployeeType.PART_TIME;
		default:
			return EmployeeType.NONE;
		}
	}

	public static String getEmployeeTypeCode(String value) {
		if (value == null) {
			return "";
		}
		switch (value) {
		case "REGULAR":
			return "R";
		case "PART_TIME":
			return "P";
		default:
			return "";
		}
	}
}
