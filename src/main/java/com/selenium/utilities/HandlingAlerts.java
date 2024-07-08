package com.selenium.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.selenium.setup.DriverSetup;

public class HandlingAlerts extends DriverSetup {

	//Method for handling 'Login/SignUp' pop up 
	public static WebElement popup() {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		WebElement closePopup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Close")));
		return closePopup;
	}
}
