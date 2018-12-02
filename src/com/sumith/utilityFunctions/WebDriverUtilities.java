package com.sumith.utilityFunctions;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.RenderedWebElement;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.html5.BrowserConnection;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.os.WindowsUtils;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

public class WebDriverUtilities {

	// This class contains Selenium Wrapper Classes. All the Selenium functions
	// are wrapped into Java functions.

	WebDriver wd = SetWebDriverBase.setWebDriver();

	// For opening the Web Browser
	public void open(String URL) {
		wd.get(URL);
		wd.manage().window().maximize();
	}

	// For navigating to a URL
	public void navigateURL(String URL) {
		wd.navigate().to(URL);
	}

	// For closing the single Web Browser
	public void close() {
		wd.close();
	}

	// For closing the entire Web Driver instance
	public void quit() {
		wd.quit();
	}

	// For getting the Browser Header
	public String getBrowserTitle() {
		return wd.getTitle();
	}

	// For getting the Current URL
	public String getCurrentURL() {
		return wd.getCurrentUrl();
	}

	// For clearing the Browser Cookies
	public void clearCookies() {
		wd.manage().deleteAllCookies();
	}

	// For selecting a Check Box
	public void selectCheckBox(WebElement ele, String str) {
		if (!ele.isSelected() && str.equalsIgnoreCase("true"))
			ele.click();
		if (ele.isSelected() && str.equalsIgnoreCase("false"))
			ele.click();
		else {
			System.out.println("You have specified an invalid attribute...");
		}
	}

	// For inputing value into a text field
	public void inputText(WebElement ele, String txt) {
		ele.clear();
		ele.sendKeys(txt);
	}

	// Verifying Alert
	public void verifyAlert(String act) {
		if (isAlertPresent()) {
			Alert alrt = wd.switchTo().alert();
			System.out.println(alrt.getText());
			if (act.equalsIgnoreCase("accept"))
				alrt.accept();
			if (act.equalsIgnoreCase("dismiss"))
				alrt.dismiss();
		} else {
			System.out.println("No ALERT is present...");
		}
		;
	}

	// For checking the presence of an Alert
	public boolean isAlertPresent() {
		try {
			wd.switchTo().alert();
			return true;
		} catch (NoAlertPresentException E) {
			E.getMessage();
			return false;
		}
	}

	// WebDriverWait wait = new WebDriverWait(driver, 10);
	// Alert alert = wait.until(ExpectedConditions.alertIsPresent());

	// Checking for the Presence of an Element

