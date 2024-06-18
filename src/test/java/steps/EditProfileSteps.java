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
		RandomizeUtil randomizeUtil = new RandomizeUtil();
		String name = randomizeUtil.generateRandomAlphabeticWord(TEN);
		String surname = randomizeUtil.generateRandomAlphabeticWord(TEN);
		this.newRandomName = String.format("%s %s", name, surname);
		ProfilePage profilePage = mainMenuPage.tapOnProfileItem().setName(name);
		profilePage = profilePage.setSurname(surname);
		profilePage.tapOnSaveButton();
	}

	@Then("User sees profile name has changed")
	public void profileNameHasChanged() {
		MainMenuPage mainMenuPage = new MainMenuPage(DriverManager.getDriver());
		Assert.assertEquals(mainMenuPage.getProfileName(), newRandomName,
				"[ MAIN MENU screen ]: Profile name is the same after user has changed it on 'Profile' page");
	}
}
