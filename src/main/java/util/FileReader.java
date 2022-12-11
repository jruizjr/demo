package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.*;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import model.TestAction;
import model.TestCase;
import model.TestStep;

public class FileReader {
	
	public String getEnvURL(String fileDir) throws IOException {
		String url;
		try{
			File file = new File(fileDir);
			FileInputStream inputStream = new FileInputStream(file);
			XSSFWorkbook wb = new XSSFWorkbook(inputStream);
			XSSFSheet sh = wb.getSheet("Environment");
			XSSFCell cell = (XSSFCell) sh.getRow(1).getCell(1);
			url = cell.getStringCellValue();
			wb.close();
			inputStream.close();
			return url;
		} catch (IOException e){
			System.out.println(e);
		}
		return null;
	}
	
	public String getTestSuiteName(String fileDir, int row) throws IOException {
		String testSuite;
		try{
			File file = new File(fileDir);
			FileInputStream inputStream = new FileInputStream(file);
			XSSFWorkbook wb = new XSSFWorkbook(inputStream);
			XSSFSheet sh = wb.getSheet("TestSuite");
			XSSFCell cell = sh.getRow(row).getCell(0);
			testSuite = cell.getStringCellValue();
			wb.close();
			inputStream.close();
			return testSuite;		
		} catch (IOException e){
			System.out.println(e);
			return null;
		}
	}
	
	public TestAction getTestAction(String fileDir, TestStep testStep, int rowTA) throws IOException {
		TestAction testAction = new TestAction();
		try{
			File file = new File(fileDir);
			FileInputStream inputStream = new FileInputStream(file);
			XSSFWorkbook wb = new XSSFWorkbook(inputStream);
			XSSFSheet sh = wb.getSheet("TestSteps");
			
			testAction.setPageName(sh.getRow(rowTA).getCell(1).getStringCellValue());
			testAction.setElementName(sh.getRow(rowTA).getCell(2).getStringCellValue());
			testAction.setAction(sh.getRow(rowTA).getCell(3).getStringCellValue());
			testAction.setData(sh.getRow(rowTA).getCell(4).getStringCellValue());
			wb.close();
			inputStream.close();
			return testAction;		
		} catch (IOException e){
			System.out.println(e);
			return null;
		}
	}
	
	public TestCase getTestCase(String fileDir, int rowTC) throws IOException {
		TestCase testCase;
		try{
			File file = new File(fileDir);
			FileInputStream inputStream = new FileInputStream(file);
			XSSFWorkbook wb = new XSSFWorkbook(inputStream);
			XSSFSheet sh = wb.getSheet("TestSuite");
			testCase = new TestCase(sh.getRow(rowTC).getCell(2).getStringCellValue());
			wb.close();
			inputStream.close();
			return testCase;		
		} catch (IOException e){
			System.out.println(e);
			return null;
		}
	}
	
	public int getNoOfSteps (String fileDir, TestCase testCase) throws IOException {
		int rowCount = 0;
		int colNum = 0;
		
		String temp = "";
		try{
			File file = new File(fileDir);
			FileInputStream inputStream = new FileInputStream(file);
			XSSFWorkbook wb = new XSSFWorkbook(inputStream);
			wb.setMissingCellPolicy(MissingCellPolicy.CREATE_NULL_AS_BLANK);
			XSSFSheet sh = wb.getSheet("TestCases");
			int lastRowNum = sh.getLastRowNum();
			
			for(Row row:sh) {
				for(Cell cell : row)
                {  
					switch(cell.getCellType())
	                {
	                case STRING:
	                	temp = cell.getStringCellValue();
	                    if(temp.equals(testCase.getTestCaseName())==true) {
	                        colNum = cell.getColumnIndex();
	                        break;
	                    }
	                default:
	                	break;
	                }
				}
			}
			int tempRow = 1;
			boolean chk = true;
			while (chk){
				if(tempRow <= lastRowNum) {
					XSSFCell cell = sh.getRow(tempRow).getCell(colNum, MissingCellPolicy.CREATE_NULL_AS_BLANK);
					if(cell == null || cell.getStringCellValue().equalsIgnoreCase("") == true) {
						chk = false;
					}
				} else break;
				rowCount++;tempRow++;
			}
			wb.close();
			inputStream.close();
			return rowCount;		
		} catch (IOException e){
			System.out.println(e);
			return rowCount;
		}
	}
	
	public int getStartRowAction (String fileDir, TestStep testStep) throws IOException {
		int rowCount = 0;
		int rowNum = 0;
		
		String temp = "";
		try{
			File file = new File(fileDir);
			FileInputStream inputStream = new FileInputStream(file);
			XSSFWorkbook wb = new XSSFWorkbook(inputStream);
			wb.setMissingCellPolicy(MissingCellPolicy.CREATE_NULL_AS_BLANK);
			XSSFSheet sh = wb.getSheet("TestSteps");
			for(Row row:sh) {
				for(Cell cell : row)
                {  
					switch(cell.getCellType())
	                {
	                case STRING:
	                	temp = cell.getStringCellValue();
	                    if(temp.equals(testStep.getStepName())==true) {
	                        rowNum = cell.getRowIndex();
	                        break;
	                    }
	                default:
	                	break;
	                }
				}
			} 
			wb.close();
			inputStream.close();
			return rowNum;
		} catch (IOException e){
			System.out.println(e);
			return rowCount;
		}
	}
	
