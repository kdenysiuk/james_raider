package org.jamesraider.constants;

public enum Countries {
	AFGHANISTAN("Afghanistan", "+93"),
	UNITED_STATES("United States", "+1"),
	ZIMBABWE("Zimbabwe", "+263");
	// Add all available countries

	private final String countryName;
	private final String countryCode;

	private Countries(String countryName, String countryCode) {
		this.countryName = countryName;
		this.countryCode = countryCode;
	}

	public String getCountryName() {
		return this.countryName;
	}

	public String getCountryCode() {
		return this.countryCode;
	}
}
