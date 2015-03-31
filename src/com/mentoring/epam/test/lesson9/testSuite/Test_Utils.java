package com.mentoring.epam.test.lesson9.testSuite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by Iurii_Galias on 3/31/15.
 */
public class Test_Utils {

    private static WebDriver driver;
    public String addressee;
    public String topic;
    public String messageBody;


    public Test_Utils(WebDriver driver){
        this.driver= driver;
    }

    public void sendLetter(String addressee, String topic, String messageBody){
        this.addressee = addressee;
        this.topic = topic;
        this.messageBody = messageBody;


        driver.findElement(By.name("to")).sendKeys(addressee);
        driver.findElement(By.name("subjectbox")).sendKeys(topic);
        driver.findElement(By.xpath(".//div[contains(@role, 'textbox')]")).sendKeys(messageBody);
    }
}
