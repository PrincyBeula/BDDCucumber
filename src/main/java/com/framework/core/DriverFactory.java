package com.framework.core;

import java.io.File;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


public class DriverFactory {

	private static WebDriver driver;

	/**
	 * @param browserName
	 * 
	 *                    Initialize the browser
	 */
	public WebDriver initializeDriver(String browserName) {
		switch (browserName.toLowerCase()) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "drivers" + File.separator + "chromedriver.exe");
			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("profile.default_content_setting_values.notifications", 2);
			chromePrefs.put("profile.default_content_settings.popups", 0);
			chromePrefs.put("safebrowsing.enabled", "false");
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", chromePrefs);
			options.addArguments("disable-popup-blocking");
			driver = new ChromeDriver(options);
			break;
		case "internetexplorer":
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "drivers" + File.separator + "IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			break;
		case "firefox":
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "drivers" + File.separator + "geckodriver.exe");
			driver = new FirefoxDriver();
			break;
		default:
			throw new RuntimeException("Browser name not found");
		}
		driver.manage().window().maximize();
		return driver;
	}

	/**
	 * @return
	 * 
	 *         Get the driver
	 */
	public WebDriver getDriver() {
		return driver;
	}

	/**
	 * Quit the WebDriver
	 */
	public void quitDriver() {
		driver.quit();
	}
}
