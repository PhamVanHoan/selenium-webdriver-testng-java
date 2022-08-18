package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Textbox_TextArea {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String firstName, lastName, employeeID, editFirstName, editLastName, immigrationNumber, comments;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac")) {// Mac
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver.exe");

		} else // Windows

		{
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		firstName = "Hoan";
		lastName = "Pham";
		editFirstName = "Harry";
		editLastName = "Nguyen";
		immigrationNumber = "1214234534";
		comments = "79 Le Thanh Tong\nNam Tu Liem\nHa Noi";
	}

	@Test
	public void TC_01_Textbox_TextArea() {
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php");

		// Nhập vào username, password
		driver.findElement(By.cssSelector("input[name='username']")).sendKeys("Admin");
		driver.findElement(By.cssSelector("input[name='password']")).sendKeys("admin123");

		// Click login
		driver.findElement(By.xpath("//button[text()=' Login ']")).click();
		sleepInSecond(5);

		// Mở màn hình Add Employee
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/pim/addEmployee");

		// Nhập dữ liệu màn hình Add Employee
		driver.findElement(By.cssSelector("input#firstname")).sendKeys(firstName);
		driver.findElement(By.cssSelector("input#lastname")).sendKeys(lastName);

		// Lưu giá trị của EmployeeID vào biến
		// Lấy ra giá trị
		// Gán vào biến

		employeeID = driver.findElement(By.cssSelector("input#employeeId")).getAttribute("name");

		// Click Save button
		driver.findElement(By.cssSelector("input#btnSave")).click();

		// Verify the fields are disable
		Assert.assertFalse(driver.findElement(By.cssSelector("input#personal_txtEmpFirstName")).isEnabled());
		Assert.assertFalse(driver.findElement(By.cssSelector("input#personal_txtEmpLastName")).isEnabled());
		Assert.assertFalse(driver.findElement(By.cssSelector("input#personal_txtEmployeeId")).isEnabled());

		// Verify actual value the same expected value
		Assert.assertEquals(driver.findElement(By.cssSelector("input#personal_txtEmpFirstName")), firstName);
		Assert.assertEquals(driver.findElement(By.cssSelector("input#personal_txtEmpLastName")), lastName);
		Assert.assertEquals(driver.findElement(By.cssSelector("input#personal_txtEmployeeId")), employeeID);

		// Click Save button
		driver.findElement(By.cssSelector("input#btnSave"));

		// Verify the fields are enable
		Assert.assertTrue(driver.findElement(By.cssSelector("input#personal_txtEmpFirstName")).isEnabled());
		Assert.assertTrue(driver.findElement(By.cssSelector("input#personal_txtEmpLastName")).isEnabled());
		Assert.assertTrue(driver.findElement(By.cssSelector("input#personal_txtEmployeeId")).isEnabled());

		// Edit the field firstName, lastName
		driver.findElement(By.cssSelector("input#personal_txtEmpFirstName")).clear();
		driver.findElement(By.cssSelector("input#personal_txtEmpFirstName")).sendKeys(editFirstName);
		driver.findElement(By.cssSelector("input#personal_txtEmpLastName")).clear();
		driver.findElement(By.cssSelector("input#personal_txtEmpLastName")).sendKeys(editLastName);

		// Click Save button
		driver.findElement(By.cssSelector("input#btnSave"));
		sleepInSecond(3);

		// Verify the fields are disable
		Assert.assertFalse(driver.findElement(By.cssSelector("input#personal_txtEmpFirstName")).isEnabled());
		Assert.assertFalse(driver.findElement(By.cssSelector("input#personal_txtEmpLastName")).isEnabled());
		Assert.assertFalse(driver.findElement(By.cssSelector("input#personal_txtEmployeeId")).isEnabled());

		// Verify actual value the same expected value
		Assert.assertEquals(driver.findElement(By.cssSelector("input#personal_txtEmpFirstName")), editFirstName);
		Assert.assertEquals(driver.findElement(By.cssSelector("input#personal_txtEmpLastName")), editLastName);
		Assert.assertEquals(driver.findElement(By.cssSelector("input#personal_txtEmployeeId")), employeeID);

		
		//Open Immigration tab
		driver.findElement(By.xpath("//a[text()='Immigration']")).click();
		
		//Click to Add button
		driver.findElement(By.cssSelector("input#btnAdd")).click();
		
		// Enter to Immigration number and Comments textarea
		driver.findElement(By.cssSelector("input#immigration_number")).sendKeys(immigrationNumber);
		driver.findElement(By.cssSelector("textarea#immigration_comments")).sendKeys(comments);	
		sleepInSecond(5);
		
		//Click Save
		driver.findElement(By.cssSelector("input#btnSave")).click();
		sleepInSecond(3);
		
		//Click to Passport link
		driver.findElement(By.xpath("//a[text()='Passport']")).click();
	
		// Verify actual value same with expected value
		Assert.assertEquals(driver.findElement(By.cssSelector("input#immigration_number")), immigrationNumber);
		Assert.assertEquals(driver.findElement(By.cssSelector("textarea#immigration_comments")), comments);
		
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

	// Sleep cứng (Static wait)
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
