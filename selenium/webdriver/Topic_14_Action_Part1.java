package webdriver;

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

public class Topic_14_Action_Part1 {

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
		
		//Khởi tạo jsExecutor
		jsExecutor = (JavascriptExecutor) driver;

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	public void TC_01_Hover() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		WebElement ageTextbox = driver.findElement(By.cssSelector("input#age"));

		action.moveToElement(ageTextbox).perform();
		sleepInSecond(5);

		Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(),
				"We ask for your age only for statistical purposes.");
	}

	public void TC_02_Hover() {
		driver.get("https://www.myntra.com/");
		WebElement kidLink = driver.findElement(By.xpath("//a[@data-group='kids']"));
		action.moveToElement(kidLink).perform();
		sleepInSecond(3);

		action.click(driver.findElement(By.xpath("//a[text()='Home & Bath']"))).perform();

		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Kids Home Bath']")).isDisplayed());
	}

	public void TC_03_FPT_Shop() {
		driver.get("https://fptshop.com.vn/");
		action.moveToElement(driver.findElement(By.xpath("//a[@title='ĐIỆN THOẠI']"))).perform();
		sleepInSecond(3);

		driver.findElement(By.xpath("//a[@title='Apple (iPhone)']")).click();
		sleepInSecond(3);

		Assert.assertEquals(driver.getCurrentUrl(), "https://fptshop.com.vn/dien-thoai/apple-iphone");

	}

	
	public void TC_04_Click_And_Hold() {
		driver.get("https://automationfc.github.io/jquery-selectable/");

		// Store all 12 elements
		List<WebElement> allNumbers = driver.findElements(By.cssSelector("ol#selectable>li"));

		// Click và giữ chuột tại số thứ 1
		action.clickAndHold(allNumbers.get(0))
				// Hover chuột tới số 11
				.moveToElement(allNumbers.get(10))
				// Nhả chuột tráo ra
				.release()
				// Thực thi các action trên
				.perform();
		
		//List lại element đã được chọn
		allNumbers = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));

		Assert.assertEquals(allNumbers.size(), 9);
	}
	
	public void TC_05_Click_And_Hold_Random() {
		driver.get("https://automationfc.github.io/jquery-selectable/");

		// Store all 12 elements
		List<WebElement> allNumbers = driver.findElements(By.cssSelector("ol#selectable>li"));

		// Nhấn phím Control xuống
		action.keyDown(Keys.CONTROL).perform();
		
		// Click vào các số cần chọn
		action.click(allNumbers.get(0))
		.click(allNumbers.get(2))
		.click(allNumbers.get(5))
		.click(allNumbers.get(7))
		.click(allNumbers.get(10))
		.perform();
		
		//Nhả phím Control ra
		action.keyUp(Keys.CONTROL).perform();
		
		//List lại element đã được chọn
		allNumbers = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));

		Assert.assertEquals(allNumbers.size(), 5);
	}
	
	
	public void TC_06_Double_Click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		WebElement doubleClickMeText = driver.findElement(By.xpath("//button[text()='Double click me']"));
		
		//Scroll to element
		//Truyền vào true => scroll tới méo trên
		//Truyền vào false => scroll tới méo dưới
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", doubleClickMeText);
		sleepInSecond(2);
		
		action.doubleClick(doubleClickMeText).perform();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(), "Hello Automation Guys!");
	}
	

	public void TC_07_Right_Click() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		
		action.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
		sleepInSecond(2);
		
		WebElement deleteBefore = driver.findElement(By.cssSelector("li.context-menu-icon-delete"));
		action.moveToElement(deleteBefore).perform();
		
		Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-delete.context-menu-hover.context-menu-visible")).isDisplayed());
		
		action.click(deleteBefore).perform();
		
		Alert  alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "clicked: delete");
		alert.accept();
		
		Assert.assertFalse(deleteBefore.isDisplayed());
				
	}
	
	@Test
	public void TC_08_Drag_Drop_HTML4() {
		driver.get("https://automationfc.github.io/kendo-drag-drop/");
		WebElement sourceCircle = driver.findElement(By.cssSelector("div#draggable"));
		WebElement targetCircle = driver.findElement(By.cssSelector("div#droptarget"));
		
		action.dragAndDrop(sourceCircle, targetCircle).perform();
		
		sleepInSecond(3);
		
		//Text
		Assert.assertEquals(targetCircle.getText(), "You did great!");
		
		//Bacjkground color
		String backgroundColorHexa = Color.fromString(targetCircle.getCssValue("background-color")).asHex().toUpperCase();	
		Assert.assertEquals(backgroundColorHexa, "#03A9F4");
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
