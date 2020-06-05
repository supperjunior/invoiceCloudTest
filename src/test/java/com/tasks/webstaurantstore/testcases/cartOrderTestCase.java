package com.tasks.webstaurantstore.testcases;

import com.tasks.webstaurantstore.ScriptBase;
import com.tasks.webstaurantstore.pages.HomePage.EnvironmentType;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.*;

@Epic("Tests")
@Feature("Order Page")
public class cartOrderTestCase extends ScriptBase {

	/**************************** Test Data ****************************/
	protected String searchItem = "stainless work table";
	protected int lastItem = 60;

	/**************************** END ****************************/
	
	@BeforeClass
	public void setUp() throws InterruptedException{
		homepage.setUpEnv();
		// Step 1: Go to https://www.webstaurantstore.com/
		homepage.loadURL(EnvironmentType.test_env);
	}

    @Test (priority = 0, description="Verify add to the cart")
    @Severity(SeverityLevel.NORMAL)
    @Description("")
    @Story("")
	public void testAddToCart() {
    	
    	// Step 2: Search for 'stainless work table'.
    	homepage.searchForItems(searchItem);
    	
		// Step 3L Check the search result ensuring every product item has the word 'Table' its title.
    	resultpage.verifySearchResults();
    	
		// Step 4: Add the last of found items to Cart.
    	resultpage.addItemToCard(lastItem);
    	
    	// Step 5: Empty Cart.
    	cartpage.emptyCart();
	}

	@AfterClass
	public void cleanUp() {
		// Clean up env
		homepage.cleanUpEnv();
	}

}