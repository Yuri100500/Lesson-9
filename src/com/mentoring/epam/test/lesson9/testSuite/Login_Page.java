package com.mentoring.epam.test.lesson9.testSuite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


/**
 * Created by Iurii_Galias on 3/30/15.
 */
public class Login_Page {

    private static WebDriver driver;
    private static WebElement element;

    public Login_Page(WebDriver driver){
        this.driver = driver;
    }

    public static void enterToGoogleAccount(String login, String password){
        driver.findElement(By.id("Email")).sendKeys(login);
        driver.findElement(By.id("Passwd")).sendKeys(password);
        driver.findElement(By.id("signIn")).click();
    }

    public static WebElement areYouOnTheMainPage(){
        element = driver.findElement(By.id("signIn"));
        return element;
    }
}
