package com.qtpselenium.pom.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;
import com.qtpselenium.pom.util.Constants;

public class LaunchPage extends BasePage{
	
	public LaunchPage(WebDriver dr) {
		super(dr);
	}
	

	public LoginPage goToLoginPage() {
		 test.log(Status.INFO, "Going to Login Page");
		 driver.get(Constants.LOGIN_URL);
		 // *** validate the title ****
		 validateTitle(Constants.TITLE_LOGIN_PAGE);
		 test.log(Status.INFO, "Login Page Opened successfully");
		 //LoginPage lp = new LoginPage();
		 LoginPage lp = PageFactory.initElements(driver, LoginPage.class);
		 lp.setReport(test);
		 return lp;		
	}

}
