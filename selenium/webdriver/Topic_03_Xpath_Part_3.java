package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Xpath_Part_3 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac")) {//Mac
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver.exe");

		} else //Windows

		{
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void Register_01_Empty_Data() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");

		// Action
		driver.findElement(By.id("txtFirstname")).sendKeys("");
		driver.findElement(By.id("txtEmail")).sendKeys("");
		driver.findElement(By.id("txtCEmail")).sendKeys("");
		driver.findElement(By.id("txtPassword")).sendKeys("");
		driver.findElement(By.id("txtCPassword")).sendKeys("");
		driver.findElement(By.id("txtPhone")).sendKeys("");
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();

		// Verify dữ liệu

		Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(), "Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Vui lòng nhập số điện thoại.");

	}

	@Test
	public void Register_02_Invalid_Email() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");

		// Action
		driver.findElement(By.id("txtFirstname")).sendKeys("Hoan Pham");
		driver.findElement(By.id("txtEmail")).sendKeys("123@345@123");
		driver.findElement(By.id("txtCEmail")).sendKeys("123@345@123");
		driver.findElement(By.id("txtPassword")).sendKeys("123456");
		driver.findElement(By.id("txtCPassword")).sendKeys("123456");
		driver.findElement(By.id("txtPhone")).sendKeys("0987654321");
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();

		// Verify dữ liệu

		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email hợp lệ");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Vui lòng nhập email hợp lệ");
		

	}

	@Test
	public void Register_03_Incorrect_Email() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");

		// Action
		driver.findElement(By.id("txtFirstname")).sendKeys("Hoan Pham");
		driver.findElement(By.id("txtEmail")).sendKeys("hoan@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("hoan1@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("123456");
		driver.findElement(By.id("txtCPassword")).sendKeys("123456");
		driver.findElement(By.id("txtPhone")).sendKeys("0987654321");
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();

		// Verify dữ liệu

		
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");
		

	}
	@Test
	public void Register_04_Password_Less_Than_6_Chars() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");

		// Action
		driver.findElement(By.id("txtFirstname")).sendKeys("Hoan Pham");
		driver.findElement(By.id("txtEmail")).sendKeys("hoan@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("hoan@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("12345");
		driver.findElement(By.id("txtCPassword")).sendKeys("12345");
		driver.findElement(By.id("txtPhone")).sendKeys("0987654321");
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();

		// Verify dữ liệu

		
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
		

	}
	@Test	
	public void Register_05_Incorrect_Confirm_Password() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");

		// Action
		driver.findElement(By.id("txtFirstname")).sendKeys("Hoan Pham");
		driver.findElement(By.id("txtEmail")).sendKeys("hoan@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("hoan@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("123456");
		driver.findElement(By.id("txtCPassword")).sendKeys("123457");
		driver.findElement(By.id("txtPhone")).sendKeys("0987654321");
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();

		// Verify dữ liệu

		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu bạn nhập không khớp");
		

	}
	
	@Test	
	public void Register_06_Invalid_Phone_Number() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");

		// Action
		driver.findElement(By.id("txtFirstname")).sendKeys("Hoan Pham");
		driver.findElement(By.id("txtEmail")).sendKeys("hoan@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("hoan@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("123456");
		driver.findElement(By.id("txtCPassword")).sendKeys("123456");
		

		// Verify dữ liệu
		
		// < 10 chars
		driver.findElement(By.id("txtPhone")).sendKeys("0987654");
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại phải từ 10-11 số.");
		

		// > 10 chars
		driver.findElement(By.id("txtPhone")).clear();
		driver.findElement(By.id("txtPhone")).sendKeys("098765432111");
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại phải từ 10-11 số.");
		
		// > Start without correct format
		driver.findElement(By.id("txtPhone")).clear();
		driver.findElement(By.id("txtPhone")).sendKeys("11111111111");
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019");
		System.out.println("Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019");
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
