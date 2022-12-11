package execution;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import model.*;
import util.*;

public class executeTest {
	
	//protected Logger logger;
	WebDriver webDriver;
	WebDriverWait wait;
	int suiteLR, tcLR, tsLR;
	static TestAction testAction;
	static TestStep testStep;
	static TestCase testCase;
	static TestSuite testSuite;
	static String fileDir = System.getProperty("user.dir") + "/src/main/java/resources/demoTest.xlsx";
	@BeforeSuite(alwaysRun = true)
	public void beforeSuite() throws Exception {
		try {
			//logger = Logger.getLogger(this.getClass());
			//logger.info("Starting Test");
		} catch (Exception e) {
			//logger.error("Error occured during setup; ", e);
			throw e;
		}
	}
	
	@AfterSuite(alwaysRun = true)
	public void afterSuite() throws Exception {
		try {
			//logger.info("Ending Test");
			//logger = null;
			
		} catch (Exception e) {
			//logger.error("Error occured during teardown; ", e);
			throw e;
		}
	}
	
	@BeforeTest(alwaysRun = true)
	public void initTest() throws Exception
    {
		System.out.println("BeforeTest");
		FileReader fr = new FileReader();
		
        try {
        	testSuite = new TestSuite();
        	System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/java/resources/chromedriver.exe");
        	webDriver = new ChromeDriver();
        	wait = new WebDriverWait(webDriver, 60000);
        	suiteLR = fr.getTestSuiteLastRow(fileDir);
        	tcLR = fr.getTestCaseLastRow(fileDir);
        	tsLR = fr.getTestStepLastRow(fileDir);
        	testSuite.setTestSuiteName(fr.getTestSuiteName(fileDir, 1));
        	System.out.println("[info]Getting Test Cases");
        	for(int x=1; x<=suiteLR; x++) {
		    	testCase = fr.getTestCase(fileDir, x);
		    	int nosteps = fr.getNoOfSteps(fileDir, testCase);
		    	for(int y = 1; y <= nosteps ; y++) {
		    		testStep = fr.getTestStep(fileDir, testCase, y);
		    		System.out.println("StepName: " + testStep.getStepName());
		    		if(testStep.getStepName().isBlank()==false) {
		    			int rowStart = fr.getStartRowAction(fileDir, testStep);
		    			int noaction = fr.getNoOfAction(fileDir, testStep, rowStart);
		    			for(int z = rowStart; z < rowStart + noaction; z++) {
				    		testAction = fr.getTestAction(fileDir, testStep, z);
			    			testStep.addTestAction(testAction);
				    	}
		    			testCase.addTestStep(testStep);
		    		}
		    	}
		    	testSuite.addTestCase(testCase);
		    }System.out.println("[info]Completed getting test cases");
        	
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	@AfterTest(alwaysRun = true)
	public void tearDown() throws Exception
    {
        try {
        	webDriver.quit();
		    webDriver = null;
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	@Test
	public void defaultTest() throws Exception {
		FileReader fr = new FileReader();
		String status = "";
		try {
			System.out.println("########## Starting test for " + testSuite.getTestSuiteName() + " ##########");
			webDriver.manage().window().maximize();
			//webDriver.get(fr.getEnvURL(System.getProperty("user.dir") + "/src/main/java/resources/demoTest.xlsx"));
		    Thread.sleep(3000);
		    List<TestCase> tcs = testSuite.getTestCases();
		    List<TestStep> tss;
		    List<TestAction> tas;
		    for(int x=0; x<tcs.size(); x++) {
		    	System.out.println("[Info] TestCases :" + tcs.get(x).getTestCaseName());
		    }
		    testcaseloop:
		    for(int x=0; x<tcs.size(); x++) {
		    	tss = tcs.get(x).getTestSteps();
		    	webDriver.get(fr.getEnvURL(System.getProperty("user.dir") + "/src/main/java/resources/demoTest.xlsx"));
		    	System.out.println("########## Starting test for " +  tcs.get(x).getTestCaseName() + " ##########");
		    	//System.out.println("[info] Executing TestCase: " + tcs.get(x).getTestCaseName());
		    	teststeploop:
		    	for(int y=0; y<tss.size(); y++) {
			    	tas = tss.get(y).getTestActions();
			    	System.out.println("[info] Executing TestStep: " + tss.get(y).getStepName());
			    	for(int z=0; z<tas.size(); z++) {
				    	TestAction ta = tas.get(z);
				    	String elementType = fr.getActionElement(fileDir, ta.getElementName());
				    	String locator = fr.getElementLocator(fileDir, ta.getElementName());
				    	System.out.println("[info] Executing TestAction: " + ta.getAction());
				    	status = ProcessAction.performAction(webDriver, ta, elementType, locator);
				    	System.out.println("Status: " + status);
				    	if(status.equals("Fail") == true) {
				    		tcs.get(x).setStatus(status);
				    		System.out.println("[Fail] Executed TestCase: " + tcs.get(x).getTestCaseName());
				    		break teststeploop;
				    	}
				    	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//div[contains(@class,'popup modal')]//*[contains(text(),'Sending Feedback')]")));
				    	Thread.sleep(1000);
				    } System.out.println("[info] Executed TestStep: " + tss.get(y).getStepName());
			    } tcs.get(x).setStatus(status);
		    	//if(status.equals("Pass")) System.out.println("[Pass] Executed TestCase: " + tcs.get(x).getTestCaseName() + ". Overall status Pass");
		    }
		    
		    /*System.out.println("Getting test action");
		    TestAction testAction = fr.getTestAction(fileDir, "NavigateContact");
		    System.out.println(testAction.getAction());
		    ProcessAction.performAction(webDriver, testAction, "Button", ".//ul[@class='nav']//a[text()='Contact']");*/
		    System.out.println("EndTest");
		    for(int x=0; x<tcs.size(); x++) {
		    	System.out.println("[Result] TestCase :" + tcs.get(x).getTestCaseName() + " - " + tcs.get(x).getStatus());
		    }
		    Thread.sleep(5000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
