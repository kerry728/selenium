package com.learn.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class CommonActions {
	
	public static Workbook wb;
	public static Sheet sheet;
	public static Row row;
	public static Cell cell;
	
	
	//switch to the new window to handle elements
	public static void locateNewWinHandler(String winHandler, WebDriver driver) {
		
		Set<String> allWinHandler = new HashSet<String>(driver.getWindowHandles());
		allWinHandler.remove(winHandler);
		TestController.driver.switchTo().window(allWinHandler.iterator().next());
		
	}
	
	//take screenshot
	public static void takeScreenshot(String path) throws IOException {
		
		String wholePath = "C:\\temp\\"+path+".png";
		File srcFile = ((TakesScreenshot)TestController.driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile, new File(wholePath));
	}
	
	public static void mouseOverAndClick(WebElement element, WebDriver driver) {
		
		Actions builder = new Actions(driver);
		Actions hoverClick = builder.moveToElement(element).click();
		hoverClick.build().perform();
	}
	
	public static Object[][] getExcelData(String filePath, String SheetName) {
		
		String[][] toArray = null;
	
		try {
			FileInputStream excelFile = new FileInputStream(filePath);
			wb = new XSSFWorkbook(excelFile);
			sheet = wb.getSheet(SheetName);
			int totalRowNum = sheet.getLastRowNum();
			int totalColumnNum = sheet.getRow(0).getLastCellNum() - 1;
			System.out.println("totalRowNum  "+totalRowNum);
			System.out.println("totalColumnNum  "+totalColumnNum);
			toArray = new String[totalRowNum][totalColumnNum];
			int ci = 0;
			for(int i = 1; i <= totalRowNum; ci++,i++) {
				int cj = 0;
				for(int j = 1; j <= totalColumnNum; cj++,j++) {
					toArray[ci][cj] = getCellData(i,j);
					System.out.println("ci "+ci+" cj "+cj+"  "+ toArray[ci][cj]);
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Cannot find the filePath "+filePath);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Cannot find the sheet." +SheetName);
		}
		
		return toArray;
		
	}
	
	public static String getCellData(int rowNum, int colNum) {
		
		cell = sheet.getRow(rowNum).getCell(colNum);
		int dataType = cell.getCellType();
		System.out.println(dataType);
		if(dataType == 3) 
			return "";
		else {
			String cellData = cell.getStringCellValue();
			return cellData;
		}
	}
	
}	
