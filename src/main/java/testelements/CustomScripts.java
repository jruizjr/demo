package testelements;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import model.TestAction;

public class CustomScripts {
	
	public static String validateErrorMessage(WebDriver driver, TestAction testAction, String locator) throws NoSuchElementException {
		String status = "";
		WebDriverWait wait = new WebDriverWait(driver, 60000);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//div[contains(@class,'popup modal')]//*[contains(text(),'Sending Feedback')]")));
		
		try{
			String exist = "";
			if(driver.findElement(By.xpath(locator))!=null) {
				exist = "yes";
			}
			if(testAction.getData().equals(exist)==true) {
				status = "Pass";
			}
		} catch (NoSuchElementException e) {
			
			if(testAction.getData().equals("no")==true) {
				status = "Pass";
			}
		}
		return status;
	}
	
	public static String validateSucessMessage(WebDriver driver, TestAction testAction, String locator) throws NoSuchElementException {
		String status = "";
		WebDriverWait wait = new WebDriverWait(driver, 60000);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//div[contains(@class,'popup modal')]//*[contains(text(),'Sending Feedback')]")));
		try{
			String exist = "";
			if(driver.findElement(By.xpath(locator))!=null) {
				exist = "yes";
			}
			if(testAction.getData().equals(exist)==true) {
				status = "Pass";
			}
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
		return status;
	}
	
	public static String performCustomAction (WebDriver driver, TestAction testAction, String action, String locator) {
		String status = "Fail";
		WebDriverWait wait = new WebDriverWait(driver, 60000);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//div[contains(@class,'popup modal')]//*[contains(text(),'Sending Feedback')]")));
		switch(action) {
		case "validateErrorMessage":
			status = validateErrorMessage(driver, testAction, locator);
			return status;
		case "validateSuccessMessage":
			status = validateSucessMessage(driver, testAction, locator);
			return status;
		default:
			break;	
		}
		return status;
	}
}
