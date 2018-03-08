package com.amazaonaws.test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.amazaonaws.pages.AwsCalculatorPage;
import com.prestashop.utilities.TestBaseClass;

public class AWSCalculatorTests extends TestBaseClass {
	
	  AwsCalculatorPage  calculator = new AwsCalculatorPage();
	
  @Test(priority=0, description="Monthly bill should be $0.00 by default")
  public void deafultMonthlyBillTest() {
	assertTrue(calculator.isAt());
	assertEquals(0.0, calculator.getMonthlyBillAmount());
	  
  }
  
  @Test(priority=1)
  public void addEc2DeafultValueTest() { 
	  
	  calculator.addEc2.click();
	 // calculator.description.sendKeys("test");
	 // assertTrue(calculator.selecetInstanceType.isSelected());
	  assertTrue(calculator.description.getAttribute("value").isEmpty());
	  assertEquals(calculator.getInstanceCount(),1);
	  assertEquals(Integer.parseInt(calculator.usageCount.getAttribute("value")),100);
	  assertEquals(calculator.getUsageOption(), "% Utilized/Month");
	  //assert that Os--> Linux on t1.micro
	  assertEquals("Linux on t1.micro", calculator.ec2Type.getText());
	  //assert that billing option is On-Demand (No Contract)
	  assertEquals(calculator.billingOption.getText(),"On-Demand (No Contract)");
	  //assert that price is 14.64
	  assertEquals(calculator.getMonthlyCost(),14.64);
	  double servicesTabMonthlyCost = calculator.getMonthlyCost();
	  
	  calculator.billLabel.click();
	  double billTabMonthlyCost=Double.parseDouble(calculator.monthlyBillCostBeforeDiscount.getAttribute("value")); 
	  assertEquals(servicesTabMonthlyCost,billTabMonthlyCost );
	  
	  calculator.services.click();
	 
 }
  @Test (priority=2)
  public void clearFormTest() throws InterruptedException {
    
	  //1. click on the clear form
	calculator.clearForm.click();
	assertTrue(calculator.checkClearAlert());
	//2.verify message is diplayed
	String popupText=calculator.confirmDialog.getText();
	assertTrue(popupText.contains("Please Confirm")&& popupText.contains("Are you sure you want to clear all entries in this service form?")); 
	//3. click OK
	calculator.OK.click();
	//4. .verify that form is cleared.
	assertEquals(calculator.isEC2InstancesTableClear() , true);	
	
	}
	  
 }
  
  
  
  

