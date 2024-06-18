package org.jamesraider.utils;

import static org.jamesraider.utils.ConfigUtil.CAPABILITIES_APP_ACTIVITY;
import static org.jamesraider.utils.ConfigUtil.CAPABILITIES_APP_PACKAGE;
import static org.jamesraider.utils.ConfigUtil.CAPABILITIES_APP_PATH;
import static org.jamesraider.utils.ConfigUtil.CAPABILITIES_UDID;

import java.net.MalformedURLException;
import java.net.URL;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class DriverManager {
	private static AndroidDriver driver;

	public static void createDriver() {
		if (driver == null) {
			try {
				UiAutomator2Options options = new UiAutomator2Options().setUdid(CAPABILITIES_UDID)
						.setApp(CAPABILITIES_APP_PATH).setAppPackage(CAPABILITIES_APP_PACKAGE)
						.setAppActivity(CAPABILITIES_APP_ACTIVITY);
				driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), options);
			} catch (MalformedURLException e) {
				throw new AssertionError(String.format("It was not able to create driver: %s", e));
			}
		}
	}

	public static AndroidDriver getDriver() {
		return driver;
	}

	public static void quitDriver() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}
}