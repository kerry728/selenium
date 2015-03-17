package com.learn.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.opera.core.systems.scope.protos.ExecProtos.ActionList.Action;

public class CommonActions {
	
	public static Workbook wb;
	public static Sheet sheet;
	public static Row row;
	public static Cell cell;
	public static OutputStream fileout;
	
//	public static void createWorkBook() throws IOException {
//		//create a excel
//		wb = new HSSFWorkbook();
//		//create a new sheet which the name is: "basicLogin"
//		sheet = wb.createSheet("basicLogin");
//		//create Row in line (1) in above sheet
//		row = sheet.createRow(0);
//		//create two Columns in above Row
//		cell = row.createCell(0);
//		cell.setCellValue("username");
//		row.createCell(1).setCellValue("password");
//		
//		fileout = new FileOutputStream("src"+File.separator+"JDData.xls");
//		wb.write(fileout);
//		fileout.close();
//
//	}
//	
//	public static void readWorkBook() throws IOException {
//		//read as ×Ö½ÚÁ÷
//		InputStream inputStream = new FileInputStream("src" + File.separator + "JDData.xls");
//		Workbook wb = new HSSFWorkbook(inputStream);
//		Sheet sheet = wb.getSheetAt(0);
//		for (Row row : sheet) {
//			for (Cell cell : row) {
//				System.out.print(cell + "  ");
//			}
//			System.out.println();
//		}
//		wb.close();
//		inputStream.close();
//	}
//	
	//switch to the new window to handle elements
	public static void locateNewWinHandler(String winHandler, WebDriver driver) {
		
		Set<String> allWinHandler = new HashSet<String>(driver.getWindowHandles());
		allWinHandler.remove(winHandler);
		TestController.driver.switchTo().window(allWinHandler.iterator().next());
		
	}
	
	//take screenshot
	public static void takeScreenshot() throws IOException {
		
		File srcFile = ((TakesScreenshot)TestController.driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile, new File("c:\\temp\\screenshot.png"));
	}
	
	public static void mouseOverAndClick(WebElement element, WebDriver driver) {
		
		Actions builder = new Actions(driver);
		Actions hoverClick = builder.moveToElement(element).click();
		hoverClick.build().perform();
	}
	
}	
