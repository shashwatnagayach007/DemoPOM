package com.Astegic.Utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class WorkbookUtil {

	public static Sheet sh;
	private static Workbook wb;
	private static Cell cell;
	private static Row row;

	// This method is to set the File path and to open the Excel file, Pass
	// Excel excel file name with it's Path and Sheet name as Arguments to this
	// method
	public static void getWorkbookSheet(String excelfilename, String SheetName)
			throws Exception {
		try {

			FileInputStream ExcelFile = new FileInputStream(excelfilename);
			// Open the Workbook file
			wb = WorkbookFactory.create(ExcelFile);
			// Access the required test data sheet
			sh = wb.getSheet(SheetName);
			// Log.info("Excel sheet opened");
		} catch (Exception e) {
			throw (e);
		}
	}

	// This method is to read the test data from the Excel cell, in this we are
	// passing parameters as Row num and Col num
	public static String getCellData(int RowNum, int ColNum) throws Exception {
		try {
			cell = sh.getRow(RowNum).getCell(ColNum);
			String CellData = cell.getStringCellValue();
			return CellData;
		} catch (Exception e) {
			return "";
		}
	}

	public static int getLastRowNum() throws Exception {
		try {
			// Getting last row Number Count
			return sh.getLastRowNum();
		} catch (Exception e) {
			throw (e);
		}
	}

	// This method is to write in the Excel cell, Row num and Col num are the
	// parameters
	@SuppressWarnings("static-access")
	public static void setWorkbookSheet(int RowNum, int ColNum,
			String excelfilename, String Result) throws Exception {
		try {
			row = sh.getRow(RowNum);
			cell = row.getCell(ColNum, Row.RETURN_BLANK_AS_NULL);
			if (cell == null) {
				cell = row.createCell(ColNum);
				cell.setCellValue(Result);
			} else {
				cell.setCellValue(Result);
			}
			// Constant variables Test Data path and Test Data file name
			FileOutputStream fileOut = new FileOutputStream(excelfilename);
			wb.write(fileOut);
			fileOut.flush();
			fileOut.close();
		} catch (Exception e) {
			throw (e);
		}
	}

}
