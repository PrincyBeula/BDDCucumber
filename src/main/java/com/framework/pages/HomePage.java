package com.framework.pages;

import static org.testng.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.framework.core.Helper;
import com.framework.core.Reusables;
import com.framework.fileReader.PropertiesFileReader;

import cucumber.api.DataTable;

public class HomePage extends Reusables{
	
	By btn_AcceptCookies = By.xpath("//button[text()='Accept cookies']");
	By txtFrom = By.id("from.search");
	By txt_userName = By.xpath("//input[contains(@id,'email')]");
	By txt_password = By.xpath("//input[contains(@id,'password')]");
	//By.id("formInput-password");
	By signin = By.xpath("//button[descendant::*[text()='Sign in']]");
	//By.xpath("//a[@title='Sign in']");
	By register = By.xpath("//span[text()='Register']");
	By btn_submit = By.xpath("//button[@type='submit' and text()='Sign in']");
	By txtTo = By.id("to.search");
	By rad_oneWay = By.id("single");
	By rad_twoWay = By.id("return");
	By rad_openReturn = By.id("openReturn");
	By rad_seasons = By.id("seasons");
	By btn_Today = By.xpath("//button[descendant::*[text()='Today']]");
	By btn_Tomorrow = By.xpath("//button[descendant::*[text()='Tomorrow']]");
	By btn_SameDay = By.xpath("//button[descendant::*[text()='Same day']]");
	By btn_NextDay = By.xpath("//button[descendant::*[text()='Next day']]");
	By txt_OutboundDate = By.id("page.journeySearchForm.outbound.title");
	By txt_InboundDate = By.id("page.journeySearchForm.inbound.title");
	By cmb_OutDateType = By.xpath("//fieldset[@data-test='outbound-datepicker']//select[@name='dateType']");
	By cmb_ReturnDateType = By.xpath("//fieldset[@data-test='inbound-datepicker']//select[@name='dateType']");
	By cmb_OutHours = By.xpath("//fieldset[@data-test='outbound-datepicker']//select[@name='hours']");
	By cmb_OutMinutes = By.xpath("//fieldset[@data-test='outbound-datepicker']//select[@name='minutes']");
	By cmb_ReturnHours = By.xpath("//fieldset[@data-test='inbound-datepicker']//select[@name='hours']");
	By cmb_ReturnMinutes = By.xpath("//fieldset[@data-test='inbound-datepicker']//select[@name='minutes']");
	By txt_SeasonsDate = By.xpath("//input[@data-test='date-input-field']");
	By btn_PassengerSummary = By.id("passenger-summary-btn");
	By cmb_Adults = By.xpath("//select[@name='adults']");
	By cmb_Children = By.xpath("//select[@name='children']");
	By cmb_ChildrenAge = By.xpath("//select[@aria-label='select child age']");
	By btn_Done = By.xpath("//button[descendant::*[text()='Done']]");
	By btn_Search = By.xpath("//button[@data-test='submit-journey-search-button']");

	public HomePage(WebDriver driver,PropertiesFileReader fileReader)
	{
		super(driver, fileReader);
		if(isDisplayed(btn_AcceptCookies))
			clickElement(btn_AcceptCookies, "Accept Cookies");
		waitForElement(txtFrom);
		
		if(isDisplayed(txtFrom))
			System.out.println("HomePage loaded successfully");
		else
			throw new RuntimeException("Homepage not loaded");
	}
	
	public void login(String username,String password)
	{
		try
		{
			waitForElement(signin);
			clickElement(signin, "Sign in");
			waitTillElementClickable(txt_userName);
			enterText(txt_userName, username, "User Email");
			enterText(txt_password, password, "User password");
			clickElement(btn_submit, "Submit");
			hardDelay(2000);
		}catch(Exception e)
		{
			Helper.handleTestFaliure(e, this.getClass().getSimpleName());
		}
	}
	
