package org.jamesraider.gui.mobile.android.rides;

import static org.jamesraider.constants.NumberConstants.FIVE;

import org.jamesraider.gui.mobile.AbstractPage;
import org.jamesraider.gui.mobile.android.mainmenu.MainMenuPage;
import org.jamesraider.utils.DriverManager;
import org.openqa.selenium.By;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;

public class RidesPage extends AbstractPage {

	private final By RIDES_TITLE = AppiumBy
			.xpath("//*[@resource-id='com.hdw.james.rider:id/toolbar']/android.widget.TextView[@text='Rides']");
	private final By HAMBURGER_BUTTON = AppiumBy.id("com.hdw.james.rider:id/MAIN_MENU_ID");

	public RidesPage(AppiumDriver driver) {
		super(driver);
	}

	@Override
	public boolean isOpened(long timeout) {
		return isElementPresent(RIDES_TITLE, timeout);
	}

	public MainMenuPage tapOnHamburgerButton() {
		tapElement(HAMBURGER_BUTTON, FIVE);
		return new MainMenuPage(DriverManager.getDriver());
	}
}
