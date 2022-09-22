package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_Random_Popup {

	// Khai báo
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;

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
	public void TC_01_Java_Code_Geeks() {
		driver.get("https://www.javacodegeeks.com/");

		WebElement popup = driver.findElement(By.xpath("//div[@class='lepopup-popup-container']/div[1]"));
		if (popup.isDisplayed()) {
			driver.findElement(By.cssSelector("div.lepopup-input>input[type='email']"))
					.sendKeys(getRandomEmailAddress());
			driver.findElement(By.cssSelector("a[data-label='Get the Books']")).click();
			sleepInSecond(2);
		}

		// Verify popup không hiển thị
		Assert.assertFalse(popup.isDisplayed());
		sleepInSecond(2);

		driver.findElement(By.cssSelector("input#s")).sendKeys("Agile Testing Explained");
		sleepInSecond(2);
		driver.findElement(By.cssSelector("button.search-button")).click();
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//h2[@class= 'post-title']/a[text()='Agile Testing Explained']"))
				.isDisplayed());

	}

	// @Test
	public void TC_02_KMPlayer_In_HTML_DOM() {
		driver.get("https://www.kmplayer.com/home");

		WebElement popup = driver.findElement(By.cssSelector("img#support-home"));

		if (popup.isDisplayed()) {
			jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("area#btn-r")));
		}

		driver.findElement(By.xpath("//li/a[text()='PC 64X']")).click();
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='KMPlayer 64X']")).isDisplayed());
	}

	@Test
	public void TC_03_De_Hieu_Not_In_HTML_DOM() {

		driver.get("https://dehieu.vn/");

		List<WebElement> popup = driver.findElements(By.cssSelector("div.popup-content"));

		if (popup.size() > 0 && popup.get(0).isDisplayed()) {
			driver.findElement(By.cssSelector("input#popup-name")).sendKeys("Jonh");
			driver.findElement(By.cssSelector("input#popup-email")).sendKeys(getRandomEmailAddress());
			driver.findElement(By.cssSelector("input#popup-phone")).sendKeys("0987654321");
			sleepInSecond(2);

			driver.findElement(By.cssSelector("button#close-popup")).click();
			sleepInSecond(2);
		}

		driver.findElement(By.xpath("//a[text()='Tất cả khóa học']")).click();
		sleepInSecond(2);

		driver.findElement(By.cssSelector("input#search-courses")).sendKeys("Khóa học Thiết kế tủ điện");
		driver.findElement(By.cssSelector("i.fa-search")).click();
		sleepInSecond(2);

		Assert.assertTrue(driver.findElement(By.xpath("//h4[text()='Khóa học Thiết kế tủ điện']")).isDisplayed());

	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

	public String getRandomEmailAddress() {
		Random rand = new Random();
		return "jonh" + rand.nextInt(9999) + "@hotmail.com";

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
