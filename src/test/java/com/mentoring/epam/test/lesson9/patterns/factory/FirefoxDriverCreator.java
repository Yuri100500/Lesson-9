package com.mentoring.epam.test.lesson9.patterns.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;

/**
 * Created by 1 on 26.04.2015.
 */
public class FirefoxDriverCreator extends WebDriverCreator {
    @Override
    public WebDriver factoryMethod() {
        FirefoxBinary binary = new FirefoxBinary(new File("C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe"));
        FirefoxProfile profile = new FirefoxProfile(new File("C:\\Users\\1\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\zupao4xs.default"));

        WebDriver driver = new FirefoxDriver(binary,profile);

        return driver;
    }
}