	public int getNoOfAction (String fileDir, TestStep testStep, int rowStart) throws IOException {
		int rowCount = 0;

		try{
			File file = new File(fileDir);
			FileInputStream inputStream = new FileInputStream(file);
			XSSFWorkbook wb = new XSSFWorkbook(inputStream);
			wb.setMissingCellPolicy(MissingCellPolicy.CREATE_NULL_AS_BLANK);
			XSSFSheet sh = wb.getSheet("TestSteps");
			int lastRowNum = sh.getLastRowNum();
			
			int tempRow = rowStart+1;
			boolean chk = true;
			while (chk){
				if(tempRow <= lastRowNum) {
					XSSFCell cell = sh.getRow(tempRow).getCell(0, MissingCellPolicy.CREATE_NULL_AS_BLANK);
					if(cell.getStringCellValue().equalsIgnoreCase(testStep.getStepName()) == false 
							&& cell.getStringCellValue().equalsIgnoreCase("") == false) {
						chk = false;
					}
				} else break;
				rowCount++;tempRow++;
			}
			wb.close();
			inputStream.close();
			return rowCount;		
		} catch (IOException e){
			System.out.println(e);
			return rowCount;
		}
	}
	
	public TestStep getTestStep(String fileDir, TestCase testCase, int rowTS) throws IOException {
		TestStep testStep;
		int colNum = 0;
		String temp = "";
		try{
			File file = new File(fileDir);
			FileInputStream inputStream = new FileInputStream(file);
			XSSFWorkbook wb = new XSSFWorkbook(inputStream);
			XSSFSheet sh = wb.getSheet("TestCases");
			
			for(Row row:sh) {
				for(Cell cell : row)
                {  
					switch(cell.getCellType())
	                {
					
	                case STRING:
	                	temp = cell.getStringCellValue();
	                    if(temp.equals(testCase.getTestCaseName())==true) {
	                        
	                        colNum = cell.getColumnIndex();
	                        break;
	                    }
	                    
	                default:
	                	break;
	                }
				}
			}	
			testStep = new TestStep(sh.getRow(rowTS).getCell(colNum).getStringCellValue());
			wb.close();
			inputStream.close();
			return testStep;		
		} catch (IOException e){
			System.out.println(e);
			return null;
		}
	}
	
	public int getTestSuiteLastRow(String fileDir) throws IOException {
		int lastRow;
		try{
			File file = new File(fileDir);
			FileInputStream inputStream = new FileInputStream(file);
			XSSFWorkbook wb = new XSSFWorkbook(inputStream);
			XSSFSheet sh = wb.getSheet("TestSuite");
			lastRow = sh.getLastRowNum();
			wb.close();
			inputStream.close();
			return lastRow;		
		} catch (IOException e){
			System.out.println(e);
			return 0;
		}	
	}
	
	public int getTestCaseLastRow(String fileDir) throws IOException {
		int lastRow;
		try{
			File file = new File(fileDir);
			FileInputStream inputStream = new FileInputStream(file);
			XSSFWorkbook wb = new XSSFWorkbook(inputStream);
			XSSFSheet sh = wb.getSheet("TestCases");
			lastRow = sh.getLastRowNum();
			wb.close();
			inputStream.close();
			return lastRow;		
		} catch (IOException e){
			System.out.println(e);
			return 0;
		}
	}

	public int getTestStepLastRow(String fileDir) throws IOException {
		int lastRow;
		try{
			File file = new File(fileDir);
			FileInputStream inputStream = new FileInputStream(file);
			XSSFWorkbook wb = new XSSFWorkbook(inputStream);
			XSSFSheet sh = wb.getSheet("TestSteps");
			lastRow = sh.getLastRowNum();
			wb.close();
			inputStream.close();
			return lastRow;		
		} catch (IOException e){
			System.out.println(e);
			return 0;
		}
	}

	public String getActionElement(String fileDir, String element) throws IOException {
		String elementType;
		int rowNum = 0;
		try{
			File file = new File(fileDir);
			FileInputStream inputStream = new FileInputStream(file);
			XSSFWorkbook wb = new XSSFWorkbook(inputStream);
			XSSFSheet sh = wb.getSheet("AppElements");
			for(Row row:sh) {
				for(Cell cell : row)
                {  
					switch(cell.getCellType())
	                {
	                case STRING:
	                	if(cell.getStringCellValue().equals(element) == true)
	                    {
	                        rowNum = row.getRowNum();
	                    }
	                default:
	                	break;
	                }
				}
			}
			XSSFCell cell = sh.getRow(rowNum).getCell(2);
			elementType = cell.getStringCellValue();
			wb.close();
			inputStream.close();
			return elementType;		
		} catch (IOException e){
			System.out.println(e);
			return null;
		}
	}
	
	public String getElementLocator(String fileDir, String element) throws IOException {
		String locator;
		int rowNum = 0;
		try{
			File file = new File(fileDir);
			FileInputStream inputStream = new FileInputStream(file);
			XSSFWorkbook wb = new XSSFWorkbook(inputStream);
			XSSFSheet sh = wb.getSheet("AppElements");
			for(Row row:sh) {
				for(Cell cell : row)
                {  
					switch(cell.getCellType())
	                {
	                case STRING:
	                    if(cell.getStringCellValue().equals(element) == true)
	                    {
	                        rowNum = row.getRowNum();
	                    }
	                default:
	                	break;
	                }
				}
			}
			XSSFCell cell = sh.getRow(rowNum).getCell(3);
			locator = cell.getStringCellValue();
			wb.close();
			inputStream.close();
			return locator;		
		} catch (IOException e){
			System.out.println(e);
			return null;
		}
	}
}
