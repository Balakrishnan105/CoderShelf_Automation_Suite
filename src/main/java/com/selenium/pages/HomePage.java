/** Object Repository */
package com.selenium.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.selenium.setup.*;

/**
 * Locator class for Element on the Home page.
 */

public class HomePage extends DriverSetup {

	/**
	 * Method to locate the Logo on the application
	 */
	public static WebElement logo() {
		WebElement logoIcon = driver.findElement(By.xpath("//figure[@class='header__topBar_logo']"));
		return logoIcon;
	}

	/**
	 * Method to locate the Login icon
	 */
	public static WebElement loginIcon() {
		/* Explicitly waits until the 'Login' icon is displayed */
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[@id='header']/div[1]/div/section[3]/ul/li[2]/span")));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement loginIcon = driver.findElement(By.xpath("//*[@id='header']/div[1]/div/section[3]/ul/li[2]/span"));
		return loginIcon;
	}

	/**
	 * Method to locate the Login button
	 */
	public static WebElement loginBtn() {
		WebElement loginBtn = driver
				.findElement(By.xpath("//*[@id='header']/div[1]/div/section[3]/ul/li[2]/span/ul/li[1]/a"));
		return loginBtn;
	}

	/**
	 * Method to locate the 'Sale Update' message on the login pop up
	 */
	public static WebElement popupMessage() {
		WebElement Popup = driver.findElement(By.xpath("//div[@class = 'login-message']"));
		return Popup;
	}

	/**
	 * Method to locate the 'Email Address' text box
	 */
	public static WebElement emailTextBox() {
		/* Implicitly waits for 15 seconds, Page Synchronisation. */
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		WebElement emailtxtbox = driver.findElement(By.xpath("//input[@placeholder = 'Email Address']"));
		return emailtxtbox;
	}

	/**
	 * Method to locate the 'Password' text box
	 */
	public static WebElement passwordTextBox() {
		WebElement passwordTxtBox = driver.findElement(By.xpath("//input[@placeholder = 'Password']"));
		return passwordTxtBox;
	}

	/**
	 * Method to locate the 'Submit' button
	 */
	public static WebElement submitButton() {
		WebElement submitBtn = driver.findElement(By.xpath("//input[@class='button primary']"));
		return submitBtn;
	}

	/**
	 * Method to locate the 'Invalid email' error message.
	 */
	public static WebElement emailErrorMessage() {
		WebElement emailErrorMessage = driver.findElement(By.xpath("//*[@id='password-credentials']/label"));
		return emailErrorMessage;
	}

	/**
	 * Method to locate the 'Invalid password' error message.
	 */
	public static WebElement passwordErrorMessage() {
		WebElement passwordErrorMessage = driver.findElement(By.xpath("//*[@class='flash ulmessage error  ']"));
		return passwordErrorMessage;

	}

	/**
	 * Method to locate the 'No products found' message.
	 */
	public static WebElement noProductsMessage() {
		WebElement noProductsMsg = driver.findElement(By.xpath("//*[@id='search-results']/div[3]"));
		return noProductsMsg;
	}

	/**
	 * Method to locate the 'Home page' element.
	 */
	public static WebElement homePage() {

		WebElement homePage = driver.findElement(By.xpath("//*[@id='home']"));
		return homePage;
	}

	/**
	 * Method to locate the 'Search' text box.
	 */
	public static WebElement searchBox() {
		WebElement SearchTxtbox = driver.findElement(By.xpath("/html//input[@id='search']"));
		return SearchTxtbox;
	}

	/**
	 * Method to locate the 'Search' icon.
	 */
	public static WebElement searchIcon() {
		WebElement searchIcon = driver.findElement(By.id("search_button"));
		return searchIcon;
	}

	/**
	 * Method to locate the first auto suggestion displayed in the drop down.
	 */
	public static WebElement searchBoxAutoSuggest() {
		WebElement searchsuggestion = driver
				.findElement(By.xpath("//div[@class='tt-dataset tt-dataset-suggestions']//div[1]//strong[1]"));
		return searchsuggestion;
	}

	/**
	 * Method to locate the Search results title.
	 */
	public static WebElement bookshelvesTitle() {
		WebElement Title = driver.findElement(By.xpath("//*[@id='search-results']/div[1]/h2"));
		return Title;
	}

	/**
	 * Method to locate the Collections category on the menu bar.
	 */
	public static WebElement collectionsMenu() {
		WebElement collection = driver.findElement(By.xpath("//li[@class='topnav_item collectionsunit']"));
		return collection;
	}

	/**
	 * Method to locate the 'Being at Home' sub category from the Collections
	 * category on the menu bar.
	 */
	public static WebElement beingatHomeCategory() {
		WebElement beingatHome = driver.findElement(By
				.xpath("//li[@class='topnav_item collectionsunit']//descendant::a[contains(text(),'Being At Home')]"));
		return beingatHome;
	}

	/**
	 * Method to locate all the options under the 'Being at Home' sub category
	 * from the Collections category on the menu bar and store them in a Array
	 * list.
	 */
	public static List<WebElement> subCategoriesList() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		List<WebElement> optionsList = new ArrayList<WebElement>();
		for (int i = 1; i <= 13; i++) {
			optionsList.add(driver.findElement(By
					.xpath("//li[@class='topnav_item collectionsunit']//descendant::a[contains(text(),'Being At Home')]//parent::div//following-sibling::ul[@class='taxonslist']/child::li[contains(@class,'subnav_item')]["
							+ i + "]/child::a/child::span")));
		}
		return optionsList;
	}

}
