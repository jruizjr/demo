package testelements;

import org.apache.log4j.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BaseElement {
	
	/*public enum TestElementType {
		TEXTBOX("testelements.Text"),
		BUTTON("testelements.Button"),
		LABEL("testelements.Label");
	}*/
	
	protected static WebDriver driver;
	protected WebElement webElement;
	
	protected final Logger logger = Logger.getLogger(this.getClass());
	
	public BaseElement(WebDriver driver) {
		BaseElement.driver = driver;
	}
}
