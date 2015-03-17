package com.learn.basic;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginAndSearch extends TestController{

	//this function is to check the login if success
	@Parameters({"username","pwd"})
	@Test
	public void loginCheck(String username,String password) throws IOException {
		
		driver.findElement(By.partialLinkText("请登录")).click();
		driver.findElement(By.cssSelector("input#loginname")).sendKeys(username);
		driver.findElement(By.cssSelector("input#nloginpwd")).sendKeys(password);
		
		driver.findElement(By.id("loginsubmit")).click();
		
		String actualloginname = driver.findElement(By.className("link-user")).getText();
		Assert.assertEquals(actualloginname, username);
	}	
	
	//this function is to check the result if the search item is not exist in the  system
	@Test
	public void testSearch() {
		String key = p.getProperty("search1");
		
		driver.findElement(By.cssSelector("input#key")).sendKeys(key);
		driver.findElement(By.cssSelector("input.button[value=搜索]")).click();
		
		String result = driver.findElement(By.cssSelector("div.ns-content")).getText();
		
		Assert.assertEquals(result, String.format("抱歉，没有找到“%s”的搜索结果，为您推荐以下结果", key));
	}
	
	@Test
	public void testSearchAndOrder() {
		
		nav.back();		
		driver.findElement(By.cssSelector("input#key")).sendKeys(p.getProperty("search2"));
		driver.findElement(By.cssSelector("input.button[value=搜索]")).click();
		
		String results = driver.findElement(By.className("related-search")).getText();
		
		//String[] splitactualsearch2 = actualsearch2.split("\n");
		Assert.assertEquals(
				results.split("\n")[0],
				p.getProperty("wenjusearch"),
				"These results do not include: "+ p.getProperty("wenjusearch"));
		
		driver.findElement(By.className("btn-buy")).click();
		
		CommonActions.locateNewWinHandler(driver.getWindowHandle(), driver);
		
		driver.findElement(By.id("GotoShoppingCart")).click();
	}
}
