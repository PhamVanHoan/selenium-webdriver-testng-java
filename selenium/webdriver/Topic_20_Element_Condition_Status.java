package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_20_Element_Condition_Status {

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
		explicitWait = new WebDriverWait(driver, 10);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	public void TC_01_Visible_Displayed_Visibility() {
		driver.get("https://www.facebook.com/");

		// 1. Có trên UI (bắt buộc)
		// 1. Có trong HTML (bắt buộc)

		// Wait cho Email address textbox hiển thị
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		driver.findElement(By.id("email")).sendKeys("automatiomn@gmail.net");
	}

	public void TC_02_Invisible_Undisplayed_Invisibility_I() {
		driver.get("https://www.facebook.com/");

		driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();

		// 2. Không có trên UI (bắt buộc)
		// 1. Có trong HTML

		// Chờ cho Re-enter Email textbox không hiển thị trong vòng 10s
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.name("reg_email_confirmation__")));

	}

	public void TC_02_Invisible_Undisplayed_Invisibility_II() {
		driver.get("https://www.facebook.com/");

		// 2. Không có trên UI (bắt buộc)
		// 2. Không Có trong HTML

		// Chờ cho Re-enter Email textbox không hiển thị trong vòng 10s
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.name("reg_email_confirmation__")));

	}

	public void TC_03_Presence_I() {
		driver.get("https://www.facebook.com/");

		// 1. Có trên UI
		// 1. Có trong HTML (bắt buộc)

		// Wait cho Email address textbox presence trong HTML
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
		driver.findElement(By.id("email")).sendKeys("automatiomn@gmail.net");
	}

	public void TC_03_Presence_II() {
		driver.get("https://www.facebook.com/");

		driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();

		// 2. Không có trên UI
		// 1. Có trong HTML

		// Chờ cho Re-enter Email textbox không hiển thị trong vòng 10s
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.name("reg_email_confirmation__")));

	}

	public void TC_04_Staleless() {
		// 2. Không có trên UI
		// 2. Không Có trong HTML
		driver.get("https://www.facebook.com/");
		driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();

		// Phase 1
		WebElement reEnterEmailAddress = driver.findElement(By.name("reg_email_confirmation__"));

		// Thao tác với element khác làm cho element re-enter email ko còn trong DOM nữa

		// Close popup đi
		driver.findElement(By.cssSelector("img._8idr")).click();

		// Chờ cho Re-enter Email textbox không còn trong DOM
		explicitWait.until(ExpectedConditions.stalenessOf(reEnterEmailAddress));

	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
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
