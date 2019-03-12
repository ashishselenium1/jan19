package com.qtpselenium.pom.pages;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.Status;
import com.qtpselenium.pom.util.Constants;

public class LandingPage extends BasePage{
	@FindBy(id=Constants.CREATE_PORTFOLIO)
		WebElement createPortFolio;
	@FindBy(id=Constants.PORTFOLIO_NAME)
		WebElement portfolioName;
	@FindBy(id=Constants.CREATEPORTFOLIO_BUTTON)
		WebElement createPortFolioButton;
	@FindBy(id=Constants.PORTFOLIO_DROPDOWN)
		WebElement dropdown;
	@FindBy(id=Constants.DELETEPORTFOLIO_BUTTON)
		WebElement deleteButton;
	@FindBy(id=Constants.ADD_STOCK_BUTTON)
		WebElement addStock;
	@FindBy(id=Constants.STOCK_NAME)
	WebElement stockNameField;
	@FindBy(xpath=Constants.FIRST_OPTION)
		WebElement firstStockName;
	@FindBy(id=Constants.STOCK_ADD_DATE)
		WebElement calendarIcon;
	@FindBy(id=Constants.STOCK_QUANITY)
		WebElement stockQuantity;
	@FindBy(id=Constants.STOCK_PRICE)
		WebElement stockPrice;
	@FindBy(id=Constants.ADDSTOCK_BUTTON)
		WebElement submitButton;

	
	public LandingPage(WebDriver dr){
		super(dr);
	} 
	
	public void addStock(String portfolioName, String stockName, String date,
			String quantity, String price) {
		Select s = new Select(dropdown);
		s.selectByVisibleText(portfolioName);
		
		
		addStock.click();
		stockNameField.sendKeys(stockName);
		firstStockName.click();
		calendarIcon.click();
		selectDate(date);
		stockQuantity.sendKeys(quantity);
		stockPrice.sendKeys(price);
		submitButton.click();
		test.log(Status.INFO, "Stock added and form submitted");
		test.log(Status.INFO, "Validating company in table");
		// VALIDATE
		
		
	}
	
	public void selectDate(String d){
		// day, month , year
		Date current = new Date();
		SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date selected = sd.parse(d);
			String day = new SimpleDateFormat("d").format(selected);
			String month = new SimpleDateFormat("MMMM").format(selected);
			String year = new SimpleDateFormat("yyyy").format(selected);
			System.out.println(day+" --- "+month+" --- "+ year);
			String desiredMonthYear=month+" "+year;
			
			while(true){
				String displayedMonthYear=driver.findElement(By.cssSelector(".dpTitleText")).getText();
				if(desiredMonthYear.equals(displayedMonthYear)){
					// select the day
					driver.findElement(By.xpath("//td[text()='"+day+"']")).click();
					break;
				}else{
					
					if(selected.compareTo(current) > 0)
						driver.findElement(By.xpath("//*[@id='datepicker']/table/tbody/tr[1]/td[4]/button")).click();
					else if(selected.compareTo(current) < 0)
						driver.findElement(By.xpath("//*[@id='datepicker']/table/tbody/tr[1]/td[2]/button")).click();

				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


}
