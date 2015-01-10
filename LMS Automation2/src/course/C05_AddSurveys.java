
package course;


import java.io.FileInputStream;
import java.io.IOException;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import GlobalVariables.GlobalDecl;

public class C05_AddSurveys 
{

	GlobalDecl GV = new GlobalDecl();
	public int a = 0, Ch = 0, Le = 0,  AllCourses = 0;;
	//Enter Course Name to be created.
	public String CourseName = GV.CourseName;
	//Enter the Total no.of Chapters to be created.
	int TotaChaptersRequired = GV.TotaChaptersRequired;
	int TotaLessonsRequired = GV.TotaLessonsRequired;
	int TotaAssignmentsRequired = GV.TotaAssignmentsRequired;
	int TotaAssessmentsRequired = GV.TotaAssessmentsRequired;
	int TotaSurveysRequired = GV.TotaSurveysRequired;

	FileInputStream fi_surv;
	Workbook w_surv;
	Sheet s_fi_surv;
	String[][] surv;


	@BeforeTest
	public void beforeTest() throws IOException, BiffException, RowsExceededException, WriteException 
	{
		//Input Excel File Data
		fi_surv=new FileInputStream(GV.Input_ExcelFile+GV.excelName );
		w_surv = Workbook.getWorkbook(fi_surv);
		s_fi_surv=w_surv.getSheet("Surveys");
		System.out.println("Cloumns: "+s_fi_surv.getColumns());
		System.out.println("Rows: "+s_fi_surv.getRows());
		surv = new String [s_fi_surv.getColumns()][s_fi_surv.getRows()];
		for (int i = 1; i < s_fi_surv.getRows(); i++) {
			for (int j = 0; j < s_fi_surv.getColumns(); j++) {
				surv[j][i] = s_fi_surv.getCell(j,i).getContents();
			}
		}
	}




