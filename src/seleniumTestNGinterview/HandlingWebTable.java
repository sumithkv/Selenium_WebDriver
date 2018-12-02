package seleniumTestNGinterview;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;

public class HandlingWebTable {

	WebDriver driver;

	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", "D:\\\\Workspace\\MyStudy_Space\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

	@Test
	public void testMethod() {
		driver.get("http://www.qavalidation.com/p/demo.html");
		driver.manage().window().maximize();
		
		WebElement Table_1 = driver.findElement(By.id("table01"));
		List<WebElement> Rows = Table_1.findElements(By.tagName("tr"));
		System.out.println("No. of rows: " + Rows.size());

		// to print all the values inside the table
		for (WebElement Row : Rows) {
			List<WebElement> Cols = Row.findElements(By.tagName("td"));
			for (WebElement Cel : Cols) {
				System.out.print(Cel.getText() + " ");
			}
			System.out.println();
		}

		// Find a matching text in a table and perform some action
		mainloop: for (int i = 0; i < Rows.size(); i++) {
			List<WebElement> Cols = Rows.get(i).findElements(By.tagName("td"));
			for (int j = 0; j < Cols.size(); j++) {
				if (Cols.get(j).getText().contains("TFS")) {
					Cols.get(0).findElement(By.tagName("input")).click();
					break mainloop;
				}
			}
		}
	}

}
