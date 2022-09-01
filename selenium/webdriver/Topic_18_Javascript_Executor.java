package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_18_Javascript_Executor {

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

		// Khởi tạo Js
		jsExecutor = (JavascriptExecutor) driver;

		// Khởi tạo wait
		explicitWait = new WebDriverWait(driver, 30);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	
	public void TC_01_TechPanda() {
		navigateToUrlByJS("http://live.techpanda.org/");
		sleepInSecond(5);
		
		String textPandaDomain = (String) executeForBrowser("return document.domain");
		Assert.assertEquals(textPandaDomain, "live.techpanda.org");

		String homePageUrl = (String) executeForBrowser("return document.URL");
		Assert.assertEquals(homePageUrl, "http://live.techpanda.org/");

		hightlightElement("//a[text()='Mobile']");
		clickToElementByJS("//a[text()='Mobile']");
		sleepInSecond(5);
		
		hightlightElement("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
		clickToElementByJS("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
		sleepInSecond(5);
		
		Assert.assertTrue(areExpectedTextInInnerText("Samsung Galaxy was added to your shopping cart."));

		hightlightElement("//a[text()='Customer Service']");
		clickToElementByJS("//a[text()='Customer Service']");
		sleepInSecond(5);
		
		hightlightElement("//input[@id= 'newsletter']");
		scrollToElementOnTop("//input[@id= 'newsletter']");

		String emailAddress = "afc" + generateRandomNumber() + "@hotmail.vn";
		sendkeyToElementByJS("//input[@id= 'newsletter']", emailAddress);
		
		hightlightElement("//button[@title ='Subscribe']");
		clickToElementByJS("//button[@title ='Subscribe']");
		sleepInSecond(5);
		
		Assert.assertTrue(areExpectedTextInInnerText("Thank you for your subscription."));
	
		navigateToUrlByJS("https://demo.guru99.com/v4/");
		sleepInSecond(5);
		
		String demoGuruDomain = (String) executeForBrowser("return document.domain");
		Assert.assertEquals(demoGuruDomain, "demo.guru99.com");
	}

	@Test
	public void TC_02_Rode() {
		driver.get("https://warranty.rode.com/");
		
		WebElement registerButton = driver.findElement(By.xpath("//button[contains(text(),'Register')]"));
		registerButton.click();
		sleepInSecond(2);
		
		//First name
		Assert.assertEquals(getElementValidationMessage("//input[@id='firstname']"), "Please fill out this field.");

		driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys("firstname");
		registerButton.click();
		sleepInSecond(2);
		
		Assert.assertEquals(getElementValidationMessage("//input[@id='surname']"), "Please fill out this field.");
		
		driver.findElement(By.xpath("//input[@id='surname']")).sendKeys("surname");
		registerButton.click();
		sleepInSecond(2);
		
		Assert.assertEquals(getElementValidationMessage("//form[contains(@action, 'register')]//input[@id='email']"), "Please fill out this field.");	
		
		driver.findElement(By.xpath("//form[contains(@action, 'register')]//input[@id='email']")).sendKeys("email");
		registerButton.click();
		sleepInSecond(2);
		
		Assert.assertEquals(getElementValidationMessage("//form[contains(@action, 'register')]//input[@id='email']"), "Please enter an email address.");	

		driver.findElement(By.xpath("//form[contains(@action, 'register')]//input[@id='email']")).clear();
		driver.findElement(By.xpath("//form[contains(@action, 'register')]//input[@id='email']")).sendKeys("email@email.com");
		registerButton.click();
		sleepInSecond(2);
		
		Assert.assertEquals(getElementValidationMessage("//form[contains(@action, 'register')]//input[@id='password']"), "Please fill out this field.");	
	}

	@Test
	public void TC_03() {

		sleepInSecond(5);
	}

	public Object executeForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor
				.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void hightlightElement(String locator) {
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element,
				"border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
	}

	public void scrollToElementOnTop(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	public void scrollToElementOnDown(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
	}

	public void sendkeyToElementByJS(String locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}

	public String getElementValidationMessage(String locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}

	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
				getElement(locator));
		return status;
	}

	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}

	public int generateRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
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
