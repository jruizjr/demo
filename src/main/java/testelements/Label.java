package testelements;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import model.TestAction;

public class Label extends BaseElement{
	
	protected WebElement webElement;
	
	public Label(WebDriver driver) {
		super(driver);
		
	}

	public static String click (WebDriver driver, TestAction testAction, String locator) throws TimeoutException{
		String status = "Fail";
		try{
			//logger.debug("Clicking button " + testAction.getPageName() + "." + testAction.getElementName());
			WebDriverWait wait = new WebDriverWait(driver, 60000);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//div[contains(@class,'popup modal')]//*[contains(text(),'Sending Feedback')]")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator))).click();
			status = "Pass";
			return status;
		} catch (TimeoutException e) {
			e.printStackTrace();
			return status;
		}
		
	}
	
	public static String checkLabel (WebDriver driver, TestAction testAction, String locator) throws TimeoutException{
		String status = "";
		String actualLabel = "";
		try{
			//logger.debug("Clicking button " + testAction.getPageName() + "." + testAction.getElementName());
			WebDriverWait wait = new WebDriverWait(driver, 60000);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//div[contains(@class,'popup modal')]//*[contains(text(),'Sending Feedback')]")));
			actualLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator))).getText();
			if(actualLabel.contains(testAction.getData())==true){
				status = "Pass";
			} else status = "Fail";
			return status;
		} catch (TimeoutException e) {
			e.printStackTrace();
			return status;
		}
		
	}
	public static String performLabelAction (WebDriver driver, TestAction testAction, String action, String locator) {
		String status = "";
		switch(action) {
		case "click":
			status = click(driver, testAction, locator);
			return status;
		case "checkLabel":
			status = checkLabel(driver, testAction, locator);
			return status;
		default:
			break;	
		}
		return status;
	}
}
