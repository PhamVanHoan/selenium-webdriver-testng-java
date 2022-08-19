package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Custom_Dropdown_Part1 {
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

	@Test
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

		
		/* NUMBER Dropdown*/
		//Gọi hàm: 1 hàm có thể gọi 1 hàm khác để dùng trong cùng 1 Class
		selectItemInCustomDropdown("span#number-button", "ul#number-menu div", "7");
		sleepInSecond(3);
		
		selectItemInCustomDropdown("span#number-button", "ul#number-menu div", "5");
		sleepInSecond(3);
	
		selectItemInCustomDropdown("span#number-button", "ul#number-menu div", "3");
		sleepInSecond(3);
		
		/* SPEED Dropdown*/
		selectItemInCustomDropdown("span#speed-button", "ul#speed-menu div", "Fast");
		sleepInSecond(3);
		
		selectItemInCustomDropdown("span#speed-button", "ul#speed-menu div", "Slower");
		sleepInSecond(3);
		
		/* Title Dropdown*/
		selectItemInCustomDropdown("span#salutation-button", "ul#salutation-menu div", "Dr");
		sleepInSecond(3);
		
		selectItemInCustomDropdown("span#salutation-button", "ul#salutation-menu div", "Mr");
		sleepInSecond(3);
	}

	@Test
	public void TC_02() {
	}

	@Test
	public void TC_03() {

		sleepInSecond(5);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	// Không dùng cho 1 dropdown cụ thể
	// Dùng chung cho dự án
	public void selectItemInCustomDropdown(String parentLocator, String childLocator, String textExpectedItem) {
		// 1- Click vào 1 phần tử nào đó thuộc dropdown để nó xổ ra
		driver.findElement(By.cssSelector(parentLocator)).click();
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));
		// Lưu trữ tất cả các item lại thì mới duyệt qua được
		List<WebElement> allItems = driver.findElements(By.cssSelector(childLocator));

		// Duyệt qua từng item
		// Vòng lặp

		for (WebElement item : allItems) {
			// Dùng biến item để thao tác trong vòng lặp for

			// Lấy text ra
			String textActualItem = item.getText();

			// kiểm tra nếu = text mong muốn thì click vào
			if (textActualItem.equals(textExpectedItem)) {
				item.click();
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
