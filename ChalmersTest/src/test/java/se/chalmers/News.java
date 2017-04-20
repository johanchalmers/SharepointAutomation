package se.chalmers;
import java.util.regex.Pattern;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Testcase Create NewsPage Centrala Nyhetsfl�det
 * @author studt
 * 
 */

public class News {

	// Add Variables

	private WebDriver driver;
	private String baseURL;


	@Before

	public void setUp() throws Exception {

		System.setProperty("webdriver.gecko.driver","C:\\Selenium\\geckodriver.exe");
		baseURL = "https://admin-uat.portal.chalmers.se";
		driver = new FirefoxDriver();


	}

	@Test

	public void Start() throws Exception {

		WebDriverWait wait = new WebDriverWait(driver,15);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		// Opens firefox , goes to UAT
		driver.get(baseURL);

		// Maximize the window
		driver.manage().window().maximize();

		// Wait  and verifies that the title appears
		wait.until(ExpectedConditions.titleContains("Chalmers tekniska högskola"));

		// Waits for "Fler Nyheter"-button to appear
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='g_7fda04e8_8021_433a_b428_d7c69decc721-0']/div/a")));

		// Clicks on "Fler Nyheter"-button
		driver.findElement(By.xpath("//*[@id='g_7fda04e8_8021_433a_b428_d7c69decc721-0']/div/a")).click();

		// Waits for "Nyhetsartiklar" (title) to appear
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='ctl00_MSO_ContentDiv']/div[2]/div[2]/div/div[1]/div/h1")));

		// Waits for Site Action Menu to appear
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ms-menu-a"))).click();
		// Clicks on Site Action Menu
		driver.findElement(By.className("ms-menu-a")).click();

		// Waits for "New Page" option to appear
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='mp1_0_3_Anchor']")));

		// Clicks on "New Page"
		driver.findElement(By.xpath(".//*[@id='mp1_0_3_Anchor']")).click();

		// Switch the focus to the second iFrame
		driver.switchTo().frame(driver.findElements(By.tagName("iframe")).get(1));

		// Waits for the input field of the New Page iFrame to appear
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='ctl00_PlaceHolderMain_nameInput']")));

		// Clicks the input field.
		driver.findElement(By.xpath(".//*[@id='ctl00_PlaceHolderMain_nameInput']")).click();

		// Types into the input field "Test News"
		driver.findElement(By.xpath(".//*[@id='ctl00_PlaceHolderMain_nameInput']")).sendKeys("Test news Johan Automation " + timestamp.getTime());

		// Clicks on Create Page
		driver.findElement(By.id("ctl00_PlaceHolderMain_createButton")).click();

		//Focus on Main Window
		driver.switchTo().defaultContent();

		// Wait until Artikeldatum field is visible
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_PlaceHolderMain_MightBeUsedButCurrentlyNotInDesign_ChalmersArticleDateField_ctl00_DateTimeField_DateTimeFieldDate")));

		// Types date into Artikeldatum field
		driver.findElement(By.id("ctl00_PlaceHolderMain_MightBeUsedButCurrentlyNotInDesign_ChalmersArticleDateField_ctl00_DateTimeField_DateTimeFieldDate")).sendKeys("2017-03-23");

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_PlaceHolderMain_MightBeUsedButCurrentlyNotInDesign_ctl00_RichHtmlControl_RichHtmlField_EmptyHtmlPanel")));

		Thread.sleep(5000);
		//Clicks the Ingress field
		driver.findElement(By.id("ctl00_PlaceHolderMain_MightBeUsedButCurrentlyNotInDesign_ctl00_RichHtmlControl_RichHtmlField_EmptyHtmlPanel")).click();
		Thread.sleep(5000);
		// Writes "Hej"
		driver.findElement(By.id("ctl00_PlaceHolderMain_MightBeUsedButCurrentlyNotInDesign_ctl00_RichHtmlControl_RichHtmlField_displayContent")).sendKeys("Hej");

		// Clicks taxonomy field
		driver.findElement(By.id("ctl00_PlaceHolderMain_MightBeUsedButCurrentlyNotInDesign_ctl03_ctl02editableRegion")).click();
		// Writes "Global"
		driver.findElement(By.id("ctl00_PlaceHolderMain_MightBeUsedButCurrentlyNotInDesign_ctl03_ctl02editableRegion")).sendKeys("Global;");
		Thread.sleep(3000);
		// Clicks Save & Cklose
		driver.findElement(By.id("ctl00_PageStateActionButton")).click();

		Thread.sleep(5000);
		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='Ribbon.PublishTab-title']/a/span[1]")));

		// Clicks Publication flik/tab
		driver.findElement(By.xpath("//*[@id='Ribbon.PublishTab-title']/a/span[1]")).click();

		// Click Publish
		Thread.sleep(5000);
		driver.findElement(By.xpath(".//*[@id='Ribbon.PublishTab.Publishing.Publish-SelectedItem']/span[1]")).click();

		// Check in comment
		driver.findElement(By.id("checkincomments")).sendKeys("Automated Test");

		// Continue button

		driver.findElement(By.id("statechangedialog_okbutton")).click();





	}
	@After
	public void Quit() throws Exception {
		// Quits the driver
		//driver.quit();
	}

}
