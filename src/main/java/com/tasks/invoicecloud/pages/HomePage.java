package com.tasks.invoicecloud.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.tasks.invoicecloud.utils.BasePage;
import com.tasks.invoicecloud.utils.EnvironmentCleanUp;

import junit.framework.Assert;

public class HomePage extends BasePage {
	
	private static Logger log = Logger.getLogger(HomePage.class.getName());

	private final String url = "https://the-internet.herokuapp.com/add_remove_elements/";

	/***************** Web Object Element ********************/
	private String addElementBtn = "//button[contains(text(), 'Add Element')]";
	private String deleteElementBtn = "//button[contains(text(), 'Delete')]";
	/**************************** END **********************************/

	/***************** Expected String Text ********************/
	private String expectedTitleText = "The Internet";
	/**************************** END **********************************/

	/**
	 * Method Name : <b>setUpEnv</b> <br>
	 * Description : This method will set up Env.</br>
	 * 
	 */
	public void setUpEnv() {
		log.info(String.format("setupEnv()"));

		EnvironmentCleanUp.envCleanup();
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("useAutomationExtension", false);
		options.addArguments("--allow-insecure-localhost");
		driver = new ChromeDriver(options);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		delayFor(2);
	}

	/**
	 * Method Name : <b>cleanUpEnv</b> <br>
	 * Description : This method will clean up Env.</br>
	 * 
	 */
	public void cleanUpEnv() {
		log.info(String.format("cleanUpEnv()"));

		driver.close();
		driver.quit();
		delayFor(2);
	}

	public enum EnvironmentType {
		test_env
	}

	private String getEnvironmentTypeURL(EnvironmentType environmentType) {

		String tempURL = "";
		if (environmentType == EnvironmentType.test_env) {
			tempURL = url;
		} 	
		return tempURL;
	}
	
	/**
	 * Method Name : <b>loadURL</b> <br>
	 * Description : This method will load the url.</br>
	 * Author: Zhen Kuang 
	 * Date: 
	 * 
	 */
	public void loadURL(EnvironmentType environmentType) throws InterruptedException {
		log.info(String.format("Browse URL : " + getEnvironmentTypeURL(environmentType) + " login to the application"));

		try {
			driver.get(getEnvironmentTypeURL(environmentType));
			String title = driver.getTitle();
			Assert.assertTrue(title.trim().equalsIgnoreCase(expectedTitleText));
	
		} catch (Exception e) {
			String getTitle = driver.getTitle();
			log.info(String.format("Unable to open the website : " + getTitle));
			e.printStackTrace();
			throw new Error("Unable to open the website");
		}

	}
	
	/**
	 * Method Name : <b>addElements</b> <br>
	 * Description : This method will click add element.</br>
	 * Author: Zhen Kuang 
	 *
	 * 
	 */
	public void addElements(int number){
		log.info(String.format("addElements(%s)", number));
		for (int i = 0; i<=number-1; i++) {
			click(By.xpath(addElementBtn));
		}
		
		List<WebElement> listOfElements = driver.findElements(By.xpath(deleteElementBtn));
		Assert.assertTrue(number==listOfElements.size());
		
	}


}
