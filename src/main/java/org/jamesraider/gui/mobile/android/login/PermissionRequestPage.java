package org.jamesraider.gui.mobile.android.login;

import static org.jamesraider.constants.NumberConstants.FIVE;

import org.jamesraider.gui.mobile.AbstractPage;
import org.jamesraider.utils.DriverManager;
import org.openqa.selenium.By;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;

public class PermissionRequestPage extends AbstractPage {

	private final By PERMISSION_REQUEST_TITLE = AppiumBy.id("com.hdw.james.rider:id/permissionsTextTitle");
	private final By LOCATION_ALLOW_BUTTON = AppiumBy.id("com.hdw.james.rider:id/permissionsLocationButton");
	private final By ANDROID_WHILE_USING_THE_APP_BUTTON = AppiumBy
			.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button");
	private final By NOTIFICATIONS_ALLOW_BUTTON = AppiumBy.id("com.hdw.james.rider:id/permissionsNotificationButton");
	private final By ANDROID_ALLOW_BUTTON = AppiumBy.id("com.android.permissioncontroller:id/permission_allow_button");
	private final By CONTINUE_BUTTON = AppiumBy.id("com.hdw.james.rider:id/permissionsContinueButton");

	public PermissionRequestPage(AppiumDriver driver) {
		super(driver);
	}

	@Override
	public boolean isOpened(long timeout) {
		return isElementPresent(PERMISSION_REQUEST_TITLE, timeout);
	}

	public PermissionRequestPage allowAllPermissions() {
		tapElement(LOCATION_ALLOW_BUTTON, FIVE);
		tapElement(ANDROID_WHILE_USING_THE_APP_BUTTON, FIVE);
		tapElement(NOTIFICATIONS_ALLOW_BUTTON, FIVE);
		tapElement(ANDROID_ALLOW_BUTTON, FIVE);
		return new PermissionRequestPage(DriverManager.getDriver());
	}

	public PermissionRequestPage tapOnContinueButton() {
		tapElement(CONTINUE_BUTTON, FIVE);
		return new PermissionRequestPage(DriverManager.getDriver());
	}
}
