package se.chalmers;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.junit.*;
import org.junit.runner.JUnitCore;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;


public class FooterCheckerTest {
	
// Add Variables
	
	private WebDriver driver;
	private String baseURL;
	
	ExtentReports extent = new ExtentReports();
	
	@Before
	
	public void setUp () throws Exception {
	
		System.setProperty("webdriver.gecko.driver","C:\\Selenium\\geckodriver.exe");
		driver = new FirefoxDriver();
		baseURL = "https://uat.portal.chalmers.se";
		
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extent.html");
		extent.attachReporter(htmlReporter);
		htmlReporter.setAppendExisting(true);
		
	}
	
	
	@Test
	public void Start () throws Exception {
		
		ExtentTest Step1=extent.createTest("FooterCheckerTest");
		
		// Opens Firefox, goes to uat
		driver.get(baseURL);
		if (baseURL == null)
		{
			Step1.fail("Couldnt find baseurl");
			
		}
		
		WebDriverWait wait = new WebDriverWait (driver,20);
		// Waits & Verifys title
		
		ExtentTest Step2=extent.createTest("Step2");
		try {
		wait.until(ExpectedConditions.titleContains("Chalmers tekniska hggskola"));
		}
		
		catch (Throwable e) {
			
			Step2.fail("Couldn't find Title of the page");
		}
		// Waits & Verifys for the 4 different h4 in the footer
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".list.footer>h4")));
		// Clicks on Energi under Styrkeområden-h4
		driver.findElement(By.xpath(".//*[@id='s4-bodyContainer']/footer/div/div[2]/div[1]/ul/li[1]/a")).click();
		
		// Waits for h3 Energi on their landing page
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".text>h3")));

	
		
		
		
	}
	@After
	public void Quit () throws Exception {
		
		// Quits the Driver
		driver.quit();
		extent.flush();
		
	}
	
	

}
