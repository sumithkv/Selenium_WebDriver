package seleniumTestNGinterview;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class SetFirefoxProfile {
	

	@SuppressWarnings("unused")
	public static void setProfile()
	{
		FirefoxProfile fp = new FirefoxProfile();
		fp.setPreference("browser.startup.homepage", "http://yahoo.com");
		fp.setPreference("network.proxy.http", "20.201.110.111");
		fp.setPreference("network.proxy.http_port", "80");
		fp.setPreference("network.proxy.proxy.type", "1");
		
		// Changing the default File downloading location
		String FilePath="E:\\ParentFolder\\ChildFolder";
		fp.setPreference("browser.download.folderList", 2);
		fp.setPreference("browser.download.manager.showWhenStarting", false);
		fp.setPreference("browser.download.manager.alertOnEXEOpen", false);
		fp.setPreference("browser.download.dir", "FilePath");
		
		WebDriver driver1 = new FirefoxDriver(fp);
		
		// OR WE CAN USE THE BELOW CODE ALSO FOR SETTING FIREFOX PROFILE.
		//This will be used if setting the Firefox profile through "Firefox.exe -p" command
		ProfilesIni profile = new ProfilesIni();
		FirefoxProfile myprofile = profile.getProfile("AutoFireFoxProfile");
 		WebDriver driver2 = new FirefoxDriver(myprofile);

	}
	
	public static void setAddOn() throws IOException
	{
		FirefoxProfile fp = new FirefoxProfile();
		fp.addExtension(new File("firebug-1.8.1.xpi"));
	}
	
	@SuppressWarnings("unused")
	public static void setProxy()
	{
		String PROXY = "localhost:8080";
		
		Proxy proxy = new Proxy();
		proxy.setHttpProxy(PROXY).setFtpProxy(PROXY).setSslProxy(PROXY);
		
		proxy.setSocksUsername("SSSLL277"); 
		proxy.setSocksPassword("password");
		
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(CapabilityType.PROXY, proxy);
		
		WebDriver driver3 = new FirefoxDriver(cap);
		
		// OR WE CAN USE THE BELOW CODE ALSO FOR SETTING PROXY
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("network.proxy.http", "20.201.110.111");
		profile.setPreference("network.proxy.http_port", "80");
		profile.setPreference("network.proxy.proxy.type", "1");
		
		WebDriver driver4 = new FirefoxDriver(profile);

	}
}
