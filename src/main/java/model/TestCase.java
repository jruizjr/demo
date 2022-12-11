package model;

import java.util.ArrayList;
import java.util.List;

public class TestCase {
	String testCaseName;
	int noOfSteps;
	String status;
	
	long startTime;
	long endTime;
	
	List<TestStep> testStep = new ArrayList<TestStep>();
	
	
	public TestCase(String testCaseName) {
		this.testCaseName = testCaseName;
	}
	
	public String getTestCaseName() {
		return testCaseName;
	}
	
	public void setTestCaseName(String testCaseName) {
		this.testCaseName = testCaseName;
	}
	
	public int getNoOfSteps() {
		return noOfSteps;
	}
	
	public void setNoOfSteps(int noOfSteps) {
		this.noOfSteps = noOfSteps;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public long getStartTime() {
		return startTime;
	}
	
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	
	public long getEndTime() {
		return endTime;
	}
	
	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
	
	public void addTestStep(TestStep testStep) {
		this.testStep.add(testStep);
	}
	
	public List<TestStep> getTestSteps(){
		return testStep;
	}
}
