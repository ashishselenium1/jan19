package com.qtpselenium.pom.pages;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.qtpselenium.pom.util.ExtentManager;

public class BasePage {

	WebDriver driver;
	ExtentTest test;
	
	public BasePage(WebDriver dr) {
		driver  = dr; // common place where driver is init & this will called everytime you create a page object
		// screenshot of everypage
	}
	
	public void setReport(ExtentTest t) {
		test=t;
	}
	
	public void validateTitle(String expectedTitle) {
		String actualTitle = driver.getTitle();
		if(!actualTitle.equals(expectedTitle)) {
			repFailure("Titles did not match. Got tile as "+ actualTitle);
		}
	}
	
	public boolean isElementPresent(String locator, String locatorStrGy) {
		List<WebElement> list=null;
		
		if(locatorStrGy.equals("xpath"))
			list = driver.findElements(By.xpath(locator));
		else if(locatorStrGy.equals("id"))
			list = driver.findElements(By.id(locator));
		else if(locatorStrGy.equals("name"))
			list = driver.findElements(By.name(locator));
		else if(locatorStrGy.equals("cssSelector"))
			list = driver.findElements(By.cssSelector(locator));
		
		if(list.size()==0)
			return false;
		else 
			return true;
		
	}
	
	public void repFailure(String errMsg) {
		// rep a failure in extent rep
		test.log(Status.FAIL, errMsg);
		// take screenshot
		takeScreenshot();
		// rep failure in testng
		Assert.fail(errMsg);
	}
	
	
	public void takeScreenshot(){
		// fileName of the screenshot
		Date d=new Date();
		String screenshotFile=d.toString().replace(":", "_")+".png";
		// take screenshot
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			// get the dynamic folder name
			FileUtils.copyFile(srcFile, new File(ExtentManager.screenshotFolderPath+screenshotFile));
			//put screenshot file in reports
			test.log(Status.INFO,"Screenshot-> "+ test.addScreenCaptureFromPath(ExtentManager.screenshotFolderPath+screenshotFile));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	
	
	
}
