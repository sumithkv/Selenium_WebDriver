package com.sumith.utilityFunctions;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class TestNgSuitClass {

	@BeforeSuite
	public void beforeSuite() {

		System.out.println(">>>>> BEFORE SUITE <<<<<");
	}

	@AfterSuite
	public void afterSuite() {

		System.out.println(">>>>> AFTER SUITE <<<<<");
	}

}
