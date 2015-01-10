
package course;


import java.io.FileInputStream;
import java.io.IOException;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import GlobalVariables.GlobalDecl;

public class C08_AddTorFquestions_QB 
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
	public void addqstsTrueORfalse() throws InterruptedException, RowsExceededException, WriteException, BiffException, IOException 
	{
		GV.Fileinput=new FileInputStream(GV.Input_ExcelFile + GV.excelName);
		GV.wbook=Workbook.getWorkbook(GV.Fileinput);



		//For True or False Questions.
		TorF=GV.wbook.getSheet("T_F");
		System.out.println("This is True or False Questions Excell file");
		System.out.println("Cloumns: "+TorF.getColumns());
		System.out.println("Rows: "+TorF.getRows());
		tf = new String [TorF.getColumns()][TorF.getRows()];
		for (int i = 1; i < TorF.getRows(); i++) {
			for (int j = 0; j < TorF.getColumns(); j++) 
			{
				tf[j][i] = TorF.getCell(j,i).getContents();

			}
		}
		System.out.println("");
		
		
		System.out.println("This 'Add T or F questions' Class File");
		GV.login();
		System.out.println("File Name: "+GV.Input_ExcelFile + GV.excelName);

		// Click on Exact Course Question Bank.
		GV.CourseQB();



		System.out.println("Entered into Course loop");

		//Add True or False to Course.
		for (int i = 1; i < TorF.getColumns(); i++)
		{
			//Click on 'Add Question' for Course.
			GV.driver.findElement(By.xpath("//div[@id='item2']/h2/div/div/a")).click();

			//Click on 'TF' from popup
			GV.driver.findElement(By.xpath(".//*[@id='dialog']/ul/li[3]/a")).click();

			//Enter Question Name
			GV.driver.findElement(By.id("qname")).sendKeys("CourseT/F_Qst_"+ i+" " +tf[i][1]);

			//Enter ' Question'.
			GV.driver.switchTo().frame("qtext_ifr");
			GV.driver.findElement(By.id("tinymce")).sendKeys("CourseT/F_Qst_"+ i + " " +tf[i][2]);
			GV.driver.switchTo().defaultContent();

			if (i==1)
			{
				GV.driver.findElement(By.id("qname")).clear();
				GV.driver.findElement(By.id("qname")).sendKeys("CourseT/F_Qst_"+ i+" " +tf[i][1]+ " Image based");

				GV.driver.switchTo().frame("qtext_ifr");
				GV.driver.findElement(By.id("tinymce")).clear();
				GV.driver.findElement(By.id("tinymce")).sendKeys("CourseT/F_Qst_"+ i + " " +tf[i][2]+ " Image based");
				GV.driver.switchTo().defaultContent();

				GV.driver.findElement(By.id("imagebased")).click();
				WebElement file = GV.driver.findElement(By.id("image"));
				file.sendKeys(GV.image1);
				Thread.sleep(1000);
			}	
			//Enter Marks.
			GV.driver.findElement(By.id("marks")).sendKeys(tf[i][5]);

			//Click on 'Mandatory' Question
			GV.driver.findElement(By.id("mandatory")).click();

			//Correct Answer feedback
			GV.driver.switchTo().frame("correctfeedback_ifr");
			GV.driver.findElement(By.id("tinymce")).sendKeys(tf[i][6]);
			GV.driver.switchTo().defaultContent();


			//Wrong Answer Feed Back
			GV.driver.switchTo().frame("incorrectfeedback_ifr");
			GV.driver.findElement(By.id("tinymce")).sendKeys(tf[i][7]);
			GV.driver.switchTo().defaultContent();

			//Enter SUBMIT button
			GV.driver.findElement(By.id("submit")).click();
		}














		System.out.println("Entered into Chapter loop");
		//Add T/F to Chapters
		for (int ch = 1; ch <= TotaChaptersRequired; ch ++) 
		{
			for (int i = 1; i < TorF.getColumns(); i++)
			{
				// To click on Exact Chapter
				GV.driver.findElement(By.xpath(".//*[@id='accor3']/li[" + ch + "]/a")).click();

				//Click on 'Add Question' link on exact Chapter #.
				GV.driver.findElement(By.xpath("//ul[@id='accor3']/li["+ ch + "]/div/div/a")).click();


				// ADDING A TF QUESTIONS.
				//Click on 'TF' from popup
				GV.driver.findElement(By.xpath(".//*[@id='dialog']/ul/li[3]/a")).click();

				//Enter Question Name
				GV.driver.findElement(By.id("qname")).sendKeys("Chapter"+ch+ "_Question" + i + " " +tf[i][1]);

				//Enter ' Question'.
				GV.driver.switchTo().frame("qtext_ifr");
				GV.driver.findElement(By.id("tinymce")).sendKeys("Chapter"+ch+ "_Question" + i + ".) " +tf[i][2]);
				GV.driver.switchTo().defaultContent();

				if (i==1)
				{
					GV.driver.findElement(By.id("qname")).clear();
					GV.driver.findElement(By.id("qname")).sendKeys("Chapter"+ch+ "_Question" + i + " " +tf[i][1]+ " Image based");

					GV.driver.switchTo().frame("qtext_ifr");
					GV.driver.findElement(By.id("tinymce")).clear();
					GV.driver.findElement(By.id("tinymce")).sendKeys("Chapter"+ch+ "_Question" + i + ".) " +tf[i][2]+ " Image based");
					GV.driver.switchTo().defaultContent();

					GV.driver.findElement(By.id("imagebased")).click();
					WebElement file = GV.driver.findElement(By.id("image"));
					file.sendKeys(GV.image1);
					Thread.sleep(1000);
				}	
				//Enter Marks.
				GV.driver.findElement(By.id("marks")).sendKeys(tf[i][5]);

				//Click on 'Mandatory' Question
				GV.driver.findElement(By.id("mandatory")).click();

				//Correct Answer feedback
				GV.driver.switchTo().frame("correctfeedback_ifr");
				GV.driver.findElement(By.id("tinymce")).sendKeys(tf[i][6]);
				GV.driver.switchTo().defaultContent();


				//Wrong Answer Feed Back
				GV.driver.switchTo().frame("incorrectfeedback_ifr");
				GV.driver.findElement(By.id("tinymce")).sendKeys(tf[i][7]);
				GV.driver.switchTo().defaultContent();

				//Enter SUBMIT button
				GV.driver.findElement(By.id("submit")).click();

				GV.selenium.waitForPageToLoad("30000");
			}
		}







		System.out.println("Entered into Lesson loop");
		//Add T/F to Lessons
		for (int ch = 1; ch <= TotaChaptersRequired; ch ++) 
		{
			for (int le = 1; le <= TotaLessonsRequired ; le++) 
			{
				System.out.println("Entered in Lesson_"+le+" loop");
				for (int i = 1; i < (TorF.getColumns()); i++)
				{

					// To click on Exact Chapter
					GV.driver.findElement(By.xpath(".//*[@id='accor3']/li[" + ch + "]/a")).click();
					Thread.sleep(1000);
					//Click on 'Add Question' link on exact Lesson #.  //li/ul/li/div/div/a
					//	GV.driver.findElement(By.xpath("//ul[@id='accor3']/li[" +ch+ "]/ul/li[" + le + "]/div/div/a")).click();
					GV.driver.findElement(By.xpath("//li[" +ch+ "]/ul/li[" + le + "]/div/div/a")).click();
					
					//Click on 'True/False' from popup
					GV.driver.findElement(By.xpath(".//*[@id='dialog']/ul/li[3]/a")).click();

					//Enter Question Name
					GV.driver.findElement(By.id("qname")).sendKeys("Ch"+ch+"le"+le+"_Qst" + i + " " +tf[i][1]);

					//Enter Question'.
					GV.driver.switchTo().frame("qtext_ifr");
					GV.driver.findElement(By.id("tinymce")).sendKeys("Ch_"+ch+"_"+"le_"+le+"_Qst " + i + ".) " +tf[i][2]);
					GV.driver.switchTo().defaultContent();
					if (i==1)
					{
						GV.driver.findElement(By.id("qname")).clear();
						GV.driver.findElement(By.id("qname")).sendKeys("Ch"+ch+"le"+le+"_Qst" + i + " " +tf[i][1]+ " Image based");

						GV.driver.switchTo().frame("qtext_ifr");
						GV.driver.findElement(By.id("tinymce")).clear();
						GV.driver.findElement(By.id("tinymce")).sendKeys("Ch_"+ch+"_"+"le_"+le+"_Qst " + i + ".) " +tf[i][2]+ " Image based");
						GV.driver.switchTo().defaultContent();

						GV.driver.findElement(By.id("imagebased")).click();
						WebElement file = GV.driver.findElement(By.id("image"));
						file.sendKeys(GV.image1);
						Thread.sleep(1000);
					}	
					//Enter Marks.
					GV.driver.findElement(By.id("marks")).sendKeys(tf[i][5]);

					//Click on 'Mandatory' Question
					GV.driver.findElement(By.id("mandatory")).click();

					//Correct Answer feedback
					GV.driver.switchTo().frame("correctfeedback_ifr");
					GV.driver.findElement(By.id("tinymce")).sendKeys(tf[i][6]);
					GV.driver.switchTo().defaultContent();


					//Wrong Answer Feed Back
					GV.driver.switchTo().frame("incorrectfeedback_ifr");
					GV.driver.findElement(By.id("tinymce")).sendKeys(tf[i][7]);
					GV.driver.switchTo().defaultContent();

					//Enter SUBMIT button
					GV.driver.findElement(By.id("submit")).click();
				}
			}
		}

		GV.CourseListing();
		Thread.sleep(1000);

		GV.selenium.stop();
		GV.driver.quit();

	}


}
