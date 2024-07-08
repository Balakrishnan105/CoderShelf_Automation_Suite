/** This package consists of the test scripts for automation testing*/
package com.selenium.testScripts;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.selenium.main.MainClass;
import com.selenium.pages.HomePage;
import com.selenium.setup.DriverSetup;
import com.selenium.utilities.ExcelReadAndWrite;
import com.selenium.utilities.ExtentReport;
import com.selenium.utilities.RetryClass;
import com.selenium.utilities.Screenshots;

/**
 * This class consists of 23 test methods on a one on one relationship with the
 * test cases.
 */

public class TestScript extends DriverSetup {

	/**
	 * This 'Before test method' opens the browser, navigates to the 'Urban
	 * Ladder' web application, and closes the login pop up before the tests.
	 */
	@BeforeTest(alwaysRun = true, groups = { "smoke", "regression"})
	public void beforeTest() {
		DriverSetup.getDriver();
		DriverSetup.getUrl();
		ExtentReport.startReport();
		MainClass.closePopup();
	}

	/**
	 * This 'Before Method' method navigates back to the home page by clicking
	 * on the logo and logs the test names in the report for each method.
	 */
	@BeforeMethod(groups = { "smoke", "regression" })
	public void beforeEachMethod(Method method) {
		MainClass.clickOnLogo();
		ExtentReport.beforeTest(method);
	}

	/**
	 * This 'After test' method closes the browser and ends the report logging
	 */
	@AfterTest(groups = { "smoke", "regression" })
	public void afterTest() {
		DriverSetup.closeBroswer();
		ExtentReport.endReport();
	}

	/**
	 * This 'After Method' test method logs the results after performing the
	 * each test
	 */
	@AfterMethod(groups = { "smoke", "regression" })
	public void afterEachMethod(ITestResult result) {
		ExtentReport.afterTest(result);
	}

	/**
	 * This 'Data provider' method provides the data passed in various test
	 * methods
	 */
	@DataProvider(name = "searchData")
	public Object[][] getdataprovider(Method m) throws EncryptedDocumentException, IOException, InvalidFormatException {
		String path = System.getProperty("user.dir") + "\\Test Data\\Input Data.xls";
		Object[][] Objectdata = null;
		ExcelReadAndWrite excel = new ExcelReadAndWrite(path, "sheet1");
		String data = excel.getCellData(2, 1);
		String data2 = excel.getCellData(1, 1);
		String data3 = excel.getCellData(3, 1);
		ExcelReadAndWrite exceldata = new ExcelReadAndWrite(path, "sheet2");
		String Recipientname = null, Recipientemail = null, name = null, email = null, phone = null;
		Recipientname = exceldata.getCellData(1, 1);
		Recipientemail = exceldata.getCellData(1, 1);
		name = exceldata.getCellData(1, 2);
		email = exceldata.getCellData(1, 3);
		phone = exceldata.getCellData(1, 4);
		if (m.getName().equalsIgnoreCase("searchBoxValidation")
				|| m.getName().equalsIgnoreCase("navigationUsingSearchIconValidation")
				|| m.getName().equalsIgnoreCase("navigationUsingSeacrhIconResultsPageAssertion")
				|| m.getName().equalsIgnoreCase("sortByFilterValidation")
				|| m.getName().equalsIgnoreCase("getStudyChairsNameAndPrice"))

			Objectdata = new Object[][] { { data } };

		else if (m.getName().equalsIgnoreCase("navigationUsingSuggestionsValidation")
				|| m.getName().equalsIgnoreCase("priceFilterValidation")
				|| m.getName().equalsIgnoreCase("storageTypeFilterValidation")
				|| m.getName().equalsIgnoreCase("navigateUsingSuggestionsResultPageAssertion")
				|| m.getName().equalsIgnoreCase("excludeOutofStockFilterValidation")
				|| m.getName().equalsIgnoreCase("getBookshelvesNameAndPrice"))
			Objectdata = new Object[][] { { data2 } };
		else if (m.getName().equalsIgnoreCase("invalidDatasearchBoxValidation"))
			Objectdata = new Object[][] { { data3 } };
		else if (m.getName().equalsIgnoreCase("giftCardsFormValidation"))
			Objectdata = new Object[][] { { Recipientname, Recipientemail, name, email, phone } };

		return Objectdata;
	}

	/**
	 * This test method validates whether the application opens by asserting the
	 * presence of the logo
	 */
	@Test(priority = 0, groups = { "smoke" }, retryAnalyzer = RetryClass.class)
	public void urlValidation() {
		try {
			MainClass.assertLogoVisibility();
			System.out.println("Web application opens and Logo appears");
		} catch (AssertionError e) {
			throw e;
		}
	}

