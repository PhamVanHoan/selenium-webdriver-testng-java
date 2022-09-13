package testng;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import listener.ExtentReportListener;

@Listeners(ExtentReportListener.class)
public class Topic_09_Listener {
	public static WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_Register() {
		driver.get("http://live.techpanda.org/index.php/customer/account/create/");

		driver.findElement(By.id("firstname")).sendKeys("Auto");
		driver.findElement(By.id("lastname")).sendKeys("FC");
		
		String emailAddress = "hoan" +getRandomNumber()+"@hotmail.com";
		System.out.println(emailAddress);
		driver.findElement(By.id("email_address")).sendKeys(emailAddress);
		driver.findElement(By.id("password")).sendKeys("123123");
		driver.findElement(By.id("confirmation")).sendKeys("123123");
		driver.findElement(By.cssSelector("button[title='Register']")).click();
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='success-msg']//span[text()='Thank you for registering with Main Website Store.']")).isDisplayed());
	
	}

	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
