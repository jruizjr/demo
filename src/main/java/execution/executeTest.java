package execution;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import Util.FileReader;

public class executeTest {
	
	//protected Logger logger;
	WebDriver webDriver;
	WebDriverWait wait;
	/*@BeforeSuite(alwaysRun = true)
	public void beforeSuite() throws Exception {
		try {
			logger = Logger.getLogger(this.getClass());
			logger.info("Starting Test");
						
		} catch (Exception e) {
			logger.error("Error occured during setup; ", e);
			throw e;
		}
	}
	
	@AfterSuite(alwaysRun = true)
	public void afterSuite() throws Exception {
		try {
			logger.info("Ending Test");
			logger = null;
			
		} catch (Exception e) {
			logger.error("Error occured during teardown; ", e);
			throw e;
		}
	}
	*/
	@BeforeSuite(alwaysRun = true)
	public void initTest() throws Exception
    {

        try {
        	System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/java/resources/chromedriver.exe");
        	webDriver = new ChromeDriver();
			//WebDriverWait wait = new WebDriverWait(webDriver, 60000);
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	@AfterSuite(alwaysRun = true)
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
		try {
			
			String path = System.getenv("Path");
			System.out.println(path); // Should contain C:\Windows\system32
			
			webDriver.manage().window().maximize();
			webDriver.get(Util.FileReader.getEnvURL(System.getProperty("user.dir") + "/src/main/java/resources/demoTest.xlsx"));
		    //webDriver.get("https://www.google.com");
		    Thread.sleep(15000);
		    
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
