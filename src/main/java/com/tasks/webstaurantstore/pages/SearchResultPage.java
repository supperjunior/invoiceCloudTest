package com.tasks.webstaurantstore.pages;

import static org.testng.Assert.assertTrue;

import java.util.List;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.tasks.webstaurantstore.utils.BasePage;

public class SearchResultPage extends BasePage {

	private static Logger log = Logger.getLogger(SearchResultPage.class.getName());

	/***************** Web Object Element ********************/
	private String searchResultTableEle = "product_listing";
	private String buyBottonEle = "buyButton";

	/**************************** END **********************************/

	/***************** Expected String Text ********************/

	/**************************** END **********************************/

	/**
	 * Method Name : <b>verifySearchResults</b> <br>
	 * Description : This method will verify search results.</br>
	 * Author: Zhen Kuang Date: 06/03/2019
	 * 
	 */
	public void verifySearchResults() {
		log.info(String.format("verifySearchResults()"));

		verifyTableTitle();
		List<WebElement> pageLists = driver.findElement(By.id("paging")).findElements(By.tagName("li"));
		for (int i = 0; i < pageLists.size(); i++) {
			if (i <= 1) {
				driver.findElement(By.xpath("//*[@id='paging']/div/ul/li[" + (8) + "]/a/i")).click();
				delayFor(1);
				verifyTableTitle();
			} else if (i >= 5) {
				driver.findElement(By.xpath("//*[@id='paging']/div/ul/li[" + (15 - i) + "]/a/i")).click();
				delayFor(1);
				verifyTableTitle();
			} else {
				driver.findElement(By.xpath("//*[@id='paging']/div/ul/li[" + (8 + i - 1) + "]/a/i")).click();
				delayFor(1);
				verifyTableTitle();
			}
		}
	}

	/**
	 * Method Name : <b>verifyTableTitle</b> <br>
	 * Description : This method will verify search results that contain
	 * 'Table'.</br>
	 * Author: Zhen Kuang Date: 06/03/2019
	 * 
	 */
	public void verifyTableTitle() {
		log.info(String.format("verifyTableTitle()"));

		waitForElement(By.id(searchResultTableEle));
		List<WebElement> listResults = driver.findElement(By.id(searchResultTableEle)).findElements(By.tagName("a"));
		for (int i = 0; i < listResults.size(); i++) {
			try {
				if (listResults.get(i).getAttribute("class").contains("description")) {
					assertTrue(listResults.get(i).getText().contains("Table"), listResults.get(i).getText());
				}
			} catch(Throwable e) {
				System.out.println("*****" + listResults.get(i).getText() + " does not have the word 'Table' its title *****");
		     }
		}
	}
	
	/**
	 * Method Name : <b>addItemToCard</b> <br>
	 * Description : This method will add item to cart.</br>
	 * Author: Zhen Kuang Date: 06/03/2019
	 * 
	 */
	public void addItemToCard(int number) {
		log.info(String.format("addItemToCard()"));

		int count = 0;
		List<WebElement> listResults = driver.findElement(By.id(searchResultTableEle)).findElements(By.tagName("a"));
		for (int i = 0; i < listResults.size(); i++) {
			try {
				if (listResults.get(i).getAttribute("class").contains("description")) {
					count = count + 1;
					if (count == number){
						listResults.get(i).click();
						click(By.id(buyBottonEle));
						click(By.xpath("//*[@id='subject']/div[2]/div/a[1]/button"));
					}
				}
			} catch(Throwable e) {
		     }
		}
	}

}
