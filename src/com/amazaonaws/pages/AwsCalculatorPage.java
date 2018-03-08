package com.amazaonaws.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.prestashop.utilities.Driver;

public class AwsCalculatorPage {
	
	private WebDriver driver;
	
	public AwsCalculatorPage() {
		this.driver =Driver.getDriver(null);
		PageFactory.initElements(driver,this);
	}
	 
	@FindBy(className="billLabel")
	public WebElement billLabel;
	
	@FindBy(xpath="(//*[@class='gwt-PushButton small gwt-PushButton-up'])[1]")
	public WebElement addEc2;   
	
	@FindBy(xpath="//tr[@class=\"EC2InstanceRow itemsTableDataRow table\"]//input[@class=\"gwt-TextBox input\"]")
	public WebElement description;
	
	@FindBy(xpath= "//tr[@class=\"EC2InstanceRow itemsTableDataRow table\"]//table[@class=\"SF_EC2_INSTANCE_FIELD_INSTANCES field integerNumericField\"]//input[@class=\"gwt-TextBox numericTextBox input\"]")        
	public WebElement instanceCount;
	
	@FindBy(xpath="//table[@class='SF_EC2_INSTANCE_FIELD_USAGE field usageField']//input")
	public WebElement usageCount;
	
	@FindBy(xpath="//table[@class='SF_EC2_INSTANCE_FIELD_USAGE field usageField']//select")
	public WebElement usage;
	
	public String getUsageOption() {
		Select select = new Select(usage);
		return select.getFirstSelectedOption().getText();
	}
	@FindBy(xpath="//div[@class='gwt-HTML field SF_EC2_INSTANCE_FIELD_TYPE instanceTypeField rowDialogSelectorFieldView']")
	public WebElement ec2Type;
	
	@FindBy(xpath="//div[@class='gwt-HTML field SF_COMMON_FIELD_BILLING instanceBillingField rowDialogSelectorFieldView']")
	public WebElement billingOption;
	
	@FindBy(xpath="//div[@class='gwt-HTML DynamicPrice DynamicPricePricing']")
	public WebElement monthlyCost;
	
	@FindBy(xpath="//button[@class='gwt-Button reset small']")
	public WebElement clearForm;
	
	@FindBy(xpath="//div[@class='gwt-DialogBox ConfirmDialog Dialog']")
	public WebElement confirmDialog; 
	
	@FindBy(xpath="//button[.='OK']")
	public WebElement OK;
	
	@FindBy(xpath="//a[.='Amazon EC2 Service (US-East)']/../../../td[4]/table/tbody/tr/td/input")
	public WebElement monthlyBillCostBeforeDiscount;
	
	@FindBy(xpath="//div[.='Services']")
	public WebElement services;
	
	public boolean isEC2InstancesTableClear() { 
		return driver.findElements(By.xpath("//tr[@class='EC2InstanceRow itemsTableDataRow table']")).isEmpty();
	}
	 
	public boolean checkClearAlert() {	
	return confirmDialog.isDisplayed(); 
	}

	public double getMonthlyCost() {
		return Double.parseDouble(monthlyCost.getText().replace("$ ", ""));
	}
	
	public int getInstanceCount() {
		return Integer.parseInt(instanceCount.getAttribute("value"));
	}
	
	public boolean isAt() {
		return driver.getTitle().equals("Amazon Web Services Simple Monthly Calculator");
	}
	
	public double getMonthlyBillAmount() {
		return Double.parseDouble(billLabel.getText().split("\\$ ")[1].replace(")", ""));
	}

}
