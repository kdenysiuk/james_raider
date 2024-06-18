package org.jamesraider.gui.mobile;

import static org.jamesraider.constants.NumberConstants.FIVE;
import static org.jamesraider.constants.NumberConstants.ONE;
import static org.jamesraider.constants.NumberConstants.ZERO;

import java.lang.invoke.MethodHandles;
import java.time.Duration;
import java.util.List;

import org.jamesraider.constants.SwipeDirections;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.appium.java_client.AppiumDriver;

public abstract class AbstractPage {
	AppiumDriver driver;
	public static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	public AbstractPage(AppiumDriver driver) {
		this.driver = driver;
	}

	public boolean isOpened(long timeout) {
		throw new UnsupportedOperationException();
	}

	public WebElement findElement(By locator, long timeout) {
		return findElement(locator, timeout, true);
	}

	public WebElement findElement(By locator, long timeout, boolean failIfNotFound) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		} catch (Exception e) {
			if (failIfNotFound) {
				LOGGER.error("Element not found {}", e.toString());
				throw new AssertionError(String.format("Element not found %s", locator.toString()));
			} else {
				LOGGER.warn("Element not found {}", locator.toString());
			}
		}
		return null;
	}

	public boolean waitUntilElementAbsence(By locator, long timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	public void tapElement(By locator, long timeout) {
		tapElement(locator, timeout, true);
	}

	public void tapElement(By locator, long timeout, boolean failIfNotPresent) {
		try {
			WebElement element = findElement(locator, timeout);
			LOGGER.info("Tapping element by locator {}", locator.toString());
			element.click();
		} catch (Exception e) {
			if (failIfNotPresent) {
				LOGGER.error("Element not found {}", e.toString());
				throw new AssertionError(String.format("Element not found %s", locator.toString()));
			} else {
				LOGGER.warn("Element not found {}", locator.toString());
			}
		}
	}

	public boolean isElementPresent(By locator, long timeout) {
		WebElement element = findElement(locator, timeout, false);
		boolean isPresent = element != null;
		LOGGER.info("Element is present: {}", isPresent);
		return isPresent;
	}

	public List<WebElement> findElements(By locator, long timeout) {
		return findElements(locator, timeout, true);
	}

	public List<WebElement> findElements(By locator, long timeout, boolean failIfNotFound) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			return driver.findElements(locator);
		} catch (Exception e) {
			if (failIfNotFound) {
				LOGGER.error("List of elements not found {}", e.toString());
				throw new AssertionError(String.format("List of elements %s", locator.toString()));
			} else {
				LOGGER.warn("List of elements {}", locator.toString());
			}
		}
		return null;
	}

	// Swipe methods

	public void swipeByCoordinates(int startXPosition, int startYPosition, int endXPosition, int endYPosition,
			int speed) {
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence swipe = new Sequence(finger, ONE);
		swipe.addAction(finger.createPointerMove(Duration.ofMillis(ZERO), PointerInput.Origin.viewport(),
				startXPosition, startYPosition));
		swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		swipe.addAction(finger.createPointerMove(Duration.ofMillis(speed), PointerInput.Origin.viewport(), endXPosition,
				endYPosition));
		swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		driver.perform(List.of(swipe));
	}

	public void swipeInContainer(By container, SwipeDirections swipeDirections, int times, int speed) {
		WebElement element = findElement(container, FIVE);
		int containerHeight = element.getSize().getHeight();
		int containerWidth = element.getSize().getWidth();
		int containerXPos = element.getLocation().getX();
		int containerYPos = element.getLocation().getY();
		int startXPosition = 0;
		int startYPosition = 0;
		int endXPosition = 0;
		int endYPosition = 0;
		switch (swipeDirections) {
		case UP:
			startXPosition = (int) (containerXPos + containerWidth * 0.5);
			endXPosition = startXPosition;
			startYPosition = (int) (containerYPos + containerHeight * 0.80);
			endYPosition = (int) (containerYPos + containerHeight * 0.20);
			break;
		case DOWN:
			startXPosition = (int) (containerXPos + containerWidth * 0.5);
			endXPosition = startXPosition;
			startYPosition = (int) (containerYPos + containerHeight * 0.20);
			endYPosition = (int) (containerYPos + containerHeight * 0.80);
			break;
		case LEFT:
			startYPosition = (int) (containerXPos + containerHeight * 0.5);
			endYPosition = startYPosition;
			startXPosition = (int) (containerYPos + containerWidth * 0.20);
			endXPosition = (int) (containerYPos + containerWidth * 0.80);
			break;
		case RIGHT:
			startYPosition = (int) (containerXPos + containerHeight * 0.5);
			endYPosition = startYPosition;
			startXPosition = (int) (containerYPos + containerWidth * 0.80);
			endXPosition = (int) (containerYPos + containerWidth * 0.20);
			break;
		default:
			throw new AssertionError(String.format("Not implemented direction: %s", swipeDirections));
		}
		LOGGER.info("Swiping in direction {}", swipeDirections);
		for (int i = 0; i < times; i++) {
			swipeByCoordinates(startXPosition, startYPosition, endXPosition, endYPosition, speed);
		}
	}

	public boolean swipeToElement(By locator, By container, SwipeDirections swipeDirections, int times, int speed) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(ONE));
		WebElement element = null;
		int attempts = ONE;
		while (element == null && attempts <= times) {
			LOGGER.info("Number of attempt: {}", attempts);
			try {
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			} catch (Exception e) {
				swipeInContainer(container, swipeDirections, ONE, speed);
			}
			attempts++;
		}
		boolean isElementFound = element != null;
		LOGGER.warn("Element is found: {}", isElementFound);
		return isElementFound;
	}

}
