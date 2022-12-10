package Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FileReader {
	
	public static String getEnvURL(String fileDir) throws IOException {
		String url;
		try{
			File file = new File(fileDir);
			//FileOutputStream outputStream = new FileInputStream(file);
			FileInputStream inputStream = new FileInputStream(file);
			XSSFWorkbook wb = new XSSFWorkbook(inputStream);
			XSSFSheet sh = wb.getSheet("Environment");
			XSSFCell cell = (XSSFCell) sh.getRow(1).getCell(1);
			url = cell.getStringCellValue();
			inputStream.close();
			return url;
		} catch (IOException e){
			System.out.println(e);
		}
		return null;
	}
	
	public String getTestSuite(String fileDir) {
		
		
		return null;
	}
	
	public String getTestCase(String fileDir) {
		
		
		return null;
	}
	
	public String getTestAction(String fileDir) {
		
		
		return null;
	}
}
