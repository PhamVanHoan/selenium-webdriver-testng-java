package webdriver;

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

public class Topic_10_Default_Radio_Checkbox {

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

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	public void TC_01_Jotform() {
		driver.get("https://automationfc.github.io/multiple-fields/");

		// Chọn check box Cancer - Fainting Spells
		checkToCheckboxOrRadio("//input[@value = 'Cancer']");
		checkToCheckboxOrRadio("//input[@value = 'Fainting Spells']");
		// Verify được chọn thành công
		Assert.assertTrue(isElementSelected("//input[@value = 'Cancer']"));
		Assert.assertTrue(isElementSelected("//input[@value = 'Fainting Spells']"));

		// Chọn Radio: 5+ days - 1-2 cups/day
		checkToCheckboxOrRadio("//input[@value = '5+ days']");
		checkToCheckboxOrRadio("//input[@value = '1-2 cups/day']");

		// Verify được chọn thành công
		Assert.assertTrue(isElementSelected("//input[@value = '5+ days']"));
		Assert.assertTrue(isElementSelected("//input[@value = '1-2 cups/day']"));

		// Bỏ chọn thì chỉ cần click tiếp 1 lần nữa
		// Bỏ chọn check box Cancel - Fainting Spells

		unCheckToCheckbox("//input[@value = 'Cancer']");
		unCheckToCheckbox("//input[@value = 'Fainting Spells']");

		// Verify được bỏ chọn thành công
		Assert.assertFalse(isElementSelected("//input[@value = 'Cancer']"));
		Assert.assertFalse(isElementSelected("//input[@value = 'Fainting Spells']"));

		// Bỏ chọn thì phải chọn 1 radio button khác
		// Chọn Radio: 5+ days - 1-2 days
		checkToCheckboxOrRadio("//input[@value = 'Never']");
		checkToCheckboxOrRadio("//input[@value = '5+ cups/day']");

		// Verify được chọn thành công
		Assert.assertTrue(isElementSelected("//input[@value = 'Never']"));
		Assert.assertTrue(isElementSelected("//input[@value = '5+ cups/day']"));

	}

	public void TC_02_Jotform_Select_All() {
		driver.get("https://automationfc.github.io/multiple-fields/");

		List<WebElement> allCheckboxes = driver.findElements(By.xpath("//input[@type= 'checkbox']"));

		// Dùng vòng lặp để duyệt qua và click chọn
		for (WebElement checkbox : allCheckboxes) {
			checkToCheckboxOrRadio(checkbox);
			sleepInSecond(1);

		}

		// Dùng vòng lặp để duyệt qua và kiểm tra
		for (WebElement checkbox : allCheckboxes) {
			Assert.assertTrue(isElementSelected(checkbox));
		}

		// Bỏ chọn hết
		for (WebElement checkbox : allCheckboxes) {
			unCheckToCheckbox(checkbox);
			sleepInSecond(1);

		}

		// Dùng vòng lặp để duyệt qua và kiểm tra
		for (WebElement checkbox : allCheckboxes) {
			Assert.assertFalse(isElementSelected(checkbox));
		}
	}

	public void TC_03_Select_All() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		sleepInSecond(5);

		List<WebElement> allCheckboxs = driver.findElements(By.xpath("//div[@id= 'example']//input[@type='checkbox']"));

		// Check all và verify
		for (WebElement checkbox : allCheckboxs) {
			checkToCheckboxOrRadio(checkbox);

		}

		// Uncheck all và verify
		for (WebElement checkbox : allCheckboxs) {
			unCheckToCheckbox(checkbox);
		}
	}

	@Test
	public void TC_04_Select_All() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		sleepInSecond(5);

		// Check
		checkToCheckboxOrRadio("//label[text()= 'Rain sensor']/preceding-sibling::input");
		Assert.assertTrue(isElementSelected("//label[text()= 'Rain sensor']/preceding-sibling::input"));

		// Uncheck
		unCheckToCheckbox("//label[text()= 'Rain sensor']/preceding-sibling::input");
		Assert.assertFalse(isElementSelected("//label[text()= 'Rain sensor']/preceding-sibling::input"));

	}

	public void checkToCheckboxOrRadio(String xpathLocator) {
		// Kiểm tra trước nó đã chọn hay chưa
		// Nếu chọn rồi thì không cần click nữa
		// Nếu chưa chọn thì click vào
		if (!driver.findElement(By.xpath(xpathLocator)).isSelected()) {
			driver.findElement(By.xpath(xpathLocator)).click();
		}
	}

	public void checkToCheckboxOrRadio(WebElement element) {
		// Kiểm tra trước nó đã chọn hay chưa
		// Nếu chọn rồi thì không cần click nữa
		// Nếu chưa chọn thì click vào
		if (!element.isSelected() && element.isEnabled()) {
			element.click();
			Assert.assertTrue(isElementSelected(element));
		}
	}

	public void unCheckToCheckbox(String xpathLocator) {
		// Kiểm tra trước nó đã chọn hay chưa
		// Nếu chưa chọn thì không cần click nữa
		// Nếu chọn rồi thì click vào
		if (driver.findElement(By.xpath(xpathLocator)).isSelected()) {
			driver.findElement(By.xpath(xpathLocator)).click();

		}
	}

	public void unCheckToCheckbox(WebElement element) {
		// Kiểm tra trước nó đã chọn hay chưa
		// Nếu chưa chọn thì không cần click nữa
		// Nếu chọn rồi thì click vào
		if (element.isSelected() && element.isEnabled()) {
			element.click();
			Assert.assertFalse(isElementSelected(element));
		}
	}

	public boolean isElementSelected(String xpathLocator) {
		return driver.findElement(By.xpath(xpathLocator)).isSelected();
	}

	public boolean isElementSelected(WebElement element) {
		return element.isSelected();
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
