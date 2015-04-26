package com.mentoring.epam.test.lesson9.patterns.singleton;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

/**
 * Created by 1 on 26.04.2015.
 */
public class GoogleTest {

    @Test
    public void googleTest(){
        WebDriver driver = new FirefoxDriver();
        driver.navigate().to("http://gmail.com");
    }
}
