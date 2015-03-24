package com.learn.basic;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BasicFunctionsImplement extends TestController{
	
	//click an element which need mouse over another element then can shows
	@Test
	public static void mouseOver() throws InterruptedException {
		
		WebElement mainElement = driver.findElement(By.id("biz-service"));            
		CommonActions.mouseOverAndClick(mainElement, driver);		
		driver.findElement(By.xpath(".//*[@id='biz-service']/div/div[2]/a")).click();
		
	}
	
	//This function is to check the invisible element in the page
	@Test
	public static void wantFeedback() throws IOException {
		
		WebElement feedback = driver.findElement(By.className("survey"));
		
		JavascriptExecutor js = (JavascriptExecutor)TestController.driver;
		js.executeScript("arguments[0].click();", feedback);
		CommonActions.locateNewWinHandler(TestController.driver.getWindowHandle(),TestController.driver);
		Assert.assertEquals("京东首页调查问卷", TestController.driver.getTitle());

		//System.out.println(TestController.driver.findElement(By.id("question2367")).getText());
		WebElement checkRadio1 = TestController.driver.findElement(By.id("answer95425X91X23673"));
		checkRadio1.click();
		Assert.assertTrue(checkRadio1.isSelected());
		
		//second question
		//select the radios for the second question
		TestController.driver.findElement(By.id("answer95425X91X97941-5")).click();
		TestController.driver.findElement(By.id("answer95425X91X97942-4")).click();
		TestController.driver.findElement(By.id("answer95425X91X97943-3")).click();
		TestController.driver.findElement(By.id("answer95425X91X97944-2")).click();
		TestController.driver.findElement(By.id("answer95425X91X97945-1")).click();
		
		//fourth question
		TestController.driver.findElement(By.id("answer95425X91X23715")).click();
		TestController.driver.findElement(By.id("answer95425X91X2371other")).sendKeys("就是不喜欢");
		Assert.assertTrue(
				TestController.driver.findElement(By.id("answer95425X91X2371othercbox")).isSelected(),
				"The checkbox is not selected even I input the values in the text box!!");
		
		//click submit
		TestController.driver.findElement(By.id("movesubmitbtn")).click();
		Assert.assertEquals(TestController.driver.findElement(By.cssSelector("#question2369 .errormandatory")).getText(), "本题必答.");
		
		//take screenshot
		CommonActions.takeScreenshot("feedbackScr");
		
	}
	
}
