package com.tasks.webstaurantstore.pages;


import static org.testng.Assert.assertTrue;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tasks.webstaurantstore.utils.BasePage;

public class CartPage extends BasePage {

	private static Logger log = Logger.getLogger(CartPage.class.getName());

	/***************** Web Object Element ********************/
	private String emptyCartEle = "//a[@href='/shoppingcart:cart.emptycart?nojs=1']";
	private String emptyMsgEle = "//p[contains(text(), 'Your cart is empty.')]";

	/**************************** END **********************************/

	/***************** Expected String Text ********************/

	/**************************** END **********************************/

	/**
	 * Method Name : <b>emptyCart</b> <br>
	 * Description : This method will empty cart.</br>
	 * Author: Zhen Kuang Date: 06/03/2019
	 * 
	 */
	public void emptyCart() {
		log.info(String.format("emptyCart()"));

		delayFor(1);
		driver.findElement(By.xpath(emptyCartEle)).click();
		delayFor(1);
		List<WebElement> list = driver.findElements(By.tagName("button"));
		for (int j = 0; j < list.size(); j++) {
			try {
				if (list.get(j).getText().equals("Empty Cart")) {
					list.get(j).click();
					delayFor(1);
					String emptyCartMsg = readText(By.xpath(emptyMsgEle));
					assertTrue(emptyCartMsg.equalsIgnoreCase("Your cart is empty."));
				}
			} catch(Throwable e) {
		     }
		}
	}

}

