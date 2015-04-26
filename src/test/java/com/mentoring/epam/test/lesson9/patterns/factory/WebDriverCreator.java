package com.mentoring.epam.test.lesson9.patterns.factory;

import org.openqa.selenium.WebDriver;

/**
 * Created by 1 on 26.04.2015.
 */
public abstract class WebDriverCreator {
    protected WebDriver driver;
    public abstract WebDriver factoryMethod();
}
