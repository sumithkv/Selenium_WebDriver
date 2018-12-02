package com.sumith.testClasses;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import com.sumith.businessProcesses.Login;
import com.sumith.utilityFunctions.SetWebDriverBase;

public class UnSuccessfulLogin {

	WebDriver wd = SetWebDriverBase.setWebDriver();

	@BeforeMethod
	public void beforeMethod() {

		System.out.println("***** BEFORE METHOD *****");

		wd.get("http://www.google.co.in/");

	}

	@Test(description="To verify the unsuccessful login")
	public void secondTest() throws Exception {

		Login lg = new Login(wd);

		lg.testLogin("Sumith", "Password@123", "true");

		lg.InvalidLogin();
	}

	@AfterMethod
	public void afterMethod() {

		System.out.println("***** AFTER METHOD *****");

		wd.quit();
	}

}
