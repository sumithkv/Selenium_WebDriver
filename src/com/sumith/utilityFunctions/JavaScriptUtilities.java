package com.sumith.utilityFunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class JavaScriptUtilities {

	WebDriver wd = SetWebDriverBase.setWebDriver();
	JavascriptExecutor jse = (JavascriptExecutor) wd;

	// Setting attribute for a WebElement
	public void setAttribute() {
		WebElement element = wd.findElement(By.xpath("Click ME"));
		jse.executeScript("arguments[0].setAttribute('attr', '10')", element);
	}

	// Clicking a WebElement
	public void clickElement() {
		WebElement element = wd.findElement(By.xpath("Click ME"));
		jse.executeScript("arguments[0].click();", element);
	}

	// Typing into a Text Box
	public void inputTextBox() {
		jse.executeScript("document.getElementById(‘id of the element’).value=’test data’;");
	}

	// Get Text using Java Script
	@SuppressWarnings("unused")
	public void getText() {
		String text = (String) (jse).executeScript("return document.getElementById('EleID').value", "");
	}

	// To select a Check Box || Radio Button
	public void selectCheckBox() {
		WebElement checkBox = wd.findElement(By.xpath("//input[@type='checkbox']"));
		jse.executeScript("arguments[0].checked=true;", checkBox);
	}

	// To get the selected value from a Check Box || Radio Button
	public String getSelectedValue() {
		WebElement radioButton = wd.findElement(By.xpath("//input[@type='radio']"));
		String value = (String) jse.executeScript("if(radioButton.checked) return arguments[0].value", radioButton);
		return value;
	}

	// Enable/Disable Text box during Selenium Webdriver Test Case Execution
	public void enableDisableTestBox() {
		String todisable = "document.getElementsByName('fname')[0].setAttribute('disabled', '');";
		jse.executeScript(todisable);
		String toenable = "document.getElementsByName('lname')[0].removeAttribute('disabled');";
		jse.executeScript(toenable);
	}

	// Scrolling down until the WebElement is in the view
	public void scrollElementIntoView() {
		WebElement element = wd.findElement(By.xpath("//input[contains(@value,'Save')]"));
		jse.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	// Scroll to bottom of the web page
	public void scrollingToBottomofAPage() {
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	// Scroll by coordinates of the page
	public void scrollingByCoordinatesofAPage() {
		jse.executeScript("window.scrollBy(0,500)");

	}

	// To verify that the Web page is fully loaded
	public void waitForPageLoad() throws InterruptedException {
		while (jse.executeScript("return document.readyState").equals("complete")) {
			System.out.println("Page is loading... Please wait...");
		}
	}

	// Javascript to upload a file
	public void fileUpload() {
		String script = "document.getElementById('fileName').value='" + "C:\\\\temp\\\\file.txt" + "';";
		jse.executeScript(script);
	}

	// Asserting Color of an element
	public void assertColor() {
		String jsColor = "return window.document.getElementById('gbx3').getPropertyValue('color')";
		String color = (String) jse.executeScript(jsColor);
		Assert.assertTrue(color.equals("rgb(34, 34, 34)"));
	}

}
