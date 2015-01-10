
package course;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import jxl.Sheet;
import jxl.Workbook;
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

public class C11_Add_Questions_QT 
{
	GlobalDecl GV = new GlobalDecl();
	public boolean b;
	public String imagepath = GV.image3;

	Sheet shortqst;
	Sheet MCQqst;
	Sheet TorF;
	Sheet Comp_qst;
	Sheet MRqst;


	String shrt[][];
	String mcq[][];
	String 	tf[][];
	String comp[][];
	String MR[][];



	@Test (priority = 1)
	public void QTaddQuestions() throws InterruptedException, RowsExceededException, WriteException, BiffException, IOException 
	{
		GV.Fileinput=new FileInputStream(GV.Input_ExcelFile + GV.excelName);
		GV.wbook=Workbook.getWorkbook(GV.Fileinput);

		//For Comprehensive Questions.
		Comp_qst=GV.wbook.getSheet("QT_Comp");
		System.out.println("This is MCQ Questions Excell file");
		System.out.println("Cloumns: "+Comp_qst.getColumns());
		System.out.println("Rows: "+Comp_qst.getRows());
		comp = new String [Comp_qst.getColumns()][Comp_qst.getRows()];
		for (int i = 1; i < Comp_qst.getRows(); i++) 
		{
			for (int j = 0; j < Comp_qst.getColumns(); j++) 
			{
				comp[j][i] = Comp_qst.getCell(j,i).getContents();

			}
		}
		System.out.println("");



		//For Multiple Choice Questions.
		MCQqst=GV.wbook.getSheet("QT_MCQs");
		System.out.println("This is MCQ Questions Excell file");
		System.out.println("Cloumns: "+MCQqst.getColumns());
		System.out.println("Rows: "+MCQqst.getRows());
		mcq = new String [MCQqst.getColumns()][MCQqst.getRows()];
		for (int i = 1; i < MCQqst.getRows(); i++) 
		{
			for (int j = 0; j < MCQqst.getColumns(); j++) 
			{
				mcq[j][i] = MCQqst.getCell(j,i).getContents();

			}
		}
		System.out.println("");



		// For Short Question.
		shortqst=GV.wbook.getSheet("QT_ShortQsts");
		System.out.println("This is Short Questions Excell file");
		System.out.println("Cloumns: "+shortqst.getColumns());
		System.out.println("Rows: "+shortqst.getRows());
		shrt = new String [shortqst.getColumns()][shortqst.getRows()];
		for (int i = 1; i < shortqst.getRows(); i++) 
		{
			for (int j = 0; j < shortqst.getColumns(); j++)
			{
				shrt[j][i] = shortqst.getCell(j,i).getContents();

			}
		}
		System.out.println("");

		//For True or False Questions.
		TorF=GV.wbook.getSheet("QT_T_F");
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



		//For Multiple Response Questions.
		MRqst=GV.wbook.getSheet("QT_Multi Resp");
		System.out.println("This is Multi Resp Questions Excell file");
		System.out.println("Cloumns: "+MRqst.getColumns());
		System.out.println("Rows: "+MRqst.getRows());
		MR = new String [MRqst.getColumns()][MRqst.getRows()];
		for (int i = 1; i < MRqst.getRows(); i++) {
			for (int j = 0; j < MRqst.getColumns(); j++) 
			{
				MR[j][i] = MRqst.getCell(j,i).getContents();

			}
		}
		System.out.println("");









		System.out.println("This 'Add QT questions' Class File");
		GV.login();
		System.out.println("File Name: "+ GV.Input_ExcelFile + GV.excelName);


		GV.CourseListing();
		//GV.driver.get(GV.baseUrl+"course/createcourse/view/id/"+GV.course_id);

		WebElement li = GV.driver.findElement(By.xpath(".//*[@id='accor3']"));
		java.util.List<WebElement> tr = li.findElements(By.tagName("li"));
		System.out.println("Size is: " + tr.size() );


		for (int i = 1; i <= tr.size(); i++) 
		{
			System.out.println("");
			System.out.println("-----------------------------------------------------------------------------------------------");
			System.out.println("Iteration number --> "+i + " of "+ tr.size());

			try
			{
				GV.driver.manage().timeouts().implicitlyWait(03, TimeUnit.SECONDS);
				String title = GV.driver.findElement(By.xpath(".//*[@id='accor3']/li["+ i + "]/div/div/a[3]")).getAttribute("title");
				System.out.println(title);

				if (title.contains("Question Bank")) 
				{
					String url = GV.driver.findElement(By.xpath(".//*[@id='accor3']/li["+ i + "]/div/div/a[3]")).getAttribute("href");
					System.out.println(url);

					String QTname = GV.driver.findElement(By.xpath(".//*[@id='accor3']/li["+ i +"]/a")).getText();
					System.out.println("Qualifying Test Name ------> " +"'"+ QTname+ "'");
					GV.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					GV.driver.get(url);


					//Add True or False to Course.
					for (int tf_i = 1; tf_i < TorF.getColumns(); tf_i++)
					{
						//Click on 'Add Question'
						GV.driver.findElement(By.name("modal")).click();

						//Click on 'TF' from popup
						GV.driver.findElement(By.xpath(".//*[@id='dialog']/ul/li[3]/a")).click();

						//Enter Question Name
						GV.driver.findElement(By.id("qname")).sendKeys("QT_T/F_Qst_"+ tf_i+" " +tf[tf_i][1]);

						//Enter ' Question'.
						GV.driver.switchTo().frame("qtext_ifr");
						GV.driver.findElement(By.id("tinymce")).sendKeys("QT_T/F_Qst_"+ tf_i + ".) " +tf[tf_i][2]);
						GV.driver.switchTo().defaultContent();

						if (tf_i == 1)
						{
							GV.driver.findElement(By.id("qname")).clear();
							GV.driver.findElement(By.id("qname")).sendKeys("CompT/F_Qst_"+ tf_i+" " +tf[tf_i][1] + " imaged based");


							//Enter ' Question'.
							GV.driver.switchTo().frame("qtext_ifr");
							GV.driver.findElement(By.id("tinymce")).clear();
							GV.driver.findElement(By.id("tinymce")).sendKeys("Image Based Qst ---> QT_T/F_Qst_"+ tf_i + ".) " +tf[tf_i][2]);
							GV.driver.switchTo().defaultContent();


							GV.driver.findElement(By.id("imagebased")).click();

							WebElement file = GV.driver.findElement(By.id("image"));
							file.sendKeys(imagepath);
							Thread.sleep(1000);


						}	

						//Enter Marks.
						GV.driver.findElement(By.id("marks")).sendKeys(tf[tf_i][5]);

						//Click on 'Mandatory' Question
						GV.driver.findElement(By.id("mandatory")).click();

						//Correct Answer feedback
						GV.driver.switchTo().frame("correctfeedback_ifr");
						GV.driver.findElement(By.id("tinymce")).sendKeys(tf[tf_i][6]);
						GV.driver.switchTo().defaultContent();


						//Wrong Answer Feed Back
						GV.driver.switchTo().frame("incorrectfeedback_ifr");
						GV.driver.findElement(By.id("tinymce")).sendKeys(tf[tf_i][7]);
						GV.driver.switchTo().defaultContent();

						//Enter SUBMIT button
						GV.driver.findElement(By.id("submit")).click();
					}




					// ADDING  Multi Response Question.
					for (int mr_i = 1; mr_i < MRqst.getColumns(); mr_i++)
					{
						//Click on 'Add Question'
						GV.driver.findElement(By.name("modal")).click();

						//Click on 'Multi Response' from popup
						GV.driver.findElement(By.xpath(".//*[@id='dialog']/ul/li[5]/a/span")).click();

						//Write Question Name
						GV.driver.findElement(By.id("qname")).sendKeys("CourseMR_Qst_" +(mr_i) + " " +MR[mr_i][1]);

						//Write MCQ Quetsion Text ?
						GV.driver.switchTo().frame("qtext_ifr");
						GV.driver.findElement(By.id("tinymce")).sendKeys("CourseMR_Qst_" + (mr_i) + ".) " +MR[mr_i][2]);
						GV.driver.switchTo().defaultContent();
						if (mr_i==1)
						{
							GV.driver.findElement(By.id("qname")).clear();
							GV.driver.findElement(By.id("qname")).sendKeys("CourseMR_Qst_" +(mr_i) + " " +MR[mr_i][1] + " imaged based");


							GV.driver.switchTo().frame("qtext_ifr");
							GV.driver.findElement(By.id("tinymce")).clear();
							GV.driver.findElement(By.id("tinymce")).sendKeys("CourseMR_Qst_" + (mr_i) + ".) " +MR[mr_i][2]);
							GV.driver.switchTo().defaultContent();

							GV.driver.findElement(By.id("imagebased")).click();
							WebElement file = GV.driver.findElement(By.id("image"));
							file.sendKeys(GV.image1);
							Thread.sleep(1000);
						}	

						//Enter Marks.
						GV.driver.findElement(By.id("marks")).sendKeys(MR[mr_i][5]);

						//Check box ON for shuffleanswers
						GV.driver.findElement(By.id("shuffleanswers")).click();

						//Enter Mandatory YES
						GV.driver.findElement(By.id("mandatory")).click();


						//Enter Choice_1
						GV.driver.findElement(By.xpath(".//*[@id='choice-choice_0']")).sendKeys(MR[mr_i][8]);
						Thread.sleep(1000);
						//Select Choice_0 as correct answer
						GV.driver.findElement(By.id("correctans-canswer_0")).click();


						for (int k = 1; k < 5; k++) 
						{
							//Cick on'Add More'
							GV.driver.findElement(By.xpath(".//*[@id='addmore']")).click();
						}


						//Enter Choice_2
						GV.driver.findElement(By.xpath(".//*[@id='choice-choice_1']")).sendKeys(MR[mr_i][10]);


						//Enter Choice_3
						GV.driver.findElement(By.xpath(".//*[@id='choice-choice_2']")).sendKeys(MR[mr_i][12]);
						//Select Choice_3 as correct answer
						GV.driver.findElement(By.id("correctans-correctans_2")).click();


						//Enter Choice_4
						GV.driver.findElement(By.xpath(".//*[@id='choice-choice_3']")).sendKeys(MR[mr_i][14]);

						//Correct Answer feedback
						GV.driver.switchTo().frame("correctfeedback_ifr");
						GV.driver.findElement(By.id("tinymce")).sendKeys(MR[mr_i][16]);
						GV.driver.switchTo().defaultContent();


						//Wrong Answer Feed Back
						GV.driver.switchTo().frame("incorrectfeedback_ifr");
						GV.driver.findElement(By.id("tinymce")).sendKeys(MR[mr_i][17]);
						GV.driver.switchTo().defaultContent();

						//Enter SUBMIT button
						GV.driver.findElement(By.id("submit")).click();
					}




					// ADDING A 'ComprehensiveQuestions' to COURSE.
					for (int cq_i = 1; cq_i < Comp_qst.getColumns(); cq_i++)
					{
						//Click on 'Add Question'
						GV.driver.findElement(By.name("modal")).click();

						//Click on 'Comprehensive' from popup
						GV.driver.findElement(By.xpath(".//*[@id='dialog']/ul/li[4]/a/span")).click();

						//Write Question Name
						GV.driver.findElement(By.id("qname")).sendKeys("QT_CompQst_" +(cq_i) + " " +comp[cq_i][1]);

						//Write Comprehensive Quetsion Text ?
						GV.driver.switchTo().frame("qtext_ifr");
						GV.driver.findElement(By.id("tinymce")).sendKeys("QT_CompMCQ_Qst_" + (cq_i) + ".) " +comp[cq_i][2]);
						GV.driver.switchTo().defaultContent();


						//Enter Marks.
						GV.driver.findElement(By.id("marks")).sendKeys(comp[cq_i][3]);



						//Enter Choice_1
						GV.driver.findElement(By.xpath(".//*[@id='choice-choice_0']")).sendKeys(comp[cq_i][4]);

						for (int k = 1; k < 5; k++) 
						{
							//Cick on'Add More'
							GV.driver.findElement(By.xpath(".//*[@id='addmore']")).click();
						}


						//Enter Choice_2
						GV.driver.findElement(By.xpath(".//*[@id='choice-choice_1']")).sendKeys(comp[cq_i][6]);
						//Choose Radio button for Choice 2 as correct Answer.
						GV.driver.findElement(By.xpath(".//*[@id='correctans-1']")).click();

						//Enter Choice_3
						GV.driver.findElement(By.xpath(".//*[@id='choice-choice_2']")).sendKeys(comp[cq_i][8]);


						//Enter Choice_4
						GV.driver.findElement(By.xpath(".//*[@id='choice-choice_3']")).sendKeys(comp[cq_i][10]);




						//Correct Answer Feed Back
						GV.driver.findElement(By.id("correctfeedback")).sendKeys(comp[cq_i][11]);

						//Correct Answer Feed Back
						GV.driver.findElement(By.id("incorrectfeedback")).sendKeys(comp[cq_i][12]);

						//Enter SUBMIT button
						GV.driver.findElement(By.id("submit")).click();
					}








					// ADDING A MCQ QUESTIONS to COURSE.
					for (int mc_i = 1; mc_i < MCQqst.getColumns(); mc_i++)
					{
						//Click on 'Add Question'
						GV.driver.findElement(By.name("modal")).click();

						//Click on 'MCQ' from popup
						GV.driver.findElement(By.xpath(".//*[@id='dialog']/ul/li[1]/a")).click();


						//Write Question Name
						GV.driver.findElement(By.id("qname")).sendKeys("QT_MCQ_Qst_" +(mc_i) + " " +mcq[mc_i][1]);

						//Write MCQ Quetsion Text ?
						GV.driver.switchTo().frame("qtext_ifr");
						GV.driver.findElement(By.id("tinymce")).sendKeys("QT_MCQ_Qst_" + (mc_i) + ".) " +mcq[mc_i][2]);
						GV.driver.switchTo().defaultContent();

						if (mc_i==1)
						{
							GV.driver.findElement(By.id("qname")).clear();
							GV.driver.findElement(By.id("qname")).sendKeys("CompMCQ_Qst_" +(mc_i) + " " +mcq[mc_i][1] + "imaged based");

							//Write MCQ Quetsion Text ?
							GV.driver.switchTo().frame("qtext_ifr");
							GV.driver.findElement(By.id("tinymce")).clear();
							GV.driver.findElement(By.id("tinymce")).sendKeys("Image Based Qst --> QT_MCQ_Qst_" + (mc_i) + ".) " +mcq[mc_i][2]);
							GV.driver.switchTo().defaultContent();

							GV.driver.findElement(By.id("imagebased")).click();

							WebElement file = GV.driver.findElement(By.id("image"));
							file.sendKeys(imagepath);
							Thread.sleep(1000);

						}	

						//Enter Marks.
						GV.driver.findElement(By.id("marks")).sendKeys(mcq[mc_i][5]);



						//Enter Choice_1
						GV.driver.findElement(By.xpath(".//*[@id='choice-choice_0']")).sendKeys(mcq[mc_i][8]);
						GV.driver.findElement(By.xpath(".//*[@id='choice-choice_0']")).sendKeys(Keys.TAB);

						for (int k = 1; k < 5; k++) 
						{
							//Cick on'Add More'
							GV.driver.findElement(By.xpath(".//*[@id='addmore']")).click();
						}


						//Enter Choice_2
						GV.driver.findElement(By.xpath(".//*[@id='choice-choice_1']")).sendKeys(mcq[mc_i][10]);
						//Choose Radio button for Choice 2 as correct Answer.
						GV.driver.findElement(By.xpath(".//*[@id='correctans-1']")).click();

						//Enter Choice_3
						GV.driver.findElement(By.xpath(".//*[@id='choice-choice_2']")).sendKeys(mcq[mc_i][12]);


						//Enter Choice_4
						GV.driver.findElement(By.xpath(".//*[@id='choice-choice_3']")).sendKeys(mcq[mc_i][14]);

						//Correct Answer feedback
						GV.driver.switchTo().frame("correctfeedback_ifr");
						GV.driver.findElement(By.id("tinymce")).sendKeys(mcq[mc_i][16]);
						GV.driver.switchTo().defaultContent();


						//Wrong Answer Feed Back
						GV.driver.switchTo().frame("incorrectfeedback_ifr");
						GV.driver.findElement(By.id("tinymce")).sendKeys(mcq[mc_i][17]);
						GV.driver.switchTo().defaultContent();

						//Enter SUBMIT button
						GV.driver.findElement(By.id("submit")).click();
					}




					//Add SHORT QUESTIONS to Course.

					for (int sq_i = 1; sq_i < shortqst.getColumns(); sq_i++)
					{

						//Click on 'Add Question'
						GV.driver.findElement(By.name("modal")).click();

						//Click on 'Short Questions' from popup
						GV.driver.findElement(By.xpath(".//*[@id='dialog']/ul/li[2]/a")).click();

						//Write Question Name
						GV.driver.findElement(By.id("qname")).sendKeys("QT_ShrtQst_" + sq_i + " " +shrt[sq_i][1]);

						//Write Short Answer Quetsion Text ?
						GV.driver.switchTo().frame("qtext_ifr");
						GV.driver.findElement(By.id("tinymce")).sendKeys("QT_ShrtQst_" + sq_i + ".) " + shrt[sq_i][2]);
						GV.driver.switchTo().defaultContent();

						//Enter Marks.
						GV.driver.findElement(By.id("marks")).sendKeys(shrt[sq_i][3]);
						//Enter Sequence
						String seq = Integer.toString(sq_i);
						GV.driver.findElement(By.xpath(".//*[@id='sequence']")).sendKeys(seq);

						//Click on Mandatory
						GV.driver.findElement(By.xpath(".//*[@id='mandatory']")).click();


						for (int k = 1; k < 4; k++) 
						{
							//Cick on'Add More'
							GV.driver.findElement(By.xpath(".//*[@id='addmore']")).click();
						}


						//Enter Choice_1
						GV.driver.findElement(By.xpath(".//*[@id='choice-choice_0']")).sendKeys(shrt[sq_i][6]);
						//select Grade for Choice_1
						new Select (GV.driver.findElement(By.id("grade-grade_0"))).selectByValue(shrt[sq_i][7]);



						//Enter Choice_2
						GV.driver.findElement(By.xpath(".//*[@id='choice-choice_1']")).sendKeys(shrt[sq_i][8]);
						//select Grade for Choice_2
						new Select (GV.driver.findElement(By.id("grade_1"))).selectByValue(shrt[sq_i][9]);



						//Enter Choice_3
						GV.driver.findElement(By.xpath(".//*[@id='choice-choice_2']")).sendKeys(shrt[sq_i][10]);
						//select Grade for Choice_3
						new Select (GV.driver.findElement(By.id("grade_2"))).selectByValue(shrt[sq_i][11]);


						//Enter Choice_4
						GV.driver.findElement(By.xpath(".//*[@id='choice-choice_3']")).sendKeys(shrt[sq_i][12]);
						//select Grade for Choice_4
						new Select (GV.driver.findElement(By.id("grade_3"))).selectByValue(shrt[sq_i][13]);


						//Enter Difficulty Rating
						new Select(GV.driver.findElement(By.id("difficulty"))).selectByValue(shrt[sq_i][14]);

						//Correct Answer feedback
						GV.driver.switchTo().frame("correctfeedback_ifr");
						GV.driver.findElement(By.id("tinymce")).sendKeys(shrt[sq_i][15]);
						GV.driver.switchTo().defaultContent();


						//Wrong Answer Feed Back
						GV.driver.switchTo().frame("incorrectfeedback_ifr");
						GV.driver.findElement(By.id("tinymce")).sendKeys(shrt[sq_i][16]);
						GV.driver.switchTo().defaultContent();

						//Enter SUBMIT button
						GV.driver.findElement(By.id("submit")).click();
					}
				}
			}

			catch (Exception e) 
			{
				System.out.println("CATCH Block executed: No QTs found in this Chapter/Course");
			}

			GV.driver.get(GV.baseUrl+"course/createcourse/view/id/"+GV.course_id);
			GV.selenium.waitForPageToLoad("3000");

		}

		GV.selenium.stop();
		GV.driver.quit();
	}
}


