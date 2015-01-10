
package course;


import java.io.FileInputStream;
import java.io.IOException;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import GlobalVariables.GlobalDecl;

public class C07_AddSHRTquesiions_QB 
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

	Sheet shortqst;
	Sheet MCQqst;
	Sheet TorF;

	String shrt[][];
	String mcq[][];
	String 	tf[][];

	

	@Test
	public void addqsts2CourseChapLessons() throws InterruptedException, RowsExceededException, WriteException, BiffException, IOException 
	{
		GV.Fileinput=new FileInputStream(GV.Input_ExcelFile + GV.excelName);
		GV.wbook=Workbook.getWorkbook(GV.Fileinput);


		// For Short Question.
		shortqst=GV.wbook.getSheet("ShortQsts");
		System.out.println("This is Short Questions Excell file");
		System.out.println("Cloumns: "+shortqst.getColumns());
		System.out.println("Rows: "+shortqst.getRows());
		shrt = new String [shortqst.getColumns()][shortqst.getRows()];
		for (int i = 1; i < shortqst.getRows(); i++) 
		{
			for (int j = 0; j < shortqst.getColumns(); j++) {
				shrt[j][i] = shortqst.getCell(j,i).getContents();

			}
		}
		System.out.println("");
		
		
		
		System.out.println("This 'Add Short questions' Class File");
		GV.login();
		System.out.println("File Name: "+GV.Input_ExcelFile + GV.excelName);


		// Click on Exact Course Question Bank.
		GV.CourseQB();


		
		  //Add SHORT QUESTIONS to Course.

		for (int i = 1; i < shortqst.getColumns(); i++)
		{

			//Click on 'Add Question' for Course.
			GV.driver.findElement(By.xpath("//div[@id='item2']/h2/div/div/a")).click();

			//Click on 'Short Questions' from popup
			GV.driver.findElement(By.xpath(".//*[@id='dialog']/ul/li[2]/a")).click();

			//Write Question Name
			GV.driver.findElement(By.id("qname")).sendKeys("CourseShrtQst_" + i + " " +shrt[i][1]);

			//Write Short Answer Quetsion Text ?
			GV.driver.switchTo().frame("qtext_ifr");
			GV.driver.findElement(By.id("tinymce")).sendKeys("CourseShrtQst_" + i + ".) " + shrt[i][2]);
			GV.driver.switchTo().defaultContent();

			//Enter Marks.
			GV.driver.findElement(By.id("marks")).sendKeys(shrt[i][3]);
			//Enter Sequence
			String seq = Integer.toString(i);
			GV.driver.findElement(By.xpath(".//*[@id='sequence']")).sendKeys(seq);

			//Click on Mandatory
			GV.driver.findElement(By.xpath(".//*[@id='mandatory']")).click();


			for (int k = 1; k < 4; k++) 
			{
				//Cick on'Add More'
				GV.driver.findElement(By.xpath(".//*[@id='addmore']")).click();
			}


			//Enter Choice_1
			GV.driver.findElement(By.xpath(".//*[@id='choice-choice_0']")).sendKeys(shrt[i][6]);
			//select Grade for Choice_1
			new Select (GV.driver.findElement(By.id("grade-grade_0"))).selectByValue(shrt[i][7]);



			//Enter Choice_2
			GV.driver.findElement(By.xpath(".//*[@id='choice-choice_1']")).sendKeys(shrt[i][8]);
			//select Grade for Choice_2
			new Select (GV.driver.findElement(By.id("grade_1"))).selectByValue(shrt[i][9]);



			//Enter Choice_3
			GV.driver.findElement(By.xpath(".//*[@id='choice-choice_2']")).sendKeys(shrt[i][10]);
			//select Grade for Choice_3
			new Select (GV.driver.findElement(By.id("grade_2"))).selectByValue(shrt[i][11]);


			//Enter Choice_4
			GV.driver.findElement(By.xpath(".//*[@id='choice-choice_3']")).sendKeys(shrt[i][12]);
			//select Grade for Choice_4
			new Select (GV.driver.findElement(By.id("grade_3"))).selectByValue(shrt[i][13]);


			//Enter Difficulty Rating
			new Select(GV.driver.findElement(By.id("difficulty"))).selectByValue(shrt[i][14]);

			//Correct Answer feedback
			GV.driver.switchTo().frame("correctfeedback_ifr");
			GV.driver.findElement(By.id("tinymce")).sendKeys(shrt[i][15]);
			GV.driver.switchTo().defaultContent();


			//Wrong Answer Feed Back
			GV.driver.switchTo().frame("incorrectfeedback_ifr");
			GV.driver.findElement(By.id("tinymce")).sendKeys(shrt[i][16]);
			GV.driver.switchTo().defaultContent();

			//Enter SUBMIT button
			GV.driver.findElement(By.id("submit")).click();

		}




		//Add SHORT QUESTIONS to Chapters
		for (int ch = 1; ch <= TotaChaptersRequired; ch ++) 
		{
			for (int i = 1; i < shortqst.getColumns(); i++)
			{
				// To click on Exact Chapter
				GV.driver.findElement(By.xpath(".//*[@id='accor3']/li[" + ch + "]/a")).click();

				//Click on 'Add Question' link on exact Chapter #.
				GV.driver.findElement(By.xpath("//ul[@id='accor3']/li["+ ch + "]/div/div/a")).click();


				// ADDING A Short Questions.
				//Click on 'Short Questions' from popup
				GV.driver.findElement(By.xpath(".//*[@id='dialog']/ul/li[2]/a")).click();

				//Write Question Name
				GV.driver.findElement(By.id("qname")).sendKeys("Chapter"+ch+ "Question" + i + " " +shrt[i][1]);

				//Write Short Answer Quetsion Text ?
				GV.driver.switchTo().frame("qtext_ifr");
				GV.driver.findElement(By.id("tinymce")).sendKeys("Chapter"+ch+ "_Question" + i + ".) " + shrt[i][2]);
				GV.driver.switchTo().defaultContent();

				//Enter Marks.
				GV.driver.findElement(By.id("marks")).sendKeys(shrt[i][3]);
				//Enter Sequence
				String seq = Integer.toString(i);
				GV.driver.findElement(By.xpath(".//*[@id='sequence']")).sendKeys(seq);

				//Click on Mandatory
				GV.driver.findElement(By.xpath(".//*[@id='mandatory']")).click();


				for (int k = 1; k < 4; k++) 
				{
					//Cick on'Add More'
					GV.driver.findElement(By.xpath(".//*[@id='addmore']")).click();
				}


				//Enter Choice_1
				GV.driver.findElement(By.xpath(".//*[@id='choice-choice_0']")).sendKeys(shrt[i][6]);
				//select Grade for Choice_1
				new Select (GV.driver.findElement(By.id("grade-grade_0"))).selectByValue(shrt[i][7]);



				//Enter Choice_2
				GV.driver.findElement(By.xpath(".//*[@id='choice-choice_1']")).sendKeys(shrt[i][8]);
				//select Grade for Choice_2
				new Select (GV.driver.findElement(By.id("grade_1"))).selectByValue(shrt[i][9]);



				//Enter Choice_3
				GV.driver.findElement(By.xpath(".//*[@id='choice-choice_2']")).sendKeys(shrt[i][10]);
				//select Grade for Choice_3
				new Select (GV.driver.findElement(By.id("grade_2"))).selectByValue(shrt[i][11]);


				//Enter Choice_4
				GV.driver.findElement(By.xpath(".//*[@id='choice-choice_3']")).sendKeys(shrt[i][12]);
				//select Grade for Choice_4
				new Select (GV.driver.findElement(By.id("grade_3"))).selectByValue(shrt[i][13]);


				//Enter Difficulty Rating
				new Select(GV.driver.findElement(By.id("difficulty"))).selectByValue(shrt[i][14]);

				//Correct Answer feedback
				GV.driver.switchTo().frame("correctfeedback_ifr");
				GV.driver.findElement(By.id("tinymce")).sendKeys(shrt[i][15]);
				GV.driver.switchTo().defaultContent();


				//Wrong Answer Feed Back
				GV.driver.switchTo().frame("incorrectfeedback_ifr");
				GV.driver.findElement(By.id("tinymce")).sendKeys(shrt[i][16]);
				GV.driver.switchTo().defaultContent();

				//Enter SUBMIT button
				GV.driver.findElement(By.id("submit")).click();

			}
		}











		 




		//Add SHORT QUESTIONS to Lessons
		for (int ch = 1; ch <= TotaChaptersRequired; ch ++) 
		{
			for (int le = 1; le <= TotaLessonsRequired ; le++) 
			{

				for (int i = 1; i < (shortqst.getColumns()); i++)
				{

					// To click on Exact Chapter
					GV.driver.findElement(By.xpath(".//*[@id='accor3']/li[" + ch + "]/a")).click();
					Thread.sleep(1000);
					//Click on 'Add Question' link on exact Lesson #.  //li/ul/li/div/div/a
					//	GV.driver.findElement(By.xpath("//ul[@id='accor3']/li[" +ch+ "]/ul/li[" + le + "]/div/div/a")).click();
					GV.driver.findElement(By.xpath("//li[" +ch+ "]/ul/li[" + le + "]/div/div/a")).click();

					//Click on 'Add Question' link on exact Lesson #.
					//	GV.driver.findElement(By.xpath("//li[" +ch+ "]/ul/li[" + le + "]/div/div/a")).click();



					// ADDING A Short Questions.
					//Click on 'Short Questions' from popup
					GV.driver.findElement(By.xpath(".//*[@id='dialog']/ul/li[2]/a")).click();

					//Write Question Name
					GV.driver.findElement(By.id("qname")).sendKeys("Ch"+ch+"le"+le+"_Qst" + i + " " +shrt[i][1]);

					//Write Short Answer Quetsion Text ?
					GV.driver.switchTo().frame("qtext_ifr");
					GV.driver.findElement(By.id("tinymce")).sendKeys("Ch_"+ch+"_"+"le_"+le+"_Qst " + i + ".) " +shrt[i][2]);
					GV.driver.switchTo().defaultContent();

					//Enter Marks.
					GV.driver.findElement(By.id("marks")).sendKeys(shrt[i][3]);
					//Enter Sequence
					String seq = Integer.toString(i);
					GV.driver.findElement(By.xpath(".//*[@id='sequence']")).sendKeys(seq);

					//Click on Mandatory
					GV.driver.findElement(By.xpath(".//*[@id='mandatory']")).click();


					for (int k = 1; k < 4; k++) 
					{
						//Cick on'Add More'
						GV.driver.findElement(By.xpath(".//*[@id='addmore']")).click();
					}


					//Enter Choice_1
					GV.driver.findElement(By.xpath(".//*[@id='choice-choice_0']")).sendKeys(shrt[i][6]);
					//select Grade for Choice_1
					new Select (GV.driver.findElement(By.id("grade-grade_0"))).selectByValue(shrt[i][7]);



					//Enter Choice_2
					GV.driver.findElement(By.xpath(".//*[@id='choice-choice_1']")).sendKeys(shrt[i][8]);
					//select Grade for Choice_2
					new Select (GV.driver.findElement(By.id("grade_1"))).selectByValue(shrt[i][9]);



					//Enter Choice_3
					GV.driver.findElement(By.xpath(".//*[@id='choice-choice_2']")).sendKeys(shrt[i][10]);
					//select Grade for Choice_3
					new Select (GV.driver.findElement(By.id("grade_2"))).selectByValue(shrt[i][11]);


					//Enter Choice_4
					GV.driver.findElement(By.xpath(".//*[@id='choice-choice_3']")).sendKeys(shrt[i][12]);
					//select Grade for Choice_4
					new Select (GV.driver.findElement(By.id("grade_3"))).selectByValue(shrt[i][13]);


					//Enter Difficulty Rating
					new Select(GV.driver.findElement(By.id("difficulty"))).selectByValue(shrt[i][14]);

					//Correct Answer feedback
					GV.driver.switchTo().frame("correctfeedback_ifr");
					GV.driver.findElement(By.id("tinymce")).sendKeys(shrt[i][15]);
					GV.driver.switchTo().defaultContent();


					//Wrong Answer Feed Back
					GV.driver.switchTo().frame("incorrectfeedback_ifr");
					GV.driver.findElement(By.id("tinymce")).sendKeys(shrt[i][16]);
					GV.driver.switchTo().defaultContent();

					//Enter SUBMIT button
					GV.driver.findElement(By.id("submit")).click();
				}

			}
		}



		GV.CourseListing();
		GV.selenium.stop();
		GV.driver.quit();
	}

	
}
