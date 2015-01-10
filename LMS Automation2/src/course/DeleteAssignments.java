// Delete an existing Assignment.
package course;


import java.io.IOException;
import java.util.List;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import GlobalVariables.GlobalDecl;

public class DeleteAssignments 
{

	GlobalDecl GV = new GlobalDecl();
	public int a = 0, Ch = 0, Le = 0,  AllCourses = 0;
	int TotalDels;

	//Enter Course Name to be created.
	public String CourseName = GV.CourseName;
	//Enter the Total no.of Chapters to be created.
	int TotaChaptersRequired = GV.TotaChaptersRequired;
	int TotaLessonsRequired = GV.TotaLessonsRequired;
	int TotaAssignmentsRequired = GV.TotaAssignmentsRequired;
	int TotaAssessmentsRequired = GV.TotaAssessmentsRequired;
	int TotaSurveysRequired = GV.TotaSurveysRequired;



	@BeforeTest
	public void beforeTest() throws IOException, BiffException, RowsExceededException, WriteException, InterruptedException 
	{
		System.out.println("This is 'Add Assignment' Class File");
		GV.login();

	}




	@Test
	public void addAssignments() throws InterruptedException, RowsExceededException, WriteException, BiffException, IOException 
	{

		GV.CourseListing();
		//Add Activities to Course


		try 
		{
			// To click on Exact Chapter
			GV.driver.findElement(By.xpath("//a[contains(text(),'Chapter_1')]")).click();
			Thread.sleep(1000);
			//Click on exact lesson
			GV.driver.findElement(By.xpath("//a[contains(text(),'Ch1_Lesson1')]")).click();
			
			WebElement lessonAssigndelete = GV.driver.findElement(By.xpath(".//*[@id='accor3']/li[1]/ul[1]/li/ul"));
			List <WebElement> del = lessonAssigndelete.findElements(By.xpath(".//*/div[1]/a[2]/span"));
			TotalDels = del.size();
			System.out.println("TotalDels = " +TotalDels);
		}

		catch (Exception e) 
		{

			System.out.println("catch block executed");
		}

		if (TotalDels > 1) 
		{


			for (int i = 3; i < TotalDels ; i++) 
			{
				GV.driver.get(GV.c_url);
				System.out.println("");
				System.out.println("--------------------------------------------------------");
				GV.selenium.waitForPageToLoad("30000");
				// To click on Exact Chapter
				GV.driver.findElement(By.xpath("//a[contains(text(),'Chapter_1')]")).click();
		
				//Click on exact lesson
				GV.driver.findElement(By.xpath("//a[contains(text(),'Ch1_Lesson1')]")).click();
				Thread.sleep(1000);
				String d = GV.driver.findElement(By.xpath(".//*[@id='accor3']/li[1]/ul[1]/li/ul/li["+i+"]/a")).getAttribute("href");
				System.out.println("Url is--> " +d);

				if (d.contains("/assignments/")) 
				{
					GV.driver.findElement(By.xpath(".//*[@id='accor3']/li[1]/ul[1]/li/ul/li["+ i +"]/div/div[1]/a[2]/span")).click();
					System.out.println("Delete click on "+ ".//*[@id='accor3']/li[1]/ul[1]/li/ul/li["+ i + "]/div/div[1]/a[2]");
					Thread.sleep(3000);
					try 
					{
						Alert alert = GV.driver.switchTo().alert();
						alert.accept();
						System.out.println("deleted Url is--> " + d);
					} 
					catch (Exception e) 
					{
						System.out.println("Catch Bloc Executed: --> Not deleted - " + d);
					}



				}

				else
				{
					System.out.println(i+" is not an Assignment");

				}

			}
		}

		//GV.CourseListing();
		//	GV.driver.get(GV.baseUrl+"course/createcourse/view/id/"+GV.course_id);



	}


}
