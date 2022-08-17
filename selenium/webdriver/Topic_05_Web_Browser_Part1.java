package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Browser_Part1 {
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
		driver.close(); // **

		// Đóng hẳn browser (close all tabs)
		driver.quit(); // **

		// Tìm ra 1 element (single)
		WebElement loginButton = driver.findElement(By.cssSelector("")); // **

		// Tìm ra nhiều element (multiple)
		List<WebElement> links = driver.findElements(By.cssSelector("")); // **

		// Mở ra Url truyền vào
		driver.get("https://www.facebook.com/"); // **

		// Trả về 1 Url tại page đang đứng
		String gamePageUrl = driver.getCurrentUrl();

		// Lấy title của Page
		String gamePAgeTitle = driver.getTitle();

		// Source code của page hiện tại
		driver.getPageSource();

		// Lấy ra cái ID của tab/ window đang đứng/ active (Windows/TAb)
		driver.getWindowHandle(); // trả ra 1 //**
		driver.getWindowHandles(); // trả ra tất cả //**

		driver.manage().getCookies(); // Cookies (Framework) //**
		driver.manage().logs().getAvailableLogTypes();// Logs (Framework)

		driver.manage().window().fullscreen();
		driver.manage().window().maximize(); // **

		// Test GUI (Graphic User Interface)
		// Font/ Size/ Color/ Position/ Location
		// Ưu tiên làm chức năng trước (Functional UI)
		// Làm giao diện sau (Graphic UI)

		// Chờ cho element được tìm thấy trong khoảng time x giây (WebDriverWait)
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		// Chờ cho page được load thành công sau x giây
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);

		// Chờ cho script được inject thành công vào browser/ element sau x giây
		// (JavascriptExecutor)
		driver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);

		//
		driver.navigate().back();
		driver.navigate().forward();
		driver.navigate().refresh();
		driver.navigate().to("https://www.facebook.com/");

		// Alert/ Frame(Iframe)/ Windows(Tab)
		driver.switchTo().alert();
		driver.switchTo().frame(0);
		driver.switchTo().window("");

	}

	@Test
	public void TC_02_Element() {
		// Các hàm tương tác với Element sẽ thông qua cái class WebElement (đại diện cho
		// biến nào đó)

		// 2 cách để thao tác với element
		// 1 - Khai báo biến và dùng lại (dùng đi dùng lại nhiều lần, ít nhất 2 lần thì
		// mời cần khai báo biến)

		// Khai báo biến cùng kiểu với dữ liệu trả về của hàm findElement
		WebElement emailAddressTextbox = driver.findElement(By.id("email"));
		emailAddressTextbox.clear();
		emailAddressTextbox.sendKeys("abc@gmail.com");

		// Dùng trực tiếp - chỉ dùng 1 lần
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("abc@gmail.com");

		// Xóa dữ liệu trong 1 field dạng editable (có thể nhập)
		// Textbox/ Text area/Editable dropdown
		element.clear();
		
		
		// Click vào những element: Button/ Link/ Checkbox/ Radio/ Image ..
		element.click();

		// Nhập dữ liệu vào field dạng editanle
		element.sendKeys("a@gmail.com");
		element.sendKeys(Keys.ENTER);

		// Lấy ra giá tri nằm trong attribute của element
		element.getAttribute("placeholder");
		// Email address or phone number

		// <input type="text" class="inputtext _55r1 _6luy"
		// name="email" id="email" data-testid="royal_email"
		// placeholder="Email address or phone number" autofocus="1"
		// aria-label="Email address or phone number">

		driver.findElement(By.id("firstname")).getAttribute("value");

		// Trả về thuộc tính của element
		// Font/ size/ color

		// Trả về màu nền của element
		element.getCssValue("background-color");
		element.getCssValue("font-size");

		// Test GUI: Point/ Rectangle/ Size
		element.getLocation();
		element.getRect();

		// Chụp hình và attach vào HTML report
		element.getScreenshotAs(OutputType.FILE);

		// Trả về thẻ HTML của element
		WebElement emailAddressTextBox = driver.findElement(By.xpath("//input[@id = 'email']"));
		emailAddressTextBox = driver.findElement(By.cssSelector("input[id = 'email']"));
		element.getTagName();
		
		
		// Trả về text của 1 element (Link/ header/ Message lỗi/ Message success..)
		element.getText();
		
		// Trả về giá trị đúng hoặc sai của 1 element có hiển thị hoặc không
		element.isDisplayed();
		// Hiển thị trả về true
		// Không hiển thị trả về faise
		
		// Trả về giá trị đúng hoặc sai của 1 element có thể thao tác được hay ko
		// Có bị disable ko
		// Enable : true
		// Disable: false
		element.isEnabled();
		
		// Trả về giá trị đúng hoặc sai của 1 element đã được chọn rồi hay chưa
		// Checkbox/ Radio button
		// Chọn rồi: true
		// Chưa chọn: failse
		element.isSelected();
		
		// Dropdown:có 1 thư viện riêng để xử lý (Select)
		
		
		// Chỉ làm được với form: Login/ Đăng ký/ Search...
		// Submit = ENTER ở 1 field nào đó
		// Submit vào 1 field nào đó trong form
		
		element.submit();
	}


	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
