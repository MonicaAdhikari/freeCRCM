package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

// Monica
public class ContactsPageTest extends TestBase {

	static TestUtil testutil;
	LoginPage loginpage;
	HomePage homepage;
	ContactsPage contactpage;
	
	String sheetName="Contacts";
	
	public ContactsPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		initialization();
		testutil = new TestUtil();
		contactpage = new ContactsPage();
		loginpage = new LoginPage();
		//homepage object
		homepage = loginpage.Login(prop.getProperty("username"), prop.getProperty("password"));
		impWait();
		//testutil.switchToFrame();
		contactpage = homepage.clickContactsLink();
	}
	
	@Test(priority=1)
	public void verifyContactsPageLabel() {
		Assert.assertTrue(contactpage.verifyContactsLabel(),"Contacts label is missing on the pgae");
		impWait();
		System.out.println("Contacts Page label is being verified");
	}

	@Test(priority=2)
	public void selectSingleContactsTest() {
		contactpage.selectChkBoxContactsByName("Anchita Kirti");
		impWait();
		System.out.println("Contact Check box is being selected");
	}

	@Test(priority=3)
	public void selectMultipleContactsTest() {
		contactpage.selectChkBoxContactsByName("Anchita Kirti");
		//contactpage.selectChkBoxContactsByName("Anchita Kirti");
		impWait();
		System.out.println("Contact Check box is being selected");
	}
	
	//data driven approach
	@DataProvider
	public Object[][] getTestDatafromExcel() {
		Object data[][] = TestUtil.getTestDataFromExcel(sheetName);
		return data;
	}

	//data driven approach
	@Test(priority=4, dataProvider="getTestDatafromExcel")
	public void createNewContactTest(String pTitle, String pFirstName,String pLastame,String pCompany) {
		homepage.clickNewContactsLink();
		impWait();
		contactpage.createNewContact(pTitle, pFirstName, pLastame, pCompany);		
		System.out.println("New Contact are being created");
		//Assert.assertEquals(actual, expected);
	}
	
	@AfterMethod
	public void teardown() {
		System.out.println("Quitted browser....");

		driver.quit();
	}
	
}