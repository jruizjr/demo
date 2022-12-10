package model;

import java.util.LinkedHashMap;
import java.util.Map;

public class TestCase {
	String testCaseName;
	String status;
	
	long startTime;
	long endTime;
	
	Map<String, TestStep> testStepMap =  new LinkedHashMap<String, TestStep>();
	
	public String getTestCaseName() {
		return testCaseName;
	}
	
	public void setTestCaseName(String testCaseName) {
		this.testCaseName = testCaseName;
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
}
