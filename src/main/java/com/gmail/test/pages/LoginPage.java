package com.gmail.test.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginPage extends Page {

    private static final Logger LOG = LoggerFactory.getLogger(LoginPage.class);

    @FindBy(how = How.XPATH, using = "//input[@type='email']")
    private WebElement loginField;

    @FindBy(how = How.XPATH, using = "//input[@type='password']")
    private WebElement passwordField;

    @FindBy(how = How.XPATH, using = "//div[@class='qhFLie']//content[@class='CwaK9']")
    private WebElement nextButton;

    @FindBy(how = How.XPATH, using = "//div[@data-email='hryshyntest@gmail.com']")
    private WebElement mailNameLink;

    public LoginPage() {
        PageFactory.initElements(driver, this);
    }

    public boolean isPageLoaded() {
        return waitForElement(loginField).isDisplayed();
    }

    public LoginPage typeLogin(String login) {
        loginField.clear();
        loginField.sendKeys(login);
        return this;
    }

    public LoginPage clickNext() {
        nextButton.click();
        return this;
    }

    public LoginPage typePassword(String pass) {
        waitForSeconds(3);
        waitForElement(mailNameLink);
        waitForElement(passwordField).sendKeys(pass);
        return this;
    }

    public WelcomePage clickNextToGetMail() {
        nextButton.click();
        return new WelcomePage();
    }

}
