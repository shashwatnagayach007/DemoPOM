package com.Astegic.Action;

import org.openqa.selenium.WebDriver;
import com.Astegic.PageObjects.LoginObjects;
import com.Astegic.Utility.WorkbookData;

public class Login {

	public static void loadPage(WebDriver driver) {

		LoginObjects.loadPage(driver);

	}

	public static void signIn(WebDriver driver, String workbookName,
			String SheetName, int rowNum, int colNum) {

		WorkbookData wInstance = WorkbookData.getInstance();
		
		String username = wInstance.getTestData(workbookName, SheetName,
				rowNum, colNum++);
		String password = wInstance.getTestData(workbookName, SheetName,
				rowNum, colNum++);
		LoginObjects.txtbx_UserName(driver).sendKeys(username);
		LoginObjects.txtbx_Password(driver).sendKeys(password);
		LoginObjects.btn_LogIn(driver).click();
		
		wInstance.setTestResult(rowNum, colNum++, workbookName, "PASS");

	}

}
