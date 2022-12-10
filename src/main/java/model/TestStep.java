package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TestStep {
	String testStepName;
	
	Map<String, String> paramMap;
	
	List<TestAction> testActions = new ArrayList<TestAction>();
	
	public TestStep(String stepName) {
		this.testStepName = stepName;
	}
	
	public void setStepName(String stepName) {
		this.testStepName = stepName;
	}
	
	public String getStepName() {
		return testStepName;
	}
	
	public List<TestAction> getTestActions(){
		return testActions;
	}
	
	public void setTestActions(List<TestAction> testActions) {
		this.testActions = testActions;
	}
	
	public void addTestAction(TestAction testActions) {
		this.testActions.add(testActions);
	}

}
