package com.mentoring.epam.test.lesson9.testSuite;

import org.openqa.selenium.By;
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
public class GoogleTest {


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
        driver.findElement(By.id("Email")).sendKeys(LOGIN);
        driver.findElement(By.id("Passwd")).sendKeys(PASSWORD);
        driver.findElement(By.id("signIn")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//a[@title='Аккаунт myspecialtest2015@gmail.com']")).isDisplayed(),"Error! Unsuccessful authorization");

        System.out.println("Authorization Success!!");
    }



    @Test(description = "Letter creation", dependsOnMethods = "login")
        public void letterCreation(){
        driver.findElement(By.xpath(".//div[contains(text(),'НАПИСАТЬ')]")).click();
        Assert.assertTrue(driver.findElement(By.xpath(".//div[contains(@data-tooltip,'Отправить')]")).isDisplayed(),"Error! The mail frame wasn't created!");
    }



    @Test(description = "Send letter", dependsOnMethods = "letterCreation")
    public void sendNewLetter(){
        sendLetter(LOGIN + DOMAIN, TOPIC, MESSAGEBODY);
        driver.findElement(By.xpath(".//img[contains(@alt, 'Закрыть')]")).click();
        Assert.assertTrue(driver.findElement(By.xpath(".//div[@gh='cm']")).isDisplayed(),"Error! The new letter wasn't close");
    }



    @Test(description = "Check letter in drafts",dependsOnMethods = "sendNewLetter")
    public void checkMessage() {
        driver.findElement(By.id("gbqfq")).sendKeys("in:draft");
        driver.findElement(By.id("gbqfb")).click();
        WebDriverWait wait = new WebDriverWait(driver,10);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='main']//table//tr[1]")));
        element.click();
        Assert.assertTrue(driver.findElement(By.name("subjectbox")).isDisplayed(),"Error! I can't find letter's theme in drafts");
        Assert.assertTrue(driver.findElement(By.xpath(".//div[contains(@role, 'textbox')]")).isDisplayed(), "Error! I can't find letter's body in drafts");
    }



    @Test(description = "Send letter", dependsOnMethods = "checkMessage")
    public void sendLetterAgain(){
        driver.findElement(By.xpath(".//div[contains(@data-tooltip,'Отправить')]")).click();
        Assert.assertTrue(isElementPresent(By.xpath("//font[contains(text(),'Черновик')]")),"Error, the letter is absent in drafts");
    }



    @Test(description = "Check sended", dependsOnMethods = "sendLetterAgain")
    public void checkSended(){
        WebElement element = driver.findElement(By.id("gbqfq"));
        element.clear();
        element.sendKeys("in:sent");
        driver.findElement(By.id("gbqfb")).click();
        Assert.assertTrue(isElementPresent(By.xpath("//span[@email='myspecialtest2015@gmail.com'] [@name='мне']")),"Error, the letter is not been sent");
    }



    @Test(description = "Logout", dependsOnMethods = "checkSended")
    public void logout(){
        driver.findElement(By.xpath("//a[@title='Аккаунт myspecialtest2015@gmail.com']")).click();
        driver.findElement(By.id("gb_71")).click();
        Assert.assertTrue(driver.findElement(By.id("signIn")).isDisplayed(), "Error, SignIn Element is not found");
    }



    @AfterClass(description = "Stop browser")
    public void tearDown(){
        driver.quit();
    }



    public  boolean isElementPresent(By by){
        return !driver.findElements(by).isEmpty();
    }



    private  void sendLetter(String addressee, String topic, String messageBody){
        driver.findElement(By.name("to")).sendKeys(addressee);
        driver.findElement(By.name("subjectbox")).sendKeys(topic);
        driver.findElement(By.xpath(".//div[contains(@role, 'textbox')]")).sendKeys(messageBody);
    }
}
