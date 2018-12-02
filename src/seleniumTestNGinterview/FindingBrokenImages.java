package seleniumTestNGinterview;

import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.sumith.utilityFunctions.SetWebDriverBase;

public class FindingBrokenImages {

	static int invalidimg;
	static WebDriver driver;

	public static void main(String[] args) {
		try {
			driver = SetWebDriverBase.setWebDriver();
			driver.get("http://ruchi-myseleniumblog.blogspot.in");
			invalidimg = 0;
			List<WebElement> allImages = driver.findElements(By.tagName("img"));
			System.out.println("Total  images are " + allImages.size());
			for (WebElement ele : allImages) {
				if (ele != null) {
					verifyimgActive(ele);
				}
			}

			System.out.println("Total invalid images are " + invalidimg);
			driver.quit();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	public static void verifyimgActive(WebElement img) {

		try {
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(img.getAttribute("src"));
			HttpResponse response = client.execute(request);
			if (response.getStatusLine().getStatusCode() != 200)
				System.out.println("Total images " + img.getAttribute("src") + " is INVALID");
			invalidimg++;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
