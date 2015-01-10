
package course;


import java.io.IOException;
import java.util.List;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import GlobalVariables.GlobalDecl;
import org.openqa.selenium.JavascriptExecutor;

public class C01_Create_newCourse 
{
	GlobalDecl GV = new GlobalDecl();
	public int TotalPages, b, c;
	//Enter Course Name to be created.
	public String CourseName = GV.CourseName;
	public String CourseFEE = GV.CourseFEE;



	@Test 
	public void createCourse() throws InterruptedException, RowsExceededException, WriteException, BiffException, IOException 
	{
		
		System.out.println("This Create Course Class File");
		GV.login();
		
		
		//Click on 'Course Administartion'.
		GV.driver.findElement(By.xpath("//a[contains(text(),'Course Administration')]")).click();

		// Click on Create a Course
		GV.driver.findElement(By.xpath("//a[contains(text(),'Create Course')]")).click();
		Thread.sleep(1000);

		//Enter Course Name.
		GV.driver.findElement(By.xpath(".//*[@id='title']")).sendKeys(CourseName);
		GV.driver.findElement(By.xpath(".//*[@id='shortname']")).sendKeys("SeleAutomation");

		//To get all values from DropDown Field.
		List<WebElement> options = GV.driver.findElements(By.id("category"));
		for (int i = 0; i < options.size(); i++) {
			System.out.println(options.get(i));
			if (options.get(i).getText().contains("Others"))
			{
				//System.out.println("if condition entered");
				new Select(GV.driver.findElement(By.id("category"))).selectByVisibleText("Others");
				break;
			}
		}

		GV.driver.switchTo().frame("description_ifr");
		GV.driver.findElement(By.id("tinymce")).sendKeys("Selenium automates browsers. That's it. What you do with that power is entirely up to you. Primarily it is for automating web applications for testing purposes, ");
		GV.driver.switchTo().defaultContent();

		GV.driver.findElement(By.xpath(".//*[@id='online-1']")).click();

		GV.driver.findElement(By.id("duration")).sendKeys("60");

		//Enter 'Reward' points.
		GV.driver.findElement(By.id("rewards")).sendKeys("15");
		//Click on SUBMIT button
		GV.driver.findElement(By.xpath(".//*[@id='submit']")).click();
		Thread.sleep(3000);


		GV.selenium.waitForPageToLoad("30000");

		//Enter Course FEE from Course Mgmt.
		//Click on 'Finance Management'.
		GV.driver.findElement(By.xpath("//a[contains(text(),'Finance Management')]")).click();
		Thread.sleep(2000);
		// Click on 'Courses Management'
		GV.driver.findElement(By.xpath("//a[contains(text(),'Courses Management')]")).click();
		Thread.sleep(1000);


		try {

			WebElement pagination = GV.driver.findElement(By.id("idf"));
			List <WebElement> pages = pagination.findElements(By.tagName("option"));
			System.out.println("Total No.of Pages in User Listing are: " + pages.size());
			TotalPages = pages.size();
			//System.out.println("----------------------------------------------------------------");
		}
		catch (Exception e)
		{
			System.out.println("Total No.of Pages are: 1 only.");
			TotalPages = 1;
		}


		//System.out.println(" a value is:" + a);
		for (int i = 1; i <= TotalPages; i++)
		{

			int z=1;
			WebElement UserList = GV.driver.findElement(By.xpath(".//*[@id='grid']/table/tbody"));
			java.util.List<WebElement> tr = UserList.findElements(By.tagName("tr"));

			System.out.println("");

			for (int j = 4; j <= tr.size(); j++)
			{
				String s1 = GV.driver.findElement(By.xpath(".//*[@id='grid']/table/tbody/tr["+j+"]/td[1]")).getText();
				//System.out.println((j-3)+ " Course Name--> " + s1);
				if(s1.contains(GV.CourseName))
				{
					GV.driver.findElement(By.xpath(".//*[@id='grid']/table/tbody/tr["+j+"]/td[4]/a")).click();
					GV.driver.findElement(By.id("fees")).clear();
					GV.driver.findElement(By.id("fees")).sendKeys(CourseFEE);
					GV.driver.findElement(By.id("submit")).click();
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
				new Select(GV.driver.findElement(By.id("idf"))).selectByVisibleText(p);
				Thread.sleep(2000);
			}
		}
		GV.selenium.stop();
		GV.driver.quit();

	}



}
