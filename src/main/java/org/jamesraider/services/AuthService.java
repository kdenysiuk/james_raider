package org.jamesraider.services;

import static org.jamesraider.constants.NumberConstants.FIVE;

import org.jamesraider.constants.Countries;
import org.jamesraider.entities.User;
import org.jamesraider.gui.mobile.android.login.EnterTheSixDigitCodePage;
import org.jamesraider.gui.mobile.android.login.EnterYourPhoneNumberPage;
import org.jamesraider.gui.mobile.android.login.PermissionRequestPage;
import org.jamesraider.gui.mobile.android.login.WelcomePage;
import org.jamesraider.gui.mobile.android.rides.RidesPage;
import org.jamesraider.utils.DriverManager;

import io.appium.java_client.android.AndroidDriver;

public class AuthService {
	private final AndroidDriver driver;

	public AuthService() {
		driver = DriverManager.getDriver();
	}

	public void login(User user) {
		WelcomePage welcomePage = new WelcomePage(driver);
		EnterYourPhoneNumberPage enterYourPhoneNumberPage = welcomePage.clickGetStartedButton();
		Countries country = enterYourPhoneNumberPage.getCountryByCountryCode(user.getPhoneCountryCode());
		enterYourPhoneNumberPage = enterYourPhoneNumberPage.selectCountry(country)
				.setNumberPhone(user.getPhoneNumber());
		EnterTheSixDigitCodePage enterTheSixDigitCodePage = enterYourPhoneNumberPage.tapOnContinueButton();
		enterTheSixDigitCodePage = enterTheSixDigitCodePage.typeDigitCode(user.getSmsVerificationCode());
		PermissionRequestPage permissionRequestPage = enterTheSixDigitCodePage.tapOnContinueButton();
		permissionRequestPage = permissionRequestPage.allowAllPermissions();
		permissionRequestPage.tapOnContinueButton();
	}

	public boolean isUserLoggedIn() {
		RidesPage ridesPage = new RidesPage(DriverManager.getDriver());
		return ridesPage.isOpened(FIVE);
	}

}