	public void enterJourneyDetails(DataTable detailsTable)
	{
		
		try
		{
			for(Map<String,String> journeyDetails: detailsTable.asMaps(String.class, String.class))
			{
				enterText(txtFrom, journeyDetails.get("From"),"From");
				enterText(txtTo, journeyDetails.get("To"),"To");
				switch(journeyDetails.get("Type").toLowerCase())
				{
				case "one way":
					clickElement(rad_oneWay, "One Way");
					enterOneWayJourneyDetails(journeyDetails.get("Date"),journeyDetails.get("Option"),journeyDetails.get("Time"));
					break;
				case "return":
					clickElement(rad_twoWay, "Return");
					enterOneWayJourneyDetails(journeyDetails.get("Date"),journeyDetails.get("Option"),journeyDetails.get("Time"));
					enterReturnJourneyDetails(journeyDetails.get("Return Date"),journeyDetails.get("Return Option"),journeyDetails.get("Return Time"));
					break;
				case "open return":
					clickElement(rad_openReturn, "Open Return");
					enterOneWayJourneyDetails(journeyDetails.get("Date"),journeyDetails.get("Option"),journeyDetails.get("Time"));
					break;
				case "seasons":
					clickElement(rad_seasons, "Seasons");
					enterText(txt_SeasonsDate, journeyDetails.get("Date"), "Date");
					break;
				default:
					throw new Exception("Journey type is incorrect");
				}
				
				clickElement(btn_PassengerSummary, "Passengar Details");
				waitForElement(cmb_Adults);
				comboSelectByValue(cmb_Adults, journeyDetails.get("Adult"), "Adults Count");
				comboSelectByValue(cmb_Children, journeyDetails.get("Children"), "Children Count");
				if(Integer.parseInt(journeyDetails.get("Children"))>=1)
				{
					int i=0;
					String[] age = journeyDetails.get("Children Age").split(",");
					List<WebElement> ageSelector = findElements(cmb_ChildrenAge, "ChildrenAge");
					for(WebElement cmbChildAge:ageSelector)
					{
						Select element = new Select(cmbChildAge);
						element.deselectByVisibleText(age[i]);
						i++;
					}
				}
				clickElement(btn_Done, "Done");
				waitForElement(btn_Search);
			}
			
		}catch(Exception e)
		{
			Helper.handleTestFaliure(e, this.getClass().getSimpleName());
		}
	}
	
	private void enterOneWayJourneyDetails(String travelDate, String type, String travelTime)
	{
		try
		{
			if(travelDate.equalsIgnoreCase("Today"))
			{
				clickElement(btn_Today, "Today");
				String actDate = getAttribute(txt_OutboundDate, "value", "Out Date");
				
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-YY");
				String expDate  = sdf.format(cal.getTime());
				
				assertEquals(actDate, expDate,"Today's date not set");
				
			}
			else if(travelDate.equalsIgnoreCase("Tomorrow"))
			{
				clickElement(btn_Tomorrow, "Tomorrow");
				String actDate = getAttribute(txt_OutboundDate, "value", "Out Date");
				
				Calendar cal = Calendar.getInstance();
				cal.setTime(cal.getTime());
				cal.add(Calendar.DAY_OF_YEAR, 1);
				SimpleDateFormat sdf = new SimpleDateFormat("DD-MMM-YY");
				String expDate  = sdf.format(cal.getTime());
				
				assertEquals(actDate, expDate,"Tomorrow's date not set");
			}
			else
				enterText(txt_OutboundDate, travelDate, "Journey Date");
			comboSelectByVisibleText(cmb_OutDateType, type, "Leaving or Arriving");
			String[] time = travelTime.split(":");
			comboSelectByValue(cmb_OutHours, time[0], "Hours");
			comboSelectByValue(cmb_OutMinutes, time[1], "Minutes");
			
		}catch(Exception e)
		{
			Helper.handleTestFaliure(e, this.getClass().getSimpleName());
		}
	}
	
	private void enterReturnJourneyDetails(String returnDate, String type, String travelTime)
	{
		try
		{
			if(returnDate.equalsIgnoreCase("Same Day"))
			{
				clickElement(btn_SameDay, "Same Day");
				String actDate = getAttribute(txt_InboundDate, "value", "Return Date");
				String expDate = getAttribute(txt_OutboundDate, "value", "Onward Journey Date");
				assertEquals(actDate, expDate,"Same day date is not set");
				
			}
			else if(returnDate.equalsIgnoreCase("Next Day"))
			{
				clickElement(btn_NextDay, "Next day");
				String actDate = getAttribute(txt_OutboundDate, "value", "Out Date");
				String onwardJourneydate = getAttribute(txt_OutboundDate, "value", "Onward Journey Date");
				
				SimpleDateFormat sdf = new SimpleDateFormat("DD-MMM-YY");
				Date date = sdf.parse(onwardJourneydate);
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				cal.add(Calendar.DAY_OF_YEAR, 1);
				String expDate  = sdf.format(cal.getTime());
				
				assertEquals(actDate, expDate,"Next Day's date not set");
			}
			else
				enterText(txt_InboundDate, returnDate, "Return Journey Date");
			
			comboSelectByVisibleText(cmb_OutDateType, type, "Leaving or Arriving");
			String[] time = travelTime.split(":");
			comboSelectByValue(cmb_OutHours, time[0], "Hours");
			comboSelectByValue(cmb_OutMinutes, time[1], "Minutes");
			
		}catch(Exception e)
		{
			Helper.handleTestFaliure(e, this.getClass().getSimpleName());
		}
	}
	
	public void clickSearchTrains()
	{
		try
		{
			if(isDisplayed(btn_Search)) {
				clickElement(btn_Search, "Search Trains");
				hardDelay(2000);
			}
			else
				throw new Exception("Search Button not found");
		}catch(Exception e)
		{
			Helper.handleTestFaliure(e, this.getClass().getSimpleName());
		}
	}
	
}
