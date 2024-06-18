package org.jamesraider.gui.mobile.android.mainmenu;

import static org.jamesraider.constants.NumberConstants.FIVE;

import org.jamesraider.constants.MainMenuItems;
import org.jamesraider.gui.mobile.AbstractPage;
import org.jamesraider.gui.mobile.android.account.ProfilePage;
import org.jamesraider.utils.DriverManager;
import org.openqa.selenium.By;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;

public class MainMenuPage extends AbstractPage {
	private final By ACCOUNT_TITLE = AppiumBy.xpath("//android.widget.TextView[@text='Account']");
	private final By PROFILE_NAME_TEXT = AppiumBy.id("com.hdw.james.rider:id/profileName");
	private final By BUILD_VERSION_TEXT = AppiumBy.id("com.hdw.james.rider:id/version");

	public MainMenuPage(AppiumDriver driver) {
		super(driver);
	}

	@Override
	public boolean isOpened(long timeout) {
		return isElementPresent(ACCOUNT_TITLE, timeout) && isElementPresent(BUILD_VERSION_TEXT, timeout);
	}

	public ProfilePage tapOnProfileItem() {
		tapElement(PROFILE_NAME_TEXT, FIVE);
		return new ProfilePage(DriverManager.getDriver());
	}

	public String getProfileName() {
		return findElement(PROFILE_NAME_TEXT, FIVE).getText();
	}

	public Class<? extends AbstractPage> tapOnItem(MainMenuItems item) {
		LOGGER.info("Selecting '{}' item", item.getItemName());
		By itemLocator = AppiumBy.xpath(String.format(
				"//*[@resource-id='com.hdw.james.rider:id/title' and contains(@text,\"%s\")]", item.getItemName()));
		tapElement(itemLocator, FIVE);
		return item.getExitPoint();
	}

}
