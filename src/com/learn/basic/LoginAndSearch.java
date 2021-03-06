package com.learn.basic;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
		//System.out.println(driver.getPageSource());
		Assert.assertEquals(actualloginname, username);
		
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.partialLinkText("我的订单"))).perform();
		action.contextClick().perform();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	//this function is to check the result if the search item is not exist in the  system
	
	@Test
	public void testSearch(String search1) {
		String key = p.getProperty("search1");
		
		driver.findElement(By.cssSelector("input#key")).sendKeys(key);
		driver.findElement(By.cssSelector("input.button[value=搜索]")).click();
		
		String result = driver.findElement(By.cssSelector("div.ns-content")).getText();
		
		Assert.assertEquals(result, String.format("抱歉，没有找到“%s”的搜索结果，为您推荐以下结果", search1));
		driver.quit();
	}
	
	@Test
	public void testSearchAndOrder() {
		
		//nav.back();		
		WebElement searchSecond = driver.findElement(By.cssSelector("input#key"));
		searchSecond.clear();
		
		searchSecond.sendKeys(p.getProperty("search2"));
		driver.findElement(By.cssSelector("input.button[value=搜索]")).click();
		
		String results = driver.findElement(By.className("related-search")).getText();
		System.out.println(results);
		
		//String[] splitactualsearch2 = actualsearch2.split("\n");
		Assert.assertTrue(results.contains(p.getProperty("wenjusearch")), "These results do not include: "+p.getProperty("wenjusearch"));
		
		driver.findElement(By.className("btn-buy")).click();
		
		CommonActions.locateNewWinHandler(driver.getWindowHandle(), driver);
		
		driver.findElement(By.id("GotoShoppingCart")).click();
	}
}
