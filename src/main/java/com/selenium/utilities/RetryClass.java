/***Utilities containing common reusable functions */
package com.selenium.utilities;

import org.testng.ITestResult;
import org.testng.IRetryAnalyzer;

/**
 * This class is to retry failed test cases.
 */
public class RetryClass implements IRetryAnalyzer {

	private int count = 0;

	private int maxCount = 3;

	@Override
	/**
	 * This method reruns the test methods in case of failure
	 */
	public boolean retry(ITestResult result) {

		if (count < maxCount) {

			count++;

			return true;
		}
		return false;

	}

}
