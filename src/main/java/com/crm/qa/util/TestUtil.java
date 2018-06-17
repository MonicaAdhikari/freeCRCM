package com.crm.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.crm.qa.base.TestBase;

public class TestUtil extends TestBase {
	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 10;
	String fileExtensionName;
	static String currentDir = System.getProperty("user.dir");
	public static String TESTDATA_SHEET_PATH = currentDir
			+ "src\\main\\java\\com\\crm\\qa\\testdata\\FreeCRMTestData.xlsx";
	static FileInputStream ipfile = null;
	static Workbook book;
	static Sheet sheet;

	public void switchToFrame() {
		driver.switchTo().frame("mainpanel");
	}

	// data driven approach
	// public static Object[][] getTestDataFromExcel(String filePath, String
	// fileName, String sheetName){
	public static Object[][] getTestDataFromExcel(String sheetName) {

		try {
			ipfile = new FileInputStream(TESTDATA_SHEET_PATH);

			// Find the file extension by splitting file name in substring and getting only
			// extension name
			// fileExtensionName = fileName.substring(TESTDATA_SHEET_PATH.indexOf("."));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			book = WorkbookFactory.create(ipfile);
			/*
			 * // Check condition if the file is xlsx file if
			 * (fileExtensionName.equals(".xlsx")) { // If it is xlsx file then create
			 * object of XSSFWorkbook class // guru99Workbook = new
			 * XSSFWorkbook(inputStream); } // Check condition if the file is xls file else
			 * if (fileExtensionName.equals(".xls")) { // If it is xls file then create
			 * object of XSSFWorkbook class guru99Workbook = new HSSFWorkbook(inputStream);
			 * }
			 */
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Read sheet inside the workbook by its name
		sheet = book.getSheet(sheetName);

		// Find number of rows in excel file
		int rowCount = sheet.getLastRowNum();
		// int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum(); //old
		// System.out.println("rowCount = " + rowCount);

		// Find number of columns in excel file
		int colCount = sheet.getRow(0).getLastCellNum();
		// System.out.println("colCount = " + colCount);

		Object[][] data = new Object[rowCount][colCount];

		// Create a loop over all the rows of excel file to read it
		for (int i = 0; i < rowCount; i++) {
			// System.out.println("i = " + i);
			// Create a loop to print cell values in a row
			for (int k = 0; k < colCount; k++) {
				// System.out.println("k = " + k);
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
				// System.out.println("data[i][k] = " + data[i][k]);
			}
		}
		return data;
	}

	// public static void writeExcel(String filePath,String fileName,String
	// sheetName,String[] dataToWrite) throws IOException{
	public static void writeExcel(String sheetName, String[] dataToWrite) throws IOException {
		// Create a object of File class to open xlsx file
		//File file = new File(filePath + "\\" + fileName);
		try {
			
		// Create an object of FileInputStream class to read excel file
				ipfile = new FileInputStream(TESTDATA_SHEET_PATH);

				// Find the file extension by splitting file name in substring and getting only
				// extension name
				// fileExtensionName = fileName.substring(TESTDATA_SHEET_PATH.indexOf("."));

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			try {
				book = WorkbookFactory.create(ipfile);
			/*
			 * // Check condition if the file is xlsx file if
			 * (fileExtensionName.equals(".xlsx")) { // If it is xlsx file then create
			 * object of XSSFWorkbook class // guru99Workbook = new
			 * XSSFWorkbook(inputStream); } // Check condition if the file is xls file else
			 * if (fileExtensionName.equals(".xls")) { // If it is xls file then create
			 * object of XSSFWorkbook class guru99Workbook = new HSSFWorkbook(inputStream);
			 * }
			 */
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Read sheet inside the workbook by its name
		sheet = book.getSheet(sheetName);

		// Find number of rows in excel file
		int rowCount = sheet.getLastRowNum();
		// int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum(); //old
		// System.out.println("rowCount = " + rowCount);

		// Get the first row from the sheet
		//Row row = sheet.getRow(0);

		// Find number of columns in excel file
		int colCount = sheet.getRow(0).getLastCellNum();
		// System.out.println("colCount = " + colCount);

		// Create a new row and append it at last of sheet
		Row newRow = sheet.createRow(rowCount + 1);

		// Create a loop over the cell of newly created Row
		for (int j = 0; j < colCount; j++) {

			// Fill data in row
			Cell cell = newRow.createCell(j);
			cell.setCellValue(dataToWrite[j]);
		}

		// Close input stream
		ipfile.close();

		// Create an object of FileOutputStream class to create write data in excel file
		FileOutputStream opfile = new FileOutputStream(TESTDATA_SHEET_PATH);
				
		// write data in the excel file
		book.write(opfile);
	
		// close output stream
		opfile.close();

	}

	public static void takeScreenshotAtEndOfTest() throws IOException {
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File scrFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

		/*
		 * if(osName.starsWith("Mac")) { FileUtils.copyFile(scrFile, new File(currentDir
		 * + "/sreenshots/" + System.currentTimeMillis() + ".png"));
		 * 
		 * }
		 */

		try {
			FileUtils.copyFile(scrFile, new File(currentDir + "/sreenshots/" + System.currentTimeMillis() + ".png"));
		} catch (IOException e) {
			System.out.println("e::::::::::" + e);
		}

	}

}
