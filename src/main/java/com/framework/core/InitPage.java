package com.framework.core;

import org.openqa.selenium.WebDriver;
import com.framework.fileReader.PropertiesFileReader;
import com.framework.pages.Booking;
import com.framework.pages.HomePage;
import com.framework.pages.Payment;


public class InitPage {
	
	private WebDriver driver;
	protected PropertiesFileReader fileReader;
	private HomePage homePage;
	private Booking bookingPage;
	private Payment paymentPage;

	public InitPage(WebDriver driver, PropertiesFileReader fileReader) {
		this.driver = driver;
		this.fileReader = fileReader;
	}
	
	public HomePage homePage()
	{
		return homePage = homePage != null ? homePage : new HomePage(driver, fileReader);
	}
	
	public Booking bookingPage()
	{
		return bookingPage = bookingPage != null ? bookingPage : new Booking(driver, fileReader);
	}
	
	public Payment paymentPage()
	{
		return paymentPage = paymentPage != null ? paymentPage : new Payment(driver, fileReader);
	}
}
