package seleniumTestNGinterview;

import org.testng.annotations.Test;
import com.sumith.utilityFunctions.SetWebDriverBase;
import org.testng.annotations.BeforeMethod;
import java.util.Iterator;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class HandlingMultipleWindows {

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

	// FIRST EXAMPLE

	@Test(enabled = true)
	public void testWindows1() throws InterruptedException {

		System.out.println(" ***** STARTED 11111 TEST ***** ");

		wd.get("http://www.naukri.com/");
		Thread.sleep(5000);

		String mainWindow = wd.getWindowHandle();
		System.out.println("PARENT WINDOW HANDLE IS : " + mainWindow);

		Set<String> handler = wd.getWindowHandles();

		// Handler will have all the three window handles

		for (String handlesname : handler) {
			wd.switchTo().window(handlesname);

			// It will get the Title and Window Handle of each window,
			System.out.println();
			System.out.println("TITLE IS : " + wd.getTitle());
			System.out.println("WINDOW HANDLE IS : " + handlesname);

			// This will check Windows and not. If it is not the parent window it will close
			// the child window
			if (!handlesname.contains(mainWindow)) {
				System.out.println("CLOSING THE WINDOW : " + wd.getTitle() + " ---> " + handlesname);
				wd.close();
			} else {
				System.out.println("it is the main window");
				wd.switchTo().defaultContent();
			}
			wd.switchTo().window(mainWindow);
			System.out.println("MAIN WINDOW TITLE IS : " + wd.getTitle());
		}
		System.out.println(" ***** STOPPED 11111 TEST ***** ");
	}

	// SECOND EXAMPLE

	@Test(enabled = true)
	public void testWindows2() throws InterruptedException {

		System.out.println(" ***** STARTED 22222 TEST ***** ");

		wd.get("http://www.monsterindia.com/");

		Thread.sleep(5000);

		// Get and store both window handles in array
		Set<String> AllWindowHandles = wd.getWindowHandles();
		String window1 = (String) AllWindowHandles.toArray()[0];
		System.out.print("window1 handle code = " + AllWindowHandles.toArray()[0]);
		String window2 = (String) AllWindowHandles.toArray()[1];
		System.out.print("\nwindow2 handle code = " + AllWindowHandles.toArray()[1]);

		// Switch to window2(child window) and performing actions on it.
		wd.switchTo().window(window2);

		WebElement test = wd.findElement(By.xpath(
				"//div[@id='header_id']//li[@class='left']//a[@href='http://my.monsterindia.com/my_monster.html']"));
		test.click();

		wd.findElement(By.xpath("//input[@name='fname']")).sendKeys("My Name");
		wd.findElement(By.xpath("//input[@value='Bike']")).click();
		wd.findElement(By.xpath("//input[@value='Car']")).click();
		wd.findElement(By.xpath("//input[@value='Boat']")).click();
		wd.findElement(By.xpath("//input[@value='male']")).click();
		Thread.sleep(5000);

		// Switch to window1(parent window) and performing actions on it.
		wd.switchTo().window(window1);
		wd.findElement(By.xpath("//option[@id='country6']")).click();
		wd.findElement(By.xpath("//input[@value='female']")).click();
		wd.findElement(By.xpath("//input[@value='Show Me Alert']")).click();
		wd.switchTo().alert().accept();
		Thread.sleep(5000);

		// Once Again switch to window2(child window) and performing actions on it.
		wd.switchTo().window(window2);
		wd.findElement(By.xpath("//input[@name='fname']")).clear();
		wd.findElement(By.xpath("//input[@name='fname']")).sendKeys("Name Changed");
		Thread.sleep(5000);
		wd.close();

		// Once Again switch to window1(parent window) and performing actions on it.
		wd.switchTo().window(window1);
		wd.findElement(By.xpath("//input[@value='male']")).click();
		Thread.sleep(5000);

		System.out.println(" ***** STOPPED 22222 TEST ***** ");
	}

	// THIRD EXAMPLE

	@Test(enabled = true)
	public void testWindows3() throws InterruptedException {

		System.out.println(" ***** STARTED 33333 TEST ***** ");

		// Load app
		wd.get("http://www.popuptest.com/popuptest1.html");

		Thread.sleep(5000);

		// It will return the parent window name as a String
		String parent = wd.getWindowHandle();

		// This will return the number of windows opened by Webdriver and will return
		// Set of St//rings
		Set<String> s1 = wd.getWindowHandles();

		// Now we will iterate using Iterator
		Iterator<String> I1 = s1.iterator();

		while (I1.hasNext()) {

			String child_window = I1.next();

			System.out.println("Child Window is : " + child_window);

			// Here we will compare if parent window is not equal to child window then we
			// will close

			if (!parent.equals(child_window)) {
				wd.switchTo().window(child_window);

				System.out.println();
				System.out.println("TITLE IS : " + wd.switchTo().window(child_window).getTitle());
				System.out.println("CURRENT URL IS : " + wd.getCurrentUrl());
				wd.close();
			}
			wd.switchTo().window(parent);
		}

		System.out.println(" ***** STOPPED 33333 TEST ***** ");

	}
}
