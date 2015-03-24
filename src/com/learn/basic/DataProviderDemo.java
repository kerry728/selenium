package com.learn.basic;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderDemo {
	
	public static WebDriver driver;
	public static Navigation nav;
	
	@DataProvider(name = "loginCheck")
	public static Object[][] AuthuationCheck() {
		
		//Object[][] testObjArray = {{"test1","test2"},{"nihfo","qtestdemo"},{"×Ô¶¯»¯²âÊÔ","ÃÜÂëdemo"}};
		Object[][] testObjArray = CommonActions.getExcelData("src/TestData/dataDemo.xlsx", "data1");
		return testObjArray;
	}
	
	@Test(dataProvider = "loginCheck")
	public void loginCheck(String username, String password) {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		nav = driver.navigate();
		nav.to("http://www.JD.com");
		driver.findElement(By.partialLinkText("ÇëµÇÂ¼")).click();
		
		WebElement user = driver.findElement(By.id("loginname"));
		user.clear();
		user.sendKeys(username);
		WebElement pwd = driver.findElement(By.id("nloginpwd"));
		pwd.clear();
		pwd.sendKeys(password);
		
		driver.findElement(By.id("loginsubmit")).click();
		Assert.assertEquals(driver.findElement(By.className("link-user")).getText(), username);
		
		driver.quit();
		
	}
}
