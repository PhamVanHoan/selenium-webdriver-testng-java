package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Button {

	// Khai báo
	WebDriver driver;
	WebDriverWait explicitWait;

	// Khai báo + khởi tạo
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac")) {// Mac
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver.exe");

		} else // Windows

		{
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}

		// Khởi tạo driver
		driver = new FirefoxDriver();

		// Khởi tạo wait
		explicitWait = new WebDriverWait(driver, 30);

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_Fahasa() {
		driver.get("https://www.fahasa.com/customer/account/create");
		sleepInSecond(4);

		/*
		 * //Switch qua iframe trước khi Close popup (do popup nằm trong iframe)
		 * driver.switchTo().frame("preview-notification-frame");
		 * 
		 * //Close popup
		 * driver.findElement(By.cssSelector("a#NC_CLOSE_ICON>img")).click();
		 * sleepInSecond(2);
		 * 
		 * // Quay về trang trước đó chứa iframe driver.switchTo().defaultContent();
		 */

		// Chuyển qua tab login
		driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();

		// Verify Đăng nhập button is disable
		Assert.assertFalse(driver.findElement(By.cssSelector("button.fhs-btn-login")).isEnabled());

		// Enter value to Email and Password textbox
		driver.findElement(By.cssSelector("input#login_username")).sendKeys("a@gmail.com");
		driver.findElement(By.cssSelector("input#login_password")).sendKeys("123456");

		// Verify button Đăng nhập is enable
		Assert.assertTrue(driver.findElement(By.cssSelector("button.fhs-btn-login")).isEnabled());

		// Verify background color của Đăng nhập button
		String rgbaColor = driver.findElement(By.cssSelector("button.fhs-btn-login")).getCssValue("background-color");

		// rgbaColor convert to Hexa color
		String hexaColor = Color.fromString(rgbaColor).asHex().toUpperCase();

		// Verify back-ground color
		Assert.assertEquals(hexaColor, "#C92127");

	}

	@Test
	public void TC_02() {
	}

	@Test
	public void TC_03() {

		sleepInSecond(5);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	// Sleep cứng (Static wait)
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
