package com.learn.basic;

import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.Test;


public class ReporterLogs {
	
	private static WebDriver driver;
	private static Logger log = Logger.getLogger(Log.class.getClass());
	
	@Test
	public static void reportFunction() {
		
		DOMConfigurator.configure("src/log4j.xml");
		driver = new  FirefoxDriver();
		log.info("New FirefoxDriver is inistiated....");
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		log.info("Implicit wait applied on the driver for 10 seconds");
		
		Navigation nav = driver.navigate();
		nav.to("http://www.JD.com");
		log.info("JD web application is launched!");
		
		Reporter.log("JD web application launched successfully....");
		
		driver.findElement(By.partialLinkText("ÇëµÇÂ¼")).click();
		log.info("Click ÇëµÇÂ¼  and try to login...");
		
		driver.findElement(By.cssSelector("input#loginname")).sendKeys("kerryhong728");
		log.info("Enter the username in the login dialog....");
		
		driver.findElement(By.cssSelector("input#nloginpwd")).sendKeys("qh728226");
		log.info("Enter the password for the account....");
		
		driver.findElement(By.id("loginsubmit")).click();
		log.info("After input username and password, click submit button.... ");
		
		Reporter.log("Login successfully....");
		
	}
}
