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
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;


public class FooterCheckerTestNG {
	
// Add Variables
	
	private WebDriver driver;
	private String baseURL;
	
	ExtentReports extent = new ExtentReports();
	
	
	@BeforeTest
	
	public void setUp () throws Exception {
	
		System.setProperty("webdriver.gecko.driver","C:\\Selenium\\geckodriver.exe");
		driver = new FirefoxDriver();
		baseURL = "https://uat.portal.chalmers.se";
		
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("ChalmersFooter.html");
		extent.attachReporter(htmlReporter);
		htmlReporter.setAppendExisting(true);
		
	}
	
	
	@Test
	public void Start () throws Exception {
		
		ExtentTest Step1=extent.createTest("FooterCheckerTest", "Checking the footer h4");
		
		
		// Opens Firefox, goes to uat
		if (baseURL == null)
		{
			Step1.fail("baseURL not found");
			
		}
		
		driver.get(baseURL);
		
		WebDriverWait wait = new WebDriverWait (driver,20);
		// Waits & Verifys title
		
		ExtentTest Step2=extent.createTest("Verifying title of the page");
		try {
		wait.until(ExpectedConditions.titleContains("Chalmers tekniska högskola"));
		}
		catch (TimeoutException e) {
			
			Step2.fail("Timed Out");
		}
		
		
		
		// Waits & Verifys for the 4 different h4 in the footer
		
		ExtentTest Step3=extent.createTest("Waits & Verifys for h4 in footer");
		try {
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".list.footer>h4")));
		}
		
		catch (TimeoutException e) {
			
			Step3.fail("Couldnt find h4 in footer");
			
		}
		// Clicks on Energi under Styrkeområden-h4
		
		ExtentTest Step4=extent.createTest("Clicks on eneri under styrkeområde-h4");
		try {
		driver.findElement(By.xpath(".//*[@id='s4-bodyContainer']/footer/div/div[2]/div[1]/ul/li[1]/a")).click();
		}
		catch (TimeoutException e) {
			
			Step4.fail("Couldn't Click");
		}
		
		
		// Waits for h3 Energi on their landing page
		
		ExtentTest Step5=extent.createTest("Waits for h3 Energi on landing page");
				
		try {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".text>h3")));
		}
		catch (TimeoutException e) {
			
			Step5.fail("Couldn't find h3 on Energi landing page");
		}
		
	
		
		
		
	}
	@AfterTest
	public void Quit () throws Exception {
		
		// Quits the Driver
		driver.quit();
		extent.flush();
	}
	
	

}
