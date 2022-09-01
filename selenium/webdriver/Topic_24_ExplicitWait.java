package webdriver;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_24_ExplicitWait {

	// Khai báo
	WebDriver driver;
	WebDriverWait explicitWait;

	// Khai báo + khởi tạo
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

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

		// Apply 15s cho các điều kiện, trạng thái cụ thể
		explicitWait = new WebDriverWait(driver, 15);

		// ExplicitWait (Webdriver wait): wait tường minh, wait cho các điều kiện cụ
		// thể, trạng thái của element
	}

	public void TC_01_Visible() {

		driver.get("https://automationfc.github.io/dynamic-loading/");
		explicitWait = new WebDriverWait(driver, 3);
		// Click vào Start button
		driver.findElement(By.cssSelector("div#start>button")).click();

		// Wait cho text hiển thị
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));

		// Get text và verify
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

	}

	public void TC_02_Invisible() {

		driver.get("https://automationfc.github.io/dynamic-loading/");
		explicitWait = new WebDriverWait(driver, 5);
		// Click vào Start button
		driver.findElement(By.cssSelector("div#start>button")).click();
		sleepInSecond(5);
		// Wait cho loading icon biến mất
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));
		// Get text và verify
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

	}

	public void TC_03_Ajax_Loading() {

		driver.get(
				"https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		explicitWait = new WebDriverWait(driver, 30);

		// Wait cho date picker được hiển thị
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div.RadCalendar")));

		// Verify cho Selected date là ko có ngày nào được chọn
		Assert.assertEquals(driver.findElement(By.cssSelector("span.label")).getText(),
				"No Selected Dates to display.");

		// Wait cho ngày chọn được phép click
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()=30]")));

		// Click vào ngày 30
		driver.findElement(By.xpath("//a[text()=30]")).click();

		// Wait cho Loading icon biến mất
		explicitWait.until(
				ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id*=RadCalendar]>div.raDiv")));

		// Wait cho ngày được chọn được phép click
		explicitWait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@class='rcSelected']/a[text()='30']")));

		// Verify cho Selected date là ko có ngày nào được chọn
		Assert.assertEquals(driver.findElement(By.cssSelector("span.label")).getText(), "Tuesday, August 30, 2022");

		// Không nên khai báo biến WebElement =
		// driver.findElement(By.cssSelector("span.label")) để dùng lại
		// Do khi click thì data được reload, elelement bị reload => element = rỗng

	}

	@Test
	public void TC_04_Upload_File() {
		driver.get("https://gofile.io/uploadFiles");

		explicitWait = new WebDriverWait(driver, 30);
		explicitWait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("div#rowUploadButton button.uploadButton")));

		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(vietnamFilePath + "\n" + thailanFilePath);

		// Wait cho all upload progress bar biến mất ở từng file
		explicitWait.until(
				ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.progress"))));

		// Wait cho upload message thành công được visible
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.callout-success i")));

		// Verify message upload thành công
		Assert.assertTrue(driver.findElement(By.cssSelector("div.callout-success i")).isDisplayed());

		// Wait show file button được clickable
		explicitWait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button#rowUploadSuccess-showFiles")));

		// Click vào button show file
		driver.findElement(By.cssSelector("button#rowUploadSuccess-showFiles")).click();

		// Wait cho file name, button download hiển thị

		Assert.assertTrue(explicitWait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + vietnam
						+ "']/parent::a/parent::div/following-sibling::div//button[@id='contentId-download']")))
				.isDisplayed());
		Assert.assertTrue(explicitWait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + thailan
						+ "']/parent::a/parent::div/following-sibling::div//button[@id='contentId-download']")))
				.isDisplayed());

		// Wait cho file name, button Play hiển thị

		Assert.assertTrue(explicitWait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + vietnam
						+ "']/parent::a/parent::div/following-sibling::div//button[contains(@class, 'contentPlay' )]")))
				.isDisplayed());
		Assert.assertTrue(explicitWait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + thailan
						+ "']/parent::a/parent::div/following-sibling::div//button[contains(@class, 'contentPlay' )]")))
				.isDisplayed());

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
