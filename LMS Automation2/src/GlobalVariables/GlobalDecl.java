package GlobalVariables;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;
public class GlobalDecl 
{

	public  WebDriver driver;
	public  Selenium selenium;
	public int TotalPages, b, c;
	//public String baseUrl = "http://50.116.0.186:81/";
	public String baseUrl = "http://http://50.116.0.186:81/";
	public String Username = "praveen@gyanwebsolutions.com";
	//public String Username = "12345@gmail.com";
	public String Password = "";

	//************ COURSE NAME**************************************************************
	//public String CourseName = "Test course one";
	public String CourseName = "TestCourse with all Activities";
	public String CourseFEE = "4700";
	public int TotaChaptersRequired = 1;
	public int TotaLessonsRequired = 1;
	public int TotaAssignmentsRequired = 1;
	public int TotaAssessmentsRequired = 1;
	public int TotaSurveysRequired = 1;

	// public String excelName = "QuizTest_Questions.xls";
	public String excelName = "QuizTest_Questions for Test run 2 qsts only.xls";
	//public String excelName = "emailrun.xls";

	public String course_id;
	public String c_url;
	//**********************************************************************************

/*	//Office System Path
	public String Input_ExcelFile = "D:\\PRAVIN\\Selenium\\TestDataExcelFiles\\Input_TestDataExcelFiles\\";
	public String Output_ExcelFile = "D:\\PRAVIN\\Selenium\\TestDataExcelFiles\\Output_TestDataExcelFiles\\";

	//public String FileUpload = "D:\\PRAVIN\\Selenium\\TestDataExcelFiles\\Output_TestDataExcelFiles\\";
	public String OUTPUT_ZIP_FILE = "C:\\Users\\praveen.GYAN\\Desktop\\ZIP File Test output\\TestOutputFolder.zip";

	public String SOURCE_FOLDER = "E:\\KeplerWorkSpace\\LMS\\test-output\\";
	public String OUTPUT_PopupFolder = "C:\\Users\\praveen.GYAN\\Desktop\\ZIP File Test output";

	public String image1 = "C:\\Users\\Public\\Pictures\\Sample Pictures\\Koala.jpg";
	public String image2 = "C:\\Users\\Public\\Pictures\\Sample Pictures\\Chrysanthemum.jpg";
	public String image3 = "C:\\Users\\Public\\Pictures\\Sample Pictures\\Jellyfish.jpg";

*/


	
	//Pravin Laptop.
	public String Input_ExcelFile = "E:\\TEST DATA\\TestDataExcelFiles\\Input_TestDataExcelFiles\\";
	public String Output_ExcelFile = "E:\\TEST DATA\\TestDataExcelFiles\\Output_TestDataExcelFiles\\";

	public String FileUpload = "D:\\PRAVIN\\Selenium\\TestDataExcelFiles\\Output_TestDataExcelFiles\\";

	public String OUTPUT_ZIP_FILE = "G:\\TestNG output Folder\\TestOutputFolder.zip";
	public String SOURCE_FOLDER = "E:\\LMS 1 Selenium_workspace\\LMS Automation";
	public String OUTPUT_PopupFolder = "C:\\Users\\pravin7may\\Desktop";
	
	public String image1 = "C:\\Users\\pravin7may\\Pictures\\Koala.jpg";
	public String image2 = "C:\\Users\\pravin7may\\Pictures\\Chrysanthemum.jpg";
	public String image3 = "C:\\Users\\pravin7may\\Pictures\\Jellyfish.jpg";



	//Shravan Laptop
	//public String Input_ExcelFile = "E:\\xxxxxxxxxxxxxxxx\\Input_TestDataExcelFiles\\";
	//public String Output_ExcelFile = "E:\\xxxxxxxxxxxxxx\\Output_TestDataExcelFiles\\";




	public FileInputStream Fileinput;
	public Workbook wbook;








	//###########################################  LOGIN Function ################################################################################

	public void login() throws InterruptedException, RowsExceededException, WriteException, BiffException, IOException
	{
		System.out.println("************************** START OF THE EXECUTION ******************************************************************************************************************************************************** ");



		FirefoxProfile firefoxProfile = new FirefoxProfile();
		firefoxProfile.setEnableNativeEvents(true);
		driver = new FirefoxDriver(firefoxProfile);

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		selenium = new WebDriverBackedSelenium(driver, baseUrl);

		driver.manage().window().setPosition(new Point(-10, 0));
		driver.manage().window().setSize(new Dimension(1400,570));

		driver.get(baseUrl);
		driver.manage().deleteAllCookies();
		selenium.waitForPageToLoad("30000");

		//Click on LOGIN button.
		driver.findElement(By.xpath("html/body/section/header/div/ul/li[6]/a")).click();

		//UserID and Password.
		driver.findElement(By.id("email")).sendKeys(Username);
		driver.findElement(By.id("login-password")).sendKeys(Password);
		//Click on Login Button.
		driver.findElement(By.xpath(".//*[@id='submit']")).click();
		//Click on Dashboard
		driver.findElement(By.xpath("html/body/section/header/div/ul/li[2]/a")).click();
		selenium.waitForPageToLoad("30000");
	}

	//######################################################################################################################################################














