
package com.selenium.main;

import com.selenium.setup.*;
import com.selenium.utilities.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.selenium.pages.*;

/**
 * Actions Class to perform actions on the Web Elements
 */

public class MainClass extends DriverSetup {

	/**
	 * This method checks whether the Logo is displayed
	 */
	public static void assertLogoVisibility() {
		HomePage.logo().isDisplayed();
	}

	/**
	 * This method click on the Logo
	 */
	public static void clickOnLogo() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", HomePage.logo());
	}

	/**
	 * This method click on the Log in icon to get login pop up
	 */
	public static void getLoginPopup() {
		HomePage.loginIcon().click();
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", HomePage.loginBtn());
	}

	/**
	 * This method asserts the display of the 'Log In' pop up
	 */
	public static void assertLoginPopupVisibility() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			Assert.assertTrue(driver.getPageSource().contains("SIGN UP FOR SALE UPDATES"));
		} catch (AssertionError e) {
			throw e;
		}
	}

	/**
	 * This method closes the 'Log In' pop up
	 */
	public static void closePopup() {
		HandlingAlerts.popup().click();
	}

	/**
	 * This method send data in the 'Email Address' and 'Password' text box
	 */
	public static void loginData(String Username, String Password) {
		/* Explicitly waits until the 'Email Address' text box is clickable'. */
		new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(HomePage.emailTextBox()));
		HomePage.emailTextBox().sendKeys(Username);

		/* Explicitly waits until the 'Password' text box is clickable'. */
		new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(HomePage.passwordTextBox()));
		HomePage.passwordTextBox().sendKeys(Password);
	}

	/**
	 * This method clicks the 'Log In' submit button to log in
	 */
	public static void clickSubmitBtn() {
		/* Explicitly waits until the 'Log In' submit button is clickable'. */
		new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(HomePage.submitButton()));
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", HomePage.submitButton());

	}

	/**
	 * This method asserts the error message displayed when an invalid 'Email
	 * Address' is entered.
	 */
	public static void assertEmailErrorMessage() {
		String emailMessage = HomePage.emailErrorMessage().getText();
		Assert.assertEquals(emailMessage, "Please enter a valid email address.");
	}

	/**
	 * This method asserts the error message displayed when an invalid
	 * 'Password' is entered.
	 */
	public static void assertPasswordErrorMessage() {
		String passwordMessage = HomePage.passwordErrorMessage().getText();
		Assert.assertEquals(passwordMessage, "The email or password you entered is incorrect. Please try again.");
	}

	/**
	 * This method sends data into the 'Search'text box on the homepage.
	 */
	public static void toSearch(String ProductName) {
		HomePage.searchBox().sendKeys(ProductName);
		/* Page Synchronisation */
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	/**
	 * Method which clicks on 'Search icon' after giving input to the 'Search'
	 * text box
	 */
	public static void toClickOnSearchIcon() {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", HomePage.searchIcon());
	}

	/**
	 * Method which asserts the search results with 'Search' text box input
	 */
	public static void assertSentValue(String ProductName) {
		String Text = HomePage.searchBox().getAttribute("value");
		Assert.assertEquals(Text, ProductName);
	}

	/**
	 * This method asserts the 'No products found' message when an invalid data
	 * is passed
	 */
	public static void assertNoProductsFoundMsg() {
		String inValidDataMessage = HomePage.noProductsMessage().getText();
		Assert.assertEquals(inValidDataMessage, "No products found");
	}

	/**
	 * This method enters data in the 'Search' text box, waits for the auto
	 * suggestions and clicks the first suggestion from the drop down.
	 */
	public static void selectFromSearchSuggestion() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		/*
		 * Explicitly waits until the auto suggestions appear and the
		 * suggestions are clickable
		 */
		wait.until(ExpectedConditions.visibilityOf(HomePage.searchBoxAutoSuggest()));
		wait.until(ExpectedConditions.elementToBeClickable(HomePage.searchBoxAutoSuggest()));
		HomePage.searchBoxAutoSuggest().click();
	}

	/**
	 * This method asserts the Title of the page after navigation using auto
	 * suggestions
	 */
	public static void assertTitle(String eTitle) {
		try {
			String Title = driver.findElement(By.xpath("//*[@id='search-results']/div[1]/h2")).getText();
			Assert.assertEquals(Title.equalsIgnoreCase(eTitle), true);
		} catch (AssertionError e) {
			throw e;
		}
	}

	/**
	 * This method moves the 'Price' slider and sets the range at Rs.15094
	 * (approximately Rs.15000)
	 */
	public static void setPriceFilter() {
		Actions action = new Actions(driver);
		action.moveToElement(Bookshelves.priceFilter()).perform();
		action.clickAndHold(Bookshelves.priceSlider()).moveByOffset(-206, 0).release(Bookshelves.priceSlider())
				.perform();
		/* Implicitly waits for 30 seconds, Page Synchronisation. */
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	/**
	 * This method asserts the presence of the 'Price range' label that is
	 * displayed after the filter is applied
	 */
	public static void assertPriceFilter() {
		/* Implicitly waits for 15 seconds, Page Synchronisation. */
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		try {
			Assert.assertTrue(Bookshelves.appliedPriceFilter().isDisplayed());
		} catch (AssertionError e) {
			throw e;
		}
	}

	/**
	 * This method chooses the 'Open' type option from the 'Storage type' drop
	 * down.
	 */
	public static void setStorageFilter() {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		Actions action = new Actions(driver);
		action.moveToElement(Bookshelves.storageFilter()).build().perform();

		Bookshelves.storageTypeOpen().click();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	/**
	 * This method asserts the presence of the 'Open' type label that is
	 * displayed after the 'Storage type' filter is applied.
	 */
	public static void assertStorageFilter() {
		try {
			Assert.assertTrue(Bookshelves.appliedStorageFilter().isDisplayed());
		} catch (AssertionError e) {
			throw e;
		}

	}

	/**
	 * This method clicks the 'Exclude out of Stock' checkbox.
	 */
	public static void toExcludeOutofStock() {
		/* Implicitly waits for 15 seconds, Page Synchronisation. */
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		Bookshelves.excludeOutofStockCheckBox().click();
	}

	/**
	 * This method asserts the presence of the 'In Stock only' label that is
	 * displayed after the 'Exclude out of stock' check box is applied.
	 */
	public static void assertToExcludeOutOfStockCheckBox() {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		try {
			Assert.assertTrue(Bookshelves.assertExcludeOutofStock().isDisplayed());
		} catch (AssertionError e) {
			throw e;
		}

	}

	/**
	 * This method selects the 'Recommended' option from the 'Sort By' filter if
	 * it is not selected by default.
	 */
	public static void sortProducts() {
		String Default = StudyChairs.sortDefault().getText();

		if (Default.equalsIgnoreCase("Recommended")) {
		} else {
			Actions action = new Actions(driver);
			action.moveToElement(StudyChairs.sortBy()).perform();
			StudyChairs.recommended().click();
			driver.manage().timeouts().implicitlyWait(15,
					TimeUnit.SECONDS); /* Page Synchronisation */
		}
	}

	/**
	 * This method asserts the 'Sort By' filter as Recommended.
	 */
	public static void assertSortByFilter() {
		String sortedBy = StudyChairs.sortDefault().getText();
		Assert.assertEquals((sortedBy).equalsIgnoreCase("Recommended"), true);
	}

	/**
	 * This method navigates to the 'Gift Cards' page through the link text.
	 */
	public static void navigateToGiftCardsPage() {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", GiftCards.giftCardsLinkText());
	}

	/**
	 * This method asserts the title of the page as 'Gift Cards' after
	 * navigation through the 'Gift Cards' link text.
	 */
	public static void assertGiftCardsPage() {
		String pageTitle = GiftCards.pageTitle().getText();
		Assert.assertEquals(pageTitle, "Gift Cards");
	}

	/**
	 * This methods selects 'Birthday/Anniversary' option in 'Gift Cards'
	 * Occassion grid.
	 */
	public static void chooseOcassion() {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", GiftCards.chooseOccasion());
		Screenshots.captureScreenshot("Gift Cards/ Chosen Occassion");
		// GiftCards.chooseOccasion().click();
	}

	/**
	 * This methods asserts the chosen Occassion as 'Birthday/Anniversary'
	 * option in 'Gift Cards' Occassion grid.
	 */
	public static void assertChosen() {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		Assert.assertTrue(GiftCards.chosen().isDisplayed(), "The Occasion is chosen");
		Assert.assertTrue(GiftCards.customisationTitle().isDisplayed(),
				"The Ocassion is chosen and hence the page is scrolled down to customisation.");
	}

	/**
	 * This method helps to assert whether the displayed date in 'Gift Cards'
	 * menu matches with the current date.
	 */
	public static void assertDisplayedDate() {
		LocalDate date = LocalDate.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("d");
		Calendar cal = Calendar.getInstance();
		new SimpleDateFormat("MMM").format(cal.getTime());
		String CurrentDate = date.format(format);
		String DateFound = GiftCards.displayedDate().getText();
		try {
			Assert.assertEquals(CurrentDate, DateFound);
			System.out.println("Current date matches with the enabled date");
		} catch (AssertionError e) {
			throw e;
		}

	}

	/**
	 * This method enters '1000' in 'Amount' text box in the Customisation
	 * section.
	 */
	public static void customiseGiftCard() {
		GiftCards.ClickAmount().sendKeys("1000");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	/**
	 * This method clicks 'NEXT' button after entering amount in the
	 * Customisation section
	 */
	public static void clickNext() {
		GiftCards.nextBtn().click();
	}

	/**
	 * This method validates the 'Month' and 'Date' dropdowns in the calendar
	 */
	public static void chooseCalendarDate() {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		SimpleDateFormat sdf = new SimpleDateFormat("d");
		SimpleDateFormat sdformat = new SimpleDateFormat("m");
		Calendar cal = Calendar.getInstance(); /* Getting current date */
		String strCurrentDate = sdf
				.format(cal.getTime()); /* Number of Days to add */
		String strcurrentMonthMaxDates = sdformat.format(cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		int intcurrentMonthMaxDates = Integer.parseInt(strcurrentMonthMaxDates);
		int intCurrentDate = Integer.parseInt(strCurrentDate);
		if ((intCurrentDate + 2) > intcurrentMonthMaxDates) {
			GiftCards.chooseNextMonth().click();
		}
		cal.add(Calendar.DAY_OF_MONTH, 2);
		String calculatedDate = sdf.format(cal.getTime());
		GiftCards.chooseDate(calculatedDate)
				.click(); /*
							 * Displaying the new Date after addition of Days to
							 * current date.
							 */
		String DateFound = driver
				.findElement(By
						.xpath("//*[@id='app-container']/div/main/section/section[2]/div/section[2]/div[4]/select[2]"))
				.getAttribute("value");
		Assert.assertEquals(calculatedDate, DateFound);
	}

	/**
	 * This method fills the entire 'To' and 'From' form in the 'Gift Cards'
	 * menu and clicks the 'Confirm' button
	 */
	public static void fillForm(String RecepientName, String RecepientEmail, String Name, String Email, String Phone) {
		GiftCards.recipientNameTextBox().sendKeys(RecepientName);
		GiftCards.recipientEmailTextBox().sendKeys(RecepientEmail);
		GiftCards.yourNameTextBox().sendKeys(Name);
		GiftCards.yourEmailTextBox().sendKeys(Email);
		GiftCards.yourPhoneTextBox().sendKeys(Phone);
		GiftCards.confirmBtn().click();
	}

	/**
	 * This method collects all the options in the 'Collections' menu under the
	 * 'Being at Home' category and parses in the 'Output Dat' excel sheet.
	 */
	public static void getCollectionList() {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		Actions action = new Actions(driver);
		action.moveToElement(HomePage.collectionsMenu()).build().perform();
		List<WebElement> optionlist = HomePage.subCategoriesList();
		ExcelReadAndWrite excel = new ExcelReadAndWrite(System.getProperty("user.dir") + "\\Test Data\\Output Data.xls",
				"Collection"); /*
								 * Creates an excel sheet for entering
								 * collections list drop down options
								 */
		for (int i = 0; i < 13; i++) {
			String option = ((optionlist.get(i)).getText());
			excel.setCellData(option, (i + 2), 1);
		}
	}

	/**
	 * This Method retrieves the product name and price , prints them on the
	 * console and also parses them in excel file
	 */
	public static void getproductnameandprice(String sheetname) {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		List<WebElement> name = RetrieveData.productName();
		List<WebElement> price = RetrieveData.productPrice();
		ExcelReadAndWrite excel = new ExcelReadAndWrite(System.getProperty("user.dir") + "\\Test Data\\Output Data.xls",
				sheetname);
		for (int i = 0; i <= 2; i++) {
			String productname = (name.get(i)).getText();
			String productprice = ((price).get(i)).getText();
			System.out.println(productname);
			System.out.println(productprice);
			excel.setCellData(productname, (i + 1), 0);
			excel.setCellData(productprice, (i + 1), 1);
		}
	}

	/**
	 * This method validates the presence of the Product name and price by
	 * comparing with the data on the excel sheet
	 */
	public static String[] productNameandPriceValidation(String sheetName) {
		ExcelReadAndWrite excel = new ExcelReadAndWrite(System.getProperty("user.dir") + "\\Test Data\\Output Data.xls",
				sheetName);
		String input[] = new String[2];
		input[0] = excel.getCellData(1, 0);
		input[1] = excel.getCellData(1, 1);
		return input;
	}
}