	public boolean isElementPresent(By by) {
		try {
			wd.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	// To check the presence of a pop up Window

	public void testWindowPopup() {
		// Save the WindowHandle of Parent Browser Window
		String parentWindowId = wd.getWindowHandle();

		WebElement helpButton = wd.findElement(By.id("helpbutton"));
		helpButton.click();
		try {
			// Switch to the Help Popup Browser Window
			wd.switchTo().window("HelpWindow");
		} catch (NoSuchWindowException e) {
			e.printStackTrace();
		}

		Assert.assertTrue(wd.getTitle().equals("Help"));
		// Close the Help Popup Window
		wd.close();
		// Move back to the Parent Browser Window
		wd.switchTo().window(parentWindowId);
		// Verify the driver context is in Parent Browser Window
		Assert.assertTrue(wd.getTitle().equals("Build my Car -Configuration"));

	}

	// For getting the WebElement Text
	public String getText(WebElement ele) {
		return (String) ele.getText();
	}

	// Selecting a Sub Menu by hovering the mouse pointer over a Main Menu

	public void selectSubMenu(WebElement menu, WebElement subMenu) {
		Actions act = new Actions(wd);
		act.moveToElement(menu).moveToElement(subMenu).click().build().perform();
	}

	// For selecting multiple items

	public void multiSelect(WebElement ele1, WebElement ele2) {
		Actions builder = new Actions(wd);

		builder.keyDown(Keys.CONTROL).click(ele1).click(ele2).keyUp(Keys.CONTROL);

		Action selectMultiple = builder.build();
		selectMultiple.perform();

	}

	// For drag and drop

	public void dragAndDrop(WebElement From, WebElement To) {
		Actions builder = new Actions(wd);

		Action dragAndDrop = builder.clickAndHold(From).moveToElement(To).release(To).build();

		dragAndDrop.perform();
	}

	// Taking the screenshot of a failed test case

	public void takeScreenShot(String testName) throws Exception {
		File fil = new File("C:\\Workspaces\\MyStudy_Space\\SeleniumWebDriver\\errorImages\\" + testName + ".png");
		RemoteWebDriver compatibleDriver = (RemoteWebDriver) wd;
		File screenshotFile = ((TakesScreenshot) compatibleDriver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotFile, fil);
	}

	// To check whether the browser is online or not

	public void testBrowserConnection() {
		BrowserConnection bc = (BrowserConnection) wd;
		Assert.assertTrue(bc.isOnline());
	}

	// To turn off the browser connection (Making it offline)

	public void testTurnOffConnectivity() {
		BrowserConnection networkAwareDriver = (BrowserConnection) wd;
		networkAwareDriver.setOnline(false);
	}

	// To get the attribute of an element

	public String getElementAttribute(WebElement ele, String Attr) {
		Attr = "href"; // for getting the links
		return ele.getAttribute(Attr);
	}

	// To get the CSS value of an element

	public String getElementsCSSvalue(WebElement ele, String css) {
		css = "color";
		return ele.getCssValue(css);
	}

	// Asserting Color of an element

	public void assertColor(WebElement ele) {
		String eleColor = ele.getCssValue("color");
		String backGrndColor = ele.getCssValue("back-ground-color");

		Assert.assertEquals(eleColor, "#FFEE53");
		Assert.assertEquals(backGrndColor, "rgb(181, 79, 80)");
	}

	// To select multiple rows from different positions of a table

	public void selectMultipleRows() {
		List<WebElement> tableRows = wd.findElements(By.xpath("//table[@class='Students']/tbody/tr"));
		Actions builder = new Actions(wd);

		builder.click(tableRows.get(1)).keyDown(Keys.CONTROL).click(tableRows.get(3)).click(tableRows.get(4))
				.keyUp(Keys.CONTROL).build().perform();

	}

	// Executing Java Script code

	public void testJavaScriptCalls() throws Exception {
		wd.get("http://www.google.com");
		JavascriptExecutor js = (JavascriptExecutor) wd;
		String title = (String) js.executeScript("return document.title");
		Assert.assertEquals("Google", title);
		long links = (Long) js.executeScript("var links = document.getElementsByTagName('A'); return links.length");
		Assert.assertEquals(42, links);
		wd.close();
	}

	// To kil a Windows process

	public void killProcess(String processName) {
		processName = "Firefox.exe";
		WindowsUtils.killByName(processName);
		WindowsUtils.killPID("2323");
	}

	// Reading ToolTip text
	public String getToolTip(By locator) {
		String tooltip = wd.findElement(locator).getAttribute("title");
		return tooltip;
	}

	// To get the currently focused element

	public WebElement getActiveElement() {
		return wd.switchTo().activeElement();
		// Compare the result of this with current webelement to check whether both are
		// equal or not
	}

	// To know the syntax of Fluent Wait

	@SuppressWarnings("unused")
	public void testFluentWait() {
		Wait<WebDriver> fw = new FluentWait<WebDriver>(wd).withTimeout(30, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
	}

	// To Check If An Element Is Visible With WebDriver
	public boolean isElementVisible() {
		WebElement element = wd.findElement(By.id("element-id"));
		if (element instanceof RenderedWebElement) {
			System.out.println("Element visible");
			return true;
		} else {
			System.out.println("Element Not visible");
			return false;
		}
	}

	// Check If An Element Exists
	public boolean isElementExists() {
		if (wd.findElements(By.id("element-id")).size() != 0) {
			return true;
		} else {
			return false;
		}
	}
	
	// Open a link in new Tab
	public void openLinkNewTab()
	{
		String kt = Keys.chord(Keys.CONTROL, Keys.ENTER);
		wd.findElement(By.linkText("Home")).sendKeys(kt);
	}
	
	// Open a link in new Window
	public void openLinkNewWindow()
	{
		String kw = Keys.chord(Keys.SHIFT, Keys.ENTER);
		wd.findElement(By.linkText("Product Category")).sendKeys(kw);
	}

}
