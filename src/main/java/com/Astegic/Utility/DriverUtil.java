package com.Astegic.Utility;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import com.Astegic.Logging.Log;

public class DriverUtil {
	public static final String PROP_PROJECT_BASE_DIR = "project.basedir";
	public static final String FOLDER_DRIVER = "Driver";
	public static final String DEFAULT_PROPERTIES = "system.properties";
	private static Properties prod;

	
	 // return the path of ie driver file.
	
	public static String getIeDriver() {
		String path = getKey(PROP_PROJECT_BASE_DIR) + File.separator
				+ FOLDER_DRIVER + File.separator + "IEDriverServer.exe";
		try {
			File driverIe = new File(path);
			if (driverIe.exists()) {
				return driverIe.getAbsolutePath();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	
	 // return the path of chrome driver file
	 
	public static String getChromeDriver() {
		String path = getKey(PROP_PROJECT_BASE_DIR) + File.separator
				+ FOLDER_DRIVER + File.separator + "chromedriver.exe";
		try {
			File driverChrome = new File(path);
			if (driverChrome.exists()) {
				return driverChrome.getAbsolutePath();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	
	  // return load the file system.properties
	 
	public static Properties getProperties() {
		if (prod == null) {
			prod = new Properties();
			try {
				prod.load(DriverUtil.class.getClassLoader()
						.getResourceAsStream(DEFAULT_PROPERTIES));
			} catch (IOException e) {
				
			}
		}
		return prod;
	}

	
	// @param key and return get value of key
	 
	public static String getKey(String key) {
		Object obj = getProperties().get(key);
		String value = "";
		if (obj != null)
			value = obj.toString();
		return value;
	}

	// return test-case name
	
	public static String getTestCaseName(String sTestCase) {
		String value = sTestCase;
		try {
			int posi = value.indexOf("@");
			value = value.substring(0, posi);
			posi = value.lastIndexOf(".");
			value = value.substring(posi + 1);
			return value;
		} catch (Exception e) {
			Log.error("Class Utils | Method getTestCaseName | Exception desc : "
					+ e.getMessage());
			throw (e);
		}
	}

}
