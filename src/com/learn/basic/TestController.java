package com.learn.basic;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

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
	
	@Parameters({"browser"})
	@BeforeTest
	public static void beforeTest(String browser) {
		
		if(browser.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if(browser.equals("Internet Explore")) {
			System.setProperty("webdriver.ie.driver", "C:\\Selenium\\IEDriver\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		nav = driver.navigate();
		nav.to(p.getProperty("URL"));
	}
	
	
	@BeforeClass
	public static void beforeClass() throws MalformedURLException {
//		DesiredCapabilities capability = DesiredCapabilities.firefox();
//		capability.setBrowserName("firefox");
//		capability.setPlatform(Platform.LINUX);
//		driver = new RemoteWebDriver(new URL("http://192.168.199.1:5555/wd/hub"),capability);
		//start firefox
//		driver = new FirefoxDriver();
	
		
		//start IE
//		System.setProperty("webdriver.ie.driver", "C:\\Selenium\\IEDriver\\IEDriverServer.exe");
//		driver = new InternetExplorerDriver();
		
		//start Chrome
//		System.setProperty("webdriver.chrome.driver","C:\\Selenium\\ChromeDriver\\chromedriver.exe");
//		driver = new ChromeDriver();
//		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		nav = driver.navigate();
//		nav.to(p.getProperty("URL"));
		
	}
	
	@AfterClass
	public static void afterClass() {	
		//driver.close();
	}
	
	@AfterSuite
	public static void afterSuite() {
		//driver.quit();
	}
	
	
}
