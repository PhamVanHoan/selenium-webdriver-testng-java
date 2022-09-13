package webdriver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_Actions_Part1 {

	// Khai báo
	WebDriver driver;
	WebDriverWait explicitWait;
	Actions action;
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

		// Khởi tạo action

		action = new Actions(driver);

		// Khởi tạo jsExecutor
		jsExecutor = (JavascriptExecutor) driver;

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	public void TC_01_Hover() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");

		// Hover to element
		action.moveToElement(driver.findElement(By.cssSelector("input#age"))).perform();

		Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")),
				"We ask for your age only for statistical purposes.");
	}

	public void TC_02_Hover() {
		driver.get("https://www.myntra.com/");

		// Hover to element
		action.moveToElement(driver.findElement(By.cssSelector("//header//a[text()='Kids']"))).perform();

		sleepInSecond(3);

		driver.findElement(By.cssSelector("//a[text()='Home & Bath']")).click();

		Assert.assertEquals(driver.findElement(By.cssSelector("span.breadcrumbs-crumb")).getText(), "Kids Home Bath");

	}
@Test
	public void TC_03_Fahasa() {
		driver.get("https://www.fahasa.com/");

		// Hover vào icon menu
		action.moveToElement(driver.findElement(By.cssSelector("span.icon_menu"))).perform();
		sleepInSecond(3);

		// Click vào Kỹ năng sống
		driver.findElement(By.xpath("//div[@class='fhs_column_stretch']//a[text()='Kỹ Năng Sống']")).click();
		sleepInSecond(5);

		// Hàm getText() sẽ get text ở trên UI mà User nhìn thấy
		Assert.assertEquals(driver.findElement(By.xpath("//ol[@class='breadcrumb']/li/strong")).getText(),
				"KỸ NĂNG SỐNG");

		// Hàm isDisplayed() sẽ dùng text ở dưới HTML
		Assert.assertTrue(driver.findElement(By.xpath("//ol[@class='breadcrumb']/li/strong[text()='Kỹ năng sống']"))
				.isDisplayed());
	}

	//@Test
	public void TC_04_Click_And_Hold() {
		driver.get("https://automationfc.github.io/jquery-selectable/");

		// Đang cần thao tác với cả 12 số
		List<WebElement> listNumbers = driver.findElements(By.cssSelector("ol#selectable>li"));

//		- Click vào số bắt đầu và giữ chuột trái (Chưa nhả chuột ra)
		action.clickAndHold(listNumbers.get(0))

//		- Di chuột đến số kết thúc 
				.moveToElement(listNumbers.get(9))
//		- Nhả chuột trái ra
				.release().perform();

		List<WebElement> listNumbersSelected = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));

		// Verify số lượng element đã chọn
		Assert.assertEquals(listNumbersSelected.size(), 6);

		// Verify text đã chọn của element
		// Define 1 mảng chứa text cần verify
		String[] listNumberSelectedExpected = { "1", "2", "5", "6", "9", "10" };

		// Khai báo ra 1 ArrayList để lưu lại list được get ở trên
		ArrayList<String> listNumberSelectedActual = new ArrayList<String>();
		// Vòng lặp for để duyệt qua list đã chọn ở trên
		for (WebElement number : listNumbersSelected) {
			listNumberSelectedActual.add(number.getText());
		}

		// Ép kiểu Array qua List
		Assert.assertEquals(listNumberSelectedActual, Arrays.asList(listNumberSelectedExpected)  );
	}

	//@Test
	public void TC_05_Click_And_Hold_Random() {
		driver.get("https://automationfc.github.io/jquery-selectable/");

		// Đang cần thao tác với cả 12 số
		List<WebElement> listNumbers = driver.findElements(By.cssSelector("ol#selectable>li"));

		Keys key = null;
		if (osName.contains("Mac")) {
			key = Keys.COMMAND;
		} else {
			key = Keys.CONTROL;
		}

//			- Nhấn phím Ctrl xuống (Chưa nhả ra)

		action.keyDown(key).perform();
//		- Click vào các số cần chọn

		action.click(listNumbers.get(1)).click(listNumbers.get(3)).click(listNumbers.get(4)).click(listNumbers.get(6))
				.click(listNumbers.get(10)).perform();
//		- Nhả phím Ctrl ra
		action.keyUp(key).perform();
		List<WebElement> listNumbersSelected = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
		// Verify số lượng element đã chọn
		Assert.assertEquals(listNumbersSelected.size(), 5);

	}

	// @Test
	public void TC_06_Double_Click() {
		driver.get("https://automationfc.github.io/basic-form/");

		// Phải scroll to element mới thao tác được
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(By.xpath("//button[text()='Double click me']")));

		action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
		Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(), "Hello Automation Guys!");
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
