package com.gmail.test.pages;

import java.util.concurrent.TimeUnit;

import com.gmail.test.factory.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/*
 * Abstract class representation of a Page in the UI. Page object pattern
 */
public abstract class Page {

    protected WebDriver driver = WebDriverFactory.webDriver;

    public boolean isPageLoaded() {
        waitForSeconds(3);
        return true;
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public void waitForSeconds(int aSeconds) {
        WebDriverFactory.INSTANCE.webDriver.manage().timeouts().implicitlyWait(aSeconds, TimeUnit.SECONDS);
    }

    public WebElement waitForElement(WebElement element) {
        WebElement result = new WebDriverWait(driver, 30).
                until(ExpectedConditions.visibilityOf(element));
        return result;
    }

    public WebElement waitForElement(By searchParam) {
        WebElement result = new WebDriverWait(driver, 30).
                until(ExpectedConditions.visibilityOfElementLocated(searchParam));
        return result;
    }

}
