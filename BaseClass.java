package com.crm.Generic_Utilities;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.crm.objectRepository.HomePage;
import com.crm.objectRepository.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public WebDriver driver;
	 
	 WebDriver_Utility wLib=new WebDriver_Utility();
	 File_Utility flib = new  File_Utility();
	
	@BeforeSuite
	public void bs()
	{
		System.out.println("database connection");
		
	}
	
	@BeforeClass
	public void BC() {
	System.out.println("=============Launch the Browser=======");
	driver = new ChromeDriver();
	wLib.waitForPageToLoad(driver);
	driver.manage().window().maximize();
	}
	
	
	@BeforeMethod
	public void BM() throws Throwable {
	/*common Data*/
	String USERNAME = flib.getPropertyKeyValue("un");
	String PASSWORD = flib.getPropertyKeyValue("pw");
	String URL = flib.getPropertyKeyValue("url");
	String BROWSER = flib.getPropertyKeyValue("browser");
	/* Navigate to app*/
	 driver.get(URL);
	 /* step 1 : login */
	  LoginPage Login = new LoginPage(driver);
	 Login.login(USERNAME, PASSWORD);
	}
	@AfterMethod
	public void AM() {
	 /*step 6 : logout*/
	HomePage home = new HomePage(driver);
	 home.clicklogOut();
	}
	@AfterClass
	public void AC() {
	System.out.println("=============Close the Browser=======");
	driver.quit();
	}
	@AfterSuite
	public void AS() {
	System.out.println("========================close DB========================");
	}
	


	
	
	
	
	
	
}