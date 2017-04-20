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


public class FooterCheckerTest {
	
// Add Variables
	
	private WebDriver driver;
	private String baseURL;
	
	@Before
	
	public void setUp () throws Exception {
	
		System.setProperty("webdriver.gecko.driver","C:\\Selenium\\geckodriver.exe");
		driver = new FirefoxDriver();
		baseURL = "https://uat.portal.chalmers.se";
		
		
	}
	
	
	@Test
	public void Start () throws Exception {
		
		// Opens Firefox, goes to uat
		driver.get(baseURL);
		WebDriverWait wait = new WebDriverWait (driver,15);
		// Waits & Verifys title
		wait.until(ExpectedConditions.titleContains("Chalmers tekniska högskola"));
		// Waits & Verifys for the 4 different h4 in the footer
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".list.footer>h4")));
		// Clicks on Energi under Styrkeomr�den-h4
		driver.findElement(By.xpath(".//*[@id='s4-bodyContainer']/footer/div/div[2]/div[1]/ul/li[1]/a")).click();
		
		// Waits for h3 Energi on their landing page
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".text>h3")));
		
		
		
		
		
	}
	@After
	public void Quit () throws Exception {
		
		// Quits the Driver
		driver.quit();
	}
	
	
	

}
