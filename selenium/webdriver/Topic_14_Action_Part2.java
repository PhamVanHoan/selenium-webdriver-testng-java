package webdriver;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Action_Part2 {

	// Khai báo
	WebDriver driver;
	WebDriverWait explicitWait;
	Actions action;
	JavascriptExecutor jsExecutor;
	String jsFileContent;

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


	public void TC_01_Drag_Drop_HTML5_Javascript() throws IOException {
		driver.get("https://automationfc.github.io/drag-drop-html5/");

		jsFileContent = getContentFile(projectPath + "\\dragAndDrop\\drag_and_drop_helper.js");

		jsFileContent = jsFileContent + "$('div#column-a').simulateDragDrop({ dropTarget: 'div#column-b'});";

		jsExecutor.executeScript(jsFileContent);

		Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a")).getText(), "B");
		Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b")).getText(), "A");
		sleepInSecond(3);

		jsExecutor.executeScript(jsFileContent);

		Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a")).getText(), "A");
		Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b")).getText(), "B");
	}

	@Test
	public void TC_02_Drag_Drop_HTML5_Java_Robot_Offset() throws AWTException {
		driver.get("https://automationfc.github.io/drag-drop-html5/");
		
		//A->B
		dragAndDropHTML5ByXpath("//div[@id='column-a']", "//div[@id='column-b']");
		sleepInSecond(3);

		Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a")).getText(), "B");
		Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b")).getText(), "A");
		
		
		//B->A
		dragAndDropHTML5ByXpath("//div[@id='column-a']", "//div[@id='column-b']");
		Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a")).getText(), "A");
		Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b")).getText(), "B");
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

	public String getContentFile(String filePath) throws IOException {
		Charset cs = Charset.forName("UTF-8");
		FileInputStream stream = new FileInputStream(filePath);
		try {
			Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			return builder.toString();
		} finally {
			stream.close();
		}
	}

	public void dragAndDropHTML5ByXpath(String sourceLocator, String targetLocator) throws AWTException {

		WebElement source = driver.findElement(By.xpath(sourceLocator));
		WebElement target = driver.findElement(By.xpath(targetLocator));

		// Setup robot
		Robot robot = new Robot();
		robot.setAutoDelay(500);

		// Get size of elements
		Dimension sourceSize = source.getSize();
		Dimension targetSize = target.getSize();

		// Get center distance
		int xCentreSource = sourceSize.width / 2;
		int yCentreSource = sourceSize.height / 2;
		int xCentreTarget = targetSize.width / 2;
		int yCentreTarget = targetSize.height / 2;

		Point sourceLocation = source.getLocation();
		Point targetLocation = target.getLocation();
		// System.out.println(sourceLocation.toString());
		// System.out.println(targetLocation.toString());

		// Make Mouse coordinate center of element
		sourceLocation.x += 20 + xCentreSource;
		sourceLocation.y += 110 + yCentreSource;
		targetLocation.x += 20 + xCentreTarget;
		targetLocation.y += 110 + yCentreTarget;

		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());

		// Move mouse to drag from location
		robot.mouseMove(sourceLocation.x, sourceLocation.y);

		// Click and drag
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x,
				((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

		// Move to final position
		robot.mouseMove(targetLocation.x, targetLocation.y);

		// Drop
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}
}
