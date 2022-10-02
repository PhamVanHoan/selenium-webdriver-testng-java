package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Frame_Iframe {

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

	// @Test
	public void TC_01_Kyna_Iframe() {
		driver.get("https://kyna.vn/");

		// Verify Facebook Iframe hiển thị
		Assert.assertTrue(driver.findElement(By.xpath("//iframe[contains(@src, 'facebook')]")).isDisplayed());

		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src, 'facebook')]")));
		Assert.assertEquals(
				driver.findElement(By.xpath("//a[text()='Kyna.vn']/parent::div/following-sibling::div")).getText(),
				"166K likes");
		sleepInSecond(2);

		driver.switchTo().defaultContent();

		driver.switchTo().frame("cs_chat_iframe");
		sleepInSecond(2);

		driver.findElement(By.cssSelector("div.button_bar")).click();
		driver.findElement(By.cssSelector("input.input_name")).sendKeys("John");
		driver.findElement(By.cssSelector("input.input_phone")).sendKeys("0123456798");
		new Select(driver.findElement(By.cssSelector("select#serviceSelect"))).selectByVisibleText("TƯ VẤN TUYỂN SINH");
		driver.findElement(By.cssSelector("textarea.meshim_widget_widgets_TextArea")).sendKeys("Tôi cần tư vấn");
		sleepInSecond(2);

		driver.switchTo().defaultContent();

		driver.findElement(By.cssSelector("input#live-search-bar")).sendKeys("Excel");
		driver.findElement(By.cssSelector("button.search-button")).click();

		sleepInSecond(2);

		List<WebElement> searchResults = driver.findElements(By.cssSelector("ul.k-box-card-list h4"));

		for (WebElement searchResult : searchResults) {
			Assert.assertTrue(searchResult.getText().contains("Excel"));
		}

	}

	@Test
	public void TC_02_HDFC_Frame() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");

		driver.switchTo().frame("login_page");

		driver.findElement(By.name("fldLoginUserId")).sendKeys("jonh2022");

		driver.findElement(By.cssSelector("a.login-btn")).click();

		Assert.assertTrue(driver.findElement(By.cssSelector("input#fldPasswordDispId")).isDisplayed());

		driver.findElement(By.cssSelector("input#fldPasswordDispId")).sendKeys("12345678");

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
