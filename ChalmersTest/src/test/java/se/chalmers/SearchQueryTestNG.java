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

/*
 * Testcase
 * Search query for Stefan Bengtsson
 * 
 */
	public class SearchQueryTestNG {
			
	private WebDriver driver;
	private String baseUrl;
	ExtentReports extent = new ExtentReports();
	
	@BeforeTest
	public void setUp() throws Exception {
		
		System.setProperty("webdriver.gecko.driver","C:\\Selenium\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		baseUrl = "https://uat.portal.chalmers.se";
		
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("SearchQueryReport.html");
		extent.attachReporter(htmlReporter);
		htmlReporter.setAppendExisting(true);
		
	}
	
	@Test
	public void Start() throws Exception {
		
		// Opens Firefox goes to Uat
		driver.get(baseUrl);
		// Wait until element is visible
		WebDriverWait wait = new WebDriverWait(driver,15);
		
		ExtentTest Step1=extent.createTest("Search Query");
		
		try {
		wait.until(ExpectedConditions.titleContains("Chalmers tekniska högskola"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_ctl43_ctl00_search_all_chalmers_se_sv_field")));
		}
		catch (TimeoutException e) {
			
			Step1.fail("Couldn't verify title");
		}
		
		// Type Stefan Bengtsson in the search field
		
		ExtentTest Step2=extent.createTest("Type Stefan Bengtsson");
		
		try {
		driver.findElement(By.id("ctl00_ctl43_ctl00_search_all_chalmers_se_sv_field")).sendKeys("Stefan Bengtsson");
		}
		
		catch (TimeoutException e)
		
		{
			
			Step2.fail("Couldn't enter name Stefan Bengtsson");
		}
		
		// Hits Enter
		
		driver.findElement(By.id("ctl00_ctl43_ctl00_search_all_chalmers_se_sv_field")).sendKeys(Keys.RETURN);
		
		
		// Wait until Stefan Bengtsson results are visible
		ExtentTest Step3=extent.createTest("Wait until Stefan Bengtsson results are visible");
		
		try {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".titleLink.clickLogger.resultTitle>b")));
		}
		catch (TimeoutException e) {
			
			Step3.fail("Couldnt find Stefan Bengtsson search result");
		}
		
		// Clicks on the title for Stefan Bengtssons profilepage
		
		ExtentTest Step4=extent.createTest("Clicks on title for Stefan Bengtssons profilepage");
		
		try {
		driver.findElement(By.cssSelector(".titleLink.clickLogger.resultTitle>b")).click();
		}
		catch (TimeoutException e) {
			
			Step4.fail("Couldn't click on title for Stefan Bengtsson");
		}
	}
	@AfterTest
	public void Quit() throws Exception {
		// Quits the Driver
		driver.quit();
		
		extent.flush();
	}
}
