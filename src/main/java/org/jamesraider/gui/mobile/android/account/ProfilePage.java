package org.jamesraider.gui.mobile.android.account;

import static org.jamesraider.constants.NumberConstants.FIVE;

import org.jamesraider.gui.mobile.AbstractPage;
import org.jamesraider.gui.mobile.android.mainmenu.MainMenuPage;
import org.jamesraider.utils.DriverManager;
import org.openqa.selenium.By;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;

public class ProfilePage extends AbstractPage {
	private final By ACCOUNT_TITLE = AppiumBy.xpath("//android.widget.TextView[@text='Profile']");
	private final By DONE_BUTTON = AppiumBy.id("com.hdw.james.rider:id/DEFAULT_TEXT_ACTION_MENU_ID");
	private final By NAME_FIELD = AppiumBy.id("com.hdw.james.rider:id/firstNameInput");
	private final By SURNAME_FIELD = AppiumBy.id("com.hdw.james.rider:id/lastNameInput");

	public ProfilePage(AppiumDriver driver) {
		super(driver);
	}

	@Override
	public boolean isOpened(long timeout) {
		return isElementPresent(ACCOUNT_TITLE, FIVE);
	}

	public ProfilePage setName(String name) {
		findElement(NAME_FIELD, FIVE).sendKeys(name);
		return new ProfilePage(DriverManager.getDriver());
	}

	public ProfilePage setSurname(String surname) {
		findElement(SURNAME_FIELD, FIVE).sendKeys(surname);
		return new ProfilePage(DriverManager.getDriver());
	}

	public MainMenuPage tapOnSaveButton() {
		tapElement(DONE_BUTTON, FIVE);
		return new MainMenuPage(DriverManager.getDriver());
	}
}
