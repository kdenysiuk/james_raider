package steps;

import io.cucumber.java.en.And;
import org.jamesraider.constants.MainMenuItems;
import org.jamesraider.gui.mobile.android.mainmenu.MainMenuPage;
import org.jamesraider.utils.DriverManager;

import io.cucumber.java.en.When;

public class LogoutSteps extends AbstractSteps {

	@And("User taps SIGN OUT button")
	public void userTapsSignOutButton() {
		MainMenuPage mainMenuPage = new MainMenuPage(DriverManager.getDriver());
		mainMenuPage.tapOnItem(MainMenuItems.SIGN_OUT);
	}
}
