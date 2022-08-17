package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Browser {
	WebDriver driver;
	WebElement element;

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

		// Khởi tạo
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_Browser() {
		// Các hàm tương tác với Browser sẽ thông qua biến driver

		// Đóng tab hiện tại trên browser
		driver.close(); //**

		// Đóng hẳn browser (close all tabs)
		driver.quit(); //**

		// Tìm ra 1 element (single)
		WebElement loginButton = driver.findElement(By.cssSelector("")); //**

		// Tìm ra nhiều element (multiple)
		List<WebElement> links = driver.findElements(By.cssSelector("")); //**

		// Mở ra Url truyền vào
		driver.get("https://www.facebook.com/"); //**

		// Trả về 1 Url tại page đang đứng
		String gamePageUrl = driver.getCurrentUrl();

		// Lấy title của Page
		String gamePAgeTitle = driver.getTitle();

		// Source code của page hiện tại
		driver.getPageSource();

		// Lấy ra cái ID của tab/ window đang đứng/ active (Windows/TAb)
		driver.getWindowHandle(); // trả ra 1  //**
		driver.getWindowHandles(); // trả ra tất cả //**

		driver.manage().getCookies(); // Cookies (Framework) //**
		driver.manage().logs().getAvailableLogTypes();// Logs (Framework)

		driver.manage().window().fullscreen();
		driver.manage().window().maximize(); //**

		// Test GUI (Graphic User Interface)
		// Font/ Size/ Color/ Position/ Location
		// Ưu tiên làm chức năng trước (Functional UI)
		// Làm giao diện sau (Graphic UI)

		// Chờ cho element được tìm thấy trong khoảng time x giây (WebDriverWait) 
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		// Chờ cho page được load thành công sau x giây
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);

		// Chờ cho script được inject thành công vào browser/ element sau x giây (JavascriptExecutor)
		driver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);
		
		//
		driver.navigate().back();
		driver.navigate().forward();
		driver.navigate().refresh();
		driver.navigate().to("https://www.facebook.com/");
		
		
		//Alert/ Frame(Iframe)/ Windows(Tab)
		driver.switchTo().alert();
		driver.switchTo().frame(0);
		driver.switchTo().window("");
		
	}

	@Test
	public void TC_02_Element() {
		// Các hàm tương tác với Element sẽ thông qua biến element
	}

	@Test
	public void TC_03() {
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
