package com.framework.pages;

import static org.testng.Assert.assertEquals;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.fasterxml.jackson.databind.deser.ValueInstantiator.Gettable;
import com.framework.core.Helper;
import com.framework.core.Reusables;
import com.framework.fileReader.PropertiesFileReader;

import cucumber.api.DataTable;

public class Booking extends Reusables{
	
	By btn_Change = By.xpath("//button[@data-test='changeButton']");
	By txt_Origin = By.xpath("//strong[contains(@data-test,'origin')]");
	By txt_destination = By.xpath("//strong[contains(@data-test,'destination')]");
	By txt_JourneyType = By.xpath("//span[@data-test='journeyType']/span");
	By txt_adults = By.xpath("(//span[@data-test='adults']/span)[1]");
	By txt_children = By.xpath("(//span[@data-test='children']/span)[1]");
	By btn_Continue = By.xpath("//button[@title='Continue']");
	By cmb_Direction = By.id("direction");
	By cmb_Position = By.id("position");
	By cmb_CoachType = By.id("carriageType");
	By rad_KioskTicket = By.xpath("//input[@data-test='kioskRadioButton']");
	By rad_eTicket = By.xpath("//input[@data-test='eTicketRadioButton']");
	String cb_preference = "//input[contains(@id,'facilities') and following-sibling::*[text()='%s']]";

	public Booking(WebDriver driver, PropertiesFileReader fileReader) {
		super(driver, fileReader);
		waitForElement(btn_Change);
		
		if(isDisplayed(btn_Change))
			System.out.println("Booking page loaded successfully");
		else
			throw new RuntimeException("Booking page not loaded");
	}
	
	public void validateSearchCriteria(DataTable detailsTable) {
		
		for(Map<String, String> journey:detailsTable.asMaps(String.class, String.class))
		{
			assertEquals(getText(txt_Origin, "Origin"), journey.get("Source"));
			assertEquals(getText(txt_destination, "Destination"), journey.get("Destination"));
			assertEquals(getText(txt_JourneyType, "JourneyType"), journey.get("Type")+" journey");
			assertEquals(getText(txt_adults, "Adults"), journey.get("Adult")+" Adult");
			if(Integer.parseInt(journey.get("Children"))>0)
				assertEquals(getText(txt_children, "Children"), journey.get("Children")+" Children");
		}
	}
	
	public void selectPreferences(DataTable detailsTable)
	{
		for(Map<String, String> preferences:detailsTable.asMaps(String.class, String.class))
		{
			By pref = By.xpath(String.format(cb_preference, preferences.get("Preferences")));
			waitForElement(pref);
			comboSelectByValue(cmb_Direction, preferences.get("Direction"), "Directions");
			comboSelectByValue(cmb_Position, preferences.get("Position"), "Position");
			comboSelectByValue(cmb_CoachType, preferences.get("Coach Type"), "Coach Type");
			clickElement(pref, "Preference");
		}
	}
	
	public void clickContinue()
	{
		try
		{
			if(isEnabled(btn_Continue, "Continue"))
				clickElement(btn_Continue, "Continue");
			else
				throw new Exception("Continue button is not enabled");
		}catch(Exception e)
		{	
			Helper.handleTestFaliure(e, this.getClass().getSimpleName());
		}
	}
	
	public void selectDeliveryOption(String option)
	{
		try
		{
			waitForElement(rad_KioskTicket);
			if(option.equalsIgnoreCase("KioskTicket"))
				clickElement(rad_KioskTicket, "_KioskTicket");
			else if(option.equalsIgnoreCase("eticket"))
				clickElement(rad_eTicket, "eticket");
				
		}catch(Exception e)
		{
			Helper.handleTestFaliure(e, this.getClass().getSimpleName());
		}
	}

}
