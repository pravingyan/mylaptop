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
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import GlobalVariables.GlobalDecl;

public class C06_AddMCQquestions_QB 
{

	GlobalDecl GV = new GlobalDecl();
	public int TotalPages, a = 0, Ch = 0, Le = 0;

	//Enter Course Name to be created.
	public String CourseName = GV.CourseName;
	//Enter the Total no.of Chapters to be created.
	int TotaChaptersRequired = GV.TotaChaptersRequired;
	int TotaLessonsRequired = GV.TotaLessonsRequired;
	int TotaAssignmentsRequired = GV.TotaAssignmentsRequired;
	int TotaAssessmentsRequired = GV.TotaAssessmentsRequired;

	//Short Questions.
	Sheet MCQqst;
	String mcq_quiz[][];




	@Test
	public void addqsts2CourseChapLessons() throws InterruptedException, RowsExceededException, WriteException, BiffException, IOException 
	{
		GV.Fileinput=new FileInputStream(GV.Input_ExcelFile + GV.excelName);
		GV.wbook=Workbook.getWorkbook(GV.Fileinput);


		//For Multiple Choice Questions.
		MCQqst=GV.wbook.getSheet("MCQs");
		System.out.println("This is MCQ Questions Excell file");
		System.out.println("Cloumns: "+MCQqst.getColumns());
		System.out.println("Rows: "+MCQqst.getRows());
		mcq_quiz = new String [MCQqst.getColumns()][MCQqst.getRows()];
		for (int i = 1; i < MCQqst.getRows(); i++) {
			for (int j = 0; j < MCQqst.getColumns(); j++) 
			{
				mcq_quiz[j][i] = MCQqst.getCell(j,i).getContents();
			}
		}
		System.out.println("");


		
		
		
		
		System.out.println("This 'Add MCQ questions' Class File");
		GV.login();
		System.out.println("File Name: "+GV.Input_ExcelFile + GV.excelName);
		

		// Click on Exact Course Question Bank.
		GV.CourseQB();


		// ADDING A MCQ QUESTIONS to COURSE.
		for (int i = 1; i < MCQqst.getColumns(); i++)
		{
			//Click on 'Add Question' for Course. 
			GV.driver.findElement(By.xpath("//div[@id='item2']/h2/div/div/a")).click();

			//Click on 'MCQ' from popup
			GV.driver.findElement(By.xpath(".//*[@id='dialog']/ul/li[1]/a")).click();

			//Write Question Name
			GV.driver.findElement(By.id("qname")).sendKeys("CourseMCQ_Qst_" +(i) + " " + mcq_quiz[i][1]);

			//Write MCQ Quetsion Text ?
			GV.driver.switchTo().frame("qtext_ifr");
			GV.driver.findElement(By.id("tinymce")).sendKeys("CourseMCQ_Qst_" + (i) + ".) " + mcq_quiz[i][2]);
			GV.driver.switchTo().defaultContent();
			if (i==1)
			{
				GV.driver.findElement(By.id("qname")).clear();
				GV.driver.findElement(By.id("qname")).sendKeys("CourseMCQ_Qst_" +(i) + " " + mcq_quiz[i][1] + " Imaged based");

				//Write MCQ Quetsion Text ?
				GV.driver.switchTo().frame("qtext_ifr");
				GV.driver.findElement(By.id("tinymce")).clear();
				GV.driver.findElement(By.id("tinymce")).sendKeys("CourseMCQ_Qst_" + (i) + ".) " + mcq_quiz[i][2] + " Image based");
				GV.driver.switchTo().defaultContent();

				GV.driver.findElement(By.id("imagebased")).click();

				WebElement file = GV.driver.findElement(By.id("image"));
				file.sendKeys(GV.image1);
				Thread.sleep(1000);
			}	

			//Enter Marks.
			GV.driver.findElement(By.id("marks")).sendKeys(mcq_quiz[i][5]);



			//Enter Choice_1
			GV.driver.findElement(By.xpath(".//*[@id='choice-choice_0']")).sendKeys(mcq_quiz[i][8]);
			GV.driver.findElement(By.xpath(".//*[@id='choice-choice_0']")).sendKeys(Keys.TAB);
			GV.driver.findElement(By.id("correctans-0")).click();
			GV.driver.findElement(By.id("correctans-0")).sendKeys(Keys.TAB);

			for (int k = 1; k < 5; k++) 
			{
				//Cick on'Add More'
				GV.driver.findElement(By.xpath(".//*[@id='addmore']")).click();
			}


			//Enter Choice_2
			GV.driver.findElement(By.xpath(".//*[@id='choice-choice_1']")).sendKeys(mcq_quiz[i][10]);
			//Choose Radio button for Choice 2 as correct Answer.
			GV.driver.findElement(By.xpath(".//*[@id='correctans-1']")).click();

			//Enter Choice_3
			GV.driver.findElement(By.xpath(".//*[@id='choice-choice_2']")).sendKeys(mcq_quiz[i][12]);


			//Enter Choice_4
			GV.driver.findElement(By.xpath(".//*[@id='choice-choice_3']")).sendKeys(mcq_quiz[i][14]);

			//Correct Answer feedback
			GV.driver.switchTo().frame("correctfeedback_ifr");
			GV.driver.findElement(By.id("tinymce")).sendKeys(mcq_quiz[i][16]);
			GV.driver.switchTo().defaultContent();


			//Wrong Answer Feed Back
			GV.driver.switchTo().frame("incorrectfeedback_ifr");
			GV.driver.findElement(By.id("tinymce")).sendKeys(mcq_quiz[i][17]);
			GV.driver.switchTo().defaultContent();

			//Enter SUBMIT button
			GV.driver.findElement(By.id("submit")).click();
		}



















		//Add MCQs to Chapter
		for (int ch = 1; ch <= TotaChaptersRequired; ch ++) 
		{
			for (int i = 1; i < MCQqst.getColumns(); i++)
			{
				GV.selenium.waitForPageToLoad("30000");
				// To click on Exact Chapter
				GV.driver.findElement(By.xpath(".//*[@id='accor3']/li[" + ch + "]/a")).click();

				//Click on 'Add Question' link on exact Lesson #.
				GV.driver.findElement(By.xpath("//ul[@id='accor3']/li["+ ch + "]/div/div/a")).click();


				// ADDING A MCQ QUESTIONS.
				//Click on 'MCQ' from popup
				GV.driver.findElement(By.xpath(".//*[@id='dialog']/ul/li[1]/a")).click();

				//Write Question Name
				GV.driver.findElement(By.id("qname")).sendKeys("Chapter"+ch+ "_Question" + (i) + " " + mcq_quiz[i][1]);

				//Write MCQ Quetsion Text ?
				GV.driver.switchTo().frame("qtext_ifr");
				GV.driver.findElement(By.id("tinymce")).sendKeys("Chapter"+ch+ "_Question" + (i) + ".) " + mcq_quiz[i][2]);
				GV.driver.switchTo().defaultContent();

				if (i==1)
				{
					GV.driver.findElement(By.id("qname")).clear();
					GV.driver.findElement(By.id("qname")).sendKeys("Chapter"+ch+ "_Question" + (i) + " " + mcq_quiz[i][1] + " imaged based");
					//Write MCQ Quetsion Text ?
					GV.driver.switchTo().frame("qtext_ifr");
					GV.driver.findElement(By.id("tinymce")).clear();
					GV.driver.findElement(By.id("tinymce")).sendKeys("Chapter"+ch+ "_Question" + (i) + ".) " + mcq_quiz[i][2] +"Image based ");
					GV.driver.switchTo().defaultContent();

					GV.driver.findElement(By.id("imagebased")).click();

					WebElement file = GV.driver.findElement(By.id("image"));
					file.sendKeys(GV.image1);
					Thread.sleep(1000);
				}	

				//Enter Marks.
				GV.driver.findElement(By.id("marks")).sendKeys(mcq_quiz[i][5]);

				//Enter Choice_1
				GV.driver.findElement(By.xpath(".//*[@id='choice-choice_0']")).sendKeys(mcq_quiz[i][8]);
				GV.driver.findElement(By.xpath(".//*[@id='choice-choice_0']")).sendKeys(Keys.TAB);
				GV.driver.findElement(By.id("correctans-0")).click();
				GV.driver.findElement(By.id("correctans-0")).sendKeys(Keys.TAB);
				for (int k = 1; k < 5; k++) 
				{
					//Cick on'Add More'
					GV.driver.findElement(By.xpath(".//*[@id='addmore']")).click();
				}


				//Enter Choice_2
				GV.driver.findElement(By.xpath(".//*[@id='choice-choice_1']")).sendKeys(mcq_quiz[i][10]);
				//Choose Radio button for Choice 2 as correct Answer.
				GV.driver.findElement(By.xpath(".//*[@id='correctans-1']")).click();

				//Enter Choice_3
				GV.driver.findElement(By.xpath(".//*[@id='choice-choice_2']")).sendKeys(mcq_quiz[i][12]);


				//Enter Choice_4
				GV.driver.findElement(By.xpath(".//*[@id='choice-choice_3']")).sendKeys(mcq_quiz[i][14]);

				//Correct Answer feedback
				GV.driver.switchTo().frame("correctfeedback_ifr");
				GV.driver.findElement(By.id("tinymce")).sendKeys(mcq_quiz[i][16]);
				GV.driver.switchTo().defaultContent();


				//Wrong Answer Feed Back
				GV.driver.switchTo().frame("incorrectfeedback_ifr");
				GV.driver.findElement(By.id("tinymce")).sendKeys(mcq_quiz[i][17]);
				GV.driver.switchTo().defaultContent();

				//Enter SUBMIT button
				GV.driver.findElement(By.id("submit")).click();
			}
		}











		//Add MCQs to Lessons
		for (int ch = 1; ch <= TotaChaptersRequired; ch ++) 
		{
			for (int le = 1; le <= TotaLessonsRequired ; le++) 
			{

				for (int i = 1; i < MCQqst.getColumns(); i++)
				{
					// To click on Exact Chapter
					GV.driver.findElement(By.xpath(".//*[@id='accor3']/li[" + ch + "]/a")).click();
					Thread.sleep(1000);
					//Click on 'Add Question' link on exact Lesson #.
					//GV.driver.findElement(By.xpath("//ul[@id='accor3']/li[" +ch+ "]/ul/li[" + le + "]/div/div/a")).click();
					GV.driver.findElement(By.xpath("//li[" +ch+ "]/ul/li[" + le + "]/div/div/a")).click();

					//Click on 'Add Question' link on exact Lesson #.
					GV.driver.findElement(By.xpath("//li[" +ch+ "]/ul/li[" + le + "]/div/div/a")).click();

					// ADDING A MCQ QUESTIONS.
					//Click on 'MCQ' from popup
					GV.driver.findElement(By.xpath(".//*[@id='dialog']/ul/li[1]/a")).click();

					//Write Question Name
					GV.driver.findElement(By.id("qname")).sendKeys("Ch"+ch+"le"+le+"_Qst" + (i) + " " + mcq_quiz[i][1]);

					//Write MCQ Quetsion Text ?
					GV.driver.switchTo().frame("qtext_ifr");
					GV.driver.findElement(By.id("tinymce")).sendKeys("Ch_"+ch+"_"+"le_"+le+"_Qst " + (i) + ".) " + mcq_quiz[i][2]);
					GV.driver.switchTo().defaultContent();


					if (i==1)
					{

						GV.driver.findElement(By.id("qname")).clear();
						GV.driver.findElement(By.id("qname")).sendKeys("Ch"+ch+"le"+le+"_Qst" + (i) + " " + mcq_quiz[i][1] + "imaged based");
						//Write MCQ Quetsion Text ?
						GV.driver.switchTo().frame("qtext_ifr");
						GV.driver.findElement(By.id("tinymce")).clear();
						GV.driver.findElement(By.id("tinymce")).sendKeys("Ch_"+ch+"_"+"le_"+le+"_Qst " + (i) + ".) " + mcq_quiz[i][2]);
						GV.driver.switchTo().defaultContent();

						GV.driver.findElement(By.id("imagebased")).click();

						WebElement file = GV.driver.findElement(By.id("image"));
						file.sendKeys(GV.image1);
						Thread.sleep(1000);
					}	
					//Enter Marks.
					GV.driver.findElement(By.id("marks")).sendKeys(mcq_quiz[i][5]);

					//Enter Choice_1
					GV.driver.findElement(By.xpath(".//*[@id='choice-choice_0']")).sendKeys(mcq_quiz[i][8]);
					GV.driver.findElement(By.xpath(".//*[@id='choice-choice_0']")).sendKeys(Keys.TAB);
					GV.driver.findElement(By.id("correctans-0")).click();
					GV.driver.findElement(By.id("correctans-0")).sendKeys(Keys.TAB);

					for (int k = 1; k < 5; k++) 
					{
						//Cick on'Add More'
						GV.driver.findElement(By.xpath(".//*[@id='addmore']")).click();
					}


					//Enter Choice_2
					GV.driver.findElement(By.xpath(".//*[@id='choice-choice_1']")).sendKeys(mcq_quiz[i][10]);
					//Choose Radio button for Choice 2 as correct Answer.
					GV.driver.findElement(By.xpath(".//*[@id='correctans-1']")).click();

					//Enter Choice_3
					GV.driver.findElement(By.xpath(".//*[@id='choice-choice_2']")).sendKeys(mcq_quiz[i][12]);


					//Enter Choice_4
					GV.driver.findElement(By.xpath(".//*[@id='choice-choice_3']")).sendKeys(mcq_quiz[i][14]);


					//Correct Answer feedback
					GV.driver.switchTo().frame("correctfeedback_ifr");
					GV.driver.findElement(By.id("tinymce")).sendKeys(mcq_quiz[i][16]);
					GV.driver.switchTo().defaultContent();


					//Wrong Answer Feed Back
					GV.driver.switchTo().frame("incorrectfeedback_ifr");
					GV.driver.findElement(By.id("tinymce")).sendKeys(mcq_quiz[i][17]);
					GV.driver.switchTo().defaultContent();

					//Enter SUBMIT button
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
