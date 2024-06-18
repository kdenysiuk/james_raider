package steps;

import java.lang.invoke.MethodHandles;

import org.jamesraider.utils.DriverManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.appium.java_client.AppiumDriver;

public abstract class AbstractSteps {
	public static AppiumDriver driver;
	public static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	public AbstractSteps() {
		driver = DriverManager.getDriver();
	}
}
