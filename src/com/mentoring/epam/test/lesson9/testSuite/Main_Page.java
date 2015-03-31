package com.mentoring.epam.test.lesson9.testSuite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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



    public static  WebElement findNewLetterButton(){
        driver.findElement(By.xpath(".//div[contains(text(),'НАПИСАТЬ')]")).click();
        return element;
    }



    public static WebElement findSendButton(){
        element = driver.findElement(By.xpath(".//div[contains(@data-tooltip,'Отправить')]"));
        return element;
    }



    public static WebElement findCloseButton(){
        element = driver.findElement(By.xpath(".//img[contains(@alt, 'Закрыть')]"));
        return element;
    }



    public static WebElement isNewLetterFrameClosed(){
        element = driver.findElement(By.xpath(".//div[@gh='cm']"));
        return element;
    }



    public static WebElement messageInputField(){
        element = driver.findElement(By.id("gbqfq"));
        return element;
    }


    public static WebElement findButton(){
        element = driver.findElement(By.id("gbqfb"));
        return  element;
    }


    public static WebElement findUpperMessageInDrafts(){
        element = driver.findElement(By.xpath("//div[@role='main']//table//tr[1]//td[6]//div[@role='link']"));
        return  element;
    }



    public static WebElement findThemeInNewLetterFrame(){
        element = driver.findElement(By.name("subjectbox"));
        return  element;
    }



    public static WebElement findMessageBodyInNewLetterFrame(){
        element = driver.findElement(By.xpath(".//div[contains(@role, 'textbox')]"));
        return  element;
    }



    public static WebElement findLetterInDrafts(){
        element = driver.findElement(By.xpath("//font[contains(text(),'Черновик')]"));
        return  element;
    }



    public static WebElement checkLetterInSended(){
        element = driver.findElement(By.xpath("//span[@email='myspecialtest2015@gmail.com'] [@name='мне']"));
        return  element;
    }



    public static WebElement findExitButton(){
        element =  driver.findElement(By.id("gb_71"));
        return  element;
    }

}
