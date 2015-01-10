package course;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import GlobalVariables.GlobalDecl;

public class C02_AddChap_Lessons {

	GlobalDecl GV = new GlobalDecl();
	public int a = 0, Ch = 0, Le = 0,  AllCourses = 0;

	//Enter Course Name to be created.
	public String CourseName = GV.CourseName;
	//Enter the Total no.of Chapters to be created.
	int TotaChaptersRequired = GV.TotaChaptersRequired;
	int TotaLessonsRequired = GV.TotaLessonsRequired;
	int TotaAssignmentsRequired = GV.TotaAssignmentsRequired;
	int TotaAssessmentsRequired = GV.TotaAssessmentsRequired;
	int TotaSurveysRequired = GV.TotaSurveysRequired;





	@Test 
	public void addChapter_Lessons_Activities() throws RowsExceededException, WriteException, BiffException, InterruptedException, IOException
	{
		System.out.println("This Create Chapters and Lessons Class File");
		GV.login();
		

		GV.CourseListing();

		// Add new Chapter(s)
		for (int i = 1; i <= TotaChaptersRequired; i++)
		{
			//Click on ADD Chapters
			//GV.driver.findElement(By.linkText("Add Chapter")).click();

			GV.driver.findElement(By.xpath(".//*[@id='item2']/div[1]/div[1]/a[1]")).click();
			
			//Add Chapter to Course.
			GV.driver.findElement(By.id("title")).sendKeys("Chapter_"+i);
			GV.driver.findElement(By.id("shortname")).sendKeys("Chp"+i);
			GV.driver.findElement(By.id("submit")).click();
			System.out.println("Chapter_" + i + " Created.");
		}
		System.out.println("Maximum Chapters created are: "+ Ch);
		System.out.println("");


		//To Add LESSONS to Chapters
		for (int ch = 1; ch <= TotaChaptersRequired; ch++)
		{
			for (int le = 1; le <= TotaLessonsRequired; le++)
			{
				// To click on Exact Chapter
				//GV.driver.findElement(By.xpath(".//*[@id='accor3']/li[" + ch + "]/a")).click();

				//Click on Add Lesson
				GV.driver.findElement(By.xpath(".//*[@id='accor3']/li["+ch+"]/div/div[1]/a[1]")).click();
				//Add Lesson to Course.
				GV.driver.findElement(By.id("title")).sendKeys("Ch"+ch+ "_Lesson"+le);
				GV.driver.findElement(By.id("shortname")).sendKeys("C"+ch+ "_L_"+le);
				GV.driver.findElement(By.xpath(".//*[@id='submit']")).click();
				System.out.println("C"+ch+ "_Lesson"+le  + " Created.");
				//MaxLess = le;
			}
			System.out.println("");
		}

		
		GV.selenium.stop();
		GV.driver.quit();

	}


}
