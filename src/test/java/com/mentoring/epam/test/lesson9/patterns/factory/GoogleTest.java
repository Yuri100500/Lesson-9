package com.mentoring.epam.test.lesson9.patterns.factory;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;


/**
 * Created by 1 on 26.04.2015.
 */
public class GoogleTest {

    @Test
    public void googleTest(){
        WebDriverCreator creator = new FirefoxDriverCreator();

        WebDriver driver = creator.factoryMethod();
        driver.navigate().to("http://gmail.com");
    }
}
