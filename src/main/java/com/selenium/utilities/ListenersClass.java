/***Utilities containing common reusable functions */
package com.selenium.utilities;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

/**
 * This class is a re run utility
 */
public class ListenersClass extends TestListenerAdapter {

	@Override

	/**
	 * This method gets the results of test methods and skips the failed ones
	 * until the maximum number of retries are done.
	 */
	public void onTestFailure(ITestResult result) {

		Reporter.setCurrentTestResult(result);

		if (result.getMethod().getRetryAnalyzer().retry(result))
			result.setStatus(ITestResult.SKIP);

	}

}