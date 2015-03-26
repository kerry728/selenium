package com.learn.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilsDemo {
	
	private static Workbook wb;
	private static Sheet sheet;
	private static Cell cell;
	
	
	public static Object[][] getTableArray(String filepath, String sheetName) {
		
		String[][] tabArray = null;
		
		try {
			FileInputStream excelFile = new FileInputStream(filepath);
			wb = new XSSFWorkbook(excelFile);
			sheet = wb.getSheet(sheetName);
			
			int startRow = 1;
			int startCol = 1;
			int ci,cj;
			
			int totalRows = sheet.getLastRowNum();
			int totalCols = 2;
			tabArray = new String[totalRows][totalCols];
			ci = 0;
			
			for(int i = startRow; i <= totalRows; i++,ci++) {
				cj = 0;
				for(int j = startCol; j <= totalCols; j++,cj++) {
					tabArray[ci][cj] = getCellData(i,j);
					System.out.print(tabArray[ci][cj]+"  ");
				}
				
				System.out.println();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Cannot find filepath: "+filepath);
			e.printStackTrace();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Cannot find excelFile.");
			e.printStackTrace();
			
		}
		
		return tabArray;	
		 
	}
	
	public static String getCellData(int row, int col) {
		cell = sheet.getRow(row).getCell(col);
		int dataType = cell.getCellType();
		
		if(dataType == 3) {
			return "";
		} else {
			String cellData = cell.getStringCellValue();
			return cellData;
		}
	}
	
	public static void main(String[] args) {
		getTableArray("src/TestData/TestData.xlsx","Sheet1");
	}
}
