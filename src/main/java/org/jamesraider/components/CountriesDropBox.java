package org.jamesraider.components;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.jamesraider.constants.Countries;
import static org.jamesraider.constants.NumberConstants.FIFTY;
import static org.jamesraider.constants.NumberConstants.FIVE;
import static org.jamesraider.constants.NumberConstants.QUICK_SWIPE;
import org.jamesraider.constants.SwipeDirections;
import org.jamesraider.gui.mobile.AbstractPage;
import org.jamesraider.gui.mobile.android.login.EnterYourPhoneNumberPage;
import org.jamesraider.utils.DriverManager;
import org.openqa.selenium.By;

public class CountriesDropBox extends AbstractPage {
    private final By COUNTRY_LIST_CONTAINER = AppiumBy.className("android.widget.ListView");

    public CountriesDropBox(AppiumDriver driver) {
        super(driver);
    }

    @Override
    public boolean isOpened(long timeout) {
        return true;
    }

    public EnterYourPhoneNumberPage selectCountry(Countries country) {
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
}
