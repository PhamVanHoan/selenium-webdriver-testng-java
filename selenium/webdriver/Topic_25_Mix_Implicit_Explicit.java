package webdriver;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_25_Mix_Implicit_Explicit {

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

		driver.manage().window().maximize();

	}

	public void TC_01_Element_Found() {

		// Element có xuất hiện và không cần chờ đến timeout
		// Dù có xét cả 2 loại wait cũng không ảnh hưởng

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 10);

		// Implicit wait chỉ apply cho tìm elememt
		// Explicit wait apply cho các điều kiện của element

		driver.get("https://www.facebook.com/");

		// Exlicit
		System.out.println("Thời gian bắt đầu explicit:" + getTimeStamp());
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email")));
		System.out.println("Thời gian kết thúc explicit:" + getTimeStamp());

		// Implicit
		System.out.println("Thời gian bắt đầu Implicit:" + getTimeStamp());
		driver.findElement(By.cssSelector("input#email"));
		System.out.println("Thời gian kết thúc Implicit:" + getTimeStamp());

	}

	public void TC_02_Element_Not_Found_Implicit() {

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get("https://www.facebook.com/");

		// Implicit
		System.out.println("Thời gian bắt đầu Implicit:" + getTimeStamp());
		try {
			driver.findElement(By.cssSelector("input#selenium"));
		} catch (Exception e) {
			System.out.println("Thời gian kết thúc Implicit:" + getTimeStamp());

		}

		// Output
		// Có cơ chế tìm lại sau mỗi 0,5s
		// Khi hết time out đánh fail testcase này
		// Throw ra exception: NoSuchElement
	}

	public void TC_03_Element_Not_Found_Implicit_Explicit() {
		// 3.1 Implicit = Explicit
		// 3.2 Implicit > Explicit => Tổng time = implicit
		// 3.3 Implicit < Explicit => Tổng time = Explicit + time chênh lệch

		// Implicit luôn được kích hoạt trước do ưu tiên tìm Element trước
		// Có đoạn chênh lệch khoảng 0-2s thì Explicit được kích hoạt

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 10);

		driver.get("https://www.facebook.com/");

		// Implicit
		System.out.println("Thời gian bắt đầu Implicit:" + getTimeStamp());
		try {
			driver.findElement(By.cssSelector("input#selenium"));
		} catch (Exception e) {
			System.out.println("Thời gian kết thúc Implicit:" + getTimeStamp());

		}

		// Exlicit
		System.out.println("Thời gian bắt đầu explicit:" + getTimeStamp());
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#selenium")));
		} catch (Exception e) {
			System.out.println("Thời gian kết thúc explicit:" + getTimeStamp());
		}

		// Output
		// Có cơ chế tìm lại sau mỗi 0,5s
		// Khi hết time out đánh fail testcase này
		// Throw ra exception: NoSuchElement
	}

	public void TC_04_Element_Not_Found_Explicit_By() {
		explicitWait = new WebDriverWait(driver, 10);

		driver.get("https://www.facebook.com/");

		// Explicit - By là tham số truyền vào của hàm explicit -
		// visibilityOfElementLocated(By)
		// Implicit = 0
		// Tổng time = Explicit + time chênh lệch

		System.out.println("Thời gian bắt đầu explicit:" + getTimeStamp());
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#selenium")));
		} catch (Exception e) {
			System.out.println("Thời gian kết thúc explicit:" + getTimeStamp());
		}

		// visibilityOfElementLocated: 2 bước => chạy cả Implicit sau đó Explicit
		// Tìm Element
		// Tìm visible
	}

	@Test
	public void TC_05_Element_Not_Found_Explicit_Element() {
		explicitWait = new WebDriverWait(driver, 10);

		driver.get("https://www.facebook.com/");

		// Explicit - By là tham số truyền vào của hàm explicit -
		// visibilityOfElementLocated(By)
		// Implicit = 0
		// Tổng time = Explicit + time chênh lệch

		System.out.println("Thời gian bắt đầu explicit:" + getTimeStamp());
		try {
			explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("input#selenium"))));
		} catch (Exception e) {
			System.out.println("Thời gian kết thúc explicit:" + getTimeStamp());
		}

		// visibilityOf
		// Tìm Element
		// Nếu Tìm Element pass thì mới chạy Visible
	}

	@Test
	public void TC_06_Real() {

		// Nếu chỉ dùng explicit thì phải Wait cho từng element trước khi action
		// Nên set implicit = explicit cho thực tế
		explicitWait = new WebDriverWait(driver, 10);

		driver.get("https://www.facebook.com/");

		// Wait cho email
		explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("input#selenium"))));
		// Sendkeys
		driver.findElement(By.cssSelector("input#selenium")).sendKeys("Email@gmail.com");

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	// Show ra time-stamp tại thời điểm gọi hàm
	// Time-stamp = ngày giờ phút giây
	public String getTimeStamp() {
		Date date = new Date();
		return date.toString();
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
