package com.crm.qa.pages;

import org.openqa.selenium.By;

import com.crm.qa.base.TestBase;

public class TasksPage extends TestBase {
	public void selectChkBoxContactsByName(String name) {
		//collect all checkboxes
		driver.findElement(By.xpath("//a[text()='"+name+"']//parent::td[@class='datalistrow']"
				+"//preceding-sibling::td[@class='datalistrow']//input[@name='contact_id']")).click();
		impWait();
	}
	
}
