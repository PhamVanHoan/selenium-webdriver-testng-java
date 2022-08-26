package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Frame_Iframe {

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

	@Test
	public void TC_01_Iframe() {
		//A
		driver.get("https://kyna.vn/");

		// Swith to
		// 1- Alert
		// 2 - Frame/Iframe
		// 3 - Windows/Tab

		// Cách 1 dùng index: driver.switchTo().frame(0);

		// Cách 2 dùng id/name
		// driver.switchTo().frame("");

		// Cách 3: dùng webelement
		
		// A->B
		driver.switchTo().frame(driver.findElement(By.cssSelector("div.fanpage iframe")));

		//B	
		String facebookLikeNumber = driver
				.findElement(By.xpath("//a[text()='Kyna.vn']/parent::div/following-sibling::div")).getText();

		Assert.assertEquals(facebookLikeNumber, "166K likes");

		//Không support nhày từ Iframe B -> C
		//-> Phảu nhảy về default page A, sau đó => C
		
		//A
		driver.switchTo().defaultContent();
		//A-> C
		driver.switchTo().frame("cs_chat_iframe");
		
		//Click Element thuộc C
		driver.findElement(By.cssSelector("div.meshim_widget_Widget")).click();
		
		driver.findElement(By.cssSelector("input.input_name")).sendKeys("Name");
		driver.findElement(By.cssSelector("input.input_phone")).sendKeys("099786378645");
		new Select(driver.findElement(By.cssSelector("select#serviceSelect"))).selectByVisibleText("TƯ VẤN TUYỂN SINH");
		
		driver.findElement(By.cssSelector("textarea[name='message']")).sendKeys("Tư vấn khóa học");
		
		//C-> A
		driver.switchTo().defaultContent();
		
		//A
		String keyword = "Excel";
		
		driver.findElement(By.cssSelector("input#live-search-bar")).sendKeys(keyword);
		
		driver.findElement(By.cssSelector("button.search-button")).click();
		
		List <WebElement> courseNames = driver.findElements(By.cssSelector("div.content>h4"));
		
		//Number
		Assert.assertEquals(courseNames.size(), 9);
		
		for (WebElement course : courseNames) {
			System.out.println(course);
			
			//Course name contains keyword
			Assert.assertTrue(course.getText().contains(keyword));
		}
	}

	@Test
	public void TC_02_HDFC_Bank() {
		
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		
		driver.switchTo().frame("login_page");
		
		driver.findElement(By.name("fldLoginUserId")).sendKeys("automationfc");
		driver.findElement(By.cssSelector("a.login-btn")).click();
		
		WebElement passwordTextbox = driver.findElement(By.cssSelector("input#fldPasswordDispId"));
		Assert.assertTrue(passwordTextbox.isDisplayed());
		passwordTextbox.sendKeys("automationfc");
		
		
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
