package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Xpath_Part_1 {
	// Khai báo biến driver đại diện cho Selenium webdriver
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {

		// Set geckordriver: giao tiếp giữa browser và code
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");

		// Bật trình duyệt Firefox lên
		driver = new FirefoxDriver();

		// Set thời gian tìm element
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Phóng to browser
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01() {

		// Mở app ra
		driver.get("https://www.facebook.com/");

//		<input class="text form-control" id="txtEmail" name="txtEmail" 
//				placeholder="Địa chỉ email" type="email" value="">
	}
	// Xpath
	// Absolute XPath: /html/body
	// Relavite XPath: //tagname= [@atribute0name = 'atribute-value']
	// Relavite XPath: //tagname= [@atribute0name = "atribute-value"]

	// Css

	//     tagname= [atribute0name = 'atribute-value']
	//     tagname= [atribute0name = "atribute-value"]

	
	// Dấu * đại diện cho bất kỳ 1 thẻ nào => không nên dùng do chạy chậm, không biết tên thẻ là gì
	@Test
	public void TC_02() {
	}

	@Test
	public void TC_03() {
	}

	@AfterClass
	public void afterClass() {

	}
}
