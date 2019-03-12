package com.qtpselenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;
import com.qtpselenium.pom.util.Constants;

public class LoginPage extends BasePage{

	@FindBy(xpath=Constants.UNERNAME_XPATHS)
	public WebElement username;
	
	@FindBy(id=Constants.USERNAME_SUBMIT_ID)
	public WebElement userNameSubmitButton;
	
	@FindBy(id=Constants.PASSWORD_SUBMIT_ID)
	public WebElement password;
	
	
	public LoginPage(WebDriver dr) {
		super(dr);
	}
	
	public Object doLogin() {
		// *** validate the Presence of username field ****
		test.log(Status.INFO, "Logging in");
		
		if(!isElementPresent(Constants.UNERNAME_XPATHS, "xpath")){
			repFailure("Element not found - username field "+Constants.UNERNAME_XPATHS);
		}
		
		username.sendKeys(Constants.LOGIN_USERNAME);
		userNameSubmitButton.click();
		
		// wait for the visiblity - explicit wait
		WebDriverWait wait = new WebDriverWait(driver,15);
		try {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("userpass")));
		}catch(Exception e) {
			repFailure("Password field failed to be visible");
		}
		//*** validate the Presence of password field ****
		password.sendKeys(Constants.LOGIN_PASSWORD);
		password.sendKeys(Keys.ENTER);
		test.log(Status.INFO, "Entered username and password");
		// validation of title
	    //	validateTitle();
		
		boolean result = isElementPresent(Constants.SIGNOUT_LINK, "xpath");
		String loginResult="";
		if(result) {
			test.log(Status.INFO, "Login Success");
			loginResult="LoginSuccess";
			// Success
			PortfolioDetails pd = PageFactory.initElements(driver,PortfolioDetails.class);
			pd.setReport(test);
			//PortfolioDetails pd = new PortfolioDetails();
			return pd;
		}
		else {
			test.log(Status.INFO, "Login Failure");
			loginResult="LoginFailure";
			LoginPage lp = PageFactory.initElements(driver,LoginPage.class);
			lp.setReport(test);
			return lp;
		}
		
		
		
		
		// unsuccessful - LoginPage
		
	}

}
