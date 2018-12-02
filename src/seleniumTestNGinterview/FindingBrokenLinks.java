package seleniumTestNGinterview;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FindingBrokenLinks {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {

		System.setProperty("webdriver.chrome.driver", "D:\\\\Workspace\\MyStudy_Space\\Drivers\\chromedriver.exe");
		WebDriver wd = new ChromeDriver();

		wd.get("http://www.toolsqa.com/automation-practice-switch-windows/");

		List<WebElement> MyLinks = findBrokenLinks(wd);

		System.out.println("Total number of elements found " + MyLinks.size());

		for (WebElement element : MyLinks) {
			try {
				System.out.println("URL: " + element.getAttribute("href") + " returned "
						+ isBrokenLink(new URL(element.getAttribute("href"))));
			} catch (Exception exp) {
				System.out.println(
						"At " + element.getAttribute("innerHTML") + " Exception occured -&gt; " + exp.getMessage());
			}
		}
	}

	// This method will return all the HTTP Links present on the page
	@SuppressWarnings("rawtypes")
	public static List findBrokenLinks(WebDriver wd) {
		// The below code will find all the links including image links
		List<WebElement> link = wd.findElements(By.tagName("a"));
		List<WebElement> img = wd.findElements(By.tagName("img"));
		List<WebElement> all = new ArrayList<WebElement>();
		all.addAll(link);
		all.addAll(img);

		// The below code will separate the links with HTTP attribute
		List<WebElement> httpLinks = new ArrayList<WebElement>();
		for (WebElement we : all) {
			if (we.getAttribute("http") != null) {
				httpLinks.add(we);
			}
		}

		return httpLinks;
	}

	// This code will be validate whether the link is BROKEN or NOT
	public static String isBrokenLink(URL url) throws Exception {
		HttpURLConnection http = (HttpURLConnection) url.openConnection();

		try {
			http.connect();
			String response = http.getResponseMessage();
			http.disconnect();
			return response;
		} catch (Exception E) {
			return E.getMessage();
		}

	}

	// This code is for validating the response
	public static boolean getResponseCode(String urlString) {
		boolean isValid = false;
		try {
			URL u = new URL(urlString);
			HttpURLConnection h = (HttpURLConnection) u.openConnection();
			h.setRequestMethod("GET");
			h.connect();
			System.out.println(h.getResponseCode());
			if (h.getResponseCode() != 404) {
				isValid = true;
			}
			h.disconnect();
		} catch (Exception e) {

		}
		return isValid;
	}

}
