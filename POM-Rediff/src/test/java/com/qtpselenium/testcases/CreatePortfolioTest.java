package com.qtpselenium.testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.session.FirefoxFilter;
import org.openqa.selenium.support.PageFactory;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.qtpselenium.pom.pages.LaunchPage;
import com.qtpselenium.pom.pages.LoginPage;
import com.qtpselenium.pom.pages.PortfolioDetails;
import com.qtpselenium.pom.util.DataUtil;
// Passing the driver among pages  - PageFactory Implementation
// Different outputs from 1 function
// Framework - driver.findElement - PageFactory Implementation
// Hardcoding the IDs and the Xpaths
import com.qtpselenium.pom.util.ExtentManager;
import com.qtpselenium.pom.util.Xls_Reader;

// validation of port creation
//reporting - log, screenshots
// reading data from xls


public class CreatePortfolioTest extends BaseTest{
	
	
	@BeforeMethod
	public void init() {
		rep = ExtentManager.getInstance("D:\\Common\\reports\\");
		test = rep.createTest("Create Portfolio Test");
	}
	
	@AfterMethod
	public void quit() {
		rep.flush();
	}
	
	
	@Test(dataProvider="getData")
	public void createPortFolioTest(String runmode, String browser, String portFolioName) {
		
		// check the runmode
		if(runmode.equals("N") | !DataUtil.isRunnable(xls, "CreatePortfolioTest")) {
			test.log(Status.SKIP, "Skipping the test as Runmode was set to NO");

			throw new SkipException("Skipping the test as Runmode was set to NO");
		}
		
		test.log(Status.INFO, "Creating Portfolio");
		openBrowser(browser);
		 
		test.log(Status.INFO, "opened browser");
		// create object of launch page
		// pass the driver into launch page class 
		LaunchPage lp = PageFactory.initElements(driver, LaunchPage.class); // 3 advantages
		lp.setReport(test);
		LoginPage loginPage = lp.goToLoginPage();
		Object page = loginPage.doLogin();
		
		PortfolioDetails pd=null;
		if(page instanceof LoginPage) {
			// report failure
			LoginPage loginpage = (LoginPage)page;
			loginpage.repFailure("Login Failure");
		}else {
			 pd = (PortfolioDetails)page;
				test.log(Status.INFO, "Login success");
		}
		pd.createPortFolio("PortfolioFeb19");
		// validate

		pd.repFailure("Portfolio creation error");
		test.log(Status.PASS, "Created portfolio successfully");
		
	}
	
	@DataProvider
	public Object[][] getData(){
		// read data from xls
		return DataUtil.getTestData(xls, "CreatePortfolioTest");

		
	}

}
