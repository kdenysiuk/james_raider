package steps;

import static org.jamesraider.constants.NumberConstants.FIVE;

import org.jamesraider.constants.Countries;
import org.jamesraider.entities.User;
import org.jamesraider.gui.mobile.android.login.EnterTheSixDigitCodePage;
import org.jamesraider.gui.mobile.android.login.EnterYourPhoneNumberPage;
import org.jamesraider.gui.mobile.android.login.PermissionRequestPage;
import org.jamesraider.gui.mobile.android.login.WelcomePage;
import org.jamesraider.gui.mobile.android.rides.RidesPage;
import org.jamesraider.utils.ConfigUtil;
import org.jamesraider.utils.DriverManager;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps extends AbstractSteps {

	private final User user = new User(ConfigUtil.PHONE_NUMBER, ConfigUtil.PHONE_COUNTRY_CODE,
			ConfigUtil.PHONE_SMS_VALIDATION);

	@Given("User is on Welcome page")
	public void userIsOnWelcomePage() {
		WelcomePage welcomePage = new WelcomePage(driver);
		Assert.assertTrue(welcomePage.isOpened(5), "Welcome page is not opened after launching app!");
	}

	@When("User taps Get Started button")
	public void userTapsGetStartedButton() {
		new WelcomePage(driver).clickGetStartedButton();
	}

	@And("User sets country and number phone")
	public void userSetsCountryAndPhoneNumber() {
		EnterYourPhoneNumberPage enterYourPhoneNumberPage = new EnterYourPhoneNumberPage(driver);
		Countries country = enterYourPhoneNumberPage.getCountryByCountryCode(user.getPhoneCountryCode());
		enterYourPhoneNumberPage = enterYourPhoneNumberPage.selectCountry(country)
				.setNumberPhone(user.getPhoneNumber());
		enterYourPhoneNumberPage.tapOnContinueButton();
	}

	@Then("User is on Enter the six digit code page")
	public void userIsOnEnterTheSixDigitCodePage() {
		Assert.assertTrue(new EnterTheSixDigitCodePage(DriverManager.getDriver()).isOpened(FIVE),
				"[ ENTER THE SIX DIGIT CODE screen ]: Page is not opened after tapping 'Continue' button in 'Enter your phone number' page");
	}

	@And("User sets six digit code")
	public void userSetsSixDigitCode() {
		EnterTheSixDigitCodePage enterTheSixDigitCodePage = new EnterTheSixDigitCodePage(DriverManager.getDriver());
		enterTheSixDigitCodePage = enterTheSixDigitCodePage.typeDigitCode(user.getSmsVerificationCode());
		enterTheSixDigitCodePage.tapOnContinueButton();
	}

	@Then("User is on Permission Request page")
	public void userIsOnPermissionRequestPage() {
		Assert.assertTrue(new PermissionRequestPage(DriverManager.getDriver()).isOpened(FIVE),
				"[ PERMISSIONS REQUEST screen ]: Page is not opened after tapping 'Continue' button in 'Enter the six digit code' page");
	}

	@And("User grants all permissions")
	public void userGrantsAllPermissions() {
		PermissionRequestPage permissionRequestPage = new PermissionRequestPage(DriverManager.getDriver());
		permissionRequestPage = permissionRequestPage.allowAllPermissions();
		permissionRequestPage.tapOnContinueButton();
	}

	@Then("User is on Rides page")
	public void userIsLoggedIn() {
		Assert.assertTrue(new RidesPage(DriverManager.getDriver()).isOpened(FIVE),
				"[ RIDES screen ]: Page is not opened after tapping 'Continue' button in 'Permissions Request' page and user has granted all permissions");
	}
}
