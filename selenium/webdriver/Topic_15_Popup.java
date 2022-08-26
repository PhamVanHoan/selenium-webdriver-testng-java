package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_Popup {

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

		// Khởi tạo jsExecutor
		jsExecutor = (JavascriptExecutor) driver;

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	public void TC_01_Fixed_In_DOM_NgoaiNgu24h() {
		driver.get("https://ngoaingu24h.vn/");
		WebElement loginPopup = driver.findElement(By.xpath("//div[@id='modal-login-v1'][1]"));

		// Verify login popup không hiển thị
		Assert.assertFalse(loginPopup.isDisplayed());

		// Click button Đăng nhập
		driver.findElement(By.cssSelector("button.login_")).click();
		sleepInSecond(3);

		Assert.assertTrue(loginPopup.isDisplayed());

		driver.findElement(By.xpath("//div[@id='modal-login-v1'][1]//input[@id='account-input']"))
				.sendKeys("automationfc");
		driver.findElement(By.xpath("//div[@id='modal-login-v1'][1]//input[@id='password-input']"))
				.sendKeys("automationfc");
		driver.findElement(By.xpath("//div[@id='modal-login-v1'][1]//button[contains(@class,'btn-login-v1')]")).click();
		sleepInSecond(3);

		Assert.assertEquals(driver
				.findElement(By.xpath("//div[@id='modal-login-v1'][1]//div[@class='row error-login-panel']")).getText(),
				"Tài khoản không tồn tại!");

		// Click Close popup
		driver.findElement(By.xpath("//div[@id='modal-login-v1'][1]//button[@class='close']")).click();
		sleepInSecond(3);

		// Verify popup không hiển thị
		sleepInSecond(5);
		Assert.assertFalse(loginPopup.isDisplayed());

	}

	public void TC_02_Fixed_In_DOM() {

		driver.get("https://kyna.vn/");

		WebElement loginPopup = driver.findElement(By.cssSelector("div#k-popup-account-login"));

		// Verify Undisplay popup
		Assert.assertFalse(loginPopup.isDisplayed());

		// Click button Đăng nhập
		driver.findElement(By.cssSelector("a.login-btn")).click();
		sleepInSecond(2);

		// Display popup
		Assert.assertTrue(loginPopup.isDisplayed());

		driver.findElement(By.cssSelector("input#user-login")).sendKeys("Automationfc");
		driver.findElement(By.cssSelector("input#user-password")).sendKeys("Automationfc");
		driver.findElement(By.cssSelector("button#btn-submit-login")).click();
		sleepInSecond(2);

		Assert.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message")).getText(),
				"Sai tên đăng nhập hoặc mật khẩu");

		driver.findElement(By.cssSelector("button.k-popup-account-close")).click();

		Assert.assertFalse(loginPopup.isDisplayed());
	}

	public void TC_03_Fixed_Not_In_DOM_Tiki() {

		driver.get("https://tiki.vn/");

		// Khi mở trang ra thì popup không hề có trong DOM nên sẽ không thể findElement
		// được
		// show ra lỗi NoSuchElementException sau khoảng thời gian là xx giây
		// impliciWait
		// WebElement loginPopup =
		// driver.findElement(By.cssSelector("div.ReactModal__Content"));

		// Trong trường hợp popup không có trong DOM thì findElements này sẽ tìm thấy 0
		// Element
		// Và cũng chờ hết time out của impliciWait nhưng không có đánh fail testcase và
		// cũng không show exception nào hết
		// Chỉ trả về empty list => size = 0
		List<WebElement> loginPopup = driver.findElements(By.cssSelector("div.ReactModal__Content"));

		// Verify unDisplay popup
		Assert.assertEquals(loginPopup.size(), 0);

		// Click Đăng nhập/đăng ký
		driver.findElement(By.xpath("//span[text()='Đăng Nhập / Đăng Ký']")).click();

		// Verify Display popup with multiple element
		loginPopup = driver.findElements(By.cssSelector("div.ReactModal__Content"));
		Assert.assertEquals(loginPopup.size(), 1);
		Assert.assertTrue(loginPopup.get(0).isDisplayed());

		// Verify display popup with single element
		Assert.assertTrue(driver.findElement(By.cssSelector("div.ReactModal__Content")).isDisplayed());

		// Click để đóng Popup
		driver.findElement(By.cssSelector("img.close-img")).click();
		sleepInSecond(3);

		// Undisplayed
		loginPopup = driver.findElements(By.cssSelector("div.ReactModal__Content"));
		Assert.assertEquals(loginPopup.size(), 0);
	}

	public void TC_04_Random_In_DOM_KMPlayer() {

		driver.get("https://www.kmplayer.com/");

		WebElement popupLayer = driver.findElement(By.cssSelector("div.pop-layer"));

		// Phải luôn chạy được testcase dù popup có hiển thị hay không
		// Đang trong đợt sale nó hiển thị thì script mình phải đóng nó đi để chạy tiếp
		// Hết sale ko hiển thị thì script chạy luôn
		if (popupLayer.isDisplayed()) {
			System.out.println("Step close popup");
			// Close đi để chạy bước tiếp theo

			jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("area#btn-r")));
		}
		// Step tiếp theo
		System.out.println("Step tiếp theo");

		driver.findElement(By.cssSelector("p.donate-coffee")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.buymeacoffee.com/kmplayer?ref=hp&kind=top");
	}

	@Test
	public void TC_05_Random_Not_In_DOM_DeHieu() {
		driver.manage().window().setSize(new Dimension(1366, 768));
		driver.get("https://dehieu.vn/");
		
		sleepInSecond(5);
		
		// Phải luôn chạy được testcase dù popup có hiển thị hay không
		// Đang trong đợt sale nó hiển thị thì script mình phải đóng nó đi để chạy tiếp
		// Hết sale ko hiển thị thì script chạy luôn
		List <WebElement> contentPopup = driver.findElements(By.cssSelector("div.popup-content"));

	// Nếu element >0 thì close popup
		
		if(contentPopup.size()>0 && contentPopup.get(0).isDisplayed()) {
			driver.findElement(By.id("popup-name")).sendKeys("Automaution");
			driver.findElement(By.id("popup-email")).sendKeys("Automaution@a.com");
			driver.findElement(By.id("popup-phone")).sendKeys("09876554322");
			sleepInSecond(5);
			//Close Popup đi
			
			jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("button.close")));
		}
		
		//Step tiếp theo
		driver.findElement(By.xpath("//a[text()='Tất cả khóa học']")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "https://dehieu.vn/khoa-hoc");
		
		
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
