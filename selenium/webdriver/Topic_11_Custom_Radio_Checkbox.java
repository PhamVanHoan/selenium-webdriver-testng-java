package webdriver;

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

public class Topic_11_Custom_Radio_Checkbox {

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

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	public void TC_01_Custom_Checkbox() {
		driver.get("https://material.angular.io/components/checkbox/examples");

		// Case 1: => Không work được
		// Thẻ input: bị ẩn nên không click được
		// Thẻ input: dùng để verify được

//		// Click
//		driver.findElement(By.xpath("//span[text()='Checked']/preceding-sibling::span/input")).click();
//		sleepInSecond(3);
//
//		// Verify
//		Assert.assertTrue(
//				driver.findElement(By.xpath("//span[text()='Checked']/preceding-sibling::span/input")).isSelected());

		// Case 2: => Không work được
		// Không dùng thẻ input để click - thay thế = 1 thẻ đang hiển thị đại diện cho
		// Checkbox/Radio: span/div..
		// Các thẻ này lại không verify được

//		// Click
//		driver.findElement(By.xpath("//span[text()='Checked']/preceding-sibling::span")).click();
//		sleepInSecond(3);
//
//		// Verify
//		Assert.assertTrue(
//				driver.findElement(By.xpath("//span[text()='Checked']/preceding-sibling::span")).isSelected());

		// Case 3 => work được nhưng không tối ưu
		// Dùng thẻ span để click
		// Dùng thẻ input để verify
		// => Trong 1 dự án mà 1 element cần tới 2 locator để define thì sinh ra nhiều
		// code/cần maintain nhiều
		// Dễ gây hiểu nhầm cho người mới

//		// Click
//		driver.findElement(By.xpath("//span[text()='Checked']/preceding-sibling::span")).click();
//		sleepInSecond(3);
//
//		// Verify
//		Assert.assertTrue(
//				driver.findElement(By.xpath("//span[text()='Checked']/preceding-sibling::span/input")).isSelected());

		// Case 4: work-around
		// Thẻ input để click và verify
		// Hàm click() của WebElement ko click và element bị ẩn được
		// Dùng hàm click() của JavascriptExecuter để click: ko quan tâm element bị ẩn
		// hay không
		WebElement checkedCheckbox = driver
				.findElement(By.xpath("//span[text()='Checked']/preceding-sibling::span/input"));
		jsExecutor.executeScript("arguments[0].click();", checkedCheckbox);

		sleepInSecond(3);
		Assert.assertTrue(checkedCheckbox.isSelected());

	}

	public void TC_02_Custom_Radio() {
		driver.get("https://material.angular.io/components/radio/examples");
		sleepInSecond(2);

		WebElement checkedRadio = driver
				.findElement(By.xpath("//span[contains(text(),'Spring')]/preceding-sibling::span/input"));
		jsExecutor.executeScript("arguments[0].click();", checkedRadio);

		sleepInSecond(3);
		Assert.assertTrue(checkedRadio.isSelected());
	}

	public void TC_03_Custom_VnDirect() {
		driver.get("https://account-v2.vndirect.com.vn/");
		sleepInSecond(2);

		WebElement checkedCheckbox = driver.findElement(By.xpath("//input[@name='acceptTerms']"));
		jsExecutor.executeScript("arguments[0].click();", checkedCheckbox);

		sleepInSecond(3);
		Assert.assertTrue(checkedCheckbox.isSelected());
	}

	@Test
	public void TC_04_Google() {
		driver.get(
				"https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");

		WebElement canThoRadio = driver.findElement(By.xpath("//div[@aria-label='Cần Thơ']"));

		// Verify trước khi click
		Assert.assertEquals(canThoRadio.getAttribute("aria-checked"), "false");

		checkToCheckbox(canThoRadio);
		// Verify sau khi click
		Assert.assertEquals(canThoRadio.getAttribute("aria-checked"), "true");

		WebElement miQuangCheckbox = driver.findElement(By.xpath("//div[@aria-label='Mì Quảng']"));
		// Verify trước khi click
		Assert.assertEquals(miQuangCheckbox.getAttribute("aria-checked"), "false");

		checkToCheckbox(miQuangCheckbox);
		// Verify sau khi click
		Assert.assertEquals(miQuangCheckbox.getAttribute("aria-checked"), "true");

		unCheckToCheckbox(miQuangCheckbox);
		// Verify sau khi click
		Assert.assertEquals(miQuangCheckbox.getAttribute("aria-checked"), "false");
	
		
	}

	public void checkToCheckbox(WebElement element) {
		if (element.getAttribute("aria-checked").equals("false")) {
			element.click();
		}

	}

	public void unCheckToCheckbox(WebElement element) {
		if (element.getAttribute("aria-checked").equals("true")) {
			element.click();
		}

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
