package com.sumith.businessProcesses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class Logout {

	WebDriver wd;

	public Logout(WebDriver driver) {
		wd = driver;
	}

	public void testLogout() throws Exception {
		WebElement myaccount = wd.findElement(By.xpath("//div[@id='wpadminbar']//li[@id='wp-admin-bar-my-account']"));
		myaccount.click();

		WebElement logout = wd.findElement(By.xpath(
				"//div[@id='wpadminbar']//li[@id='wp-admin-bar-my-account']//div[@class='ab-sub-wrapper']//li[@id='wp-admin-bar-logout']//a[text()='Log Out']"));
		logout.click();

		System.out.println("...User Logged Out...");
	}

	public void validateLogout() {
		WebElement logoutMsg = wd
				.findElement(By.xpath("//div[@id='login']//p[contains(text(),'You are now logged out')]"));

		Assert.assertTrue(logoutMsg.getText().contains("You are now logged out"));

		System.out.println("...Log Out Validated...");

	}

}
