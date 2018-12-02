package seleniumTestNGinterview;

import org.testng.annotations.Test;

import com.sumith.utilityFunctions.SetWebDriverBase;

import org.testng.annotations.BeforeMethod;

import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class HandlingCookies {

	WebDriver wd = SetWebDriverBase.setWebDriver();

	@BeforeTest
	public void beforeTest() {
		wd.get("http://www.google.co.in/");
	}

	@AfterTest
	public void afterTest() {
		wd.quit();
	}

	@BeforeMethod
	public void beforeMethod() {
		System.out.println(">>>>> Before Method <<<<<");
	}

	@AfterMethod
	public void afterMethod() {
		System.out.println(">>>>> After Method <<<<<");
	}

	@Test
	public void testCookies() {

		// Go to the correct domain
		wd.get("http://www.example.com");

		// Now set the cookie. This one's valid for the entire domain
		Cookie cookie = new Cookie("key", "value");
		wd.manage().addCookie(cookie);

		// And now output all the available cookies for the current URL
		Set<Cookie> allCookies = wd.manage().getCookies();
		for (Cookie loadedCookie : allCookies) {
			System.out.println(String.format("%s -> %s", loadedCookie.getName(), loadedCookie.getValue()));
		}

	}
}
