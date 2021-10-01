package com.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.framework.core.Helper;
import com.framework.core.Reusables;
import com.framework.fileReader.PropertiesFileReader;

import cucumber.api.Scenario;

public class Payment extends Reusables{

	By btn_PayWithCard = By.xpath("//button[descendant::*[text()='Pay with card']]");
	By txt_CoachDetails = By.xpath("//div[@data-test='collapsible-content']//li/span");
	
	public Payment(WebDriver driver, PropertiesFileReader fileReader) {
		super(driver, fileReader);
		waitForElement(btn_PayWithCard);
		if(isDisplayed(btn_PayWithCard))
			System.out.println("Payment page loaded successfully");
		else
			throw new RuntimeException("Payment page not loaded");
	}
	
	public String getReservationDetails()
	{
		String value = null;
		try
		{
			hardDelay(1000);
			waitForElement(txt_CoachDetails);
			value = getText(txt_CoachDetails, "Reservation Details");
			if(value.isEmpty() || value==null)
				throw new Exception("Coach deatils is empty");
		}catch(Exception e)
		{
			Helper.handleTestFaliure(e, this.getClass().getSimpleName());
		}
		return value;
	}
}
