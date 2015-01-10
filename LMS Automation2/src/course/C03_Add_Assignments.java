
package course;


import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.By;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import GlobalVariables.GlobalDecl;

public class C03_Add_Assignments 
{

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

	public void addAssignments() throws InterruptedException, RowsExceededException, WriteException, BiffException, IOException 
	{
			System.out.println("This is 'Add Assignment' Class File");
			GV.login();

		 GV.CourseListing();
		  //Add Activities to Course
		 
		for (int Assignments = 1; Assignments <= TotaAssignmentsRequired; Assignments++) 
		{			
			System.out.println("Entered into Course loop  Assignment__"+ Assignments);
			//Add Assignments for Course.			
			// To click on Add Activity for Course  
			GV.driver.findElement(By.xpath(".//*[@id='item2']/div[1]/div[1]/a[2]")).click();

			//Click on Link Text 'Assignment' from Popup.
			GV.driver.findElement(By.xpath(".//*[@id='dialog']/ul/li[2]/a")).click();

			//Enter Assignment Name
			GV.driver.findElement(By.xpath(".//*[@id='name']")).sendKeys("Course"+"_Assignment_"+Assignments);	

			//Enter Description for Assignment
			GV.driver.switchTo().frame("description_ifr");
			GV.driver.findElement(By.id("tinymce")).sendKeys("Course"+"_"+Assignments + "This is the Assignment Question");
			GV.driver.switchTo().defaultContent();

			//Select ACTIVE radio button.
			GV.driver.findElement(By.id("active-1")).click();

			//Click on SUBMIT button.
			GV.driver.findElement(By.id("submit")).click();

			System.out.println(GV.CourseName+"_Assignment_"+Assignments + " created");


		}


		//GV.CourseListing();
		GV.driver.get(GV.baseUrl+"course/createcourse/view/id/"+GV.course_id);





		// Add ACTIVITY to Chapters
		for (int ch = 1; ch <= TotaChaptersRequired; ch++)
		{
			for (int Assignments = 1; Assignments <= TotaAssignmentsRequired; Assignments++) 
			{
				System.out.println("Entered into Chapters loop  Ch__"+ ch + "_Assign_" + Assignments);
				//To ADD Assignments to Chapter.
				// To click on Exact Chapter
				GV.driver.findElement(By.xpath(".//*[@id='accor3']/li[" + ch + "]/a")).click();

				//Click on 'Add Activity'       
				GV.driver.findElement(By.xpath(".//*[@id='accor3']/li[" + ch + "]/div/div[1]/a[2]")).click();

				//Click on Link Text 'Assignment' from Popup.
				GV.driver.findElement(By.xpath(".//*[@id='dialog']/ul/li[2]/a")).click();

				//Enter Assignment Name
				GV.driver.findElement(By.xpath(".//*[@id='name']")).sendKeys("Ch"+ch+"_Assignment"+Assignments);	

				//Enter Description for Assignment
				GV.driver.switchTo().frame("description_ifr");
				GV.driver.findElement(By.id("tinymce")).sendKeys("Ch"+ch+"_QuizTest"+Assignments + "This is the Assignment Question");
				GV.driver.switchTo().defaultContent();

				//Select ACTIVE radio button.
				GV.driver.findElement(By.id("active-1")).click();

				//Click on SUBMIT button.
				GV.driver.findElement(By.id("submit")).click();

			}




			//GV.CourseListing();
			GV.driver.get(GV.baseUrl+"course/createcourse/view/id/"+GV.course_id);

		}
		//*********************************************************************************************************




		// Add ACTIVITY to Lessons
		for (int ch = 1; ch <= TotaChaptersRequired; ch++)
		{
			System.out.println("");
			for (int le = 1; le <= TotaLessonsRequired ; le++)
			{

				for (int Assignments = 1; Assignments <= TotaAssignmentsRequired; Assignments++) 
				{
					//To ADD Assignments to Lessons.
					System.out.println("Entered into Lessons loop  Ch__"+ ch +"_le_" +le + "_Assign_"+ Assignments );
					// To click on Exact Chapter
					GV.driver.findElement(By.xpath(".//*[@id='accor3']/li[" + ch + "]/a")).click();
					Thread.sleep(1000);

					//Click on 'Add Activity' to a Lesson.
					GV.driver.findElement(By.xpath(".//*[@id='accor3']/li["+ch+"]/ul[1]/li["+le+"]/div/div[1]/a")).click();

					//Click on Link Text 'Assignment' from Popup.
					GV.driver.findElement(By.xpath(".//*[@id='dialog']/ul/li[2]/a")).click();

					//Enter Assignment Name
					GV.driver.findElement(By.xpath(".//*[@id='name']")).sendKeys("Ch"+ch+"_Le"+le+"_Assignment"+Assignments);	

					//Enter Description for Assignment
					GV.driver.switchTo().frame("description_ifr");
					GV.driver.findElement(By.id("tinymce")).sendKeys("Ch"+ch+"_Le"+le+"_QuizTest"+Assignments + "This is the Assignment Question");
					GV.driver.switchTo().defaultContent();

					//Select ACTIVE radio button.
					GV.driver.findElement(By.id("active-1")).click();

					//Click on SUBMIT button.
					GV.driver.findElement(By.id("submit")).click();

				}
			}
		}
		
		//GV.CourseListing();
		GV.driver.get(GV.baseUrl+"course/createcourse/view/id/"+GV.course_id);


		GV.selenium.stop();
		GV.driver.quit();
		
	}

	
}
