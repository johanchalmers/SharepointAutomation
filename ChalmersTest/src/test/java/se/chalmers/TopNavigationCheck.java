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
 * Verifies that the TopNavigation is present
 * 
 */


public class TopNavigationCheck {
	
	// Add Variables
	
	private WebDriver driver;
	private String baseURL;
	
	@Before
	
	public void setUp() throws Exception {
		
		System.setProperty("webdriver.gecko.driver","C:\\Selenium\\geckodriver.exe");
		baseURL = "https://uat.portal.chalmers.se";
		driver = new FirefoxDriver();
		
	}
	
	@Test
	
	public void Start() throws Exception {
		
		WebDriverWait wait = new WebDriverWait(driver,15);
		// Opens firefox , goes to UAT
		driver.get(baseURL);
		// Wait  and verifies that the title appears
		wait.until(ExpectedConditions.titleContains("Chalmers tekniska högskola"));
		// Wait and verifies for the top navigation menu
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".nav.navbar-nav.hidden-xs.hidden-sm.desktop-nav>li>a")));
	
		
		
	}
	
	@After
	
	public void Quit() throws Exception {
		
		driver.quit();
	}

}
