/** Object Repository */
package com.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.selenium.setup.DriverSetup;

/**
 * Locator class for Element on the Bookshelves display page.
 */

public class Bookshelves extends DriverSetup {

	/**
	 * Method to locate the Price Filter Drop down
	 */
	public static WebElement priceFilter() {
		WebElement priceFilter = driver.findElement(By.xpath("//li[@class = 'item' and @data-group = 'price']"));
		return priceFilter;
	}

	/**
	 * Method to locate the Price slider in the drop down
	 */
	public static WebElement priceSlider() {
		WebElement priceSlider = driver.findElement(By.xpath("//div[@class='noUi-handle noUi-handle-upper']"));
		return priceSlider;
	}

	/**
	 * Method to locate the label for selected price range
	 */
	public static WebElement appliedPriceFilter() {
		WebElement appliedPrice = driver.findElement(By.xpath("//*[@id='search-results']/div[2]/div[2]/div/ul/li"));
		return appliedPrice;
	}

	/**
	 * Method to locate the Storage type filter drop down
	 */
	public static WebElement storageFilter() {
		WebElement storageFilter = driver
				.findElement(By.xpath("//li[@class = 'item' and @data-group = 'storage type']"));
		return storageFilter;
	}

	/**
	 * Method to locate the Open type check box in the Storage type drop down
	 */
	public static WebElement storageTypeOpen() {
		WebElement storageTypeOpen = driver.findElement(By.xpath("//input[@id='filters_storage_type_Open']"));
		return storageTypeOpen;
	}

	/**
	 * Method to locate the label for the selected storage type
	 */
	public static WebElement appliedStorageFilter() {
		WebElement appliedOpenType = driver.findElement(By.xpath("//li/span[@class ='text']"));
		return appliedOpenType;
	}

	/**
	 * Method to locate the Exclude out of stock check box
	 */
	public static WebElement excludeOutofStockCheckBox() {
		WebElement excludeOutofStock = driver
				.findElement(By.xpath("//input[@id='filters_availability_In_Stock_Only']"));
		return excludeOutofStock;
	}

	/**
	 * Method to locate the label 'In stock Only'
	 */
	public static WebElement assertExcludeOutofStock() {
		WebElement InstockOnly = driver.findElement(By.xpath("//li[@ data-option-name ='In Stock Only']"));
		return InstockOnly;
	}
}
