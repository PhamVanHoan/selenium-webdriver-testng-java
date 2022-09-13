package testng;

import org.testng.annotations.Test;

public class Topic_03_Priority {

	
	@Test(enabled = false)
	public void EndUser_01_Register_New_Employee () {
		
	}
	
	
	@Test(enabled = true, description = "JIRA 1234 - View employee")
	public void EndUser_02_View_Employee () {
		
	}
	
	@Test
	public void EndUser_03_Edit_Employee () {
		
	}
	
	@Test
	public void EndUser_04_Move_Employee () {
		
	}
}
