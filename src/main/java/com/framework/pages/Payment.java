package com.framework.pages;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.framework.core.Helper;
import com.framework.core.Reusables;
import com.framework.fileReader.PropertiesFileReader;

import cucumber.api.Scenario;

public class Payment extends Reusables{

	By btn_PayWithCard = By.xpath("//button[descendant::*[text()='Pay with card']]");
	By txt_CoachDetails = By.xpath("//div[@data-test='collapsible-content']//li/span");
	By btn_Remove = By.xpath("//button[descendant::*[text()='Remove']]");
	By btn_RemoveTrip = By.xpath("//button[descendant::*[text()='Remove trip']]");
	By btn_Removing = By.xpath("//button[descendant::*[text()='Removing']]");
	By txt_BasketEmpty = By.xpath("//span[text()='Your basket is empty.']");
	
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
	
	public void clearBasket()
	{
		try
		{
			List<WebElement> tickets = findElements(btn_Remove, "Remove Button");
			for(WebElement ticket:tickets)
			{
				ticket.click();
				waitForElement(btn_RemoveTrip);
				waitTillElementClickable(btn_RemoveTrip);
				clickElement(btn_RemoveTrip, "Remove Trip");
				waitTillElementInvisible(btn_Removing);
				hardDelay(3000);
			}
			assertEquals(isDisplayed(txt_BasketEmpty), true,"Basket is not cleared");
		}catch(Exception e)
		{
			Helper.handleTestFaliure(e, this.getClass().getSimpleName());
		}
	}
}
