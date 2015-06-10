package com.mentoring.epam.test.lesson9.testSuite;

import com.google.common.base.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.util.concurrent.TimeUnit;

/**
 * Created by Iurii_Galias on 3/30/15.
 */
public class Main_Page {

    private static WebDriver driver;
    private static WebElement element;

    public Main_Page(WebDriver driver) {
        this.driver = driver;
    }


    public WebElement isLogging() {
        element = driver.findElement(By.xpath(".//span[@class='gb_d gbii']"));  //check that we are logging
        return element;
    }

    public void clickOnAccountSettings() {
        isLogging().click();
    }


    public void findNewLetterButton() {
        element = driver.findElement(By.xpath(".//div[@role ='button'] [@gh='cm']"));
        element.click();
    }


    public WebElement findSendButton() {
        driver.findElement(By.xpath(".//div[@role='button'][@data-tooltip-delay='800']"));
        return element;
    }


    public void clickOnSendButton() {
        findSendButton().click();
    }


    public void findAndClickCloseButton() {
        element = driver.findElement(By.xpath(".//tbody//td[2]//img[3]"));
        element.click();
    }


    public WebElement isNewLetterFrameClosed() {
        element = driver.findElement(By.xpath(".//div[@gh='cm']"));
        return element;
    }


    //find message input field
    private WebElement findInputField() {
        element = driver.findElement(By.id("gbqfq"));
        return element;
    }

    //move to draft with using method findInputField;
    public void moveToDraft() {
        findInputField().sendKeys("in:draft");
    }


    public void findSendedLetters() {
        element = findInputField();
        element.clear();
        element.sendKeys("in:sent");
        clickMagnifierButton();
    }


    public void clickMagnifierButton() {
        element = driver.findElement(By.id("gbqfb"));
        element.click();
    }


    //Find and open upper message in google account (in drafts)
    public WebElement openUpperMessageInDrafts() {
        element = driver.findElement(By.xpath("//div[@role='main']//table//tr[1]//td[6]//div[@role='link']"));
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(30, TimeUnit.SECONDS)
                .pollingEvery(5, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);

        WebElement element = wait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                return driver.findElement(By.xpath("//div[@role='main']//table//tr[1]//td[6]//div[@role='link']"));
            }
        });
        element.click();
        return element;
    }


    public WebElement findThemeInNewLetterFrame() {
        element = driver.findElement(By.name("subjectbox"));
        return element;
    }


    public WebElement findMessageBodyInNewLetterFrame() {
        element = driver.findElement(By.xpath(".//div[contains(@role, 'textbox')]"));
        return element;
    }


    public WebElement findLetterInDrafts() {
        element = driver.findElement(By.xpath("//div[@role='main']//table//tr[1]//td[6]//div[@role='link']"));
        return element;
    }


    public WebElement checkLetterInSended() {
        element = driver.findElement(By.xpath("//div[@role='main']//table//tr[1]//td[6]//div[@role='link']"));
        return element;
    }


    private WebElement findExitButton() {
        element = driver.findElement(By.id("gb_71"));
        return element;
    }


    public void clickExitButton() {
        findExitButton().click();
    }


    public void sendLetter(String addressee, String topic, String messageBody) {
        driver.findElement(By.name("to")).sendKeys(addressee);
        driver.findElement(By.name("subjectbox")).sendKeys(topic);
        driver.findElement(By.xpath(".//div[contains(@role, 'textbox')]")).sendKeys(messageBody);
    }

}
