package org.jamesraider.utils;

import java.io.IOException;
import java.util.Properties;

public class ConfigUtil {
	public static final String configFileName = "_config.properties";
	public static final String CAPABILITIES_APP_PACKAGE = ConfigUtil.getPropValues("capabilities.appPackage");
	public static final String CAPABILITIES_APP_ACTIVITY = ConfigUtil.getPropValues("capabilities.appActivity");
	public static final String CAPABILITIES_UDID = ConfigUtil.getPropValues("capabilities.udid");
	public static final String CAPABILITIES_APP_PATH = ConfigUtil.getPropValues("capabilities.appPath");
	public static final String PHONE_COUNTRY_CODE = ConfigUtil.getPropValues("phone.countryCode");
	public static final String PHONE_NUMBER = ConfigUtil.getPropValues("phone.number");
	public static final String PHONE_SMS_VALIDATION = ConfigUtil.getPropValues("phone.smsValidation");

	public static String getPropValues(String configKeyToFetch) {
		Properties properties = new Properties();
		String configValueToReturn;
		try {
			properties.load(ConfigUtil.class.getClassLoader().getResourceAsStream(configFileName));
			configValueToReturn = properties.getProperty(configKeyToFetch);
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
		return configValueToReturn;
	}
}
