package com.mentoring.epam.test.lesson9.testSuite;


import com.mentoring.epam.test.lesson9.patterns.singleton.GoogleTest;
import com.mentoring.epam.test.lesson9.readfromfile.TxtFileReader;
import com.mentoring.epam.test.lesson9.utils.highlight.Highlight;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Created by Iurii_Galias on 3/25/15.
 */



public class Google_Test {
    private static final com.mentoring.epam.test.lesson9.utils.log4j.Logger log = new com.mentoring.epam.test.lesson9.utils.log4j.Logger();
    private static final String PATH_TO_SCREENSHOTS_FOLDER ="D:\\ScreenShot";


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

        //Using singleton
        GoogleTest google = new GoogleTest();
        google.googleTest();
    }


    @Test(description = "Login to mailbox")
    public void login() {
        log.debug("Class Ololo " +getClass());
        makeScreenshot();
        login_page.enterToGoogleAccount(TxtFileReader.login, TxtFileReader.password);
        Assert.assertTrue(main_page.isLogging().isDisplayed(), "Error! Unsuccessful authorization");
        System.out.println("Authorization Success!!");
        //log.info("Success!!!!!!");
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
        log.info("Closing Firefox instance...GoodBye");
        makeScreenshot();
        driver.quit();
    }

    public void makeScreenshot(){
        try{
            File screenshot = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);
            FileUtils.copyFileToDirectory(screenshot,new File(PATH_TO_SCREENSHOTS_FOLDER));
            com.mentoring.epam.test.lesson9.utils.log4j.Logger.htmlOutput("Taken screenshots <a href='screenshots/"
                    + screenshot.getName() + "'>" + screenshot.getName() + "</a>");
        }catch (Exception e){
            com.mentoring.epam.test.lesson9.utils.log4j.Logger.error(e.getMessage());
        }
    }
}
/*} class HighlightElement {
    public static void highlightElement(WebElement element, WebDriver driver){
        String bg = element.getCssValue("backgroundColor");
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("arguments[0].style.backgroundColor =  '" + "red" + "'",element);
        js.executeScript("arguments[0].style.backgroundColor =  '" + bg + "'",element);
    }
}*/

