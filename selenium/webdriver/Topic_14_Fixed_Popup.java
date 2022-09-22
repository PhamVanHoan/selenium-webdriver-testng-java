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

public class Topic_14_Fixed_Popup {

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

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	public void TC_01_NgoaiNgu24h() {
		driver.get("https://ngoaingu24h.vn/");
		By loginPopup = By.xpath("(//div[@id='modal-login-v1'])[1]");

		// Mới vào page, popup không hiển thị
		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());

		// Click button login
		driver.findElement(By.cssSelector("button.login_")).click();

		// popup hiển thị
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());

		// Nhập thông tin không hợp lệ
		driver.findElement(By.cssSelector("div#modal-login-v1[style] input#account-input"))
				.sendKeys("Automationtesting");
		driver.findElement(By.cssSelector("div#modal-login-v1[style] input#password-input"))
				.sendKeys("Automationtesting");
		driver.findElement(By.cssSelector("div#modal-login-v1[style] button.btn-login-v1")).click();

		Assert.assertEquals(
				driver.findElement(By.cssSelector("div#modal-login-v1[style] div.error-login-panel")).getText(),
				"Tài khoản không tồn tại!");
	}

	// @Test
	public void TC_02_Tiki() {
		driver.get("https://tiki.vn/");

		// Lúc mới mở chưa có popup trong HTML/DOM
		Assert.assertEquals(driver.findElements(By.cssSelector("div[role='dialog']")).size(), 0);

		// Click vào Đăng nhập

		driver.findElement(By.cssSelector("span[class*='Userstyle__ItemText']")).click();

		// Verify popup hiển thị
		Assert.assertTrue(driver.findElement(By.cssSelector("div[role='dialog']")).isDisplayed());

		// Click vào đăng nhập bằng emal
		driver.findElement(By.cssSelector("p.login-with-email")).click();

		// Click Đăng nhập button
		driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();

		// Verify 2 message hiển thị

		Assert.assertTrue(
				driver.findElement(By.xpath("//span[@class='error-mess' and text()='Email không được để trống']"))
						.isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//span[@class='error-mess' and text()='Mật khẩu không được để trống']"))
						.isDisplayed());

		sleepInSecond(2);
		// Close popup
		driver.findElement(By.cssSelector("img.close-img")).click();

	}

	@Test
	public void TC_03_Facebook() {
		driver.get("https://www.facebook.com/");

		driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();

		Assert.assertTrue(
				driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/parent::div")).isDisplayed());

		// Close poup
		driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/parent::div/img")).click();

		Assert.assertEquals(driver.findElements(By.xpath("//div[text()='Sign Up']/parent::div/parent::div")).size(), 0);

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
