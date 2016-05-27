package com.Astegic.TestCases;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.Astegic.Action.Login;
import com.Astegic.Logging.Log;
import com.Astegic.Utility.BrowserUtil;
import com.Astegic.Utility.Constant;
import com.Astegic.Utility.DriverUtil;
import com.Astegic.Utility.EmailTestNGReports;
import com.Astegic.Utility.WaitUtil;
import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;


public class LoginTest {

	private String sTestCaseName;
	private static String workbookName = Constant.WORKBOOK_FILE_PATH;
	private static String sheetName = Constant.SHEET_NAME;
	int rowNum = 1;
	int colNum = 0;
	private static WebDriver driver = null;
//	ExtentReports eReports;
//	ExtentTest logger;

	@Parameters({ "browser" })
	@BeforeTest
	public void beforeTest(String browser) {
		DOMConfigurator.configure("log4j.xml");
		// Getting the Test Case name, we will get long test case name including
		// package and class name etc.
//		eReports = new ExtentReports("C:\\DemoScreenshoot\\demo.html");
		
		sTestCaseName = this.toString();
		// This method will refine test case name, exactly the name we have used
		sTestCaseName = DriverUtil.getTestCaseName(this.toString());
		Log.startTestCase(sTestCaseName);
		driver = BrowserUtil.launchBrowser(driver, browser);
		
	}

	@Test()
	public void loginTest() {
		Login.loadPage(driver);
		Log.info("Loading of page is done");
		Login.signIn(driver, workbookName, sheetName, rowNum, colNum);
		WaitUtil.implicitWait(driver);
		Log.info("Login done");
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
		Log.info("Browser closed");
//		EmailTestNGReports.sendPDFReportByGMail("nagayachshashwat@gmail.com", "2232341718", "snagayach@astegic.com", "Test report sub", "Test repot body");
	}
}
