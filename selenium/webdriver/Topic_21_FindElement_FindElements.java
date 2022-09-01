package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_21_FindElement_FindElements {

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

		driver.get("http://live.techpanda.org/index.php/customer/account/login/");

		// Khởi tạo wait
		explicitWait = new WebDriverWait(driver, 30);

		// Đang apply 10s cho việc tìm element
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_FindElement() {
		// 1. Tìm thấy duy nhất 1 element/node
		// Tìm thấy và thao tác trực tiếp lên node đó
		// Vì nó tìm thấy nên không cần chờ hết time out 10s
		driver.findElement(By.cssSelector("input#email"));

		// 2. Tìm thấy nhiều hơn 1 element/node
		// Nó sẽ thao tác với node đầu tiên
		// Không quan tâm các node còn lại
		driver.findElement(By.cssSelector("input[type= 'email']"));

		// 3. Không tìm thấy Element/node nào
		// Có cơ chế tìm lại = 0.5s sẽ tìm lại 1 lần
		// Nếu trong thời gian đang tìm lại mà thấy element thì thỏa mãn đk = Pass
		// Nếu hết thời gian time out (10s) mà vẫn ko tìm thấy element thì
		// + Đánh fail testcase này tại step này, không chạy step tiếp theo
		// + Throw ra exception NoSuchElementException
		driver.findElement(By.cssSelector("input[type= 'check']"));
	}

	@Test
	public void TC_02_FindElements() {

		// 1. Tìm thấy duy nhất 1 element/node
		// Tìm thấy và lưu vào List có 1 element
		// Vì nó tìm thấy nên không cần chờ hết time out 10s
		List<WebElement> elements = driver.findElements(By.cssSelector("input#email"));
		System.out.println("List element number: " + elements.size());

		// 2. Tìm thấy nhiều hơn 1 element/node
		// Tìm thấy và lưu vào List = element tương ứng
		elements = driver.findElements(By.cssSelector("input[type= 'email']"));
		System.out.println("List element number: " + elements.size());

		// 3. Không tìm thấy Element/node nào
		// Có cơ chế tìm lại = 0.5s sẽ tìm lại 1 lần
		// Nếu trong thời gian đang tìm lại mà thấy element thì thỏa mãn đk = Pass
		// Nếu hết thời gian time out (10s) mà vẫn ko tìm thấy element thì
		// + Ko đánh fail testcase + vẫn chạy step tiếp theo
		// + Trả về 1 list rỗng
		elements = driver.findElements(By.cssSelector("input[type= 'check']"));
		System.out.println("List element number: " + elements.size());

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
