package seleniumTestNGinterview;

import java.io.BufferedReader;
import java.io.FileReader;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class ReadingDataFromCSV {

	@BeforeClass
	public void beforeClass() {
		System.out.println("***** BEFORE CLASS *****");
	}

	@AfterClass
	public void afterClass() {
		System.out.println("***** AFTER CLASS *****");
	}

	@BeforeTest
	public void beforeTest() {
		System.out.println("***** BEFORE TEST *****");
	}

	@AfterTest
	public void afterTest() {
		System.out.println("***** AFTER TEST *****");
	}

	@BeforeSuite
	public void beforeSuite() {
		System.out.println("***** BEFORE SUITE *****");
	}

	@AfterSuite
	public void afterSuite() {
		System.out.println("***** AFTER SUITE *****");
	}

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("***** BEFORE METHOD *****");
	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("***** AFTER METHOD *****");
	}

	@Test(dataProvider = "CSVdataProvider")
	public void testMethod(String n, String Name, String Gender, String Mobile) {
		System.out.println("***** STARTED CSV Data Provider TEST *****");

		System.out.println(n);
		System.out.println(Name);
		System.out.println(Gender);
		System.out.println(Mobile);

		System.out.println("***** FINISHED TEST *****");
	}

	// Data Provider Declaration
	@DataProvider
	public Object[][] CSVdataProvider() throws Exception {
		return getTestDataCSV("D:\\Workspace\\MyStudy_Space\\FilePath\\TestDataCSV.csv");
	}

	// Method for reading data from CSV file
	public Object[][] getTestDataCSV(String FileName) throws Exception {
		int lines = 0;
		String data = null;
		int i = 0;

		// This is for counting the number of lines in the file. Hence we can declare a
		// 2 dimensional string array
		BufferedReader br = new BufferedReader(new FileReader(FileName));
		while ((br.readLine()) != null) {
			lines++;
		}
		br.close();
		String[][] testdata = new String[lines][4];

		BufferedReader br2 = new BufferedReader(new FileReader(FileName));
		while ((data = br2.readLine()) != null) {

			String[] s = data.split(",");

			testdata[i][0] = s[0];
			testdata[i][1] = s[1];
			testdata[i][2] = s[2];
			testdata[i][3] = s[3];

			i = i + 1;

		}

		br2.close();

		return testdata;

	}
}
