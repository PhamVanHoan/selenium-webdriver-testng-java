package javaTester;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Topic_03_Data_Type {
	public static void main(String[] args) {
		// Thông tin của nhân viên
		// Tên /Tuổi/ ngày tháng năm sinh/ giới tính/ quê quán/ lương
		// Ánh xạ thông tin này vào trong lặp trình

		// Cách khai báo biến
		// 1- Kiểu dữ liệu của biến
		// 2- Tên biến
		// 3- Giá trị của biến

		// 2 cách khai báo biến và gán giá trị
		// 1- Vừa khai báo vừa gán giá trị
		// String name = "Automation";

		// 2- Khai báo trước rồi gán sau
		// String name;
		// name = "Automation";
		// name = "Automation FC";

		// Lấy giá trị cuối cùng được khai báo => name = "Automation FC"

		// 2 loại kiểu dữ liệu

		// 1. Kiểu dữ liệu nguyên thủy
		// 8 loại
		// Số nguyên: bypte/ short/ int/ long
		byte bNumber = 5;
		short sNumber = 500;
		int iNumber = 6000;
		long lNumber = 1234456778;

		// Số thực: float/ double
		float salary = 15.5f;

		double point = 9.8d;
		// Ký tự: char
		// Chỉ được phép dùng nháy đơn, chứa duy nhất 1 ký tự;
		char a= 'a';
		
		// Logic: boolean
		
		boolean marriedStatus = true;
		marriedStatus = false;

		// 2. Kiểu dữ liệu tham chiếu

		// Chuỗi: String (Chữ, số, ký tự đặc biệt)
		// Dấu nháy đôi
		
		String emailInvalid = "abc123@G;@##.com";

		// Class/ Interface (Date)
		
		Date date = new Date();
		
		WebDriver driver = new FirefoxDriver();

		// Đối tượng: Object
		Object students;
		
		// Array: Mảng (Khai báo số lượng dữ liệu trước) - Cố đinh số lượng
		int numbers [] = {13, 2, 3};
		
		String addresses [] = {"Da Nang", "Hanoi", "SG"};
		
		

		// List/ Set/ Queue (Collection) - Động
		List <Integer> studenNumber = new ArrayList<Integer>();
		List <String> studenAddress = new ArrayList<String>();
		
		Set <String> studenCity = new LinkedHashSet<>();
	}
}
