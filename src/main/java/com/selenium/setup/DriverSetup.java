package com.selenium.setup;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;

public class DriverSetup {
	public static WebDriver driver;
	public static String browserName;

	/**
	 * Method to open the browser using Config.properties file
	 */
	public static WebDriver getDriver() {

		try {
			/* Instantiate properties from Config.properties file */
			Properties prop = new Properties();
			String projectpath = System.getProperty("user.dir");
			InputStream input;
			input = new FileInputStream(projectpath + "//config.properties");
			prop.load(input);
			browserName = prop.getProperty("Browser");

			/* Opens the browsers as given in the Config.properties file */
			if (browserName.equalsIgnoreCase("Chrome")) {
				browserName = prop.getProperty("Browser");
				String driverPath = System.getProperty("user.dir") + "\\Drivers\\chromedriver.exe";
				System.setProperty("webdriver.chrome.driver", driverPath);
				driver = new ChromeDriver();
				driver.manage().window().maximize();
			} else if (browserName.equalsIgnoreCase("Internet Explorer")) {
				browserName = prop.getProperty("Browser");
				String driverPath2 = System.getProperty("user.dir") + "\\Drivers\\IEDriverServer.exe";
				System.setProperty("webdriver.ie.driver", driverPath2);
				driver = new InternetExplorerDriver();
				driver.manage().window().maximize();
			} else if (browserName.equalsIgnoreCase("Edge")) {
				browserName = prop.getProperty("Browser");
				String driverPath1 = System.getProperty("user.dir") + "\\Drivers\\msedgedriver.exe";
				System.setProperty("webdriver.edge.driver", driverPath1);
				driver = new EdgeDriver();
				driver.manage().window().maximize();
			} else if (browserName.equalsIgnoreCase("Opera")) {
				browserName = prop.getProperty("Browser");
				String driverPath1 = System.getProperty("user.dir") + "\\Drivers\\operadriver.exe";
				System.setProperty("webdriver.opera.driver", driverPath1);
				driver = new OperaDriver();
				driver.manage().window()
						.maximize(); /* To maixmize the window */
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return driver;
	}

	/**
	 * Method to open the 'Urban Ladder' web application by getting the Url from
	 * config properties file.
	 */
	public static void getUrl() {
		try {
			/* Instantiate properties from Config.properties file */
			Properties prop = new Properties();
			String projectpath = System.getProperty("user.dir");

			InputStream input = new FileInputStream(projectpath + "//config.properties");
			prop.load(input);

			/* Gets the URL from Config.properties file */
			String URL = prop.getProperty("URL");
			driver.get(URL); /* Navigates to the 'Urban Ladder' web page */

			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			/* Page Synchronisation */
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Method to refresh the page
	 */
	public static void refreshPage() {
		driver.navigate().refresh(); /* Refreshes the entire web page */
	}

	/**
	 * Method to close the browser
	 */
	public static void closeBroswer() {
		driver.quit(); /* Closes the browser */
	}

}
