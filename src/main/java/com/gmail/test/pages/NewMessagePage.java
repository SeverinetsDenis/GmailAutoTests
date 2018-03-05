package com.gmail.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.support.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NewMessagePage extends Page {

    private static final Logger LOG = LoggerFactory.getLogger(NewMessagePage.class);

    @FindBy(how = How.XPATH, using = "//div[@class='wO nr l1']/textarea")
    private WebElement whomField;

    @FindBy(how = How.XPATH, using = "//div[@class='T-I J-J5-Ji aoO T-I-atl L3']")
    private WebElement sendButton;

    public NewMessagePage() {
        PageFactory.initElements(driver, this);
    }

    @Override
    public boolean isPageLoaded() {
        return waitForElement(whomField).isDisplayed();
    }

    public NewMessagePage typeAddress(String address){
        whomField.click();
        whomField.sendKeys(address);
        whomField.sendKeys(Keys.ENTER);
        return this;
    }

    public NewMessagePage typeMessage(String message){
        WebElement bodyOfLetter = driver.findElement(By.xpath("//table[@class='cf An']//tr//td[2]//div[2]/div"));
        bodyOfLetter.click();
        bodyOfLetter.sendKeys(message);
        return this;
    }

    public WelcomePage clickSendButton(){
        sendButton.click();
        return new WelcomePage();
    }

}
