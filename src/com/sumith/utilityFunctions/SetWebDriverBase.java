package com.sumith.utilityFunctions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

public class SetWebDriverBase {

	// This class is used for setting up a Selenium WebDriver. Here we need to
	// specify the Web Browser.

	public static WebDriver setWebDriver() {

		WebDriver wd = null;
		String Browser = LoadTestProperties.getProperty("WebBrowser");

		if (Browser.equalsIgnoreCase("firefox"))

		{
			System.setProperty("webdriver.gecko.driver", "D:\\\\Workspace\\MyStudy_Space\\Drivers\\geckodriver.exe");
			FirefoxProfile fp = new FirefoxProfile();

			fp.setPreference("browser.startup.homepage", "http://yahoo.com");
			fp.setPreference("network.proxy.http", "20.201.110.111");
			fp.setPreference("network.proxy.http_port", "80");
			fp.setPreference("network.proxy.proxy.type", "1");

			wd = new FirefoxDriver(fp);
		}

		else if (Browser.equalsIgnoreCase("ie"))

		{
			System.setProperty("webdriver.ie.driver", "D:\\\\Workspace\\MyStudy_Space\\Drivers\\IEDriverServer.exe");
			wd = new InternetExplorerDriver();
		} else if (Browser.equalsIgnoreCase("chrome"))

		{
			System.setProperty("webdriver.chrome.driver", "D:\\\\Workspace\\MyStudy_Space\\Drivers\\chromedriver.exe");
			wd = new ChromeDriver();
		}

		else if (Browser.equalsIgnoreCase("phantomjs"))

		{
			System.setProperty("phantomjs.binary.path", "C:\\Selenium\\phantomjs.exe");
			wd = new PhantomJSDriver();
		}

		else {
			System.out.println("The Specified Browser Does Not Exists...");
		}

		wd.manage().window().maximize();

		// Implicit wait specified for the WebDriver
		wd.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		// Page load timeout for the WebDriver
		wd.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

		// Script timeout for the WebDriver

		wd.manage().timeouts().setScriptTimeout(1, TimeUnit.MINUTES);

		return wd;
	}

}
