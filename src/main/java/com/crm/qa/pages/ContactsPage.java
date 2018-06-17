package com.crm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.crm.qa.base.TestBase;

public class ContactsPage extends TestBase{
	
	//@FindBy(xpath="//td[contains(text(),'Contacts')]")
	@FindBy(xpath="//td[@class='datacardtitle']")	
	WebElement lblContacts;
	
	@FindBy(name="title")	
	WebElement ddPersonTitle;

	@FindBy(id="first_name")	
	WebElement txtFirstName;
	
	@FindBy(id="surname")	
	WebElement txtLastName;

	@FindBy(name="client_lookup")	
	WebElement txtCompany;

	@FindBy(xpath="//input[@type='submit' and @value = 'Save']")	
	WebElement btnSave;
	
	//Initialized current class objects
	public ContactsPage() {
		//Initialize page factory
		PageFactory.initElements(driver, this);		
	}

	//Actions
	public Boolean verifyContactsLabel() {
		impWait();
		return lblContacts.isDisplayed();		
	}
	
	public void selectChkBoxContactsByName(String name) {
		//collect all checkboxes
		driver.findElement(By.xpath("//a[text()='"+name+"']//parent::td[@class='datalistrow']"
				+"//preceding-sibling::td[@class='datalistrow']//input[@name='contact_id']")).click();
		impWait();
	}
	
	public void createNewContact(String valTitle, String ftName, String ltName, String comp) {
		Select select = new Select(ddPersonTitle);
		select.selectByVisibleText(valTitle);
		impWait();
		
		txtFirstName.sendKeys(ftName);
		txtLastName.sendKeys(ltName);
		txtCompany.sendKeys(comp);
		btnSave.click();
		impWait();
	}
}
