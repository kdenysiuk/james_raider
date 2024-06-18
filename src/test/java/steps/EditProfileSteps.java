package steps;

import static org.jamesraider.constants.NumberConstants.TEN;

import org.jamesraider.gui.mobile.android.account.ProfilePage;
import org.jamesraider.gui.mobile.android.mainmenu.MainMenuPage;
import org.jamesraider.utils.DriverManager;
import org.jamesraider.utils.RandomizeUtil;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class EditProfileSteps extends AbstractSteps {
	private String newRandomName;

	@And("User changes profile name")
	public void userChangesProfileName() {
		MainMenuPage mainMenuPage = new MainMenuPage(DriverManager.getDriver());
		this.newRandomName = new RandomizeUtil().generateRandomAlphabeticWord(TEN);
		ProfilePage profilePage = mainMenuPage.tapOnProfileItem().setName(newRandomName);
		profilePage.tapOnSaveButton();
	}

	@Then("User sees profile name has changed")
	public void profileNameHasChanged() {
		MainMenuPage mainMenuPage = new MainMenuPage(DriverManager.getDriver());
		Assert.assertTrue(mainMenuPage.getProfileName().contains(newRandomName), "");
	}
}
