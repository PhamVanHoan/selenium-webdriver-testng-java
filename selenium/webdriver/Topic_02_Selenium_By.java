package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Selenium_By {

	// Khai báo 1 biến dại diên cho thư viện Selenium WebDriver
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		// Bước 1 mở trình duyệt lên
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();

		// Bấm cho maximize browser lên
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.manage().window().maximize();

	}

	@Test
	public void TC_01() {
		// Bước 2: nhập vào Url
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");

		// Bước 3: Click vào My account để mở trang login

		// Tìm 1 element
        //driver.findElement(null)
		
		// Tìm nhiều element
        //driver.findElements(null)
		
		//HTLM của element (Email Textbox)
		
/*		<input type="email" autocapitalize="off" 
				autocorrect="off" spellcheck="false" 
				name="login[username]" value="" id="email" 
				class="input-text required-entry validate-email" 
				title="Email Address">
	}*/
		
		// input - thẻ của element này (tagname)
		// atribute name: thuộc tính của thẻ - type, autocapitalize, autocorrect, spellcheck, name, value, id, class, title
		// atribute value - giá trị của thuộc tính: off, email ...
		
		//Xpath Format
		//tagname[@attribute-name = 'atribute-value']
		//input[@name = 'login[username]']
		//input[@id = 'email']
		//input[@title = 'Email Address']
		
		
		
		//CSS Format tagname[attribute-name = 'atribute-value']
		
		//ID - Email TextBox
		driver.findElement(By.id("email"));
		
		// Class:
		//1 - Giá trị ko có khoảng trắng -> Lấy hết
		//2 - Giá trị chứa khoảng trắng -> Lấy 1 phần
		
		//Cách gõ code ít bị lỗi
		// Không được dư ký tự ()
		// thiếu ;
		// Ctrl Space
		//driver.findElement(By.className(""));
		
		//Name
		driver.findElement(By.name("login[username]"));
		
		//Tagname - Tìm xem có bao nhiêu element /page
		driver.findElement(By.tagName("a"));
		
		
		//LinkText (Limk) - Text tuyệt đối
		driver.findElement(By.linkText("SEARCH TERMS"));
		
		
		//Partial LinkTexxt (Link) - Text tuyệt đối/ tương đối
		driver.findElement(By.partialLinkText("SEARCH"));
		driver.findElement(By.partialLinkText("RCH TER"));
		driver.findElement(By.partialLinkText("TERMS"));
		
		
		//Css - Cover được hết cả 6 loại trên
		driver.findElement(By.cssSelector("input[name = 'login[username]']"));
		driver.findElement(By.cssSelector("input[id = 'email']"));
		driver.findElement(By.cssSelector("input[title = 'Email Address']"));
		
		//Xpath
		driver.findElement(By.xpath("//input[@name = 'login[username]']"));
		driver.findElement(By.xpath("//input[@id = 'email']"));
		driver.findElement(By.xpath("//input[@title = 'Email Address']"));
		
	}

	@Test
	public void TC_02() {
	}

	@Test
	public void TC_03() {
	}

	@AfterClass
	public void afterClass() {
		// Bước 6: Đóng browser
		driver.quit();
	}
}