	/**
	 * This test method validates the functionality of the log in icon by
	 * asserting the appearance of the log in pop up after clicking the icon
	 */
	@Test(priority = 1, groups = { "smoke" }, retryAnalyzer = RetryClass.class)
	public void loginIconValidation() {
		MainClass.getLoginPopup();
		try {
			Thread.sleep(15000);
			MainClass.assertLoginPopupVisibility();
			Screenshots.captureScreenshot("Log in/ Sign Up pop up");
			MainClass.closePopup();
			System.out.println("Log In icon validation was successful");
		} catch (AssertionError | InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This test method verifies whether an error message is thrown when an
	 * invalid email id is entered.
	 */
	@Test(priority = 2, retryAnalyzer = RetryClass.class)
	public void invalidEmailLoginErrorMessageValidation() {
		try {
			Properties prop = new Properties();
			String projectpath = System.getProperty("user.dir");
			InputStream input;
			input = new FileInputStream(projectpath + "//Config.properties");
			prop.load(input);
			MainClass.getLoginPopup();
			Thread.sleep(15000);
			String Username = prop.getProperty("invalidemail");
			String Password = prop.getProperty("password");
			MainClass.loginData(Username, Password);
			MainClass.clickSubmitBtn();
			MainClass.assertEmailErrorMessage();
			Screenshots.captureScreenshot("Log in/ Invalid Email address error message");
			System.out.println("Invalid email error message validation was successful");
		} catch (AssertionError | InterruptedException | IOException e) {
			System.out.println("Validation fail");
			e.printStackTrace();
		}
		MainClass.closePopup();
	}

	/**
	 * This test method verifies whether an error message is thrown when an
	 * invalid password is entered.
	 */
	@Test(priority = 3, retryAnalyzer = RetryClass.class)
	public void invalidPasswordloginErrorMessageValidation() {
		Properties prop = new Properties();
		String projectpath = System.getProperty("user.dir");
		try {
			InputStream input = new FileInputStream(projectpath + "//Config.properties");
			prop.load(input);
			MainClass.getLoginPopup();
			Thread.sleep(15000);
			String Username = prop.getProperty("useremail");
			String Password = prop.getProperty("invalidpassword");
			MainClass.loginData(Username, Password);
			MainClass.clickSubmitBtn();
			MainClass.assertPasswordErrorMessage();
			Screenshots.captureScreenshot("Log in/ Invalid password error message");
			System.out.println("Invalid password error message validation was successful");
		} catch (AssertionError | InterruptedException | IOException e) {
			System.out.println("Validation fail");
			e.printStackTrace();
		}
	}

	/**
	 * This test method validates the log in functionality by entering valid
	 * email and password.
	 */
	@Test(priority = 4, groups = { "regression" }, retryAnalyzer = RetryClass.class)
	public void validLoginCredentialsValidation() {
		try {
			Properties prop = new Properties();
			String projectpath = System.getProperty("user.dir");
			FileInputStream input = new FileInputStream(projectpath + "//Config.properties");
			prop.load(input);
			MainClass.getLoginPopup();
			Thread.sleep(15000);
			String Username = prop.getProperty("useremail");
			String Password = prop.getProperty("password");
			MainClass.loginData(Username, Password);
			Thread.sleep(5000);
			MainClass.clickSubmitBtn();
			Assert.assertEquals(true, HomePage.homePage().isDisplayed());
			Screenshots.captureScreenshot("Log in/ Home Page Displayed");
			System.out.println("Login Validation was successful");
		} catch (AssertionError | InterruptedException | IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This test method validates the functionality of the search text box by
	 * entering valid data
	 */
	@Test(priority = 5, dataProvider = "searchData", groups = { "smoke" }, retryAnalyzer = RetryClass.class)
	public void searchBoxValidation(String Productname) {
		try {
			MainClass.toSearch(Productname);
			MainClass.assertSentValue(Productname);
			System.out.println("Search text box Validation was successful");
		} catch (AssertionError e) {
			throw e;
		}
	}

	/**
	 * This test method validates the functionality of the search text box by
	 * entering invalid data
	 */
	@Test(priority = 6, dataProvider = "searchData", retryAnalyzer = RetryClass.class)
	public void invalidDataSearchBoxValidation(String Productname) {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		MainClass.toSearch(Productname);
		MainClass.toClickOnSearchIcon();
		Screenshots.captureScreenshot("Products/ No Products found");
		try {
			MainClass.assertNoProductsFoundMsg();
			System.out.println("Validation of Search text box functionality with invalid data was successful");
		} catch (AssertionError e) {
			throw e;
		}
	}

	/**
	 * This test method validates the functionality of the search icon
	 */
	@Test(priority = 7, dataProvider = "searchData", retryAnalyzer = RetryClass.class)
	public void navigationUsingSearchIconValidation(String Productname) {
		MainClass.toSearch(Productname);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		MainClass.toClickOnSearchIcon();
		System.out.println("Search icon validation successful");
	}

	/**
	 * This test method validates whether the search result matches with the
	 * product searched for, in here 'Study Chairs'
	 */
	@Test(priority = 8, dataProvider = "searchData", retryAnalyzer = RetryClass.class)
	public void navigationUsingSeacrhIconResultsPageAssertion(String Productname) {

		try {
			MainClass.toSearch(Productname);
			MainClass.toClickOnSearchIcon();
			Thread.sleep(3000);
			MainClass.assertTitle("Search Results For 'Study Chairs'");
			System.out.println("Navigation through Search icon assertion successful");
		} catch (AssertionError e) {
			throw e;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This test method validates the functionality of the 'Sort By' filter
	 */
	@Test(priority = 9, dataProvider = "searchData", retryAnalyzer = RetryClass.class)
	public void sortByFilterValidation(String Productname) {

		MainClass.toSearch(Productname);
		MainClass.toClickOnSearchIcon();
		try {
			Thread.sleep(5000);
			MainClass.sortProducts();
			MainClass.assertSortByFilter();
			System.out.println("Sort By filter validation successful");
		} catch (AssertionError | InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This test method validates whether the name and price of the products of
	 * are displayed after applying the filters, in here for 'Study Chairs'.
	 */
	@Test(priority = 10, dataProvider = "searchData", groups = { "regression" }, retryAnalyzer = RetryClass.class)
	public void getStudyChairsNameAndPrice(String Productname) {

		MainClass.toSearch(Productname);
		MainClass.toClickOnSearchIcon();
		MainClass.sortProducts();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		MainClass.getproductnameandprice("Study Chairs");
		String data[] = MainClass.productNameandPriceValidation("Study Chairs");
		if (data[0] != null) {
			System.out.println("Study Chairs name validation successful");
		}
		if (data[1] != null) {
			System.out.println("Study Chairs price validation successful");
		}
	}

	/**
	 * This test methods validates the display of auto suggestions and if the
	 * navigation through auto suggests is possible
	 */
	@Test(priority = 11, dataProvider = "searchData", retryAnalyzer = RetryClass.class)
	public void navigationUsingSuggestionsValidation(String Productname)
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		MainClass.toSearch(Productname);
		MainClass.selectFromSearchSuggestion();
		Thread.sleep(5000);
		System.out.println("Navigation using suggestions validation was Successful");
	}

	/**
	 * This test method validates whether the search result matches with the
	 * product searched for, in here 'Bookshelves'.
	 */
	@Test(priority = 12, dataProvider = "searchData", retryAnalyzer = RetryClass.class)
	public void navigateUsingSuggestionsResultPageAssertion(String Productname) {

		MainClass.toSearch(Productname);
		MainClass.selectFromSearchSuggestion();
		try {
			MainClass.assertTitle("Search Results For 'bookshelves'");
			System.out.println("Suggestions Assertion Successful");
		} catch (AssertionError e) {
			throw e;
		}
	}

	/**
	 * This test method validates the functionality of the 'Price' filter in the
	 * products page
	 */
	@Test(priority = 13, dataProvider = "searchData", retryAnalyzer = RetryClass.class)
	public void priceFilterValidation(String Productname) {

		MainClass.toSearch(Productname);
		MainClass.toClickOnSearchIcon();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		MainClass.setPriceFilter();
		try {
			MainClass.assertPriceFilter();
			System.out.println("Price filter validation was successful");
		} catch (AssertionError e) {
			throw e;
		}
	}

	/**
	 * This test method validates the functionality of the 'Storage' filter in
	 * the products page.
	 */
	@Test(priority = 14, dataProvider = "searchData", retryAnalyzer = RetryClass.class)
	public void storageTypeFilterValidation(String Productname) {
		MainClass.toSearch(Productname);
		MainClass.toClickOnSearchIcon();
		try {
			Thread.sleep(5000);
			MainClass.setStorageFilter();
			Thread.sleep(3000);
			MainClass.assertStorageFilter();
			System.out.println("Storage filter validation was successful");
		} catch (AssertionError | InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This test method validates the functionality of the 'Exclude Out Of
	 * Stock' filter in the products page.
	 */
	@Test(priority = 15, dataProvider = "searchData", retryAnalyzer = RetryClass.class)
	public void excludeOutOfStockFilterValidation(String Productname) {
		try {
			Thread.sleep(2000);
			MainClass.toSearch(Productname);
			MainClass.toClickOnSearchIcon();
			Thread.sleep(3000);
			MainClass.toExcludeOutofStock();
			Thread.sleep(5000);
			MainClass.assertToExcludeOutOfStockCheckBox();
			System.out.println("Exclude out of stock filter validation was successful");
		} catch (AssertionError | InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This test method validates whether the name and price of the products of
	 * are displayed after applying the filters.
	 */
	@Test(priority = 16, dataProvider = "searchData", groups = { "regression" }, retryAnalyzer = RetryClass.class)
	public void getBookshelvesNameAndPrice(String Productname) {
		try {
			MainClass.toSearch(Productname);
			MainClass.toClickOnSearchIcon();
			Thread.sleep(3000);
			MainClass.setPriceFilter();
			Thread.sleep(3000);
			MainClass.setStorageFilter();
			Thread.sleep(3000);
			MainClass.toExcludeOutofStock();
			Thread.sleep(5000);
			MainClass.getproductnameandprice("Bookshelves");
			String data[] = MainClass.productNameandPriceValidation("Bookshelves");
			if (data[0] != null) {
				System.out.println("Bookshelves name validation sucessful");
			}
			if (data[1] != null) {
				System.out.println("Bookshelves price validation successful");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This test method validates the drop down of 'Collections' menu and prints
	 * all the sub categories under 'Being At Home' option.
	 */
	@Test(priority = 17, retryAnalyzer = RetryClass.class)
	public void getBeingatHomeCollectionsList() {
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		try {
			MainClass.getCollectionList();
			Screenshots.captureScreenshot("Collections/ Collections List Dropdown");
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * This test method validates whether the user is able to navigate using the
	 * 'Gift Cards' link text
	 */
	@Test(priority = 18, retryAnalyzer = RetryClass.class)
	public void giftCardsLinkTextNavigationValidation() {
		MainClass.navigateToGiftCardsPage();
		try {
			MainClass.assertGiftCardsPage();
			Screenshots.captureScreenshot("Gift Cards/ Result page");
			System.out.println("Gift cards link text validation was successful");
		} catch (AssertionError e) {
			throw e;
		}
		driver.navigate().to("https://www.urbanladder.com/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	/**
	 * This test method asserts whether the occasion chosen was selected or not.
	 */
	@Test(priority = 19, retryAnalyzer = RetryClass.class)
	public void chosenOccasionAssertion() {
		MainClass.navigateToGiftCardsPage();
		MainClass.chooseOcassion();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		try {
			MainClass.assertChosen();
			System.out.println("Assertion for occassion as chosen was successful");
		} catch (AssertionError e) {
			throw e;
		} finally {
			driver.navigate().to("https://www.urbanladder.com/");
		}
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	/**
	 * This test method validates the calendar
	 */
	@Test(priority = 20, retryAnalyzer = RetryClass.class)
	public void giftCardsCalendarAssertion() {
		MainClass.navigateToGiftCardsPage();
		MainClass.chooseOcassion();
		MainClass.customiseGiftCard();
		MainClass.assertDisplayedDate();
		Screenshots.captureScreenshot("Gift Cards/ Calendar-Current date");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.navigate().to("https://www.urbanladder.com/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	/**
	 * This test method validates the functionality of the Calendar
	 */
	@Test(priority = 21, retryAnalyzer = RetryClass.class)
	public void chosenDateAssertion() {
		MainClass.navigateToGiftCardsPage();
		MainClass.chooseOcassion();
		MainClass.customiseGiftCard();
		MainClass.chooseCalendarDate();
		Screenshots.captureScreenshot("Gift Cards/ Calendar-Desired date");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.navigate().to("https://www.urbanladder.com/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	/**
	 * This method validates the functionality of the text box by passing
	 * invalid email and capturing the error message.
	 */
	@Test(priority = 22, dataProvider = "searchData", retryAnalyzer = RetryClass.class, groups = { "regression" })
	public void giftCardsFormValidation(String Recipientname, String Recipientemail, String YourName, String YourEmail,
			String YourPhone) {
		MainClass.navigateToGiftCardsPage();
		MainClass.chooseOcassion();
		MainClass.customiseGiftCard();
		MainClass.clickNext();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		MainClass.fillForm(Recipientname, Recipientemail, YourName, YourEmail, YourPhone);
		Screenshots.captureScreenshot("Gift Cards/ Error Message Gift Cards Form");
	}

}
