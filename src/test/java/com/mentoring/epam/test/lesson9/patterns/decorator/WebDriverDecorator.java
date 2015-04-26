package com.mentoring.epam.test.lesson9.patterns.decorator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

/**
 * Created by 1 on 26.04.2015.
 */
public class WebDriverDecorator {

    @Test
    public void testCustomDriver(){
        WebDriver driver = new FirefoxDriver();
        driver = new CustomDriverDecorator(driver);
        driver.navigate().to("http://gmail.com");
        driver.findElement(By.id("Email")).sendKeys("myspecialtest2015");
        driver.findElement(By.id("Passwd")).sendKeys("fireinthehole32167");
        driver.findElement(By.id("signIn")).click();
    }
}
