package com.crm.qa.util;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

import com.crm.qa.base.TestBase;

public class WebEventListener extends TestBase implements WebDriverEventListener {

	public void beforeNavigateTo(String url, WebDriver driver) {
		System.out.println("Before navigating to : '" + url + "'");
	}

	public void afterNavigateTo(String url, WebDriver driver) {

		System.out.println("After naigating to : '" + url + "'");
	}

	public void beforeClickOn(WebElement element, WebDriver driver) {
		System.out.println("Trying to click on : " + element.toString());
	}

	public void afterClickOn(WebElement element, WebDriver driver) {
		System.out.println("Clicked on : " + element.toString());
	}

	@Override
	public void beforeAlertAccept(WebDriver driver) {
		System.out.println("Accepting alert : ");
	}

	@Override
	public void afterAlertAccept(WebDriver driver) {
		System.out.println("Accepted alert : ");
	}

	@Override
	public void afterAlertDismiss(WebDriver driver) {
		System.out.println("Dismissed alert : ");
	}

	@Override
	public void beforeAlertDismiss(WebDriver driver) {
		System.out.println("Dismissing alert : ");
	}

	@Override
	public void beforeNavigateBack(WebDriver driver) {
		System.out.println("Navigating back to previous page.");	
	}

	@Override
	public void afterNavigateBack(WebDriver driver) {
		System.out.println("Navigated back to previous page.");
	}

	@Override
	public void beforeNavigateForward(WebDriver driver) {
		System.out.println("Navigating forward to next page.");
	}

	@Override
	public void afterNavigateForward(WebDriver driver) {
		System.out.println("Navigated forward to next page.");

	}

	@Override
	public void beforeNavigateRefresh(WebDriver driver) {
		System.out.println("Navigating refresh to previous page.");
	}

	@Override
	public void afterNavigateRefresh(WebDriver driver) {
		System.out.println("Navigated back to previous page.");

	}

	@Override
	public void beforeFindBy(By by, WebElement element, WebDriver driver) {
		System.out.println("Trying to find Element By : " + by.toString());
	}

	@Override
	public void afterFindBy(By by, WebElement element, WebDriver driver) {
		System.out.println("Found Element By : " + by.toString());
	}

	@Override
	public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		System.out.println("Value of the : " + element.toString() + "before any changes made");
	}

	@Override
	public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		System.out.println("Element value changed to : " + element.toString());
	}

	@Override
	public void beforeScript(String script, WebDriver driver) {
	
	}

	@Override
	public void afterScript(String script, WebDriver driver) {
	
	}

	@Override
	public void beforeSwitchToWindow(String windowName, WebDriver driver) {
		System.out.println("Switching to window : " + windowName);	
	}

	@Override
	public void afterSwitchToWindow(String windowName, WebDriver driver) {
		System.out.println("Switched to window : " + windowName);
	}

	@Override
	public void onException(Throwable error, WebDriver driver) {
		System.out.println("Exception occurerd :" + error);
		try {
			TestUtil.takeScreenshotAtEndOfTest();
		} catch (IOException e){
			e.printStackTrace();
		}
	}

	@Override
	public <X> void beforeGetScreenshotAs(OutputType<X> target) {
		System.out.println("Going to take screenshot : " + target);
	}

	@Override
	public <X> void afterGetScreenshotAs(OutputType<X> target, X screenshot) {
		System.out.println("Screenshot is being taen for : " + target);
	}
}
