package util;

import org.openqa.selenium.WebDriver;

import model.TestAction;
import testelements.*;

public class ProcessAction {
	static String status = "";
	
	public static String performAction(WebDriver driver, TestAction testAction, String element, String locator) {

		switch(element) {
		case "Button":
			status = testelements.Button.performButtonAction(driver, testAction, testAction.getAction(), locator);
			return status;
		case "Text":
			status = testelements.Text.performTextAction(driver, testAction, testAction.getAction(), locator);
			return status;
		case "Label":
			status = testelements.Label.performLabelAction(driver, testAction, testAction.getAction(), locator);
			return status;
		case "CustomScript":
			status = testelements.CustomScripts.performCustomAction(driver, testAction, testAction.getAction(), locator);
			return status;
		}
		return status;
	}
}
