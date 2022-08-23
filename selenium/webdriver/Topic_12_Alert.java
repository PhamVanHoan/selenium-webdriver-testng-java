package webdriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Alert {
	WebDriver driver;
	JavascriptExecutor JsExecutor;
	Alert alert;

	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String firefoxAuthenAutoIT = projectPath + "\\autoITScripts\\" + "authen_firefox.exe";
	String chromeAuthenAutoIT = projectPath + "\\autoITScripts\\" + "authen_chrome.exe";

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

	public void TC_01_Accept_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		sleepInSecond(3);
		// Switch qua alert khi alert đang xuất hiện
		alert = driver.switchTo().alert();

		// Verify title trước khhi accept alert
		Assert.assertEquals(alert.getText(), "I am a JS Alert");

		// Accept alert
		alert.accept();

		// Verify accept alert successfully
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(),
				"You clicked an alert successfully");

	}

	public void TC_02_Confirm_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		sleepInSecond(3);
		// Switch qua alert khi alert đang xuất hiện
		alert = driver.switchTo().alert();

		// Verify title trước khhi accept alert
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");

		// Cancel alert
		alert.dismiss();

		// Verify cancel alert successfully
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked: Cancel");
	}

	public void TC_03_Prompt_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		String keyword = "Selenium WebDriver";
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		sleepInSecond(3);
		// Switch qua alert khi alert đang xuất hiện
		alert = driver.switchTo().alert();

		// Verify title trước khhi accept alert
		Assert.assertEquals(alert.getText(), "I am a JS prompt");

		// Nhập text
		alert.sendKeys(keyword);

		// Accept alert
		alert.accept();
		// Verify accept alert successfully
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You entered: " + keyword);

	}

	public void TC_04_Accept_Alert_Login() {
		driver.get("https://demo.guru99.com/v4/");

		// Click button Login
		driver.findElement(By.name("btnLogin")).click();
		sleepInSecond(2);

		alert = driver.switchTo().alert();
		// Verify title

		Assert.assertEquals(alert.getText(), "User or Password is not valid");
		alert.accept();
		sleepInSecond(2);

		// Verify Url
		Assert.assertEquals(driver.getCurrentUrl(), "https://demo.guru99.com/v4/index.php");

	}

	public void TC_05_Authentication_Alert() {
		// Pass hẳn user/password vào Url trước khi open nó ra
		// Url: http://the-internet.herokuapp.com/basic_auth
		// Pass: Username/password vào Url
		// http://username:password@the-internet.herokuapp.com/basic_auth

		driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.cssSelector("div.example>p")).getText()
				.contains("Congratulations! You must have the proper credentials"));
	}

	public void TC_06_Authentication_Alert() {

		driver.get("http://the-internet.herokuapp.com");
		sleepInSecond(3);
		String basicAuthenUrl = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
		System.out.println(basicAuthenUrl);
		driver.get(getAuthenticationUrl(basicAuthenUrl, "admin", "admin"));
		Assert.assertTrue(driver.findElement(By.cssSelector("div.example>p")).getText()
				.contains("Congratulations! You must have the proper credentials"));
	}

	@Test
	public void TC_07_Authentication_Alert_AutoIT() throws IOException {
		// Bật script của AutoIT trước rồi mở site chứ Authenication Alert
		// Thực thi file exe trong code java
		if (driver.toString().contains("firefox")) {
			Runtime.getRuntime().exec(new String[] { firefoxAuthenAutoIT, "admin", "admin" });
		} else {
			Runtime.getRuntime().exec(new String[] { chromeAuthenAutoIT, "admin", "admin" });
		}

		driver.get("http://the-internet.herokuapp.com/basic_auth");
		sleepInSecond(3);

		Assert.assertTrue(driver.findElement(By.cssSelector("div.example>p")).getText()
				.contains("Congratulations! You must have the proper credentials"));
	}

	public String getAuthenticationUrl(String basicAuthenUrl, String userName, String password) {
		String[] authenArray = basicAuthenUrl.split("//");
		basicAuthenUrl = authenArray[0] + "//" + userName + ":" + password + "@" + authenArray[1];
		return basicAuthenUrl;
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
