package com.nopcommerce.user;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.nopcommerce.data.UserDataMapper;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopcommerce.user.UserHomePageObject;
import pageObjects.nopcommerce.user.UserRegisterPageObject;
import reportConfig.ExtentTestManager;

public class Level_20_Fake_Data_P3_External_Json extends BaseTest {
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = openMultiBrowser(browserName);
		homePage = PageGeneratorManager.getUserHomePage(driver);
		userData = UserDataMapper.getUserData();
		
		emailAddress = userData.getEmailAddress() + getRandomNumberByDateTime() + "@gmail.com";
	}

	@Test
	public void User_01_Register(Method method) {
		ExtentTestManager.startTest(method.getName(), "User_01_Register");
		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 01: Open Register Page");
		registerPage = homePage.openRegisterPage();
		
		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 02: Input data to 'First name' textbox: " +  userData.getFirstName());
		registerPage.inputToTextboxByID(driver, userData.getFirstName(), "FirstName");
		
		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 03: Input data to 'Lastname' textbox: " +  userData.getLastName());
		registerPage.inputToTextboxByID(driver, userData.getLastName(), "LastName");
		
		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 04: Input data to 'Email' textbox: " +  userData.getEmailAddress());
		registerPage.inputToTextboxByID(driver, emailAddress, "Email");
		
		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 05: Input data to 'Password' textbox: " +  userData.getPassword());
		registerPage.inputToTextboxByID(driver, userData.getPassword(), "Password");
		
		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 06: Input data to 'Confirm password' textbox: " +  userData.getPassword());
		registerPage.inputToTextboxByID(driver, userData.getPassword(), "ConfirmPassword");
		
		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 07: click to Register button");
 		registerPage.clickToButtonByText(driver, "Register");
// 		try {
//			homePage = registerPage.clickToLogoutLink();
//		} catch (Exception exception) {
//			System.out.println(exception);
//		}
	}

	private WebDriver driver;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private String emailAddress;
	private UserDataMapper userData;
}