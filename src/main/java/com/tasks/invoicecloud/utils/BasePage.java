package com.tasks.invoicecloud.utils;

import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	public static WebDriver driver;
	private Logger log = Logger.getLogger(BasePage.class.getName());

	public void waitForElement(By elementLocation) {
		new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(elementLocation));

	}

	// Click Method
	public void click(By elementLocation) {
		try {
			delayFor(1);
			waitForElement(elementLocation);
			driver.findElement(elementLocation).click();
		} catch (Exception e) {
			System.out.println("Unable to click the element");
			e.printStackTrace();
		}
	}


	// check button Method
	public void buttonDisabled(WebElement firstPageEle) {
		delayFor(1);
		try {
			assertTrue(
					!(driver.findElement(By.xpath(String.format("//*[@%s='%s']", "class", firstPageEle))).isEnabled()));
		} catch (Exception e) {
			System.out.println("Unable to verify diabled element");
			e.printStackTrace();
		}
	}

	// Click Method
	public void buttonEnabled(WebElement element) {
		try {
			assertTrue(driver.findElement(By.xpath(String.format("//*[@%s='%s']", "class", element))).isEnabled());
		} catch (Exception e) {
			System.out.println("Unable to verify enable element");
			e.printStackTrace();
		}
	}

	// Write Text
	public void writeText(By elementLocation, String text) {
		waitForElement(elementLocation);
		try {
			driver.findElement(elementLocation).clear();
			delayFor(2);
			driver.findElement(elementLocation).sendKeys(text);
		} catch (Exception e) {
			System.out.println("Unable to write the element");
			e.printStackTrace();
		}
	}

	public void delayFor(int second) {

		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	// Read Text
	public String readText(By elementLocation) {
		waitForElement(elementLocation);
		return driver.findElement(elementLocation).getText();

	}
}
