/***Utilities containing common reusable functions */
package com.selenium.utilities;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.selenium.setup.DriverSetup;

/**
 * This class stores the data retrieved
 */

public class RetrieveData extends DriverSetup {

	/**
	 * This method adds all the data retrieved under product name to the array
	 * list
	 */
	public static List<WebElement> productName() {
		List<WebElement> productName = new ArrayList<WebElement>();

		productName.addAll(driver.findElements(By.xpath("//div[@class='product-info-block']/a/div[1]/span")));

		return productName;

	}

	/**
	 * This method adds all the data retrieved under product price to the array
	 * list
	 */
	public static List<WebElement> productPrice() {
		List<WebElement> productPrice = new ArrayList<WebElement>();

		productPrice.addAll(driver.findElements(By.xpath("//div[@class='product-info-block']/a/div[2]/span")));

		return productPrice;
	}
}
