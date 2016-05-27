package com.Astegic.Utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class WorkbookData {

	private static WorkbookData instance = null;
	private static FileInputStream fis;
	private static Workbook wb;
	private static Sheet sh;
	private static Cell cell;
	private static Row row;

	public static WorkbookData getInstance() {
		if (instance == null) {
			instance = new WorkbookData();
			System.out.println("NEW Instance");
		}
		return instance;
	}

	@SuppressWarnings("static-access")
	public String getTestData(String workbookName, String SheetName,
			int rowNum, int colNum) {
		String cellData = null;
		try {
			fis = new FileInputStream(workbookName);
			wb = WorkbookFactory.create(fis);
			sh = wb.getSheet(SheetName);
			cell = sh.getRow(rowNum).getCell(colNum);
			int type = cell.getCellType();
			if (type == cell.CELL_TYPE_STRING)
				cellData = cell.getStringCellValue();
			else if (type == cell.CELL_TYPE_NUMERIC) {
				double douData = cell.getNumericCellValue();
				int intData = (int) douData;
				cellData = String.valueOf(intData).toString();
			} else if (type == cell.CELL_TYPE_BLANK) {
				System.out.println("Please add test data in" + " WorkBook:- "
						+ workbookName + " Sheet:- " + SheetName + " at "
						+ " Row Number:- " + rowNum + " Column Number:- "
						+ colNum);
				return "";
			}

		} catch (EncryptedDocumentException | InvalidFormatException
				| IOException e) {
			e.getStackTrace();
		}
		return cellData;
	}

	public void setTestResult(int rowNum, int colNum, String workbookName,
			String result) {

		try {
			row = sh.getRow(rowNum);
			cell = row.getCell(colNum, Row.RETURN_BLANK_AS_NULL);
			if (cell == null) {
				cell = row.createCell(colNum);
				cell.setCellValue(result);
			} else {
				cell.setCellValue(result);
			}
			// Constant variables Test Data path and Test Data file name
			FileOutputStream fileOut = new FileOutputStream(workbookName);
			wb.write(fileOut);
			fileOut.flush();
			fileOut.close();
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	public static int getLastRowNum(String workbookName, String SheetName) {
		// Open the Excel file
		try {
			fis = new FileInputStream(workbookName);
			wb = WorkbookFactory.create(fis);
		} catch (EncryptedDocumentException | InvalidFormatException
				| IOException e) {
			e.printStackTrace();
		}
		// Access the required test data sheet
		Sheet sh = wb.getSheet(SheetName);
		return sh.getLastRowNum();
	}

}
