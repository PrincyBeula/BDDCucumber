package base;
import java.io.File;
import org.openqa.selenium.WebDriver;
import com.framework.core.DriverFactory;
import com.framework.core.Helper;
import com.framework.core.InitPage;
import com.framework.core.LogGenerator;
import com.framework.fileReader.PropertiesFileReader;

import cucumber.api.Scenario;

public class SourceTest {

	protected static PropertiesFileReader fileReader;
	protected static WebDriver driver;
	protected static Scenario scenario;
	public static DriverFactory driverFactory;
	public static InitPage initPage;
	public static String appUrl;
	public static String browser;
	public static LogGenerator logGenerator;
	public static Helper helper;

	/**
	 * Constructor
	 */
	public SourceTest() {
		fileReader = new PropertiesFileReader(System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "config.properties");
	}

	public void initialize() throws Exception {
		appUrl = fileReader.getProperty("applicationUrl");
		browser = fileReader.getProperty("browser");
		driverFactory = new DriverFactory();
		logGenerator = new LogGenerator();
		helper = new Helper();
		System.out.println("AppURL : "+appUrl);
		System.out.println("Browser : "+browser);
	}
	
	public void setScenario(Scenario sceanrio)
	{
		this.scenario = sceanrio;
	}
	
	public Scenario getScenario()
	{
		return scenario;
	}
	
}
