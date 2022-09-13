package webdriver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_Actions_Part2 {

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

	public void TC_01_Right_Click() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");

		// Click chuột phải vào Button
		action.contextClick(driver.findElement(By.cssSelector("span.btn-neutral"))).perform();
		sleepInSecond(1);

		// Hover chuột vào context menu Paste
		action.moveToElement(driver.findElement(By.cssSelector("li.context-menu-icon-paste"))).perform();
		sleepInSecond(1);

		// Verify Paste có trạng thái hover
		Assert.assertTrue(
				driver.findElement(By.cssSelector("li.context-menu-icon-paste.context-menu-hover")).isDisplayed());
		sleepInSecond(1);

		// Click vào Paste
		action.click(driver.findElement(By.cssSelector("li.context-menu-icon-paste"))).perform();
		sleepInSecond(1);

		// Handle Alert
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "clicked: paste");
		sleepInSecond(1);
		alert.accept();

		// Verify biến mất

		Assert.assertFalse(
				driver.findElement(By.cssSelector("li.context-menu-icon-paste.context-menu-hover")).isDisplayed());

	}

	@Test
	public void TC_02_Drag_And_Drop_HTML4() {
		driver.get("https://automationfc.github.io/kendo-drag-drop/");

		WebElement smallCircle = driver.findElement(By.cssSelector("div#draggable"));
		WebElement bigCircle = driver.findElement(By.cssSelector("div#droptarget"));

		action.dragAndDrop(smallCircle, bigCircle).perform();

		Assert.assertEquals(bigCircle.getText(), "You did great!");

		String rgbaColor = bigCircle.getCssValue("background-color");

		// Verify

		Assert.assertEquals(Color.fromString(rgbaColor).asHex().toUpperCase(), "#03A9F4");
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
