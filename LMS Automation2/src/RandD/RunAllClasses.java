package RandD;



import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import GlobalVariables.GlobalDecl;
import GlobalVariables.SendEmail;
public class RunAllClasses
{
	static  XmlSuite suite = new XmlSuite();
	static  XmlTest test = new XmlTest(suite);
	static List<XmlClass> classes = new ArrayList<XmlClass>();
	static List<XmlSuite> suites = new ArrayList<XmlSuite>();
	static TestNG tng = new TestNG();
	
	GlobalDecl GV = new GlobalDecl();
	@BeforeTest
	public void before()
	{
		suite.setName("MyTestSuiteJAVA");
		test.setName("testJAVA");

		//List<XmlSuite> suites = new ArrayList<XmlSuite>();
		suites.add(suite);
		suites.size();
		System.out.println(suites.size()+ "Classes are added for Test.");
		//TestNG tng = new TestNG();
		tng.setXmlSuites(suites);    
	}

					@Test ( priority = 1, groups = {"1"})
					public void C01_Create_newCourse()
					{
						classes.add(new XmlClass("Coursee.C01_Create_newCourse"));
						test.setXmlClasses(classes) ;
					}
					@Test ( priority = 2 , dependsOnGroups = {"1"})
					public void C02_AddChap_Lessons()
					{
						classes.add(new XmlClass("Coursee.C02_AddChap_Lessons"));
						//test.setXmlClasses(classes) ;
					}
					@Test ( priority = 3)
					public void C03_Add_Assignments()
					{
						classes.add(new XmlClass("Coursee.C03_Add_Assignments"));
						test.setXmlClasses(classes) ;
					}
					@Test ( priority = 4)
					public void C04_Add_AssessmentsQUIZ()
					{
						classes.add(new XmlClass("Coursee.C04_Add_AssessmentsQUIZ"));
						test.setXmlClasses(classes) ;
					}
					@Test ( priority = 5)
					public void C05_AddSurveys()
					{
						classes.add(new XmlClass("Coursee.C05_AddSurveys"));
						test.setXmlClasses(classes) ;
					}
					@Test ( priority = 6)
					public void C06_AddMCQquestions_QB()
					{
						classes.add(new XmlClass("Coursee.C06_AddMCQquestions_QB"));
						test.setXmlClasses(classes) ;
					}
					
				
					@Test ( priority = 7)
					public void C07_AddSHRTquesiions_QB()
					{
						classes.add(new XmlClass("Coursee.C07_AddSHRTquesiions_QB"));
						test.setXmlClasses(classes) ;
					}
				
					@Test ( priority = 8)
					public void C08_AddTorFquestions_QB()
					{
						classes.add(new XmlClass("Coursee.C08_AddTorFquestions_QB"));
						test.setXmlClasses(classes) ;
					}
				
					@Test ( priority = 9)
					public void C09_AddMultipleResponse()
					{
						classes.add(new XmlClass("Coursee.C09_AddMultipleResponse"));
						test.setXmlClasses(classes) ;
					}
					
					@Test ( priority = 10)
					public void C10_Add_Qualifying_Test()
					{
						classes.add(new XmlClass("Coursee.C10_Add_Qualifying_Test"));
						test.setXmlClasses(classes) ;
					}
					@Test ( priority = 11)
					public void C11_Add_Questions_QT()
					{
						classes.add(new XmlClass("Coursee.C11_Add_Questions_QT"));
						test.setXmlClasses(classes) ;
					}
					@Test ( priority = 12)
					public void send_email() 
					{
						tng.run();
					}

	@AfterTest
	public void after() throws Exception 
	{
		//SendEmail.execute(GV.emailFileName);
	}



}
