package testelements;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import model.TestAction;

public class Button extends BaseElement{
	
	public Button(WebDriver driver) {
		super(driver);
		
	}

	public static String click (WebDriver driver, TestAction testAction, String locator) throws TimeoutException{
		String status = "";
		try{
			//logger.debug("Clicking button " + testAction.getPageName() + "." + testAction.getElementName());
			WebDriverWait wait = new WebDriverWait(driver, 60000);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//div[contains(@class,'popup modal')]//*[contains(text(),'Sending Feedback')]")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator))).click();
			status = "Pass";
			return status;
			
		} catch (TimeoutException e) {
			e.printStackTrace();
			status = "Fail";
			return status;
		}
	}
	
	public static String performButtonAction (WebDriver driver, TestAction testAction, String action, String locator) {
		String status = "";
		switch(action) {
		case "click":
			status = click(driver, testAction, locator);
			return status;
		default:
			break;	
		}
		return status;
	}
}
