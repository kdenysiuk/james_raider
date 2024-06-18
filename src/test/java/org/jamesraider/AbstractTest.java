package org.jamesraider;

import java.lang.invoke.MethodHandles;

import org.jamesraider.utils.DriverManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.appium.java_client.AppiumDriver;

public abstract class AbstractTest {
	public static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	public static AppiumDriver driver;

	@BeforeSuite
	public void startServerSession() {
		LOGGER.info("Open server session.");
		DriverManager.createDriver();
		driver = DriverManager.getDriver();
	}

	@AfterSuite
	public void closeServerSession() {
		LOGGER.info("Closing server session.");
		DriverManager.quitDriver();
	}
}
