package stepdefs;


import base.SourceTest;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class BookFlightSteps extends SourceTest{

	@Given("^user is on the trainline homepage$")
	public void user_is_on_the_trainline_homepage() throws Throwable {
		initPage.homePage();
	}
	
	@Then("^user login to the website using the credentials \"([^\"]*)\" and \"([^\"]*)\"$")
	public void user_login_to_the_website_using_the_credentials_and(String arg1, String arg2) throws Throwable {
		initPage.homePage().login(arg1, arg2);
	}
	
	@When("^user enter the journey details$")
	public void user_enter_the_journey_details(DataTable arg1) throws Throwable {
	   initPage.homePage().enterJourneyDetails(arg1); 
	}
	
	@When("^click on get tickets$")
	public void click_getickets() throws Throwable {
		initPage.homePage().clickSearchTrains();
	   
	}

	@Then("^user should be taken to the search results page$")
	public void verify_search_results_page() throws Throwable {
	    initPage.bookingPage();
	}
	
	@Then("^validate journey details are displayed properly$")
	public void validate_journey_details(DataTable arg1) throws Throwable {
	    initPage.bookingPage().validateSearchCriteria(arg1);
	}
	
	@Then("^user click on continue button$")
	public void click_continue_button() throws Throwable {
		initPage.bookingPage().clickContinue();
	}

	@Then("^select seating preferences$")
	public void select_seating_preferences(DataTable arg1) throws Throwable {
		initPage.bookingPage().selectPreferences(arg1);
	}

	@Then("^user select delivery option \"([^\"]*)\"$")
	public void user_select_delivery_option(String arg1) throws Throwable {
		initPage.bookingPage().selectDeliveryOption(arg1);
	}

	@Then("^user should get redirected to payment page$")
	public void verify_payment_page() throws Throwable {
		initPage.paymentPage();
	}

	@Then("^get the reserved coach details$")
	public void get_the_reserved_coach_details() throws Throwable {
	   String coachInfo = initPage.paymentPage().getReservationDetails();
	   getScenario().write("Reserved Coach Details: "+coachInfo);
	}
}
