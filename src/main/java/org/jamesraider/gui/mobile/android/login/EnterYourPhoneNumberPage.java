package org.jamesraider.gui.mobile.android.login;

import static org.jamesraider.constants.NumberConstants.FIFTY;
import static org.jamesraider.constants.NumberConstants.FIVE;
import static org.jamesraider.constants.NumberConstants.QUICK_SWIPE;

import org.jamesraider.constants.Countries;
import org.jamesraider.constants.SwipeDirections;
import org.jamesraider.gui.mobile.AbstractPage;
import org.jamesraider.utils.DriverManager;
import org.openqa.selenium.By;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;

public class EnterYourPhoneNumberPage extends AbstractPage {

	private final By ENTER_YOU_PHONE_NUMBER_TITLE = AppiumBy
			.xpath("//*[@resource-id='com.hdw.james.rider:id/title' and @text='Enter your phone number']");
	private final By COUNTRY_CODE_DROP_BOX = AppiumBy.id("com.hdw.james.rider:id/spinner");
	private final By COUNTRY_LIST_CONTAINER = AppiumBy.className("android.widget.ListView");
	private final By PHONE_NUMBER_INPUT = AppiumBy.id("com.hdw.james.rider:id/input");
	private final By CONTINUE_BUTTON = AppiumBy.id("com.hdw.james.rider:id/continueButton");

	public EnterYourPhoneNumberPage(AppiumDriver driver) {
		super(driver);
	}

	@Override
	public boolean isOpened(long timeout) {
		return isElementPresent(ENTER_YOU_PHONE_NUMBER_TITLE, timeout);
	}

	public EnterYourPhoneNumberPage selectCountry(Countries country) {
		tapElement(COUNTRY_CODE_DROP_BOX, FIVE);
		By countryItem = AppiumBy
				.xpath(String.format("//*[@resource-id='com.hdw.james.rider:id/text' and contains(@text,\"%s\")]",
						country.getCountryName()));
		LOGGER.info("Swiping to {}", country.getCountryName());
		if (!swipeToElement(countryItem, COUNTRY_LIST_CONTAINER, SwipeDirections.DOWN, FIFTY, QUICK_SWIPE)) {
			if (!swipeToElement(countryItem, COUNTRY_LIST_CONTAINER, SwipeDirections.UP, FIFTY, QUICK_SWIPE))
				throw new AssertionError(String.format("Country item not found: %s", countryItem));
		}
		tapElement(countryItem, FIVE);
		return new EnterYourPhoneNumberPage(DriverManager.getDriver());
	}

	public Countries getCountryByCountryCode(String countryCode) {
		for (Countries country : Countries.values()) {
			if (country.getCountryCode().equals(countryCode)) {
				return country;
			}
		}
		throw new AssertionError(String.format("There's no any Country with following country code: %s", countryCode));
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
