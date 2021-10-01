package stepdefs;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.framework.core.InitPage;
import com.framework.core.LogGenerator;

import base.SourceTest;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks extends SourceTest{
	
	private static WebDriver driver;
	private Scenario scenario;
	
	@Before
	public void BeforeSteps(Scenario scenario) {
		this.scenario = scenario;
		setScenario(this.scenario);
		LogGenerator.startLog(this.scenario.getName());
		driverFactory.initializeDriver(browser);
		driver = driverFactory.getDriver();
		driver.get(appUrl);
		initPage = new InitPage(driver, fileReader);
	}

	@After
	public void AfterSteps() {
		if(scenario.isFailed())
		{
			try
			{
				scenario.embed(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES), "image/png");
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		driver.quit();
		LogGenerator.endLog(this.scenario.getName());
	}
}
