package com.mentoring.epam.test.lesson9.testSuite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

/**
 * Created by Iurii_Galias on 3/30/15.
 */
public class Main_Page {

    private static WebDriver driver;
    private static WebElement element;

    public Main_Page(WebDriver driver){
        this.driver = driver;
    }

    public static WebElement checkingLogin(){
        element = driver.findElement(By.xpath("//a[@title='Аккаунт myspecialtest2015@gmail.com']"));  //check that we are logging
        return element;
    }

    public static  WebElement findAndCheckNewLetterButton(){
        driver.findElement(By.xpath(".//div[contains(text(),'НАПИСАТЬ')]")).click();
        Assert.assertTrue(driver.findElement(By.xpath(".//div[contains(@data-tooltip,'Отправить')]")).isDisplayed(), "Error! The mail frame wasn't created!");
    }
}
