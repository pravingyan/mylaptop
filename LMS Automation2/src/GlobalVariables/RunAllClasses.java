package GlobalVariables;

import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import org.uncommons.reportng.ReportNGUtils;
public class RunAllClasses
{
	GlobalDecl GV = new GlobalDecl();

	public  XmlSuite suite = new XmlSuite();
	public  XmlTest test = new XmlTest(suite);
	public List<XmlClass> classes = new ArrayList<XmlClass>();
	public List<XmlSuite> suites = new ArrayList<XmlSuite>();
	public TestNG tng = new TestNG();


	@BeforeTest
	public void before()
	{
		suite.addListener("org.uncommons.reportng.HTMLReporter");
		suite.addListener("org.uncommons.reportng.JUnitXMLReporter");

		suite.setName("Suitename CourseModule");
		test.setName("Testname_AllCourseModules");


		//List<XmlSuite> suites = new ArrayList<XmlSuite>();
		suites.add(suite);
		suites.size();
		System.out.println(suites.size()+ " Class(s) added for execution.");
		//TestNG tng = new TestNG();
		tng.setXmlSuites(suites);    
	}


	@Test ( priority = 1)
	public void C01_Create_newCourse()
	{
		classes.add(new XmlClass("course.C01_Create_newCourse"));
	}


	@Test ( priority = 2 , dependsOnMethods = { "C01_Create_newCourse" })
	public void C02_AddChap_Lessons()
	{
		classes.add(new XmlClass("course.C02_AddChap_Lessons"));
	}


	@Test ( priority = 3, dependsOnMethods = { "C02_AddChap_Lessons" })
	public void C03_Add_Assignments()
	{
		classes.add(new XmlClass("course.C03_Add_Assignments"));
	}

	@Test ( priority = 4, dependsOnMethods = { "C03_Add_Assignments" })
	public void C04_Add_AssessmentsQUIZ()
	{
		classes.add(new XmlClass("course.C04_Add_AssessmentsQUIZ"));
	}



	@Test ( priority = 5, dependsOnMethods = { "C04_Add_AssessmentsQUIZ" })
	public void C05_AddSurveys()
	{
		classes.add(new XmlClass("course.C05_AddSurveys"));
	}



	@Test ( priority = 6, dependsOnMethods = { "C05_AddSurveys" })
	public void C06_AddMCQquestions_QB()
	{
		classes.add(new XmlClass("course.C06_AddMCQquestions_QB"));
	}



	@Test ( priority = 7, dependsOnMethods = { "C06_AddMCQquestions_QB" })
	public void C07_AddSHRTquesiions_QB()
	{
		classes.add(new XmlClass("course.C07_AddSHRTquesiions_QB"));
	}



	@Test ( priority = 8, dependsOnMethods = { "C07_AddSHRTquesiions_QB" })
	public void C08_AddTorFquestions_QB()
	{
		classes.add(new XmlClass("course.C08_AddTorFquestions_QB"));
	}



	@Test ( priority = 9, dependsOnMethods = { "C08_AddTorFquestions_QB" })
	public void C09_AddMultipleResponse()
	{
		classes.add(new XmlClass("course.C09_AddMultipleResponse"));
	}



	@Test ( priority = 10, dependsOnMethods = { "C09_AddMultipleResponse" })
	public void C10_Add_Qualifying_Test()
	{
		classes.add(new XmlClass("course.C10_Add_Qualifying_Test"));
	}



	@Test ( priority = 11, dependsOnMethods = { "C10_Add_Qualifying_Test" })
	public void C11_Add_Questions_QT()
	{
		classes.add(new XmlClass("course.C11_Add_Questions_QT"));

	}





	@AfterTest
	public void afterTest()
	{
		test.setXmlClasses(classes) ;
		tng.run();
	}




	@AfterSuite
	public void after() throws Exception 
	{
		classes.clear();

		suite.addListener("org.uncommons.reportng.HTMLReporter");
		suite.addListener("org.uncommons.reportng.JUnitXMLReporter");

		classes.add(new XmlClass("GlobalVariables.EmailTestNG"));
		test.setXmlClasses(classes) ;
		tng.run();
	}


}
