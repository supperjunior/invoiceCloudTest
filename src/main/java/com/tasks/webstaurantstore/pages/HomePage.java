package com.tasks.webstaurantstore.pages;

import static org.testng.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import com.tasks.webstaurantstore.utils.BasePage;
import com.tasks.webstaurantstore.utils.EnvironmentCleanUp;

public class HomePage extends BasePage {
	
	private static Logger log = Logger.getLogger(HomePage.class.getName());

	private final String url = "https://www.webstaurantstore.com";

	/***************** Web Object Element ********************/
	private String searchBoxEle = "searchval";
	private String searchButtonEle = "button[type='submit'][value='Search']";
	private String searchResultTitleEle = "//h1[contains(text(), 'Search Results for')]";
	/**************************** END **********************************/

	/***************** Expected String Text ********************/
	private String expectedTitleText = "WebstaurantStore: Restaurant Supplies & Foodservice Equipment";
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
	 * Date: 06/03/2019
	 * 
	 */
	public void loadURL(EnvironmentType environmentType) throws InterruptedException {
		log.info(String.format("Browse URL : " + getEnvironmentTypeURL(environmentType) + " login to the application"));

		try {
			driver.get(getEnvironmentTypeURL(environmentType));
			String title = driver.getTitle();
			assertTrue(title.equalsIgnoreCase(expectedTitleText));
	
		} catch (Exception e) {
			String getTitle = driver.getTitle();
			log.info(String.format("Unable to open the website : " + getTitle));
			e.printStackTrace();
			throw new Error("Unable to open the website");
		}

	}
	
	/**
	 * Method Name : <b>searchForItems</b> <br>
	 * Description : This method will search items.</br>
	 * Author: Zhen Kuang 
	 * Date: 06/03/2019
	 * 
	 */
	public void searchForItems(String itemName){
		log.info(String.format("searchForItems(%s)", itemName));
			
		writeText(By.id(searchBoxEle), itemName);
		click(By.cssSelector(searchButtonEle));
		String researchTitle = readText(By.xpath(searchResultTitleEle));
		assertTrue(researchTitle.equalsIgnoreCase("Search Results for: " + itemName));
	}


}
