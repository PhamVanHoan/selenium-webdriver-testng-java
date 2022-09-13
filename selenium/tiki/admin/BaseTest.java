package tiki.admin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {
	protected WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeTest(alwaysRun = true)
	public void initBrowser() {
		System.out.println("-------initBrowser-------");
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		System.out.println(driver.toString());
	}

	@AfterTest(alwaysRun = true)
	public void clearBrowser() {
		System.out.println("-------Clear Browser-------");
		driver.quit();
	}

	public WebDriver getBrowserDriver() {
		return driver;
	}

}
