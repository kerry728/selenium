package com.learn.basic;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

public class TestController {

	public static WebDriver driver;
	public static Navigation nav;
	public static Properties p;	
	
	@BeforeSuite
	public static void beforeInit() throws IOException {
		
		File file = new File("src"+File.separator+"info.properties");
		p = new Properties();
		Reader reader = new FileReader(file);
		p.load(reader);
		reader.close();
	}
	
	@BeforeClass
	public static void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		nav = driver.navigate();
		nav.to(p.getProperty("URL"));
		
	}
	
	@AfterClass
	public static void afterClass() {	
		driver.close();
	}
	
	@AfterSuite
	public static void afterSuite() {
		driver.quit();
	}
	
	
}
