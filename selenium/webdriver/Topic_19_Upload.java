package webdriver;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_19_Upload {

	// Khai báo
	WebDriver driver;
	WebDriverWait explicitWait;

	// Khai báo + khởi tạo
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	String firefoxSinglePath = projectPath + "\\autoIT\\firefoxUploadOneTime.exe";
	String chromeSinglePath = projectPath + "\\autoIT\\\\chromeUploadOneTime.exe";
	String firefoxMultiPath = projectPath + "\\autoIT\\\\firefoxUploadMultiple.exe";
	String chromeMultiPath = projectPath + "\\autoIT\\\\chromeUploadMultiple.exe";

	// Image name
	String vietnam = "Vietnam.jpg";
	String thailan = "Thai Lan.jpg";
	String indo = "Indo.jpg";

	// Upload file folder
	String uploadFileFolder = projectPath + File.separator + "uploadFile" + File.separator;

	// Image path
	String vietnamFilePath = uploadFileFolder + vietnam;
	String thailanFilePath = uploadFileFolder + thailan;
	String indoFilePath = uploadFileFolder + indo;

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

	public void TC_01_Upload_One_File_Per_Time_Sendkey() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		WebElement uploadFilebutton = driver.findElement(By.cssSelector("span.btn-success"));
		// Load file lên
		uploadFilebutton.sendKeys(vietnamFilePath);
		uploadFilebutton.sendKeys(thailanFilePath);
		uploadFilebutton.sendKeys(indoFilePath);

		// Verify image được load lên thành công
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text() ='" + vietnam + "']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text() ='" + thailan + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text() ='" + indo + "']")).isDisplayed());

		// Thực hiện upload
		List<WebElement> startButtons = driver.findElements(By.cssSelector("table button.start"));
		for (WebElement startButton : startButtons) {
			startButton.click();
			sleepInSecond(2);
		}

		// Verify image được upload lên thành công
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name']/a[text() ='" + vietnam + "']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name']/a[text() ='" + thailan + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text() ='" + indo + "']")).isDisplayed());
	}

	public void TC_02_Upload_Multiple_File_Per_Time_Sendkey() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		WebElement uploadFilebutton = driver.findElement(By.cssSelector("span.btn-success"));
		// Load file lên
		uploadFilebutton.sendKeys(vietnamFilePath + "\n" + thailanFilePath + "\n" + indoFilePath);

		// Verify image được load lên thành công
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text() ='" + vietnam + "']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text() ='" + thailan + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text() ='" + indo + "']")).isDisplayed());

		// Thực hiện upload
		List<WebElement> startButtons = driver.findElements(By.cssSelector("table button.start"));
		for (WebElement startButton : startButtons) {
			startButton.click();
			sleepInSecond(2);
		}

		// Verify image được upload lên thành công
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name']/a[text() ='" + vietnam + "']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name']/a[text() ='" + thailan + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text() ='" + indo + "']")).isDisplayed());
	}

	public void TC_03_Upload_One_File_Per_Time_AutoIT() throws IOException {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		WebElement uploadFilebutton = driver.findElement(By.cssSelector("span.btn-success"));

		uploadFilebutton.click();
		sleepInSecond(3);
		if (driver.toString().contains("firefox")) {
			// Upload = AutoIT
			Runtime.getRuntime().exec(new String[] { firefoxSinglePath, vietnamFilePath });
		} else {

			Runtime.getRuntime().exec(new String[] { chromeSinglePath, vietnamFilePath });

		}
		// Verify image được load lên thành công
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text() ='" + vietnam + "']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text() ='" + thailan + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text() ='" + indo + "']")).isDisplayed());

		// Thực hiện upload
		List<WebElement> startButtons = driver.findElements(By.cssSelector("table button.start"));
		for (WebElement startButton : startButtons) {
			startButton.click();
			sleepInSecond(2);
		}

		// Verify image được upload lên thành công
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name']/a[text() ='" + vietnam + "']")).isDisplayed());

	}

	public void TC_04_Upload_Multiple_File_Per_Time_AutoIT() throws IOException {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		WebElement uploadFilebutton = driver.findElement(By.cssSelector("span.btn-success"));

		uploadFilebutton.click();
		sleepInSecond(3);
		if (driver.toString().contains("firefox")) {
			// load file = AutoIT
			Runtime.getRuntime()
					.exec(new String[] { firefoxMultiPath, vietnamFilePath, thailanFilePath, indoFilePath });
		} else {

			Runtime.getRuntime().exec(new String[] { chromeMultiPath, vietnamFilePath, thailanFilePath, indoFilePath });

		}
		// Verify image được load lên thành công
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text() ='" + vietnam + "']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text() ='" + thailan + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text() ='" + indo + "']")).isDisplayed());

		// Thực hiện upload
		List<WebElement> startButtons = driver.findElements(By.cssSelector("table button.start"));
		for (WebElement startButton : startButtons) {
			startButton.click();
			sleepInSecond(2);
		}

		// Verify image được upload lên thành công
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name']/a[text() ='" + vietnam + "']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name']/a[text() ='" + thailan + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text() ='" + indo + "']")).isDisplayed());
	}

	@Test
	public void TC_05_Upload_One_File_Per_Time_Java_Robot() throws AWTException {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		WebElement uploadFilebutton = driver.findElement(By.cssSelector("span.btn-success"));
		uploadFilebutton.click();
		sleepInSecond(3);
		// Load file = Robot
		loadFileByRobot(vietnamFilePath);

		// Verify image được load lên thành công
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text() ='" + vietnam + "']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text() ='" + thailan + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text() ='" + indo + "']")).isDisplayed());

		// Thực hiện upload
		List<WebElement> startButtons = driver.findElements(By.cssSelector("table button.start"));
		for (WebElement startButton : startButtons) {
			startButton.click();
			sleepInSecond(2);
		}

		// Verify image được upload lên thành công
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name']/a[text() ='" + vietnam + "']")).isDisplayed());

	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

	public void loadFileByRobot(String filePath) throws AWTException {

		StringSelection select = new StringSelection(filePath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);

		// Load file
		Robot robot = new Robot();
		sleepInSecond(1);

		// Nhấn xuống Ctrl - V
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);

		// Nhả Ctrl - V
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		sleepInSecond(1);

		// Nhấn sau đó nhả Enter
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		sleepInSecond(1);
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
