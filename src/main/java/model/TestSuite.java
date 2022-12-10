package model;

import java.util.ArrayList;
import java.util.List;

public class TestSuite {
	String testSuiteName;
	
	List<TestCase> testCases = new ArrayList<TestCase>();
	
	public void setTestSuiteName(String testSuiteName) {
		this.testSuiteName = testSuiteName;
	}
	
	public String getTestSuiteName() {
		return testSuiteName;
	}
	
	public List<TestCase> getTestCases(){
		return testCases;
	}
	
	public void setTestCases(List<TestCase> testCases) {
		this.testCases = testCases;
	}
	
	public void addTestCase(TestCase testCase) {
		this.testCases.add(testCase);
	}
}
