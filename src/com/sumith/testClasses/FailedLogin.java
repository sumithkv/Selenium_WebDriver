package com.sumith.testClasses;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import com.sumith.businessProcesses.Login;
import com.sumith.businessProcesses.Logout;
import com.sumith.utilityFunctions.SetWebDriverBase;

public class FailedLogin {

	WebDriver wd = SetWebDriverBase.setWebDriver();

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("***** BEFORE METHOD *****");

		wd.get("http://www.google.co.in/");
	}

	@Test(description="To verify a Failed Login")
	public void thirdTest() throws Exception {
		Login lg = new Login(wd);
		Logout lo = new Logout(wd);

		lg.testLogin("12345", "67890", "true");

		lg.validateLogin("admin");

		lo.testLogout();

		lo.validateLogout();

	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("***** AFTER METHOD *****");

		wd.quit();

	}

}
