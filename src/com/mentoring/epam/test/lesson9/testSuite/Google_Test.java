package com.mentoring.epam.test.lesson9.testSuite;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.concurrent.TimeUnit;

/**
 * Created by Iurii_Galias on 3/25/15.
 */
public class Google_Test {


    private static final String LOGIN = "myspecialtest2015";
    private static final String DOMAIN = "@gmail.com";
    private static final String PASSWORD = "fireinthehole32167";
    private static final String TOPIC = "WebDriver test letter";
    private static final String MESSAGEBODY = "Hi, this is test WebDriver message. If you see this message - you are awesome!!";

    private WebDriver driver;

    @BeforeClass(description = "Start Firefox. Open google")
    public void startBrowser(){

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://gmail.com");
    }



    @Test(description = "Login to mailbox")
    public void login(){
        Login_Page login_page = new Login_Page(driver);
        Main_Page main_page = new Main_Page(driver);

        login_page.enterToGoogleAccount(LOGIN,PASSWORD);

        Assert.assertTrue(main_page.checkingLogin().isDisplayed(),"Error! Unsuccessful authorization");
        System.out.println("Authorization Success!!");
    }



    @Test(description = "Letter creation", dependsOnMethods = "login")
        public void letterCreation(){
        Main_Page.findNewLetterButton();

        Assert.assertTrue(Main_Page.findSendButton().isDisplayed(), "Error! The mail frame wasn't created!");
    }



    @Test(description = "Send letter", dependsOnMethods = "letterCreation")
    public void sendNewLetter(){
        Test_Utils test_utils = new Test_Utils(driver);
        test_utils.sendLetter(LOGIN + DOMAIN, TOPIC, MESSAGEBODY);
        Main_Page.findCloseButton().click();

        Assert.assertTrue(Main_Page.isNewLetterFrameClosed().isDisplayed(),"Error! The new letter frame wasn't close");
    }



    @Test(description = "Check letter in drafts", dependsOnMethods = "sendNewLetter")
    public void checkMessage() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver,10);

        Main_Page.messageInputField().sendKeys("in:draft");
        Main_Page.findButton().click();
        Thread.sleep(1500);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(Main_Page.findUpperMessageInDrafts()));
        Thread.sleep(1500);
        element.click();
        Assert.assertTrue(Main_Page.findThemeInNewLetterFrame().isEnabled(),"Error! I can't find letter's theme in drafts");
        Assert.assertTrue(Main_Page.findMessageBodyInNewLetterFrame().isEnabled(), "Error! I can't find letter's body in drafts");
    }



    @Test(description = "Send letter", dependsOnMethods = "checkMessage")
    public void sendLetterAgain(){
        Main_Page.findSendButton().click();
        Assert.assertTrue(Main_Page.findLetterInDrafts().isEnabled(), "Error, the letter is absent in drafts");
    }



    @Test(description = "Check sended", dependsOnMethods = "sendLetterAgain")
    public void checkSended(){
        WebElement element = Main_Page.messageInputField();
        element.clear();
        element.sendKeys("in:sent");
        Main_Page.findButton().click();
        Assert.assertTrue(Main_Page.checkLetterInSended().isEnabled(), "Error, the letter is not been sent");
    }



    @Test(description = "Logout", dependsOnMethods = "checkSended")
    public void logout(){
        Main_Page.checkingLogin().click();
        Main_Page.findExitButton().click();
        Assert.assertTrue(Login_Page.areYouOnTheMainPage().isEnabled(), "Error, SignIn Element is not found. You are on the wrong page");
    }



    @AfterClass(description = "Stop browser")
    public void tearDown(){
        driver.quit();
    }
}