	//***************************** To Click on exact Course name******************************************************************************************
	public void CourseListing() throws InterruptedException
	{
		System.out.println("This is CourseListing() function");

		//Click on 'Course Administartion'.
		driver.findElement(By.xpath("//a[contains(text(),'Course Administration')]")).click();
		Thread.sleep(1000);
		//Click on 'Courses'  
		driver.findElement(By.xpath("//a[contains(text(),'Manage Courses')]")).click();

		try {

			WebElement pagination = driver.findElement(By.id("idf"));
			List <WebElement> pages = pagination.findElements(By.tagName("option"));
			//	System.out.println("Total No.of Pages in User Listing are: " + pages.size());
			TotalPages =pages.size();
			//System.out.println("----------------------------------------------------------------");
		}
		catch (Exception e)
		{
			System.out.println("Total No.of Pages are: 1 only.");
			TotalPages=1;
		}


		//System.out.println(" a value is:" + a);
		for (int i = 1; i <= TotalPages; i++)
		{

			int z=1;
			WebElement UserList = driver.findElement(By.xpath(".//*[@id='grid']/table/tbody"));
			java.util.List<WebElement> tr = UserList.findElements(By.tagName("tr"));

			System.out.println("");

			for (int j = 4; j <= tr.size(); j++)
			{
				String s1 = driver.findElement(By.xpath(".//*[@id='grid']/table/tbody/tr["+j+"]/td[1]/a")).getText();
				//System.out.println((j-3)+ " Course Name--> " + s1);
				if(s1.contains(CourseName))
				{

					/*              
					//Code for Rt.Click  and OPen page in New tab/Window.
					Actions act = new Actions(driver);
					WebElement onElement = driver.findElement(By.xpath(".//*[@id='grid']/table/tbody/tr["+ j + "]/td[4]/a[1]"));
					act.contextClick(onElement).perform();
					act.sendKeys("T").perform();  // If you want the link to open in new tab then use T instead of w
					selenium.waitForPageToLoad("30000");
					// Close the Home Tab.
					driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"w");// close the Home Tab          */					

					driver.findElement(By.xpath(".//*[@id='grid']/table/tbody/tr["+ j +"]/td[1]/a")).click();

					z = 0;
					break;
				}
			}
			if ((i == TotalPages) || z == 0 )
			{
				break;
			}
			else
			{
				//Click on NEXT PAGE Button.
				int n = i+1;
				String p = Integer.toString(n);
				System.out.println("Page Number is:" + p);
				new Select(driver.findElement(By.id("idf"))).selectByVisibleText(p);
		
				selenium.waitForPageToLoad("30000");
				//Thread.sleep(2000);
			}

		}
		//Get Course CID here
		selenium.waitForPageToLoad("30000");
		c_url = driver.getCurrentUrl();
		System.out.println("Current URL is: "+ c_url);
		course_id = c_url.replace(baseUrl+"course/createcourse/view/id/", "");
		System.out.println("Course ID is: "+course_id);
	}
	//*********************************************************************************************************************************************


	// Click on Exact Course Question Bank.

	public void CourseQB() throws InterruptedException
	{
		System.out.println("This is CourseQB() function");

		//Click on 'Course Administartion'.
		driver.findElement(By.xpath("//a[contains(text(),'Course Administration')]")).click();
		Thread.sleep(1000);
		//Click on 'Courses'  
		driver.findElement(By.xpath("//a[contains(text(),'Manage Courses')]")).click();


		try {
			WebElement pagination = driver.findElement(By.id("idf"));
			List <WebElement> pages = pagination.findElements(By.tagName("option"));
			TotalPages = 	pages.size();
			System.out.println("Total No.of Pages in User Listing are: " + TotalPages);
			System.out.println("----------------------------------------------------------------");
		}
		catch (Exception e)
		{
			System.out.println("Total No.of Pages are: 1 only.");
			TotalPages=1;
		}


		for (int i = 1; i <= TotalPages; i++)
		{
			int z=1;
			WebElement UserList = driver.findElement(By.xpath(".//*[@id='grid']/table/tbody"));
			java.util.List<WebElement> tr1 = UserList.findElements(By.tagName("tr"));
			System.out.println("No.of Courses in Page " + i + " are: " +(tr1.size()-3));

			for (int j = 4; j <= tr1.size(); j++) 
			{
				String s1 = driver.findElement(By.xpath(".//*[@id='grid']/table/tbody/tr["+j+"]/td[1]/a")).getText();
				System.out.println((j-3)+ " Course Name--> " + s1);
				if(s1.contains(CourseName))
				{
					//Click on exact Question Bank
					driver.findElement(By.xpath(".//*[@id='grid']/table/tbody/tr["+ j + "]/td[4]/a[4]")).click();
					selenium.waitForPageToLoad("3000");
					z = 0;
					break;
				}

			}
			if ((i == TotalPages) || z == 0 )
			{
				break;
			}
			else
			{
				System.out.println("");
				//Click on NEXT PAGE Button.
				int n = i+1;
				String p = Integer.toString(n);
				System.out.println("Page Number is:" + p);
				new Select(driver.findElement(By.id("idf"))).selectByVisibleText(p);
				selenium.waitForPageToLoad("20000");
			}
		}
	}







	//*********************************************************************************************************************************************


	// Get getCourseID()

	public void getCourseID() throws InterruptedException
	{
		System.out.println("This is getCourseID() function");
		//Get Course CID here
		selenium.waitForPageToLoad("30000");
		String c_url = driver.getCurrentUrl();
		System.out.println("Current URL is: "+ c_url);
		course_id = c_url.replace(baseUrl+"course/createcourse/view/id/", "");
		System.out.println("Course ID is: "+course_id);

	}

}




