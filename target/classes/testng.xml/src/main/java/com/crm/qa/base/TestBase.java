package com.crm.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.crm.qa.util.TestUtil;
import com.crm.qa.util.WebEventListener;

public class TestBase {

	public static WebDriver driver;
	public static Properties prop;
	public static String propFilePath;
	public static String driverPath;
	public static String geckoDriverPath;
	public static String chromeDriverPath;
	public static String ieDriverPath;
	public static String safariDriverPath;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	
	
	// Properties
	public TestBase() {
		try {
			propFilePath = "F:\\2 Study Material\\1 Software Testing\\4 Automation Testing\\Selenium\\1 Selenium Project\\FreeCRMMavenTestNGPageFact\\src\\main\\java\\com\\crm\\qa\\config\\config.properties";
			prop = new Properties();
					
			FileInputStream ip = new FileInputStream(propFilePath);
			// System.getProperty("user.dir")+"\src\\main\\java\\com\\crm\\qa\\config\\config.properties");		
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// Browser
	public static void initialization() {
		String browserName = prop.getProperty("browser");
		String appURL = prop.getProperty("url");
		driverPath = prop.getProperty("driverpath");
		geckoDriverPath = prop.getProperty("geckodriverpath");
		chromeDriverPath = prop.getProperty("chromedriverpath");
		ieDriverPath = prop.getProperty("iedriverpath");
		safariDriverPath = prop.getProperty("safaridriverpath");
		System.out.println(driverPath);
		System.out.println(geckoDriverPath);
		
		if(browserName.equals("firefox")) {
			driver = initFirefoxDriver(appURL);
		}else if (browserName.equals("chrome")) {
			driver = initChromeDriver(appURL);
		}else if(browserName.equals("IE")) {
			driver = initIEDriver(appURL);
		}else if(browserName.equals("safari")) {
			driver = initSafariDriver(appURL);
		}else
		{
			System.out.println("browser : " + browserName + " is invalid, Launching Firefox as browser of choice..");
			driver = initFirefoxDriver(appURL);
		}
	}

	private static WebDriver initFirefoxDriver(String appURL) {
		System.out.println("Launching Firefox browser..");
		System.setProperty("webdriver.gecko.driver", driverPath+geckoDriverPath);
		WebDriver driver = new FirefoxDriver();
		
		e_driver = new EventFiringWebDriver(driver);
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		
		driver.manage().window().maximize();
		//deleteAllCookies();
		driver.navigate().to(appURL);
		return driver;
	}

	private static WebDriver initChromeDriver(String appURL) {
		System.out.println("Launching google chrome with new profile..");
		System.setProperty("webdriver.chrome.driver", driverPath+chromeDriverPath);
		WebDriver driver = new ChromeDriver();
		e_driver = new EventFiringWebDriver(driver);
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
	//	deleteAllCookies();
		driver.manage().window().maximize();
		driver.navigate().to(appURL);
		return driver;
	}

	private static WebDriver initIEDriver(String appURL) {
		System.out.println("Launching IE with new profile..");
		System.setProperty("webdriver.chrome.driver", driverPath+ieDriverPath);
		WebDriver driver = new InternetExplorerDriver();
		e_driver = new EventFiringWebDriver(driver);
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
	//	deleteAllCookies();
		driver.manage().window().maximize();
		driver.navigate().to(appURL);
		return driver;
	}
	
	private static WebDriver initSafariDriver(String appURL) {
		System.out.println("Launching Safari with new profile..");
		System.setProperty("webdriver.chrome.driver", driverPath+safariDriverPath);
		WebDriver driver = new SafariDriver();
		e_driver = new EventFiringWebDriver(driver);
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
	//	deleteAllCookies();
		driver.manage().window().maximize();
		driver.navigate().to(appURL);
		return driver;
	}

	// deleteAllCookies
	public static void deleteAllCookies() {
		driver.manage().deleteAllCookies();
	}
		
	// navigate
	public static void navigate(String object){	
		impWait();
		driver.get(object);
		}
	
	//implicitly Wait
	public static void impWait() {
	driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
	}
	
	//waitFor
	public static void waitFor(int waittime) throws InterruptedException{
		Thread.sleep(waittime);
		}

	//pageLoadTimeOut
	public static void pageLoadTimeOut(){	
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		}
	
	/*/// <summary>
	/// Refresh the browser
	/// </summary>
	public void Refresh()
	{
	    driver.switchTo().defaultContent();
	    ((Object) driver).interexecute(DriverCommand.REFRESH, null);
	} */
	

	// captureLinks
	public static void captureLinks() {
		/* Extract all links from the webpage using selenium webdriver */
		System.out.println(
				"Extract all links from the part of the webpage using selenium webdriver-----------------------------");
		List<WebElement> all_links_webpage = driver.findElements(By.tagName("a"));

		// List myList = driver.findElement(By.xpath("//*[@id='Flot']"));

		System.out.println(
				"total no links on specific part of webpage---------------------------------------------------");
		int link_size = all_links_webpage.size();

		System.out.println(
				"Text of the link for specific part of webpage--------------------------------------------------");
		for (int i = 0; i < link_size; i++) {
			System.out.println(all_links_webpage.get(i).getText());
		}

	}

	public static void click_Btnbyid(String Pobject){
		driver.findElement(By.id(Pobject)).click();
		}

	public static void click_Btnbyxpath(String Pobject){
		driver.findElement (By.xpath(Pobject)).click();
		}

	public static void click_Btnbyname(String Pobject){
		driver.findElement (By.name(Pobject)).click();
		}
	
	public static void click_Btnbycssselector(String Pobject){
		driver.findElement (By.cssSelector(Pobject)).click();
		}

	public static void input_txt(String Pobject, String object){
		//Read from Constant.java file
		driver.findElement(By.id(Pobject)).sendKeys(object);
		}
	
	public String capture_labeltxtbyxpath(String Pobject){
		WebElement lblobject = driver.findElement(By.xpath(Pobject)); //"//*[@id='portlet_58']/header/h1/span"));
		String lbl_object = lblobject.getText();
		return lbl_object;
		}
	
	public String capture_labeltxtbyid(String Pobject){
		WebElement lblobject = driver.findElement(By.id(Pobject)); //"//*[@id='portlet_58']/header/h1/span"));
		String lbl_object = lblobject.getText();
		return lbl_object;
		}
	
	public String capture_labeltxtbyclassName(String Pobject){
		WebElement lblobject = driver.findElement(By.className(Pobject)); //"//*[@id='portlet_58']/header/h1/span"));
		String lbl_object = lblobject.getText();
		return lbl_object;
		}

	//	verifyTextPresent
	public String verifyTextPresent(String Pobject, String ExpPobject, String TestMsg){
		String ActPobject = capture_labeltxtbyxpath(Pobject);
		String result;
		
        if (ActPobject.contains(ExpPobject))
        {
        	result = TestMsg + " appears correct";
        }
        else
        {
        	result = TestMsg + " does not appear correct";        	
        }
        return result;
	}

	// ReadPropertiesfromTxt
	public static void ReadPropertiesfromTxt() {
		try {
			File file = new File("ObjectRep.txt");
			FileInputStream fileInput = new FileInputStream(file);
			Properties properties = new Properties();
			properties.load(fileInput);
			fileInput.close();

			Enumeration<?> enuKeys2 = properties.keys();
			while (enuKeys2.hasMoreElements()) {
				String key = (String) enuKeys2.nextElement();
				String value = properties.getProperty(key);
				System.out.println(key + ": " + value);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// ReadPropertiesfromXML
	public static void ReadPropertiesfromXML() {
		try {
			File file = new File("test.xml");
			FileInputStream fileInput = new FileInputStream(file);
			Properties properties = new Properties();
			properties.loadFromXML(fileInput);
			fileInput.close();

			Enumeration<?> enuKeys = properties.keys();
			while (enuKeys.hasMoreElements()) {
				String key = (String) enuKeys.nextElement();
				String value = properties.getProperty(key);
				System.out.println(key + ": " + value);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// ReadPropertyFile
	public static void ReadPropertyFile() {
		try {
			File file = new File("ObjectRep.properties");
			FileInputStream fileInput = new FileInputStream(file);
			Properties properties = new Properties();
			properties.load(fileInput);
			fileInput.close();

			Enumeration<?> enuKeys = properties.keys();
			while (enuKeys.hasMoreElements()) {
				String key = (String) enuKeys.nextElement();
				String value = properties.getProperty(key);
				System.out.println(key + ": " + value);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// calendar
	public void testDAtePicker() throws Exception {

		// Date and Time to be set in textbox
		String dateTime = "12/07/2014 2:00 PM";

		driver.manage().window().maximize();

		driver.get("http://demos.telerik.com/kendo-ui/datetimepicker/index");

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// button to open calendar
		WebElement selectDate = driver.findElement(By.xpath("//span[@aria-controls='datetimepicker_dateview']"));
		selectDate.click();

		// button to move next in calendar
		WebElement nextLink = driver.findElement(By.xpath(
				"//div[@id='datetimepicker_dateview']//div[@class='k-header']//a[contains(@class,'k-nav-next')]"));

		// button to click in center of calendar header
		WebElement midLink = driver.findElement(By.xpath(
				"//div[@id='datetimepicker_dateview']//div[@class='k-header']//a[contains(@class,'k-nav-fast')]"));

		// button to move previous month in calendar
		WebElement previousLink = driver.findElement(By.xpath(
				"//div[@id='datetimepicker_dateview']//div[@class='k-header']//a[contains(@class,'k-nav-prev')]"));

		// Split the date time to get only the date part
		String date_dd_MM_yyyy[] = (dateTime.split(" ")[0]).split("/");

		// get the year difference between current year and year to set in calander
		int yearDiff = Integer.parseInt(date_dd_MM_yyyy[2]) - Calendar.getInstance().get(Calendar.YEAR);

		midLink.click();

		if (yearDiff != 0) {
			// if you have to move next year
			if (yearDiff > 0) {
				for (int i = 0; i < yearDiff; i++) {
					System.out.println("Year Diff->" + i);
					nextLink.click();
				}
			}

			// if you have to move previous year
			else if (yearDiff < 0) {
				for (int i = 0; i < (yearDiff * (-1)); i++) {
					System.out.println("Year Diff->" + i);
					previousLink.click();
				}
			}
		}

		Thread.sleep(1000);

		// Get all months from calendar to select correct one
		List<WebElement> list_AllMonthToBook = driver.findElements(By.xpath(
				"//div[@id='datetimepicker_dateview']//table//tbody//td[not(contains(@class,'k-other-month'))]"));

		list_AllMonthToBook.get(Integer.parseInt(date_dd_MM_yyyy[1]) - 1).click();
		Thread.sleep(1000);

		// get all dates from calendar to select correct one
		List<WebElement> list_AllDateToBook = driver.findElements(By.xpath(
				"//div[@id='datetimepicker_dateview']//table//tbody//td[not(contains(@class,'k-other-month'))]"));

		list_AllDateToBook.get(Integer.parseInt(date_dd_MM_yyyy[0]) - 1).click();

		/// FOR TIME
		WebElement selectTime = driver.findElement(By.xpath("//span[@aria-controls='datetimepicker_timeview']"));

		// click time picker button
		selectTime.click();

		// get list of times
		List<WebElement> allTime = driver.findElements(
				By.xpath("//div[@data-role='popup'][contains(@style,'display: block')]//ul//li[@role='option']"));
		dateTime = dateTime.split(" ")[1] + " " + dateTime.split(" ")[2];

		// select correct time
		for (WebElement webElement : allTime) {
			if (webElement.getText().equalsIgnoreCase(dateTime)) {
				webElement.click();
			}
		}
	}

}
