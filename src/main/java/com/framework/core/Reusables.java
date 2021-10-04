package com.framework.core;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.framework.fileReader.PropertiesFileReader;


public class Reusables {

	protected WebDriver driver;
	protected PropertiesFileReader fileReader;
	JavascriptExecutor js;


/**
 * @param driver
 * 
 *               Constructor
 * @param wait
 */
public Reusables(WebDriver driver, PropertiesFileReader fileReader) {
	this.driver = driver;
	this.fileReader = fileReader;
	js = (JavascriptExecutor) driver;
}

/**
 * @param field
 * @param value
 * @param desc
 * 
 *              Enter the given text in the WebElement
 */
protected void enterText(By field, String value, String desc) {
	try
	{
		WebElement element = driver.findElement(field);
		element.clear();
		element.sendKeys(value);
		LogGenerator.info(value + " is entered in " + desc);
	}catch(Exception e)
	{
		LogGenerator.error(e.getMessage());
	}
}

protected boolean isDisplayed(By field) {
	boolean flag = false;
	try
	{
		if (driver.findElements(field).size() != 0) {
			flag = true;
		}
	}catch(Exception e)
	{
		flag = false;
	}
	
	return flag;
}

protected void clickElement(By field, String desc) {
	WebElement element = driver.findElement(field);
	element.click();
	LogGenerator.info(desc + " button is clicked");
}

protected void waitForElement(By field) {
	int count = 0;
	int maxWaitTime = Integer.valueOf(fileReader.getProperty("maxTimeout"));
	if (field != null) {
		while (count < maxWaitTime) {
			boolean processing = isDisplayed(field);
			if (processing) {
				LogGenerator.info(field + " loaded in the web page");
				break;
			} else {
				count++;
			}
		}
		if (count == maxWaitTime) {
			LogGenerator.info("Element " + field + " not loaded in the web page");
		}
	} else {
		LogGenerator.info("The By element passed is null");
	}
}

public void switchToFrame(By frame, String desc) {
	try {
		waitForElement(frame);
		WebElement frameEle = driver.findElement(frame);
		driver.switchTo().frame(frameEle);
		LogGenerator.info("Switched to Frame: " + desc);
	} catch (Exception e) {
		e.printStackTrace();
	}
}

/**
 * Switch to default
 */
public void switchToDefault() {
	driver.switchTo().defaultContent();
}

protected String dateFormatter(String currentFormat, String targetFormat, String date) throws ParseException {
	SimpleDateFormat curFormat = new SimpleDateFormat(currentFormat);
	Date dt = null;
	dt = curFormat.parse(date);
	SimpleDateFormat tarFormat = new SimpleDateFormat(targetFormat);
	date = tarFormat.format(dt);
	return date;
}

protected String getAttribute(By field, String attributeName, String desc) {
	String value = null;
	value = driver.findElement(field).getAttribute(attributeName);
	LogGenerator.info("The " + desc + "'s value is " + value);
	return value;
}

protected void comboSelectByValue(By field, String value, String desc) {
	Select element = new Select(driver.findElement(field));
	element.selectByValue(value);
	LogGenerator.info(value + " is selected in " + desc);
}

protected void comboSelectByVisibleText(By field, String value, String desc) {
	Select element = new Select(driver.findElement(field));
	element.selectByVisibleText(value);
	LogGenerator.info(value + " is selected in " + desc);
}

protected List<WebElement> findElements(By field, String desc) {
	List<WebElement> element = null;
	element = driver.findElements(field);
	return element;
}

protected void hardDelay(int waitTime) throws InterruptedException {
	try {
		Thread.sleep(waitTime);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
}

protected String getText(By field, String desc) {
	String value = null;
	value = driver.findElement(field).getText();
	LogGenerator.info("The text for " + desc + " is " + value);
	return value;
}

public void waitTillElementClickable(By field) {
	try {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(field));
	} catch (Exception e) {
		LogGenerator.info(field + " element is not clickable");
	}
}

protected boolean isEnabled(By field, String desc) {
	boolean flag = false;
	try {
		waitForElement(field);
		WebElement element = driver.findElement(field);
		if (element.isEnabled()) {
			flag = true;
			LogGenerator.info(desc + " is enabled");
		}
	} catch (Exception e) {
		LogGenerator.info(desc + " is disabled");
	}
	return flag;
}

protected void enterText(By field, Keys keys, String desc) {
	WebElement element = driver.findElement(field);
	element.sendKeys(keys);
	LogGenerator.info(keys.toString() + " is performed in keyboard");
}

protected void enterTextJS(By field, String value, String desc) {
	WebElement element = driver.findElement(field);
	js.executeScript("arguments[0].value='"+value+"';", element);
	LogGenerator.info(value + " is entered in "+desc +" field");
}


public void waitTillElementInvisible(By field) {
	try {
		WebDriverWait wait = new WebDriverWait(driver, 120);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(field));
	} catch (Exception e) {
		LogGenerator.info(field + " element is visible");
	}
}
	
}
