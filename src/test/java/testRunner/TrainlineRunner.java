package testRunner;

import cucumber.api.testng.AbstractTestNGCucumberTests;

import java.io.IOException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.framework.core.Helper;

import base.SourceTest;
import cucumber.api.CucumberOptions;

@CucumberOptions( 
		features = {"src/test/resources/features"},
	    glue = "stepdefs",
	    tags = "@Demo", 
	    plugin = {
	                    "pretty",
	                    "html:target/cucumber-reports/html",
	                    "json:target/cucumber-reports/CucumberTestReport.json",
	            },
	    monochrome=true,
	    strict=true,
	    dryRun=false
		)
public class TrainlineRunner extends AbstractTestNGCucumberTests{
	
	SourceTest srcTest = new SourceTest();
	
	@BeforeSuite
	public void setup()
	{
		System.out.println("*********************Execution Starts**************************");
		try {
			srcTest.initialize();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@AfterSuite
	public void tearDown()
	{
		try {
			Helper.OpenTestExecutionReport();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("*********************Execution Ends**************************");
	}
	
	
}
