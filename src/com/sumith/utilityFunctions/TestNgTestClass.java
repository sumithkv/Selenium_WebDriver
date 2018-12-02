package com.sumith.utilityFunctions;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class TestNgTestClass {

	@BeforeTest
	public void beforeTest() {

		System.out.println("<<<<< BEFORE TEST >>>>>");

	}

	@AfterTest
	public void afterTest() {

		System.out.println("<<<<< AFTER TEST >>>>>");

	}

}
