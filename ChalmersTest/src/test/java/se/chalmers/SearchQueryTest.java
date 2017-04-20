package se.chalmers;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/*
 * Testcase
 * Search query for Stefan Bengtsson
 * 
 */
	public class SearchQueryTest {
			
	private WebDriver driver;
	private String baseUrl;
	
	@Before
	public void setUp() throws Exception {
		
		System.setProperty("webdriver.gecko.driver","C:\\Selenium\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		baseUrl = "https://uat.portal.chalmers.se";
		
		
		
	}
	
	@Test
	public void Start() throws Exception {
		
		// Opens Firefox goes to Uat
		driver.get(baseUrl);
		// Wait until element is visible
		WebDriverWait wait = new WebDriverWait(driver,15);
		wait.until(ExpectedConditions.titleContains("Chalmers tekniska högskola"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_ctl43_ctl00_search_all_chalmers_se_sv_field")));
		
		// Type Stefan Bengtsson in the search field
		driver.findElement(By.id("ctl00_ctl43_ctl00_search_all_chalmers_se_sv_field")).sendKeys("Stefan Bengtsson");
		
		// Hits Enter
		driver.findElement(By.id("ctl00_ctl43_ctl00_search_all_chalmers_se_sv_field")).sendKeys(Keys.RETURN);
		
		// Wait until Stefan Bengtsson results are visible
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".titleLink.clickLogger.resultTitle>b")));
		
		// Clicks on the title for Stefan Bengtssons profilepage
		driver.findElement(By.cssSelector(".titleLink.clickLogger.resultTitle>b")).click();
	}
	@After
	public void Quit() throws Exception {
		// Quits the Driver
		driver.quit();
	}
}
