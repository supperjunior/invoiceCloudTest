package com.tasks.invoicecloud.testcases;

import com.tasks.invoicecloud.ScriptBase;
import com.tasks.invoicecloud.pages.HomePage.EnvironmentType;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

import java.util.Random;

import org.testng.annotations.*;

@Epic("Tests")
@Feature("Order Page")
public class cartOrderTestCase extends ScriptBase {

	/**************************** Test Data ****************************/
	Random rn = new Random();
	protected int number = rn.nextInt(10) + 1;

	/**************************** END ****************************/
	
	@BeforeClass
	public void setUp() throws InterruptedException{
		homepage.setUpEnv();
		// Step 1: Go to the url
		homepage.loadURL(EnvironmentType.test_env);
	}

    @Test (priority = 0, description="Verify add to the cart")
    @Severity(SeverityLevel.NORMAL)
    @Description("")
    @Story("")
	public void testAddToCart() {
    	
    	
    	// Step 2: click element N time.
    	homepage.addElements(number);
    	

	}

	@AfterClass
	public void cleanUp() {
		// Clean up env
		homepage.cleanUpEnv();
	}

}