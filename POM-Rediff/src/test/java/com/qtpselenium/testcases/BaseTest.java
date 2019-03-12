package com.qtpselenium.testcases;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.qtpselenium.pom.util.Constants;
import com.qtpselenium.pom.util.Xls_Reader;

public class BaseTest {

	ExtentReports rep;
	ExtentTest test;
	WebDriver driver;
	Xls_Reader xls = new Xls_Reader("D:\\Common\\reports\\Rediff Data.xlsx");
	
	public void openBrowser(String browser) {
		if(Constants.GRID_RUN.equals("Y")) {
			// grid invoke
			DesiredCapabilities cap=null;
			if(browser.equals("Mozilla")){
				cap = DesiredCapabilities.firefox();
				cap.setBrowserName("firefox");
				cap.setJavascriptEnabled(true);
				cap.setPlatform(org.openqa.selenium.Platform.WINDOWS);
				
			}else if(browser.equals("Chrome")){
				 cap = DesiredCapabilities.chrome();
				 cap.setBrowserName("chrome");
				 cap.setPlatform(org.openqa.selenium.Platform.WINDOWS);
			}
			
			try {
				driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			if(browser.equals("Mozilla")) {
				//driver= new FirefoxDriver();
			}else if(browser.equals("Chrome"))
				driver= new ChromeDriver();
			else if(browser.equals("IE"))
				driver= new InternetExplorerDriver();
		}

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

	}


}
