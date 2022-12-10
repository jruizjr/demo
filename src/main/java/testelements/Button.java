package testelements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import model.TestAction;

public class Button extends BaseElement{
	
	protected WebElement webElement;
	
	public Button(WebDriver driver) {
		super(driver);
		
	}

	public void click (TestAction testAction) {
		logger.debug("Clicking button " + testAction.getPageName() + "." + testAction.getElementName());
		WebDriverWait wait = new WebDriverWait(driver, 60000);
		
		wait.until(ExpectedConditions.visibilityOf(webElement));
		
	}
}
