package com.qtpselenium.testcases;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.qtpselenium.pom.pages.LandingPage;
import com.qtpselenium.pom.pages.LaunchPage;
import com.qtpselenium.pom.pages.LoginPage;
import com.qtpselenium.pom.pages.PortfolioDetails;
import com.qtpselenium.pom.util.Constants;
import com.qtpselenium.pom.util.DataUtil;
import com.qtpselenium.pom.util.ExtentManager;
import com.qtpselenium.pom.util.Xls_Reader;

// ant
// grid
// jenkins
//9:30 pm ist - 12 noon est
public class StockTest extends BaseTest{
	
	@BeforeMethod
	public void init(){
		rep = ExtentManager.getInstance("D:\\Common\\reports\\");
		test = rep.createTest("Add Stock Test");
	}
	@AfterMethod
	public void quit() {
		rep.flush();
	}


	
	@Test(dataProvider="getData",priority=1)
	public void addStockTest(String runmode,String browser,String portfolioName,String stockName,String date, String quantity, String price){
		test.log(Status.INFO, "Starting Add Stock Test");
		
		if(!DataUtil.isRunnable(xls, "AddStockTest") || runmode.equals("N")){
			test.log(Status.SKIP, "Skipping the test as runmode is NO");
			throw new SkipException("Skipping the test as runmode is NO");
		}
		
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

		pd.addStock(portfolioName,stockName,date, quantity, price);
	    
	}
	
	
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getTestData(xls, "AddStockTest");
	}

}
