package com.sumith.utilityFunctions;

import java.util.Set;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class TestNgTestListener extends TestListenerAdapter {

	static Logger logger = Logger.getLogger(TestNgTestListener.class);
	WebDriverUtilities wu = new WebDriverUtilities();

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		super.onTestStart(result);

		logger.info("----->> Started Test Instance : " + result.getInstanceName());
		logger.info("----->> Started Test Method : " + result.getName());
	}

	@Override
	public void onTestSuccess(ITestResult tr) {
		// TODO Auto-generated method stub
		super.onTestSuccess(tr);

		logger.info("The Test Case: " + tr.getInstanceName() + " is PASSED");
		logger.info("----->> Status of the Test is : " + tr.getStatus());
	}

	@Override
	public void onTestFailure(ITestResult tr) {
		// TODO Auto-generated method stub
		super.onTestFailure(tr);

		logger.info("The Test Case: " + tr.getInstanceName() + " is FAILED");
		logger.info("----->> Status of the Test is : " + tr.getStatus());

		// This method will take the screen shot when the test case fails
		try {
			wu.takeScreenShot(tr.getInstanceName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		// TODO Auto-generated method stub
		super.onTestSkipped(tr);

		logger.info("The Test Case: " + tr.getInstanceName() + " is SKIPPED");
		logger.info("----->> Status of the Test is : " + tr.getStatus());
	}

	@Override
	public void onStart(ITestContext testContext) {
		// TODO Auto-generated method stub
		super.onStart(testContext);

		logger.info(
				"*************************************************************************************************");
		logger.info("Start Time: " + testContext.getStartDate());
		logger.info("Test SUITE Name is: " + testContext.getSuite().getName());
		logger.info("Test TEST Name is: " + testContext.getName());
		logger.info(
				"*************************************************************************************************");
	}

	@Override
	public void onFinish(ITestContext testContext) {
		// TODO Auto-generated method stub
		super.onFinish(testContext);

		logger.info(
				"*************************************************************************************************");
		logger.info("Number of Passed Tests: " + testContext.getPassedTests().size());
		logger.info("Number of Failed Tests: " + testContext.getFailedTests().size());
		logger.info("Number of Skipped Tests: " + testContext.getSkippedTests().size());

		logger.info("&&&&& PASSED TEST NAMES &&&&&");

		Set<ITestResult> s = testContext.getPassedTests().getAllResults();

		int i = 0;
		for (ITestResult r : s) {
			i++;
			logger.info(i + ": " + r.getInstanceName());
		}

		logger.info("&&&&& FAILED TEST NAMES &&&&&");

		Set<ITestResult> f = testContext.getFailedTests().getAllResults();

		int j = 0;
		for (ITestResult r : f) {
			j++;
			logger.info(j + ": " + r.getInstanceName());
		}

		logger.info("Finish Time: " + testContext.getEndDate());
		logger.info(
				"*************************************************************************************************");

	}

}
