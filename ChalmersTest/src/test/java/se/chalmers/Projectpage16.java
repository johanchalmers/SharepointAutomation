package se.chalmers;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Projectpage16 {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
	System.setProperty("webdriver.gecko.driver","C:\\Selenium\\geckodriver.exe");
    driver = new FirefoxDriver();
    baseUrl = "https://uat.portal.chalmers.se/sv/projekt/Sidor/Balance-4P.aspx";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testProjekt16() throws Exception {
	  
	  driver.get(baseUrl);
    assertEquals("Fyra faktorer för balanserade beslut vid förnyelse av tidigare exploaterad mark i städer – människor, miljö, lönsamhet och processer", driver.findElement(By.cssSelector("h1.chalmersElement-H1")).getText());
    try {
      assertEquals("Jenny Norrman", driver.findElement(By.cssSelector("div.header")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    assertEquals("Jaan-Henrik Kain", driver.findElement(By.xpath("//ul[@id='profileList']/li[2]/a/div[2]/div")).getText());
  }

  @After
  public void tearDown() throws Exception {
    // driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
