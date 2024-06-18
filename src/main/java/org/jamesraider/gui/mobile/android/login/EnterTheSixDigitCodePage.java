package org.jamesraider.gui.mobile.android.login;

import static org.jamesraider.constants.NumberConstants.FIVE;

import java.util.List;

import org.jamesraider.gui.mobile.AbstractPage;
import org.jamesraider.utils.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;

public class EnterTheSixDigitCodePage extends AbstractPage {

	private final By ENTER_THE_SIX_DIGIT_CODE_TITLE = AppiumBy.xpath("//*[@resource-id='com.hdw.james.rider:id/title' and @text='Enter the 6 digit code']");
	private final By CODE_INPUT_BOXES = AppiumBy.id("com.hdw.james.rider:id/inputEditText");
	private final By CONTINUE_BUTTON = AppiumBy.id("com.hdw.james.rider:id/continueButton");

	public EnterTheSixDigitCodePage(AppiumDriver driver) {
		super(driver);
	}

	@Override
	public boolean isOpened(long timeout) {
		return isElementPresent(ENTER_THE_SIX_DIGIT_CODE_TITLE, timeout);
	}

	public EnterTheSixDigitCodePage typeDigitCode(String code) {
		char[] codeDigits = code.toCharArray();
		List<WebElement> codeBoxes = findElements(CODE_INPUT_BOXES, FIVE);
		int digitCount = codeDigits.length;
		if (digitCount == 6 && codeBoxes.size() == 6) {
			for (int i = 0; i < digitCount; i++) {
				codeBoxes.get(i).sendKeys(String.valueOf(codeDigits[i]));
			}
		} else {
			throw new AssertionError(String.format("Code should be made by 6 digits but code '%s' is made by %s digits",
					code, digitCount));
		}
		return new EnterTheSixDigitCodePage(DriverManager.getDriver());
	}

	public PermissionRequestPage tapOnContinueButton() {
		tapElement(CONTINUE_BUTTON, FIVE);
		return new PermissionRequestPage(DriverManager.getDriver());
	}
}
