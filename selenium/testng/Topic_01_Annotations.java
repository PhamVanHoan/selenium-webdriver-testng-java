package testng;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class Topic_01_Annotations {
  @Test()
  public void TC_01() {
	  System.out.println("Testcase 01");
  }
  
  @Test()
  public void TC_02() {
	  System.out.println("Testcase 02");
  }
  
  @BeforeMethod
  public void beforeMethod() {
	  System.out.println("before Method");
  }

  @AfterMethod
  public void afterMethod() {
	  System.out.println("after Method");
  }


  @BeforeClass
  public void beforeClass() {
	  System.out.println("before Class");
  }

  @AfterClass
  public void afterClass() {
	  System.out.println("after Class");
  }

  @BeforeTest
  public void beforeTest() {
	  System.out.println("before Test");
  }

  @AfterTest
  public void afterTest() {
	  System.out.println("after Test");
  }

  @BeforeSuite
  public void beforeSuite() {
	  System.out.println("before Suite");
  }

  @AfterSuite
  public void afterSuite() {
	  System.out.println("after Suite");
  }

}
