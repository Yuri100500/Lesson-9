package com.mentoring.epam.test.lesson9.testSuite;

import com.mentoring.epam.test.lesson9.utils.highlight.Highlight;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


/**
 * Created by Iurii_Galias on 3/30/15.
 */
public class Login_Page {


    private WebDriver driver;


    public Login_Page(WebDriver driver) {
        this.driver = driver;
    }

    public  Main_Page enterToGoogleAccount(String login, String password) {
        WebElement element = driver.findElement(By.id("Email"));
        Highlight.highlightElement(element,driver);
        element.sendKeys(login);
        driver.findElement(By.id("next")).click();
        element = driver.findElement(By.id("Passwd"));
        Highlight.highlightElement(element,driver);
        element.sendKeys(password);
        driver.findElement(By.id("signIn")).click();
        return new Main_Page(driver);
    }

    public WebElement areYouOnTheMainPage() {
        WebElement element = driver.findElement(By.id("signIn"));
        return element;
    }
}
