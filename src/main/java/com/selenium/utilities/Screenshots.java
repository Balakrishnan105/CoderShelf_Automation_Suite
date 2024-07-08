/***Utilities containing common reusable functions */

package com.selenium.utilities;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import com.selenium.setup.*;

/**
 * Class for capturing screenshots wherever required.
 */
public class Screenshots extends DriverSetup {

	/**
	 * Method for capturing screenshots wherever required.
	 */
	public static void captureScreenshot(String screenshotname) {
		try {
			TakesScreenshot sShot = ((TakesScreenshot) driver);
			File Src = sShot.getScreenshotAs(OutputType.FILE);
			String filePath = System.getProperty("user.dir") + "\\Screenshots\\" + screenshotname + ".png";
			File Dest = new File(
					filePath); /* Stores the snapshot in the folder */
			FileUtils.copyFile(Src, Dest);
		} catch (Exception e) {

			System.out.println("Exception in taking Screenshot" + e.getMessage());
		}
	}
}