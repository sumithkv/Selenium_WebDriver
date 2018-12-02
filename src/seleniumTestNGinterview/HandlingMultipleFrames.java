package seleniumTestNGinterview;

import org.testng.annotations.Test;

import com.sumith.utilityFunctions.SetWebDriverBase;

import org.testng.annotations.BeforeMethod;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class HandlingMultipleFrames {
	
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
  
  @Test (enabled = true)
  public void testFrames1() {
	  
	  /*times of india website - multiple frames*/

	  wd.get("http://timesofindia.indiatimes.com/");

	  List<WebElement> frms= wd.findElements(By.tagName("iframe"));   //Frame List
	  System.out.println(frms.size());
	  for(int i=0;i<frms.size();i++)
	  {
	   System.out.println(frms.get(i).getAttribute("src"));
	  }
  }
  
  @Test (enabled = true)
  public void testFrames2() {
	  
	  List<WebElement> frms= wd.findElements(By.tagName("iframe"));
	  System.out.println(frms.size());
	  wd.switchTo().frame(0);
	  wd.findElement(By.id("clicktripad")).click();
  }
  
  @Test (enabled = true)
  public void testFrames3() {
  }

}