	@Test 
	public void addChapter_Lessons_Activities() throws InterruptedException, RowsExceededException, WriteException, BiffException, IOException 
	{
		System.out.println("This is 'Add Surveys' Class File");
		GV.login();
		//Get Course View Page.
		GV.CourseListing();

		/*
		//To ADD Survey to Course.
		for (int Survey = 1; Survey <= TotaSurveysRequired; Survey++)
		{
			// To click on Add Activity for Course
			GV.driver.findElement(By.xpath(".//*[@id='item2']/div[1]/div[1]/a[2]")).click();

			//Click on Link Text 'Surveys' from Popup.
			GV.driver.findElement(By.xpath(".//*[@id='dialog']/ul/li[4]/a")).click();

			//Enter Survey Name
			GV.driver.findElement(By.id("name")).sendKeys("Course"+"__Survey"+Survey);


			//Enter Survey description.
			GV.driver.findElement(By.id("description")).sendKeys(GV.CourseName+"__Survey"+Survey + " This is Survey Description.");


			//Click on Submit button.
			GV.driver.findElement(By.xpath(".//*[@id='submit']")).click();
			System.out.println("Course"+"__Survey"+Survey+ " created");



			// Add Questions to Course Survey. <-----*************************************************************************************
			// To click on Exact Survey name in Course

			String s = "Course"+"__Survey"+Survey;
			GV.driver.findElement(By.linkText(s)).click();


			for (int i = 1; i < (s_fi_surv.getRows()); i++)
			{

				//Click on "Add a Question" link for survey.
				GV.driver.findElement(By.linkText("Add a Question")).click();


				//click on "Multiple Choice Question " from Popup Window.
				GV.driver.findElement(By.xpath(".//*[@id='dialog']/ul/li/a")).click();


				//Enter Question Name.
				GV.driver.findElement(By.id("qname")).sendKeys("CourseSurveyQst_"+i);

				//Enter Question Text.
				GV.driver.findElement(By.id("qtext")).sendKeys(surv[0][i]);
				GV.driver.findElement(By.id("qtext")).sendKeys(Keys.TAB);

				for (int j = 1; j <= 4; j++) 
				{
					//Click on "Add More" button.
					GV.driver.findElement(By.xpath(".//*[@id='addmore']")).click();
				}

				//Enter Choice 1 out of choices 5
				GV.driver.findElement(By.id("choice-choice_0")).sendKeys(surv[1][i]);
				GV.driver.findElement(By.id("choice-choice_0")).sendKeys(Keys.TAB);

				//Enter Choice 2
				GV.driver.findElement(By.id("choice-choice_1")).sendKeys(surv[2][i]);
				GV.driver.findElement(By.id("choice-choice_1")).sendKeys(Keys.TAB);
				GV.driver.findElement(By.id("choice-choice_1")).sendKeys(Keys.TAB);

				//Enter Choice 3
				GV.driver.findElement(By.id("choice-choice_2")).sendKeys(surv[3][i]);
				GV.driver.findElement(By.id("choice-choice_2")).sendKeys(Keys.TAB);
				GV.driver.findElement(By.id("choice-choice_2")).sendKeys(Keys.TAB);

				//Enter Choice 4
				GV.driver.findElement(By.id("choice-choice_3")).sendKeys(surv[4][i]);
				GV.driver.findElement(By.id("choice-choice_3")).sendKeys(Keys.TAB);
				GV.driver.findElement(By.id("choice-choice_3")).sendKeys(Keys.TAB);
				Thread.sleep(500);
				//Enter Choice 5
				GV.driver.findElement(By.id("choice-choice_4")).sendKeys(surv[5][i]);
				Thread.sleep(500);
				//Click on Submit button.
				GV.driver.findElement(By.xpath(".//*[@id='submit']")).click();

			}
			System.out.println("Successfully added Survey Qsts");
			System.out.println("");
		}



		//*********************************************************************************************************

		//Get Course View Page.
		GV.driver.get(GV.baseUrl+"course/createcourse/view/id/"+GV.course_id);


		// Add ACTIVITY to Chapters
		for (int ch = 1; ch <= TotaChaptersRequired; ch++)
		{
			//To ADD Survey to Chapter.
			for (int Survey = 1; Survey <= TotaSurveysRequired; Survey++)
			{
				// To click on Exact Chapter
				GV.driver.findElement(By.xpath(".//*[@id='accor3']/li[" + ch + "]/a")).click();
				Thread.sleep(1000);

				//Click on 'Add Activity'
				GV.driver.findElement(By.xpath(".//*[@id='accor3']/li[" + ch + "]/div/div[1]/a[2]")).click();

				//Click on Link Text 'Surveys' from Popup.
				GV.driver.findElement(By.xpath(".//*[@id='dialog']/ul/li[4]/a")).click();

				//Enter Survey Name
				GV.driver.findElement(By.id("name")).sendKeys("Ch"+ch+"__Survey"+Survey);
				System.out.println("Ch"+ch+"__Survey"+Survey + " Created");

				//Enter Survey description.
				GV.driver.findElement(By.id("description")).sendKeys("Ch"+ch+"__Survey"+Survey + " This is Survey Description.");


				//Click on Submit button.
				GV.driver.findElement(By.xpath(".//*[@id='submit']")).click();



				Thread.sleep(3000);


				// Add Questions to Survey. <-----*************************************************************************************
				// To click on Exact Chapter
				GV.driver.findElement(By.xpath(".//*[@id='accor3']/li[" + ch + "]/a")).click();
				Thread.sleep(1000);

				//Click on exact Survey name to add questions.
				String s = "Ch"+ch+"__Survey"+Survey;

				System.out.println("Survey Name is: "+s);
				GV.driver.findElement(By.linkText(s)).click();


				for (int i = 1; i < (s_fi_surv.getRows()); i++)
				{

					//Click on "Add a Question" link for survey.
					GV.driver.findElement(By.linkText("Add a Question")).click();



					//click on "Multiple Choice Question " from Popup Window.
					GV.driver.findElement(By.xpath(".//*[@id='dialog']/ul/li/a")).click();

					//Enter Question Name.
					GV.driver.findElement(By.id("qname")).sendKeys("Ch"+ch+"SurveyQst_"+i);

					//Enter Question Text.
					GV.driver.findElement(By.id("qtext")).sendKeys(surv[0][i]);
					GV.driver.findElement(By.id("qtext")).sendKeys(Keys.TAB);

					for (int j = 1; j <= 4; j++) 
					{
						//Click on "Add More" button.
						GV.driver.findElement(By.xpath(".//*[@id='addmore']")).click();
					}

					//Enter Choice 1 out of choices 5
					GV.driver.findElement(By.id("choice-choice_0")).sendKeys(surv[1][i]);
					GV.driver.findElement(By.id("choice-choice_0")).sendKeys(Keys.TAB);

					//Enter Choice 2
					GV.driver.findElement(By.id("choice-choice_1")).sendKeys(surv[2][i]);
					GV.driver.findElement(By.id("choice-choice_1")).sendKeys(Keys.TAB);
					GV.driver.findElement(By.id("choice-choice_1")).sendKeys(Keys.TAB);

					//Enter Choice 3
					GV.driver.findElement(By.id("choice-choice_2")).sendKeys(surv[3][i]);
					GV.driver.findElement(By.id("choice-choice_2")).sendKeys(Keys.TAB);
					GV.driver.findElement(By.id("choice-choice_2")).sendKeys(Keys.TAB);

					//Enter Choice 4
					GV.driver.findElement(By.id("choice-choice_3")).sendKeys(surv[4][i]);
					GV.driver.findElement(By.id("choice-choice_3")).sendKeys(Keys.TAB);
					GV.driver.findElement(By.id("choice-choice_3")).sendKeys(Keys.TAB);
					Thread.sleep(500);
					//Enter Choice 5
					GV.driver.findElement(By.id("choice-choice_4")).sendKeys(surv[5][i]);
					Thread.sleep(500);
					//Click on Submit button.
					GV.driver.findElement(By.xpath(".//*[@id='submit']")).click();

				}
				System.out.println("Successfully added Survey Qsts");
			}
			//Get Course View Page.
			GV.driver.get(GV.baseUrl+"course/createcourse/view/id/"+GV.course_id);
			System.out.println("");
		}

		//Get Course View Page.
		GV.driver.get(GV.baseUrl+"course/createcourse/view/id/"+GV.course_id);


		//*********************************************************************************************************


		 */



		//To ADD Surveys to Lesson.
		for (int ch = 1; ch <= TotaChaptersRequired; ch ++) 
		{
			for (int le = 1; le <= TotaLessonsRequired ; le++) 
			{

				for (int Survey = 1; Survey <= TotaSurveysRequired; Survey++)
				{
					System.out.println("Entered into Lessons loop.....");


					/*
				 // To click on Exact Chapter

					GV.driver.findElement(By.xpath(".//*[@id='accor3']/li[" + ch + "]/a")).click();
					Thread.sleep(1000);

					//Click on 'Add Activity' link on exact Lesson #
					GV.driver.findElement(By.xpath("//ul[@id='accor3']/li[" + ch + "]/ul[1]/li[" + le + "]/div/div[1]/a")).click();

					//Click on Link Text 'Surveys' from Popup.
					GV.driver.findElement(By.xpath(".//*[@id='dialog']/ul/li[4]/a/span")).click();

					//Enter Survey Name
					GV.driver.findElement(By.id("name")).sendKeys("Ch"+ch+"__Le"+le+"__Survey"+Survey);
					System.out.println("Ch"+ch+"__Le"+le+"__Survey"+Survey+ " Created");

					//Enter Survey description.
					GV.driver.findElement(By.id("description")).sendKeys("Ch"+ch+"__Le"+le+"__Survey"+Survey + " This is Survey Description.");


					//Click on Submit button.
					GV.driver.findElement(By.xpath(".//*[@id='submit']")).click();

					 */
					Thread.sleep(1000);


					// Add Questions to Survey in a Lesson. <-----*************************************************************************************

					// To click on Exact Chapter
					GV.driver.findElement(By.xpath(".//*[@id='accor3']/li[" + ch + "]/a")).click();

					//Click on exact Lesson #.     
					String Lesson = GV.driver.findElement(By.xpath(".//*[@id='accor3']/li[" + ch + "]/ul[1]/li[" + le + "]/a")).getText();
					System.out.println("Lesson Name is :" + Lesson);
					GV.driver.findElement(By.partialLinkText(Lesson)).click();
					//GV.selenium.click("Ch1_Lesson1");

					//Click on exact Survey name to add questions.
					String s = "Ch"+ch+"__Le"+le+"__Survey"+Survey;
					GV.driver.findElement(By.linkText(s)).click();

					Thread.sleep(2000);

					//GV.driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"w");// close the Home Tab

					for (int i = 1; i < ((s_fi_surv.getRows())); i++)
					{

						//Click on "Add a Question" link for survey.
						GV.driver.findElement(By.linkText("Add a Question")).click();


						//click on "Multiple Choice Question " from Popup Window.  
						GV.driver.findElement(By.xpath(".//*[@id='dialog']/ul/li/a/span")).click();


						//Enter Question Name.
						GV.driver.findElement(By.id("qname")).sendKeys("Ch"+ch+"__Le"+le+"SurveyQst_"+i);

						//Enter Question Text.
						GV.driver.findElement(By.id("qtext")).sendKeys(surv[0][i]);
						GV.driver.findElement(By.id("qtext")).sendKeys(Keys.TAB);
						for (int j = 1; j <= 4; j++) 
						{
							//Click on "Add More" button.
							GV.driver.findElement(By.xpath(".//*[@id='addmore']")).click();
						}

						//Enter Choice 1 out of choices 5
						GV.driver.findElement(By.id("choice-choice_0")).sendKeys(surv[1][i]);
						GV.driver.findElement(By.id("choice-choice_0")).sendKeys(Keys.TAB);

						//Enter Choice 2
						GV.driver.findElement(By.id("choice-choice_1")).sendKeys(surv[2][i]);
						GV.driver.findElement(By.id("choice-choice_1")).sendKeys(Keys.TAB);
						GV.driver.findElement(By.id("choice-choice_1")).sendKeys(Keys.TAB);

						//Enter Choice 3
						GV.driver.findElement(By.id("choice-choice_2")).sendKeys(surv[3][i]);
						GV.driver.findElement(By.id("choice-choice_2")).sendKeys(Keys.TAB);
						GV.driver.findElement(By.id("choice-choice_2")).sendKeys(Keys.TAB);

						//Enter Choice 4
						GV.driver.findElement(By.id("choice-choice_3")).sendKeys(surv[4][i]);
						GV.driver.findElement(By.id("choice-choice_3")).sendKeys(Keys.TAB);
						GV.driver.findElement(By.id("choice-choice_3")).sendKeys(Keys.TAB);
						Thread.sleep(500);
						//Enter Choice 5
						GV.driver.findElement(By.id("choice-choice_4")).sendKeys(surv[5][i]);
						Thread.sleep(500);
						//Click on Submit button.
						GV.driver.findElement(By.xpath(".//*[@id='submit']")).click();

					}

					System.out.println("Successfully added Survey Qsts to Lesson");
				}

				GV.CourseListing();

				System.out.println("");
			}

		}
		GV.selenium.stop();
		GV.driver.quit();
	}

}
