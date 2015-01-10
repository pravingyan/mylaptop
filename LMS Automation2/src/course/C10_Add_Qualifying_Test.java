
package course;


import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import GlobalVariables.GlobalDecl;

public class C10_Add_Qualifying_Test 
{

	GlobalDecl GV = new GlobalDecl();
	public int TotalPages, a = 0, Ch = 0, Le = 0,  AllCourses = 0;

	//Enter Course Name to be created.
	public String CourseName = GV.CourseName;
	//Enter the Total no.of Chapters to be created.
	int TotaChaptersRequired = GV.TotaChaptersRequired;
	int TotaLessonsRequired = GV.TotaLessonsRequired;
	int TotaAssignmentsRequired = GV.TotaAssignmentsRequired;
	int TotaAssessmentsRequired = GV.TotaAssessmentsRequired;
	int TotaSurveysRequired = GV.TotaSurveysRequired;


	


	@Test

	public void addAssignments() throws InterruptedException, RowsExceededException, WriteException, BiffException, IOException 
	{
		
		System.out.println("This 'Add QT' Class File");
		GV.login();
		GV.CourseListing();
		

		try 
		{
			GV.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			//*[@id='accor3']/li[1]/a -----> Chapter1 xpath 
			boolean b  = GV.driver.findElement(By.xpath(".//*[@id='accor3']/li[1]/a")).isEnabled();

			if (b) 
			{
				//Click on 'Add QT' link.
				GV.driver.findElement(By.xpath(".//*[@id='item2']/div[1]/div[1]/a[3]/span")).click();
				GV.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				
				WebElement totalChapters = GV.driver.findElement(By.id("after_chapter_id"));
				List <WebElement> chps = totalChapters.findElements(By.tagName("option"));
				System.out.println("Total No.of Chapters in this Course are: " + (chps.size()-1));

				String rewards = String.valueOf(14/chps.size());
				for (int i = 1; i <= (chps.size()-1); i++) 
				{
					GV.driver.findElement(By.id("name")).sendKeys("QT"+ i);
					GV.driver.findElement(By.id("rewards")).sendKeys(rewards);
					//GV.driver.findElement(By.id("rewards")).sendKeys("0");
					GV.driver.findElement(By.id("marks")).sendKeys("100");
					GV.driver.findElement(By.id("time_limit")).sendKeys("40");
					new Select(GV.driver.findElement(By.id("after_chapter_id"))).selectByIndex(i);
					GV.driver.findElement(By.id("submit")).click();
					GV.selenium.waitForPageToLoad("30000");

					if (i == (chps.size()-1)) 
					{
						break;
					} 
					else 
					{
						//Click on 'Add QT' link.
						GV.driver.findElement(By.xpath(".//*[@id='item2']/div[1]/div[1]/a[3]/span")).click();
						Thread.sleep(1000);
						// Close the Home Tab.
						//GV.driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"w");// close the Home Tab
					}
				}

			}

		} 
		catch (Exception e) 
		{
			System.out.println("Catch block executed: There are no chapters in this Lesson to create a QT");
		}
		GV.selenium.stop();
		GV.driver.quit();
	}
	
}
