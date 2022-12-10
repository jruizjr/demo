package com.simplilearn.mavenproject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Hello world!
 *
 */
public class App 
{
	static WebDriver webDriver;
	static WebDriverWait wait;
	
	@BeforeTest (alwaysRun = true)
    public void main() throws Exception
    {
        System.out.println( "Hello World!" );
       
        try {
        	System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/java/resources/chromedriver.exe");
        	webDriver = new ChromeDriver();
			wait = new WebDriverWait(webDriver, 60000);
			webDriver.manage().window().maximize();
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	
	@Test
	public void defaultTest() throws Exception{
		try {
			
		    webDriver.get("https://www.google.com");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
