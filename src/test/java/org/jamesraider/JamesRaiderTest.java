package org.jamesraider;

import static org.jamesraider.constants.NumberConstants.FIVE;

import org.jamesraider.gui.mobile.android.login.WelcomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class JamesRaiderTest extends AbstractTest {

	@Test
	public void testSomething() {
		WelcomePage welcomePage = new WelcomePage(driver);
		Assert.assertTrue(welcomePage.isOpened(FIVE), "Welcome page is not opened after launching app!");
	}
}
