package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class HomePage extends TestBase{

	//Page Factory - OR
	//Find Page Objects
	@FindBy(xpath="//td[contains(text(),'User: Monica A')]")
	@CacheLookup
	//annotation to store the value in a cache memory, it will get the value from memory instead of page or browser
	// pros: performance will improve
	// cons: if browser or page is being refreshed, then it will create issue since cache memory will have disturbed.
	
	WebElement lblUserName;

	@FindBy(xpath="//a[contains(text(),'Contacts')]")
	WebElement lnkContacts;

	@FindBy(xpath="//a[contains(text(),'New Contact')]")
	WebElement lnkNewContacts;
	
	@FindBy(xpath="//a[contains(text(),'Deals')]")
	WebElement lnkDeals;
	
	@FindBy(xpath="//a[contains(text(),'Tasks')]")
	WebElement lnkTasks;
	
	@FindBy(xpath="//img[contains(@class,'img-responsive')]")
	WebElement crmLogo;

	//Initialized current class objects
	public HomePage() {
		//Initialize page factory
		PageFactory.initElements(driver, this);
		
	}
	
	//Actions
	public String verifyHomePageTitle() {
		String hpTitle = driver.getTitle();
		return hpTitle;
	}
	
	public Boolean verifyHomePageUserName() {
		return lblUserName.isDisplayed();		
	}
	
	public ContactsPage clickContactsLink() {
		lnkContacts.click();
		return new ContactsPage();
	}
	
	public void clickNewContactsLink() {
		Actions action = new Actions(driver);
		action.moveToElement(lnkContacts).build().perform();
		
		lnkNewContacts.click();
		//return new ContactsPage();
	}
	
	public DealsPage clickDealsLink() {
		lnkDeals.click();
		return new DealsPage();
	}

	public TasksPage clickTasksLink() {
		lnkTasks.click();
		return new TasksPage();
	}

	
}
