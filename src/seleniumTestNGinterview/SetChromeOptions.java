package seleniumTestNGinterview;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SetChromeOptions {

	public static void setOptions() {
		ChromeOptions co = new ChromeOptions();
		co.setBinary("//patch");
		co.addExtensions(new File("//path/"));
		Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put( "credentials_enable_service", false );
        prefs.put( "profile.password_manager_enabled", false );
        co.setExperimentalOption( "prefs", prefs );
        co.addArguments( "disable-infobars" );
        co.addArguments( "--disable-gpu" );
        co.addArguments( "--ignore-certificate-errors" );
    	co.addArguments("start-maximized");
		co.addArguments("--disable-save-password-bubble");

		WebDriver driver = new ChromeDriver(co);
		driver.get("www.test.com");
	}

}
