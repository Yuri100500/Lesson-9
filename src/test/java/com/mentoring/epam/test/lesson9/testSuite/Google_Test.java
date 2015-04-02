package com.mentoring.epam.test.lesson9.testSuite;


import com.mentoring.epam.test.lesson9.readfromfile.TxtFileReader;
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


    Login_Page login_page;
    Main_Page main_page;

    private WebDriver driver;

    @BeforeClass(description = "Start Firefox. Open google")
    public void startBrowser() {
        TxtFileReader.initializeSettings();
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        login_page = new Login_Page(driver);
        main_page = new Main_Page(driver);
        driver.get("http://gmail.com");
    }


    @Test(description = "Login to mailbox")
    public void login() {
        login_page.enterToGoogleAccount(TxtFileReader.login, TxtFileReader.password);
        Assert.assertTrue(main_page.isLogging().isDisplayed(), "Error! Unsuccessful authorization");
        System.out.println("Authorization Success!!");
    }


    @Test(description = "Letter creation", dependsOnMethods = "login")
    public void letterCreation() {
        main_page.findNewLetterButton();
        Assert.assertTrue(main_page.findSendButton().isDisplayed(), "Error! The mail frame wasn't created!");
    }


    @Test(description = "Send letter", dependsOnMethods = "letterCreation")
    public void sendNewLetter() {
        main_page.sendLetter(TxtFileReader.login + TxtFileReader.domain, TxtFileReader.topic, TxtFileReader.messageBody);
        main_page.findAndClickCloseButton();
        Assert.assertTrue(main_page.isNewLetterFrameClosed().isDisplayed(), "Error! The new letter frame wasn't close");
    }


    @Test(description = "Check letter in drafts", dependsOnMethods = "sendNewLetter")
    public void checkMessage() {
        main_page.moveToDraft();
        main_page.clickMagnifierButton();
        main_page.openUpperMessageInDrafts();
        Assert.assertTrue(main_page.findThemeInNewLetterFrame().isEnabled(), "Error! I can't find letter's theme in drafts");
        Assert.assertTrue(main_page.findMessageBodyInNewLetterFrame().isEnabled(), "Error! I can't find letter's body in drafts");
    }


    @Test(description = "Send letter", dependsOnMethods = "checkMessage")
    public void sendLetterAgain() {
        main_page.clickOnSendButton();
        Assert.assertTrue(main_page.findLetterInDrafts().isEnabled(), "Error, the letter is absent in drafts");
    }


    @Test(description = "Check sended", dependsOnMethods = "sendLetterAgain")
    public void checkSended() {
        main_page.findSendedLetters();
        Assert.assertTrue(main_page.checkLetterInSended().isEnabled(), "Error, the letter is not been sent");
    }


    @Test(description = "Logout", dependsOnMethods = "checkSended")
    public void logout() {
        main_page.clickOnAccountSettings();
        main_page.clickExitButton();
        Assert.assertTrue(login_page.areYouOnTheMainPage().isEnabled(), "Error, SignIn Element is not found. You are on the wrong page");
    }


    @AfterClass(description = "Stop browser")
    public void tearDown() {
        driver.quit();
    }
}
