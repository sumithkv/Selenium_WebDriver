package com.sumith.businessProcesses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class Login {

	WebDriver wd;

	public Login(WebDriver driver) {
		wd = driver;
	}

	public void testLogin(String User, String Password, String RememberMe) throws InterruptedException {
		wd.get("http://demo.opensourcecms.com/wordpress/wp-login.php");
		wd.manage().window().maximize();

		WebElement user = wd.findElement(By.id("user_login"));
		WebElement password = wd.findElement(By.id("user_pass"));
		WebElement remember = wd.findElement(By.id("rememberme"));
		WebElement login = wd.findElement(By.id("wp-submit"));

		user.clear();
		user.sendKeys(User);
		password.clear();
		password.sendKeys(Password);

		if (!remember.isSelected() && RememberMe.equalsIgnoreCase("true"))
			remember.click();
		else if (remember.isSelected() && RememberMe.equalsIgnoreCase("false"))
			remember.click();
		else {
			System.out.println("You have specified an invalid attribute...");
		}

		login.click();

		System.out.println("...User Logged In...");

	}

	public void validateLogin(String loginName) {

		WebElement username = wd
				.findElement(By.xpath("//div[@id='wpadminbar']//li[@id='wp-admin-bar-user-info']//a//span[text()=" + "'"
						+ loginName + "'" + "]"));

		Assert.assertTrue(username.toString().contains(loginName));

		System.out.println("...Log In Validated...");

	}

	public void InvalidLogin() throws InterruptedException {

		WebElement errMsg = wd.findElement(By.id("login_error"));
		String Msg = errMsg.getText();

		Assert.assertTrue(Msg.contains("Invalid username"));

		WebElement pwdLink = wd.findElement(By.xpath("//div[@id='login_error']//a[text()='Lost your password']"));

		pwdLink.click();
		Thread.sleep(1000);
		Assert.assertEquals(wd.getCurrentUrl(),
				"http://demo.opensourcecms.com/wordpress/wp-login.php?action=lostpassword");

	}

}
