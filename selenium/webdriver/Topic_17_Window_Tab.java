package webdriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Window_Tab {

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

	
	public void TC_01_Basic_Form() {
		// Driver đang ở trang A
		driver.get("https://automationfc.github.io/basic-form/index.html");

		// Vừa mở ra nó chỉ có duy nhất 1 tab
		String formTabID = driver.getWindowHandle();
		System.out.println("Form Tab ID = " + formTabID);

		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();

		switchToWindowByID(formTabID);

		// Driver đang ở trang B
		driver.findElement(By.name("q")).sendKeys("Selenium");
		String googleTabID = driver.getWindowHandle();

		// Quay về trang A
		switchToWindowByID(googleTabID);

		// Driver đang ở trang A
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();

		// Switch sang trang C
		switchToWindowByTitle("Facebook – log in or sign up");
		
		closeAllWindowWithoutParent(formTabID);
	}


	public void TC_02_TechPanda() {
		driver.get("http://live.techpanda.org/index.php/mobile.html");

		driver.findElement(By.xpath(
				"//a[text()='IPhone']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']"))
				.click();
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),
				"The product IPhone has been added to comparison list.");

		driver.findElement(By.xpath(
				"//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']"))
				.click();
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),
				"The product Samsung Galaxy has been added to comparison list.");

		driver.findElement(By.cssSelector("button[title='Compare']")).click();

		switchToWindowByTitle("Products Comparison List - Magento Commerce");
		sleepInSecond(3);

		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Compare Products']")).isDisplayed());

		driver.findElement(By.cssSelector("button[title='Close Window']")).click();

		// Switch qua Mobile
		switchToWindowByTitle("Mobile");

		driver.findElement(By.cssSelector("input#search")).sendKeys("Mobile");
	}

	@Test
	public void TC_03_Cam_Dic() {
		driver.get("https://dictionary.cambridge.org/vi/");
	
		driver.findElement(By.cssSelector("span.cdo-login-button")).click();
		switchToWindowByTitle("Login");
		sleepInSecond(5);
		
		driver.findElement(By.cssSelector("div#login_content input.gigya-input-submit")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("div#login_content span[data-bound-to = 'loginID']")).getText(), "This field is required");
		Assert.assertEquals(driver.findElement(By.cssSelector("div#login_content span[data-bound-to = 'password']")).getText(), "This field is required");
		
		
		driver.findElement(By.cssSelector("div#login_content input[name='username']")).sendKeys("automationfc.com@gmail.com");
		driver.findElement(By.cssSelector("div#login_content input[name='password']")).sendKeys("Automation000***");
		driver.findElement(By.cssSelector("div#login_content input.gigya-input-submit")).click();
		
		switchToWindowByTitle("Cambridge Dictionary | Từ điển tiếng Anh, Bản dịch & Từ điển từ đồng nghĩa");
		
		Assert.assertEquals(driver.findElement(By.cssSelector("span.cdo-username")).getText(), "Automation FC");
	}

	// Chỉ dùng duy nhất cho 2 tab/Windows
	public void switchToWindowByID(String parentID) {

		// Lấy all các ID của Tab/Window đang có
		Set<String> allWindowIDs = driver.getWindowHandles();

		// Dùng vòng lặp for để duyệt qua từng ID
		for (String id : allWindowIDs) {
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
				break;
			}
		}
	}

	// Chỉ dùng được cho 2 hoặc nhiều hơn tab/Windows
	public void switchToWindowByTitle(String expcetedPageTitle) {

		// Lấy all các ID của Tab/Window đang có
		Set<String> allWindowIDs = driver.getWindowHandles();

		// Dùng vòng lặp for để duyệt qua từng ID
		for (String id : allWindowIDs) {
			driver.switchTo().window(id);
			String currentPageTitle = driver.getTitle();
			if (currentPageTitle.equals(expcetedPageTitle)) {
				// Thoát khỏi vòng lặp
				break;
			}
		}
	}

	public void closeAllWindowWithoutParent(String parentID) {
		// Lấy all các ID của Tab/Window đang có
		Set<String> allWindowIDs = driver.getWindowHandles();

		
		for (String id : allWindowIDs) {
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
				
				// Đóng tab đang active
				driver.close();
				
				// Đóng cả browser (không quan tâm bao nhiều tab/window)
				//driver.quit();
			}
		}
		
		//Vẫn còn lại parent
		driver.switchTo().window(parentID);
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
