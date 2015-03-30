package com.mentoring.epam.test.lesson9.testSuite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


/**
 * Created by Iurii_Galias on 3/30/15.
 */
public class Home_Page {

    private static WebDriver driver;

    public Home_Page(WebDriver driver){
        this.driver = driver;
    }

    public static void enterToGoogleAccount(String login, String password){
        driver.findElement(By.id("Email")).sendKeys(login);
        driver.findElement(By.id("Passwd")).sendKeys(password);
        driver.findElement(By.id("signIn")).click();
    }
}
