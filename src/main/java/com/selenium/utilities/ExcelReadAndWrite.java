/***Utilities containing common reusable functions */

package com.selenium.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * Class to read and write data on Excel sheets.
 */
public class ExcelReadAndWrite {
	static Workbook workBook;
	static Sheet workSheet;
	private static String path;

	/**
	 * Constructor
	 */
	public ExcelReadAndWrite(String path, String sheetName) {
		ExcelReadAndWrite.path = path;
		try {
			FileInputStream file = new FileInputStream(path);
			workBook = WorkbookFactory.create(file);
			workSheet = workBook.getSheet(sheetName);
		} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * To read data from Excel sheet
	 */
	public String getCellData(int rowNum, int colNum) {
		Cell cell;

		try {
			cell = workSheet.getRow(rowNum).getCell(colNum);
			String cellData = cell.toString();
			return cellData;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

	}

	/**
	 * To write data on Excel sheet
	 */
	public void setCellData(String value, int rowNum, int colNum) {
		Cell cell;
		Row row;

		try {
			row = workSheet.getRow(rowNum);

			if (row == null) {
				row = workSheet.createRow(rowNum);

			}

			cell = row.getCell(colNum);

			if (cell == null) {
				cell = row.createCell(colNum);
				cell.setCellValue(value);

			} else {
				cell.setCellValue(value);
			}

			FileOutputStream fileOut = new FileOutputStream(path);
			workBook.write(fileOut);
			fileOut.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}