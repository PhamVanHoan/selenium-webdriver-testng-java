package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Custom_Dropdown {
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
		driver.manage().window().setSize(new Dimension(1366, 768));

		// Khởi tạo jsExecutor
		jsExecutor = (JavascriptExecutor) driver;

		// Khởi tạo wait
		explicitWait = new WebDriverWait(driver, 30);

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	public void TC_01_JQuery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

		// 2- Chờ cho tất cả các item trong dropdown được load ra xong
		// Không dùng sleep cứng được
		// Phải có hàm wait linh động:
		// Nếu chưa tìm thấy sẽ tìm lại trong khoảng thời gian được xét
		// Nếu như tìm thấy rồi thì không cần phải chờ hết khoảng thời gian này
		// 3.1 Nếu item cần chọn đang hiển thi
		// 3.2 Nếu item cần chọn ko hiển thị thì cần cuộn chuột xuống - scroll down
		// 4- Kiểm tra text của item - nếu đúng với cái mình cần thì click vào
		// Viết code để duyệt qua từng item và kiểm tra theo điều kiện

		/* NUMBER Dropdown */
		// Gọi hàm: 1 hàm có thể gọi 1 hàm khác để dùng trong cùng 1 Class
		selectItemInCustomDropdown("span#number-button", "ul#number-menu div", "7");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button span.ui-selectmenu-text")).getText(),
				"7");

		selectItemInCustomDropdown("span#number-button", "ul#number-menu div", "5");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button span.ui-selectmenu-text")).getText(),
				"5");

		selectItemInCustomDropdown("span#number-button", "ul#number-menu div", "3");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button span.ui-selectmenu-text")).getText(),
				"3");

		/* SPEED Dropdown */
		selectItemInCustomDropdown("span#speed-button", "ul#speed-menu div", "Fast");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button span.ui-selectmenu-text")).getText(),
				"Fast");

		selectItemInCustomDropdown("span#speed-button", "ul#speed-menu div", "Slower");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button span.ui-selectmenu-text")).getText(),
				"Slower");

		/* Title Dropdown */
		selectItemInCustomDropdown("span#salutation-button", "ul#salutation-menu div", "Dr.");
		sleepInSecond(3);
		Assert.assertEquals(
				driver.findElement(By.cssSelector("span#salutation-button span.ui-selectmenu-text")).getText(), "Dr.");
		selectItemInCustomDropdown("span#salutation-button", "ul#salutation-menu div", "Mr.");
		sleepInSecond(3);
		Assert.assertEquals(
				driver.findElement(By.cssSelector("span#salutation-button span.ui-selectmenu-text")).getText(), "Mr.");
	}

	public void TC_02_Honda() {
		driver.get("https://www.honda.com.vn/o-to/du-toan-chi-phi");

		scrollToElement("div.carousel-item");
		sleepInSecond(3);

		selectItemInCustomDropdown("button#selectize-input", "button#selectize-input+div>a", "CITY L");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("button#selectize-input")).getText(), "CITY L");

		scrollToElement("div.container");
		Select select = new Select(driver.findElement(By.cssSelector("select#province")));
		select.selectByVisibleText("Đà Nẵng");
		sleepInSecond(3);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Đà Nẵng");

		select = new Select(driver.findElement(By.cssSelector("select#registration_fee")));
		select.selectByVisibleText("Khu vực II");
		sleepInSecond(3);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Khu vực II");

	}

	public void TC_03_React() {

		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

		selectItemInCustomDropdown("div.dropdown", "div.menu span.text", "Elliot Fu");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider")).getText(), "Elliot Fu");

		selectItemInCustomDropdown("div.dropdown", "div.menu span.text", "Stevie Feliciano");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider")).getText(), "Stevie Feliciano");
	}

	public void TC_04_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");

		selectItemInCustomDropdown("div.btn-group", "ul.dropdown-menu a", "Third Option");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Third Option");

	}

	public void TC_05_React_Selectable() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");

		selectItemInCustomDropdown("div.dropdown", "div.menu span.text", "Aruba");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider")).getText(), "Aruba");
	}

	@Test
	public void TC_06_React_Editable() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");

		enterItemInCustomDropdown("input.search", "div.menu span.text", "Aruba");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider")).getText(), "Aruba");

		enterItemInCustomDropdown("input.search", "div.menu span.text", "Bahrain");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider")).getText(), "Bahrain");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void scrollToElement(String cssLocator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.cssSelector(cssLocator)));
	}

	// Không dùng cho 1 dropdown cụ thể
	// Dùng chung cho dự án
	public void selectItemInCustomDropdown(String parentLocator, String childLocator, String textExpectedItem) {
		// 1- Click vào 1 phần tử nào đó thuộc dropdown để nó xổ ra
		driver.findElement(By.cssSelector(parentLocator)).click();

		// Wait cho tất cả các item này xuất hiện trên cây HTML/DOM
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));

		// Tìm lưu lại hết tất cả các item vào 1 List để cho step tiếp theo
		List<WebElement> allItems = driver.findElements(By.cssSelector(childLocator));

		// Duyệt qua từng phần tử(element) trong List trên
		for (WebElement item : allItems) {

			// Lấy text ra
			String textActualItem = item.getText();

			// kiểm tra nếu = text mong muốn thì click vào
			if (textActualItem.equals(textExpectedItem)) {
				item.click();

				// Khi đã tìm thấy và thỏa mãn điều kiện rồi thì không cần duyệt tiếp nữa
				break;
			}
		}
	}

	public void enterItemInCustomDropdown(String parentLocator, String childLocator, String textExpectedItem) {
		driver.findElement(By.cssSelector(parentLocator)).clear();
		driver.findElement(By.cssSelector(parentLocator)).sendKeys(textExpectedItem);
		sleepInSecond(1);
		// Wait cho tất cả các item này xuất hiện trên cây HTML/DOM
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));

		// Tìm lưu lại hết tất cả các item vào 1 List để cho step tiếp theo
		List<WebElement> allItems = driver.findElements(By.cssSelector(childLocator));

		// Duyệt qua từng phần tử(element) trong List trên
		for (WebElement item : allItems) {

			// Lấy text ra
			String textActualItem = item.getText();

			// kiểm tra nếu = text mong muốn thì click vào
			if (textActualItem.equals(textExpectedItem)) {
				item.click();

				// Khi đã tìm thấy và thỏa mãn điều kiện rồi thì không cần duyệt tiếp nữa
				break;
			}
		}
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
