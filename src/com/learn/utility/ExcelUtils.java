package com.learn.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelUtils {
	
	private static Workbook wb;
	private static Sheet excelSheet;
	private static Cell cell;
	private static Row row;
	
	
	public static void setExcelFile(String filePath, String sheet) {
		
		try {
			FileInputStream fis = new FileInputStream(filePath);
			wb = new XSSFWorkbook(fis);
			excelSheet = wb.getSheet(sheet);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Cannot find filePath: "+filePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String getCellData(int rowNum, int colNum) {
		
		cell = excelSheet.getRow(rowNum).getCell(colNum);
		int cellType = cell.getCellType();
		
		String cellValue = getCellValue(cell, cellType);
		//System.out.println(cellValue);
		return cellValue;
	
	}
	
	
	public static String getCellValue(Cell cell, int cellType) {
		
		String result = null;
		
		switch(cellType) {
		
		case 0 : result = String.valueOf(cell.getNumericCellValue());
		break;
		
		case 1 : result = cell.getStringCellValue();
		break;
		
		case 2 : result = "This is formula, not matched.";
		break;
		
		case 3 : result = "";
		break;
		
		case 4 : result = "This is boolean type";
		break;
		
		case 5 :  result = "This is error.";
		break;
		
		default : result = "";
		
		}
		
		return result;
	}
	
	public static void setCellData(String result, int rowNum, int colNum) throws IOException {
		
		row = excelSheet.getRow(rowNum);
		cell = row.getCell(colNum,row.RETURN_BLANK_AS_NULL);
		
		if(cell == null) {
			cell = row.createCell(colNum);
			cell.setCellValue(result);
		} else {
			cell.setCellValue(result);
		}
		
		FileOutputStream fos = new FileOutputStream("src/TestData/TestData.xlsx");
		wb.write(fos);
		fos.flush();
		fos.close();
	}
	
	public static void main(String[] args) throws IOException {
		setExcelFile("src/TestData/TestData.xlsx","Sheet1");
		int row = 0;
		int col = 0;
		setCellData("pass",1,3);
		for(row = 0; row <= excelSheet.getLastRowNum(); row++) {
			for(col = 0; col < excelSheet.getRow(row).getLastCellNum(); col++) {
				System.out.print(getCellData(row,col)+"   ");
			}
			System.out.println();
		}
		
	}
}
