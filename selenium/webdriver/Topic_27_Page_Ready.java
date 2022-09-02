package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_27_Page_Ready {

	// Khai báo
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	Actions action;

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
		action = new Actions(driver);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	public void TC_01_OrangeHRM() {
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		Assert.assertTrue(isPageLoadedSuccess());
		driver.findElement(By.name("username")).sendKeys("Admin");
		driver.findElement(By.name("password")).sendKeys("admin123");
		driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();

		Assert.assertTrue(isPageLoadedSuccess());
		Assert.assertTrue(driver.findElement(By.cssSelector("div.orangehrm-vertical-padding>span")).getText()
				.contains("Records Found"));
	}

	@Test
	public void TC_02_TestProject() {
		driver.get("https://blog.testproject.io/");
		
		String keyword = "Selenium";

		if (driver.findElement(By.cssSelector("div.mailch-wrap")).isDisplayed()) {
			driver.findElement(By.cssSelector("div#close-mailch")).click();
		}

		// Hover chuột vào search Textbox
		action.moveToElement(driver.findElement(By.cssSelector("section#search-2 input.search-field"))).perform();
		
		//Load trang
		Assert.assertTrue(isPageLoadedSuccess());

		driver.findElement(By.cssSelector("section#search-2 input.search-field")).sendKeys("Selenium");
		driver.findElement(By.cssSelector("section#search-2 span.glass")).click();

		Assert.assertTrue(driver.findElement(By.cssSelector("h2.page-title>span")).isDisplayed());

		List<WebElement> postTitles = driver.findElements(By.cssSelector("h3.post-title>a"));
		for (WebElement postTitle : postTitles) {
			String postTitleText = postTitle.getText();
			System.out.println(postTitleText);
			Assert.assertTrue(postTitleText.contains(keyword));
		}
		
		
	}

	@Test
	public void TC_03() {

		sleepInSecond(5);
	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

	public boolean isPageLoadedSuccess() {
		explicitWait = new WebDriverWait(driver, 30);
		jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery != null)&& (jQuery.active ===0);");
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};
		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
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
