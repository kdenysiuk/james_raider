package org.jamesraider.entities;

public class User {
	private final String phoneNumber;
	private final String phoneCountryCode;
	private final String smsVerificationCode;

	public User(String phoneNumber, String phoneCountryCode, String smsVerificationCode) {
		this.phoneNumber = phoneNumber;
		this.phoneCountryCode = phoneCountryCode;
		this.smsVerificationCode = smsVerificationCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getPhoneCountryCode() {
		return phoneCountryCode;
	}

	public String getSmsVerificationCode() {
		return smsVerificationCode;
	}
}
