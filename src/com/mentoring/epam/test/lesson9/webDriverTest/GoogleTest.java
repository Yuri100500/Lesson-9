package com.mentoring.epam.test.lesson9.webDriverTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
public class GoogleTest {

    private static final String LOGIN = "myspecialtest2015";
    private static final String PASSWORD = "fireinthehole32167";
    private static final String TOPIC = "WebDriver test letter";
    private static final String MESSAGEBODY = "Hi, this is test WebDriver message. If you see this message - you are awesome!!";

    private WebDriver driver;

    @BeforeClass(description = "Start Firefox. Open google")
    public void startBrowser(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("http://gmail.com");
    }

    @Test(description = "Login to mailbox")
    public void login(){
        WebElement InputLogin = driver.findElement(By.id("Email"));
        InputLogin.clear();
        InputLogin.sendKeys(LOGIN);

        WebElement EnteringPassword = driver.findElement(By.id("Passwd"));
        EnteringPassword.clear();
        EnteringPassword.sendKeys(PASSWORD);

        driver.findElement(By.id("signIn")).click();
        //driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        Assert.assertTrue(isElementPresent(By.id("gb_71")));
        System.out.println("Ok!");
    }

    @Test(description = "Letter creation", dependsOnMethods = "login")
        public void letterCreation(){
        driver.findElement(By.xpath(".//*[@id=':4i']/div/div")).click();
        Assert.assertTrue(isElementPresent(By.id(":hi")));
        System.out.println("The mail frame is open");
        }

    @Test(description = "Send letter", dependsOnMethods = "letterCreation")
    public void sendNewLetter(){
        sendLetter(LOGIN,TOPIC,MESSAGEBODY);
        driver.findElement(By.xpath(".//img[contains(@alt, 'Закрыть')]")).click();
        Assert.assertTrue(isElementPresent(By.xpath(".//img[contains(@alt, 'Закрыть')]")));
        System.out.println("Your letter was saved to drafts. Good job!");
    }

    @Test(description = "Check letter in drafts",dependsOnMethods = "sendNewLetter")
    public void checkMessage() throws InterruptedException {
        driver.findElement(By.xpath(".//a[contains(@href,'https://mail.google.com/mail/#drafts')]")).click();
        WebDriverWait wait = new WebDriverWait(driver,4);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='main']//table//tr[1]")));
        element.click();
        //driver.findElement(By.xpath("//div[@role='main']//table//tr[1]")).click();
        Assert.assertTrue(isElementPresent(By.name("subjectbox")));
        Assert.assertTrue(isElementPresent(By.xpath(".//div[contains(@role, 'textbox')]")));
    }

    @AfterClass(description = "Stop browser")
    public void tearDown(){
        driver.quit();
    }

    private boolean isElementPresent(By by){
        return !driver.findElements(by).isEmpty();
    }

    private void sendLetter(String addressee, String topic, String messageBody){

        WebElement addresseeInput = driver.findElement(By.name("to"));
        addresseeInput.clear();
        addresseeInput.sendKeys(addressee);

        WebElement topicInput = driver.findElement(By.name("subjectbox"));
        topicInput.clear();
        topicInput.sendKeys(topic);

        WebElement messageBodyInput = driver.findElement(By.xpath(".//div[contains(@role, 'textbox')]"));
        messageBodyInput.clear();
        messageBodyInput.sendKeys(messageBody);
    }
}
