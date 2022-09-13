package tiki.admin;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Shopper_02_Manage_Cart extends BaseTest {
	
	String cartName, cartNumber, cartExpiredDate;
	@BeforeTest(alwaysRun = true)
	public void initDataTest() {
		System.out.println("-------initDataTest-------");
		cartName = "Pham Van Hoan";
		cartNumber = "23423-3443-243";
		cartExpiredDate = "20/3/2030";		
	}
	
	
	@Test (groups ={"admin","cart"})
	public void Product_01_Create_Visa() {
		System.out.println("-------Product_01_Create_Visa-------");
		System.out.println(driver);
		driver.get("https://www.facebook.com");
	}
	
	@Test (groups ={"admin","cart"})
	public void Product_02_View_Visa() {
		
	}
	
	@Test (groups ={"admin","cart"})
	public void Product_03_Update_Visa() {
		
	}
	
	@Test (groups ={"admin","cart"})
	public void Product_04_Delete_Visa() {
		
	}
	

}
