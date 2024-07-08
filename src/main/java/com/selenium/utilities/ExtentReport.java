/***Utilities containing common reusable functions */
package com.selenium.utilities;

import java.io.File;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.selenium.setup.DriverSetup;

/**
 * This class is for creating test reports
 */

public class ExtentReport extends DriverSetup{

	static ExtentTest test;
	static ExtentReports extentReport;
	static ExtentSparkReporter sparkReporter;
	public static ExtentTest extentTest;
	static int i = 1;

	/** This method creates a report file */
	public static void startReport() {
		String path = System.getProperty("user.dir") + "\\Test Reports\\";

		sparkReporter = new ExtentSparkReporter(path);
		sparkReporter.config().setDocumentTitle("Urban Ladder Automation");
		sparkReporter.config().setReportName("Urban Ladder Extent Report");
		sparkReporter.config().setTheme(com.aventstack.extentreports.reporter.configuration.Theme.DARK);

		extentReport = new ExtentReports();

		extentReport.attachReporter(sparkReporter);
		extentReport.setSystemInfo("Application Name", "ExtentReport");
		extentReport.setSystemInfo("Platform", System.getProperty("os.name"));
		extentReport.setSystemInfo("Environment", "QA");

		System.out.println("Extent Report blank file created");
	}

	/** This method creates the test log for each test case that is executed */
	public static void beforeTest(Method method) {
		String methodName = method.getName();
		extentTest = extentReport.createTest(methodName);
	}

	/**
	 * This method characterizes the test results and displays them in the
	 * report
	 */
	public static void afterTest(ITestResult result) {
		String methodName = result.getName();
		extentTest.createNode(methodName);

		if (result.getStatus() == ITestResult.FAILURE) {
			extentTest.log(Status.FAIL, MarkupHelper.createLabel(methodName + " - Test Case Failed", ExtentColor.RED));
			extentTest.log(Status.INFO, methodName + " was executed on the " + browserName + " browser.");
			extentTest.log(Status.FAIL,
					MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
			extentTest.fail(methodName + "Test step Failed");
		}

		if (result.getStatus() == ITestResult.SKIP) {
			extentTest.log(Status.SKIP,
					MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));
			extentTest.log(Status.INFO, methodName + " was executed on the " + browserName + " browser.");
			extentTest.skip(methodName + "Test step Skipped");
		}

		if (result.getStatus() == ITestResult.SUCCESS) {
			extentTest.log(Status.PASS,
					MarkupHelper.createLabel(result.getName() + " Test Case PASSED", ExtentColor.GREEN));
			extentTest.log(Status.INFO, methodName + " was executed on the " + browserName + " browser.");
			extentTest.pass(methodName + "Test step Passed");
		}

	}

	/** This method creates the .html file in the given directory */
	public static void endReport() {
		extentReport.flush();
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy_HH-mm-ss");
		Date date = new Date();
		String filePathdate = dateFormat.format(date).toString();
		String actualReportPath = System.getProperty("user.dir") + "/Test Reports/" + "index.html";
		new File(actualReportPath).renameTo(
				new File(System.getProperty("user.dir") + "/Test Reports/" + "UrbanLadder_" + filePathdate + ".html"));

		System.out.println("Extent Report file logged all the outputs from test cases");
	}

}