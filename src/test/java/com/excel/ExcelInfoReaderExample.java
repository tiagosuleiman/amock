package com.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * A program demonstrates reading other information of workbook, sheet and cell.
 * @author www.codejava.net
 *
 */
public class ExcelInfoReaderExample {


	public static void main(String[] args) throws IOException {
		String excelFilePath = "/home/suleiman/dev/csv/Books.xlsx";
		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
		
		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet sheet = workbook.getSheetAt(0);
		String sheetName = sheet.getSheetName();
		
		System.out.println("Sheet name = " + sheetName);
		
		int numberOfSheets = workbook.getNumberOfSheets();
		for (int i = 0; i < numberOfSheets; i++) {
			Sheet aSheet = workbook.getSheetAt(i);
			System.out.println(aSheet.getSheetName());
		}
		
		Comment cellComment = sheet.getCellComment(2, 2);
		System.out.println("comment: " + cellComment.getString());
		
		workbook.close();
		inputStream.close();
	}

}
