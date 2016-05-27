package com.Astegic.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginObjects {

	private static WebElement element = null;

	public static WebDriver loadPage(WebDriver driver) {
		driver.get("http://rc25.mforms.us/");
		return driver;
	}

	public static WebElement txtbx_UserName(WebDriver driver)
			throws NoSuchElementException {
		element = driver.findElement(By.id("username"));
		return element;
	}

	public static WebElement txtbx_Password(WebDriver driver)
			throws NoSuchElementException {
		element = driver.findElement(By.id("password"));
		return element;
	}

	public static WebElement btn_LogIn(WebDriver driver)
			throws NoSuchElementException {
		element = driver.findElement(By.xpath("//input[@type='submit']"));
		return element;
	}

}
