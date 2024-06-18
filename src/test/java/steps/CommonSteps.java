package steps;

import static org.jamesraider.constants.NumberConstants.FIVE;

import org.jamesraider.entities.User;
import org.jamesraider.gui.mobile.android.login.EnterYourPhoneNumberPage;
import org.jamesraider.gui.mobile.android.mainmenu.MainMenuPage;
import org.jamesraider.gui.mobile.android.rides.RidesPage;
import org.jamesraider.services.AuthService;
import org.jamesraider.utils.ConfigUtil;
import org.jamesraider.utils.DriverManager;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class CommonSteps extends AbstractSteps {
	private final User user = new User(ConfigUtil.PHONE_NUMBER, ConfigUtil.PHONE_COUNTRY_CODE,
			ConfigUtil.PHONE_SMS_VALIDATION);
	private final AuthService authService = new AuthService();

	@Given("User is logged in")
	public void userIsLoggedIn() {
		if (!authService.isUserLoggedIn())
			authService.login(user);
	}

	@Then("User is on Enter your phone number page")
	public void userIsOnEnterYourPhoneNumberPage() {
		Assert.assertTrue(new EnterYourPhoneNumberPage(driver).isOpened(FIVE),
				"[ ENTER YOUR PHONE NUMBER screen ]: Page is not opened after tapping 'Get Started' button in 'Welcome' page");
	}

	@And("User taps on Hamburger button")
	public void userTapsOnHamburgerButton() {
		RidesPage ridesPage = new RidesPage(DriverManager.getDriver());
		ridesPage.tapOnHamburgerButton();
	}

	@Then("User is on Main Menu page")
	public void userIsOnMainMenuPage() {
		Assert.assertTrue(new MainMenuPage(DriverManager.getDriver()).isOpened(FIVE),
				"[ MAIN MENU screen ]: Page is not opened after tapping 'Hamburger' button in 'Rides' page");
	}
}
