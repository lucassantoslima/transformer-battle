package com.aequilibrium.enuns;

public enum TeamType {

	AUTOBOT(Values.AUTOBOT), DECEPTICON(Values.DECEPTICON);

	private String description;

	TeamType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public static class Values {
		public static final String AUTOBOT = "AUTOBOT";
		public static final String DECEPTICON = "DECEPTICON";
	}

}
