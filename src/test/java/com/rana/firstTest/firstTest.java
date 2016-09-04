package com.rana.firstTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by iamraghav on 9/3/16.
 */
public class firstTest {

    WebDriver       driver;
    ExtentReports   report;
    ExtentTest      logger;


    @BeforeTest
    public void setUp(){

        report = new ExtentReports("//Users//iamraghav//Documents//Report//automation.html");

        logger = report.startTest("VerifyTitle");

        driver = new FirefoxDriver();

        logger.log(LogStatus.INFO, "Browser Launched!!!");

        driver.get("http://opensource.demo.orangehrmlive.com/");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        logger.log(LogStatus.INFO, "Application Up and Running");
    }

    @Test
    public void verifyTitle() throws Exception{

        String title = driver.getTitle();
        Assert.assertTrue(title.contains("Orange"));

        logger.log(LogStatus.PASS, "Title verified");

        Utility.captureScreenshot(driver, "webTitleName");
    }

    @Test
    public void verifyLogin() throws Exception{

        driver.findElement(By.id("txtUsername")).sendKeys("Admin");
        driver.findElement(By.id("txtPassword")).sendKeys("admin");
        driver.findElement(By.id("btnLogin")).click();

        logger.log(LogStatus.PASS, "Username, Password entered and button Clicked");
        Utility.captureScreenshot(driver,"verifyLogin");
    }

    @AfterTest
    public void tearDown(){

        report.endTest(logger);
        report.flush();

        driver.quit();


        }

    }

