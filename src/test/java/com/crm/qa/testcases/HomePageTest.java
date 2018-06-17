package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.DealsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.pages.TasksPage;
import com.crm.qa.util.TestUtil;

public class HomePageTest extends TestBase {

	String expTitle = "CRMPRO";
	String expUserName = "Monica A";
	
	TestUtil testutil;
	LoginPage loginpage;
	HomePage homepage;
	ContactsPage contactpage;
	DealsPage dealspage;
	TasksPage taskspage;
	
	public HomePageTest() {
		super();
	}

	//test cases should be separated - independent with each other
	//before each test case - launch the browser and signin
	//@test - execute test case
	//after each test case - close the browser
	
	@BeforeMethod
	public void setUp() {
		initialization();
		testutil = new TestUtil();
		loginpage = new LoginPage();
		//homepage object
		homepage = loginpage.Login(prop.getProperty("username"), prop.getProperty("password"));
		impWait();
	}
	
	@Test(priority=1)
	public void verifyHomePageTitleTest() {
		String title = homepage.verifyHomePageTitle();
		impWait();
		Assert.assertEquals(title, expTitle,"Home Page title does not match");
		System.out.println("Home Page title is being verified");
	}
	
	@Test(priority=2)
	public void verifyHomePageUserNameTest() {
		testutil.switchToFrame();
		impWait();
		Assert.assertTrue(homepage.verifyHomePageUserName());
		System.out.println("Home Page User Name is being verified");
	}

	@Test(priority=3)
	public void goToContactsTest() {
		testutil.switchToFrame();
		impWait();
		contactpage = homepage.clickContactsLink();
		impWait();
		System.out.println("Contact link is being clicked");
		}
	
	@Test(priority=4)
	public void goToDealsTest() {
		testutil.switchToFrame();
		impWait();
		dealspage = homepage.clickDealsLink();
		impWait();
		System.out.println("Deals link is being clicked");
		}

	@Test(priority = 5)
	public void goToTasksTest() {
		testutil.switchToFrame();
		impWait();
		taskspage = homepage.clickTasksLink();
		impWait();
		System.out.println("Tasks link is being clicked");
		}
	
	@AfterMethod
	public void teardown() {
		System.out.println("Quitted browser....");

		driver.quit();
	}
}
