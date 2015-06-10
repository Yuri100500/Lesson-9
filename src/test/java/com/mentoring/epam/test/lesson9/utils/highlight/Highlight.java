package com.mentoring.epam.test.lesson9.utils.highlight;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by Iurii_Galias on 5/12/15.
 */
public class Highlight {


    public static void highlightElement(WebElement element, WebDriver driver){
        String bg = element.getCssValue("backgroundColor");
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("arguments[0].style.backgroundColor =  '" + "red" + "'",element);
        js.executeScript("arguments[0].style.backgroundColor =  '" + bg + "'",element);
    }
}
