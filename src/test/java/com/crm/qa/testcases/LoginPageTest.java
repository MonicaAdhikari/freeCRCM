package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;

public class LoginPageTest extends TestBase {
	
	String expTitle = "Free CRM software in the cloud powers sales and customer service";
	
	LoginPage loginpage;
	HomePage homepage;
	
	public LoginPageTest() {
		//call TestBase class constructor to initialize objects
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		initialization();
		//LoginPage object
		loginpage = new LoginPage();
	}
	
	@Test(priority=1)
	public void loginPageTitleTest() {
		String title = loginpage.validateLoginPageTitle();
		Assert.assertEquals(title, expTitle);
		System.out.println("Login page Tite is being verified");
	}

	@Test(priority=2)
	public void crmLogoImageTest() {
		Boolean imgFlag = loginpage.validateCRMLogo();
		Assert.assertTrue(imgFlag);
		System.out.println("CRM Logo is being verified");
	}

	@Test(priority=3)
	public void loginTest() {
		homepage = loginpage.Login(prop.getProperty("username"), prop.getProperty("password"));
		System.out.println("User is being Logged in");
	}
	
	@AfterMethod
	public void teardown() {
		System.out.println("Quitted browser....");

		driver.quit();
	}
	
}
