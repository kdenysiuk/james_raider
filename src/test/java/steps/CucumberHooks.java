package steps;

import java.lang.invoke.MethodHandles;

import org.jamesraider.utils.DriverManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class CucumberHooks {
	public static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Before
	public void onExecutionStart() {
		LOGGER.info("Starting server session...");
		DriverManager.createDriver();
	}

	@After
	public void onExecutionFinish() {
		LOGGER.info("Finishing server session...");
		DriverManager.quitDriver();
	}
}
