
package course;


import java.io.IOException;
import java.util.List;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import GlobalVariables.GlobalDecl;

public class Add_Qsts_to_AssessmentsQUIZ
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


		//a[contains(@href, '/quiz/addquiz/view/id/361/cid/162')]
		
		WebElement pagination = GV.driver.findElement(By.xpath(".//*[@id='accor3']/li[1]/ul[1]/li/ul"));
			List <WebElement> quizs = pagination.findElements(By.partialLinkText("/quiz/addquiz/view/id/"));
		//	System.out.println("Total No.of Pages in User Listing are: " + pages.size());
			System.out.println("No.of Quizs foound is: "+quizs.size());

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
					
					
					
					
					
					
					
				}
			}
		}

		//GV.CourseListing();
		GV.driver.get(GV.baseUrl+"course/createcourse/view/id/"+GV.course_id);
		
		GV.selenium.stop();
		GV.driver.quit();

	}

	
}
