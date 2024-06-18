package org.jamesraider.constants;

import org.jamesraider.gui.mobile.AbstractPage;
import org.jamesraider.gui.mobile.android.login.EnterYourPhoneNumberPage;
import org.jamesraider.gui.mobile.android.mainmenu.LegalPage;
import org.jamesraider.gui.mobile.android.mainmenu.MyDriversPage;
import org.jamesraider.gui.mobile.android.mainmenu.PreviousRidesPage;
import org.jamesraider.gui.mobile.android.settings.SettingsPage;

public enum MainMenuItems {
	SETTINGS("SETTINGS", SettingsPage.class), PREVIOUS_RIDES("PREVIOUS RIDES", PreviousRidesPage.class),
	MY_DRIVERS("MY DRIVERS", MyDriversPage.class), LEGAL("LEGAL", LegalPage.class),
	SIGN_OUT("SIGN OUT", EnterYourPhoneNumberPage.class);

	private final String itemName;
	private final Class<? extends AbstractPage> exitPoint;

	private MainMenuItems(String itemName, Class<? extends AbstractPage> exitPoint) {
		this.itemName = itemName;
		this.exitPoint = exitPoint;
	}

	public String getItemName() {
		return itemName;
	}

	public Class<? extends AbstractPage> getExitPoint() {
		return exitPoint;
	}

}
