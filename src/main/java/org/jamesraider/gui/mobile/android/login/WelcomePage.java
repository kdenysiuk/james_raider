package org.jamesraider.gui.mobile.android.login;

import static org.jamesraider.constants.NumberConstants.FIVE;

import org.jamesraider.gui.mobile.AbstractPage;
import org.jamesraider.utils.DriverManager;
import org.openqa.selenium.By;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;

public class WelcomePage extends AbstractPage {

	private final By GET_STARTED_BUTTON = AppiumBy.id("com.hdw.james.rider:id/getStartedButton");

	public WelcomePage(AppiumDriver driver) {
		super(driver);
	}

	@Override
	public boolean isOpened(long timeout) {
		return isElementPresent(GET_STARTED_BUTTON, timeout);
	}

	public EnterYourPhoneNumberPage clickGetStartedButton() {
		tapElement(GET_STARTED_BUTTON, FIVE);
		return new EnterYourPhoneNumberPage(DriverManager.getDriver());
	}
}
