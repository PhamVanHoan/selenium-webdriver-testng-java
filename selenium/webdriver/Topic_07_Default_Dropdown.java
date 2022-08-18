package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Default_Dropdown {
	// Khai báo
	WebDriver driver;
	Select select;
	Random rand;
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
		rand = new Random();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	public void TC_01_Default_Dropdown() {
		driver.get("https://demo.nopcommerce.com");
		driver.findElement(By.cssSelector(".ico-register")).click();

		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Hoan");
		driver.findElement(By.cssSelector("input#LastName")).sendKeys("Pham");

		// Chỉ khởi tạo Select khi xuất hiện element
		// Khởi tạo select để thao tác với Day dropdown
		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']")));
		select.selectByVisibleText("13");

		// Khởi tạo select để thao tác với Month dropdown
		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']")));
		select.selectByVisibleText("May");

		// Khởi tạo select để thao tác với Year dropdown
		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthYear']")));
		select.selectByVisibleText("1967");

		String emailAddress = "hoanpham" + rand.nextInt(99999) + "@hotmail.com";
		driver.findElement(By.cssSelector("input#Email")).sendKeys(emailAddress);
		driver.findElement(By.cssSelector("input#Company")).sendKeys("White House");
		driver.findElement(By.cssSelector("input#Password")).sendKeys("123456");
		driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123456");

		driver.findElement(By.cssSelector("button#register-button")).click();

		Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");

		driver.findElement(By.cssSelector("a.ico-account")).click();

		Assert.assertEquals(driver.findElement(By.cssSelector("input#FirstName")).getAttribute("value"), "Hoan");
		Assert.assertEquals(driver.findElement(By.cssSelector("input#LastName")).getAttribute("value"), "Pham");

		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "13");

		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "May");

		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthYear']")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "1967");

		Assert.assertEquals(driver.findElement(By.cssSelector("input#Email")).getAttribute("value"), emailAddress);
		Assert.assertEquals(driver.findElement(By.cssSelector("input#Company")).getAttribute("value"), "White House");

	}

	@Test
	public void TC_02_Default_Dropdown() {

		driver.get("https://rode.com/en/support/where-to-buy");
		driver.findElement(By.xpath("//button[contains(text(), 'Allow All')]")).click();
		sleepInSecond(3);

		select = new Select(driver.findElement(By.id("country")));

		select.selectByVisibleText("Vietnam");
		sleepInSecond(3);

		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Vietnam");

		List<WebElement> dealers = driver.findElements(By.cssSelector("div#map h4"));

		for (WebElement element : dealers) {
			System.out.println(element.getText());
		}
	}

	@Test
	public void TC_03() {

		sleepInSecond(5);
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
