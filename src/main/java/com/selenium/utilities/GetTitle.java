/***Utilities containing common reusable functions */
package com.selenium.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.selenium.setup.DriverSetup;

/** This class is used to get the title of the page */
public class GetTitle extends DriverSetup {
	
	/** Method to locate the title of the page */
	public static WebElement getTitle() {
		WebElement Title = driver.findElement(By.xpath("//*[@id='search-results']/div[1]/h2"));
		return Title;
	}
}
