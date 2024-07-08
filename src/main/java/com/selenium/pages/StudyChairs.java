/** Object Repository */

package com.selenium.pages;

import com.selenium.setup.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Locator class for Element on the Study Chairs display page.
 */

public class StudyChairs extends DriverSetup {
	/**
	 * Method to locate the default sort by option in the page
	 */
	public static WebElement sortDefault() {
		WebElement sortDefault = driver.findElement(By.xpath("//div[@class='gname']//span"));
		return sortDefault;
	}

	/**
	 * Method to locate the sort by drop down
	 */
	public static WebElement sortBy() {
		WebElement sortBy = driver.findElement(By.xpath("//div[@class='item']//div[@class='gname']"));
		return sortBy;
	}

	/**
	 * Method to locate the Recommended option in the sort by drop down
	 */
	public static WebElement recommended() {
		WebElement Recommended = driver.findElement(
				By.xpath("//*[@id='search-results']/div[2]/div[1]/div/div/div/div/div[2]/div[2]/div/div/ul/li[2]"));
		return Recommended;
	}

}
