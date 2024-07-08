/** Object Repository */

package com.selenium.pages;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.selenium.setup.DriverSetup;

/**
 * Locator class for Element on the Gift Cards page.
 */
public class GiftCards extends DriverSetup {

	/**
	 * Method to locate the page title
	 */
	public static WebElement pageTitle() {
		WebElement pageTitle = driver.findElement(By.xpath("//h1[text()[contains(., 'Gift Cards')]]"));
		return pageTitle;
	}

	/**
	 * Method to locate the Gift Cards link text
	 */
	public static WebElement giftCardsLinkText() {
		WebElement GiftCardsText = driver
				.findElement(By.xpath("//a[@class = 'featuredLinksBar__link' and text()[contains(., 'Gift Cards')]]"));
		return GiftCardsText;
	}

	/**
	 * Method to locate the 'Birthday/Anniversary' option
	 */
	public static WebElement chooseOccasion() {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		WebElement Choose = driver.findElement(By.xpath("/html/body/div/div/main/section/section[1]/ul/li[3]"));
		return Choose;
	}

	/**
	 * Method to locate the Amount text box
	 */
	public static WebElement customisationTitle() {
		WebElement customisationTitle = driver.findElement(
				By.xpath("//*[@class = '_FCNL' and text()[contains(., '2. Now, customise your gift card')]]"));
		return customisationTitle;
	}

	/**
	 * Method to locate the Amount text box
	 */
	public static WebElement ClickAmount() {
		WebElement Amount = driver.findElement(By.xpath("//input[@placeholder = 'Amount']"));
		return Amount;
	}

	/**
	 * Method to locate the Chosen label
	 */
	public static WebElement chosen() {
		WebElement chosen = driver
				.findElement(By.xpath("//*[@id='app-container']/div/main/section/section[1]/ul/li[3]/div/div/button"));
		return chosen;
	}

	/**
	 * Method to locate the current displayed date on the calendar
	 */
	public static WebElement displayedDate() {
		WebElement Date = driver.findElement(By.xpath(
				"//*[@id='app-container']/div/main/section/section[2]/div/section[2]/div[4]/select[2]/option[2]"));
		return Date;
	}

	/**
	 * Method to locate the next month on the calendar
	 */
	public static WebElement chooseNextMonth() {
		WebElement nextMonth = driver.findElement(By.xpath("//select[1]//option[3]"));
		return nextMonth;
	}

	/**
	 * Method to locate the date to be chosen on the calendar
	 */
	public static WebElement chooseDate(String calculatedDate) {
		WebElement dateToBeChosen = driver.findElement(By.xpath("//option[contains(text(),'" + calculatedDate + "')]"));
		return dateToBeChosen;
	}

	/**
	 * Method to locate the current displayed date on the calendar
	 */
	public static WebElement nextBtn() {
		WebElement Next = driver.findElement(By.xpath("//button[text()[contains(., 'Next')]]"));
		return Next;
	}

	/**
	 * Method to locate the recipient name text box in the Gift Cards form
	 */
	public static WebElement recipientNameTextBox() {
		WebElement rName = driver.findElement(By.xpath("//input[@name = 'recipient_name']"));
		return rName;
	}

	/**
	 * Method to locate the recipient email address text box in the Gift Cards
	 * form
	 */
	public static WebElement recipientEmailTextBox() {
		WebElement rEmail = driver.findElement(By.xpath("//input[@name='recipient_email']"));
		return rEmail;
	}

	/**
	 * Method to locate the Your name text box in the Gift Cards form
	 */
	public static WebElement yourNameTextBox() {
		WebElement yName = driver.findElement(By.xpath("//input[@name='customer_name']"));
		return yName;
	}

	/**
	 * Method to locate the Your email address in the Gift Cards form
	 */
	public static WebElement yourEmailTextBox() {
		WebElement yEmail = driver.findElement(By.xpath("//input[@name='customer_email']"));
		return yEmail;
	}

	/**
	 * Method to locate the Your phone text box in the Gift Cards form
	 */
	public static WebElement yourPhoneTextBox() {
		WebElement yPhone = driver.findElement(By.xpath("//input[@name='customer_mobile_number']"));
		return yPhone;
	}

	/**
	 * Method to locate the Confirm button in the Gift Cards form
	 */
	public static WebElement confirmBtn() {
		WebElement Confirm = driver.findElement(By.xpath("//button[@type = 'submit']"));
		return Confirm;
	}

}
