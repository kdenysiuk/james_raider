package org.jamesraider.gui.mobile.android.login;

import static org.jamesraider.constants.NumberConstants.FIVE;

import org.jamesraider.components.CountriesDropBox;
import org.jamesraider.gui.mobile.AbstractPage;
import org.jamesraider.utils.DriverManager;
import org.openqa.selenium.By;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;

public class EnterYourPhoneNumberPage extends AbstractPage {

	private final By ENTER_YOU_PHONE_NUMBER_TITLE = AppiumBy
			.xpath("//*[@resource-id='com.hdw.james.rider:id/title' and @text='Enter your phone number']");
	private final By COUNTRY_CODE_DROP_BOX = AppiumBy.id("com.hdw.james.rider:id/spinner");
	private final By PHONE_NUMBER_INPUT = AppiumBy.id("com.hdw.james.rider:id/input");
	private final By CONTINUE_BUTTON = AppiumBy.id("com.hdw.james.rider:id/continueButton");

	public EnterYourPhoneNumberPage(AppiumDriver driver) {
		super(driver);
	}

	@Override
	public boolean isOpened(long timeout) {
		return isElementPresent(ENTER_YOU_PHONE_NUMBER_TITLE, timeout);
	}

	public CountriesDropBox openCountriesDropBox() {
		tapElement(COUNTRY_CODE_DROP_BOX, FIVE);
		return new CountriesDropBox(DriverManager.getDriver());
	}

	public EnterYourPhoneNumberPage setNumberPhone(String numberPhone) {
		findElement(PHONE_NUMBER_INPUT, FIVE).sendKeys(numberPhone);
		return new EnterYourPhoneNumberPage(DriverManager.getDriver());
	}

	public EnterTheSixDigitCodePage tapOnContinueButton() {
		tapElement(CONTINUE_BUTTON, FIVE);
		return new EnterTheSixDigitCodePage(DriverManager.getDriver());
	}
}
