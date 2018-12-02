package seleniumTestNGinterview;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class HandlingMultipleTabs {
	@SuppressWarnings({ "unused", "null" })
	@Test
	public void fn_MultipleTabs() throws Exception {

		WebDriver driver = null;

		// The code below will open the link in new Tab.
		String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL, Keys.RETURN);
		driver.findElement(By.linkText("urlLink")).sendKeys(selectLinkOpeninNewTab);

		// The code below will open empty new Tab.
		String selectLinkOpeninNewTab1 = Keys.chord(Keys.CONTROL, "t");
		driver.findElement(By.linkText("urlLink")).sendKeys(selectLinkOpeninNewTab);

		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
		String selectLinkOpeninNewTab2 = Keys.chord(Keys.CONTROL, "t");
		driver.findElement(By.cssSelector("body")).sendKeys(selectLinkOpeninNewTab);

		// Robot class to open tab
		driver.get("http://www.google.com");
		// Use robot class to press Ctrl+t keys
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_T);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_T);

		// Switch focus to new tab
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));

		// Launch URL in the new tab
		driver.get("http://google.com");

		Actions newTab = new Actions(driver);
		WebElement link = driver.findElement(By.xpath("//xpath of the element"));
		// Open the link in new window
		newTab.contextClick(link).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build()
				.perform();

		// Switch to the old tab using Ctrl + Tab:
		String mainWindowHandle = null;
		driver.switchTo().window(mainWindowHandle);
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "\t");
		driver.switchTo().defaultContent();

		// For navigating left to right side:
		Actions action = new Actions(driver);
		action.keyDown(Keys.CONTROL).sendKeys(Keys.TAB).build().perform();

		// For navigating right to left:
		Actions action1 = new Actions(driver);
		action1.keyDown(Keys.CONTROL).keyDown(Keys.SHIFT).sendKeys(Keys.TAB).build().perform();

		// Used Robot class to perform ALT + SPACE + 'c' keypress event.
		Robot rb = new Robot();
		rb.keyPress(KeyEvent.VK_ALT);
		rb.keyPress(KeyEvent.VK_SPACE);
		rb.keyPress(KeyEvent.VK_C);

	}
}
