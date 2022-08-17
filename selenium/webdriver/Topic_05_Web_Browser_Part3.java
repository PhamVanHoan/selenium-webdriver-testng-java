package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Browser_Part3 {
	WebDriver driver;
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
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	public void TC_01_Is_Displayed() {
//		- Có thể nhìn thấy
//		- Có kích thước cụ thể: rộng x cao
//		- Phạm vi áp dụng: tất cả các loại element: texbox. textarea, link, button..

		driver.get("https://automationfc.github.io/basic-form/index.html");

		// Email textbox
		WebElement emailTextbox = driver.findElement(By.cssSelector("input#mail"));

		if (emailTextbox.isDisplayed()) {
			emailTextbox.sendKeys("Automation Tesing");
			System.out.println("Email textbox is displayed");
		} else {
			System.out.println("Email textbox is undisplayed");
		}
		System.out.println(emailTextbox.isDisplayed());

		// Age Under 18 Radio button

		WebElement ageUnder18Radio = driver.findElement(By.cssSelector("input#under_18"));

		if (ageUnder18Radio.isDisplayed()) {
			ageUnder18Radio.click();
			System.out.println("Age under 18 Radio button is displayed");
		} else {
			System.out.println("Age under 18 Radio button is undisplayed");
		}
		System.out.println(ageUnder18Radio.isDisplayed());

		// Education text area

		WebElement educationTextarea = driver.findElement(By.cssSelector("textarea#edu"));

		if (educationTextarea.isDisplayed()) {
			educationTextarea.sendKeys("Automation Tesing");
			System.out.println("Education text area is displayed");
		} else {
			System.out.println("Education text area is undisplayed");
		}
		System.out.println(educationTextarea.isDisplayed());

		// Image 5 (Undisplayed)

		WebElement image5 = driver.findElement(By.xpath("//h5[text()='Name: User5']"));

		if (image5.isDisplayed()) {
			System.out.println("Image 5 is displayed");
		} else {
			System.out.println("Image 5 is undisplayed");
		}

		// Nếu như element hiển thị thì hàm isDisplayed trả về giá trị true
		// Nếu như element ko hiển thị thì hàm isDisplayed trả về giá trị false

		System.out.println(image5.isDisplayed());
	}

	public void TC_02_Is_Enable() {
		// - Có thể tương tác được = enable -> true
		// - Không tương tác được = disable -> false
		// - Phạm vi áp dụng: tất cả các loại element: texbox. textarea, link, button..

		driver.get("https://automationfc.github.io/basic-form/index.html");

		// Email texbox
		WebElement emailTextbox = driver.findElement(By.cssSelector("input#mail"));

		if (emailTextbox.isEnabled()) {
			System.out.println("Email textbox is enable");

		} else {
			System.out.println("Email textbox is disable");
		}

		// Password textbox

		WebElement passwordTexbox = driver.findElement(By.cssSelector("input#disable_password"));

		if (passwordTexbox.isEnabled()) {
			System.out.println("Password textbox is enable");
		} else {
			System.out.println("Password textbox is disable");
		}
	}

	public void TC_03_Is_Selected() {
		// - Đã được chọn hay chưa = Selected -> true
		// - Không tương tác được = Deselected -> false
		// - Phạm vi áp dụng: Radio button, Check box, Dropdown (Default)

		driver.get("https://automationfc.github.io/basic-form/index.html");

		// Age under 18 radio button
		WebElement ageUnder18Radio = driver.findElement(By.cssSelector("input#under_18"));
		ageUnder18Radio.click();

		if (ageUnder18Radio.isSelected()) {
			System.out.println("Age under 18 is selected");
		} else {
			System.out.println("Age under 18 is de-selected");
		}

		// Java checkbox
		WebElement javaCheckbox = driver.findElement(By.cssSelector("input#java"));
		javaCheckbox.click();

		if (javaCheckbox.isSelected()) {
			System.out.println("Java checkbox is selected");
		} else {
			System.out.println("Java checkbox is de-selected");
		}

	}

	@Test
	public void TC_04_Is_Mail_Chimp() {

		driver.get("https://login.mailchimp.com/signup/");

		// Nhập email và username textbox
		driver.findElement(By.cssSelector("input#email")).sendKeys("automationfc@gmail.com");
		sleepInSecond(3);

		WebElement passwordTextbox = driver.findElement(By.cssSelector("input#new_password"));

		// Check lowercase
		passwordTextbox.sendKeys("aa");
		sleepInSecond(2);

		Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class= '8-char not-completed']")).isDisplayed());

		// Check uppercase
		passwordTextbox.clear();
		passwordTextbox.sendKeys("AA");
		sleepInSecond(2);

		Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class= '8-char not-completed']")).isDisplayed());

		// Check number
		passwordTextbox.clear();
		passwordTextbox.sendKeys("88");
		sleepInSecond(2);

		Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class= '8-char not-completed']")).isDisplayed());

		// Check special char
		passwordTextbox.clear();
		passwordTextbox.sendKeys("@@");
		sleepInSecond(2);

		Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class= '8-char not-completed']")).isDisplayed());

		// Check 8 chars
		passwordTextbox.clear();
		passwordTextbox.sendKeys("ABCc@12");
		sleepInSecond(2);

		Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class= '8-char not-completed']")).isDisplayed());

		// Check valid data
		passwordTextbox.clear();
		passwordTextbox.sendKeys("ABCc@123");
		sleepInSecond(2);

		Assert.assertEquals(passwordTextbox.getAttribute("class"), "av-password success-check");

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
