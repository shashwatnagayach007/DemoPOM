package com.Astegic.Utility;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtil {
	static WebDriverWait wait;

	public static void waitForElementToBeClickable(WebDriver driver,
			WebElement element) throws Exception {
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public static void getScreenShot(WebDriver driver, String file) {
		try {
			File scrFile = ((TakesScreenshot) driver)
					.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(file));
		} catch (IOException ioe) {
			ioe.getStackTrace();
		}
	}

	public static void webDriverWait(WebDriver driver) {
		wait = (WebDriverWait) new WebDriverWait(driver, 18);
		wait.pollingEvery(2, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class);
	}

	public static void waitForElementToBeVisible(WebDriver driver,
			WebElement element) {
		wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOf(element));

	}

	public static void implicitWait(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	public static void waitForElementSelected(WebDriver driver, By locator) {
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeSelected(locator));
	}

	public static void waitForPresenceOfElement(WebDriver driver, By locator) {
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));

	}
}
