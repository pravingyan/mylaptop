
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

public class C04_Add_AssessmentsQUIZ
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

	public void addAssessments() throws InterruptedException, RowsExceededException, WriteException, BiffException, IOException 
	{
		System.out.println("This is 'Add Assessments' Class File");
		GV.login();
		
		GV.CourseListing();


		for (int Assessments = 1; Assessments <= TotaAssessmentsRequired; Assessments++)
		{
			System.out.println("Entered into Course loop Quiz_"+ Assessments );
			//To ADD QUIZ(Assessment) to Course.
			// To click on Add Activity for Course
			GV.driver.findElement(By.xpath(".//*[@id='item2']/div[1]/div[1]/a[2]")).click();

			//Click on Link Text 'Assessments' from Popup.
			GV.driver.findElement(By.xpath(".//*[@id='dialog']/ul/li[3]/a")).click();

			//Enter Assessment-TEST Name
			GV.driver.findElement(By.id("name")).sendKeys("Course"+"__QuizTest"+Assessments);


			//Enter Duration for the QUIZ.
			GV.driver.findElement(By.xpath(".//*[@id='timelimit']")).sendKeys("5");

			//Enter 'passmarks'
			GV.driver.findElement(By.id("passmarks")).sendKeys("10");

			//Enter Total No.of Questions for TEST.
			GV.driver.findElement(By.xpath(".//*[@id='noofquestions']")).sendKeys("3");
			
			GV.driver.switchTo().frame("passfeedback_ifr");
			GV.driver.findElement(By.id("tinymce")).sendKeys("You have got more than 10 marks , So u have passed.");
			GV.driver.switchTo().defaultContent();
			
			GV.driver.switchTo().frame("failfeedback_ifr");
			GV.driver.findElement(By.id("tinymce")).sendKeys("You have less than 10 marks , So u have Failed this TEST. Pls Retake the TEST");
			GV.driver.switchTo().defaultContent();


			//Click on Submit button.
			GV.driver.findElement(By.xpath(".//*[@id='submit']")).click();

			System.out.println("Course"+"_QuizTest"+Assessments + " Created");
		}




		//GV.CourseListing();
		GV.driver.get(GV.baseUrl+"course/createcourse/view/id/"+GV.course_id);





		// Add ACTIVITY to Chapters
		for (int ch = 1; ch <= TotaChaptersRequired; ch++)
		{

			for (int Assessments = 1; Assessments <= TotaAssessmentsRequired; Assessments++)
			{
				System.out.println("Entered into Chapters loop  Ch__"+ ch + "_Quiz_"+ Assessments );
				//To ADD QUIZ(Assessment) to Chapter.
				// To click on Exact Chapter
				//GV.driver.findElement(By.xpath(".//*[@id='accor3']/li[" + ch + "]/a")).click();

				//Click on 'Add Activity'
				GV.driver.findElement(By.xpath(".//*[@id='accor3']/li[" + ch + "]/div/div[1]/a[2]")).click();

				//Click on Link Text 'Assessments' from Popup.
				GV.driver.findElement(By.xpath(".//*[@id='dialog']/ul/li[3]/a")).click();

				//Enter Assessment-TEST Name
				GV.driver.findElement(By.id("name")).sendKeys("Ch"+ch+"__QuizTest"+Assessments);
				System.out.println("Ch"+ch+"__QuizTest"+Assessments + " Created");

				//Enter Duration for the QUIZ.
				GV.driver.findElement(By.xpath(".//*[@id='timelimit']")).sendKeys("5");

				//Enter 'passmarks'
				GV.driver.findElement(By.id("passmarks")).sendKeys("10");

				//Enter Total No.of Questions for TEST.
				GV.driver.findElement(By.xpath(".//*[@id='noofquestions']")).sendKeys("2");
				
				GV.driver.switchTo().frame("passfeedback_ifr");
				GV.driver.findElement(By.id("tinymce")).sendKeys("You have got more than 10 marks , So u have passed.");
				GV.driver.switchTo().defaultContent();
				
				GV.driver.switchTo().frame("failfeedback_ifr");
				GV.driver.findElement(By.id("tinymce")).sendKeys("You have less than 10 marks , So u have Failed this TEST. Pls Retake the TEST");
				GV.driver.switchTo().defaultContent();


				//Click on Submit button.
				GV.selenium.focus("submit");
				GV.driver.findElement(By.xpath(".//*[@id='submit']")).click();

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
				for (int Assessments = 1; Assessments <= TotaAssessmentsRequired; Assessments++)
				{
					System.out.println("Entered into Lessons loop  Ch__"+ ch +"_le_" +le + "_Quiz_"+ Assessments );
					
					//To ADD QUIZ(Assessment) to Lesson.
					// To click on Exact Chapter
					GV.driver.findElement(By.xpath(".//*[@id='accor3']/li[" + ch + "]/a")).click();
					Thread.sleep(1000);
					
					//Click on 'Add Activity' to a Lesson.
					GV.driver.findElement(By.xpath(".//*[@id='accor3']/li["+ch+"]/ul[1]/li["+le+"]/div/div[1]/a")).click();
					

					//Click on Link Text 'Assessments' from Popup.
					GV.driver.findElement(By.xpath(".//*[@id='dialog']/ul/li[3]/a")).click();

					//Enter Assessment-TEST Name
					GV.driver.findElement(By.id("name")).sendKeys("Ch"+ch+"__Le"+le+"__QuizTest"+Assessments);
					System.out.println("Ch"+ch+"__Le"+le+"__QuizTest"+Assessments + " Created");

					//Enter Duration for the QUIZ.
					GV.driver.findElement(By.xpath(".//*[@id='timelimit']")).sendKeys("5");

					//Enter 'passmarks'
					GV.driver.findElement(By.id("passmarks")).sendKeys("10");

					//Enter Total No.of Questions for TEST.
					GV.driver.findElement(By.xpath(".//*[@id='noofquestions']")).sendKeys("4");
					
								
					GV.driver.switchTo().frame("passfeedback_ifr");
					GV.driver.findElement(By.id("tinymce")).sendKeys("You have got more than 10 marks , So u have passed.");
					GV.driver.switchTo().defaultContent();
					
					GV.driver.switchTo().frame("failfeedback_ifr");
					GV.driver.findElement(By.id("tinymce")).sendKeys("You have less than 10 marks , So u have Failed this TEST. Pls Retake the TEST");
					GV.driver.switchTo().defaultContent();

					//Click on Submit button.
					GV.driver.findElement(By.xpath(".//*[@id='submit']")).click();
				}
			}
		}

		//GV.CourseListing();
		GV.driver.get(GV.baseUrl+"course/createcourse/view/id/"+GV.course_id);
		
		GV.selenium.stop();
		GV.driver.quit();

	}

	
}
