package com.gmail.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

public class WelcomePage extends Page {

    private static final Logger LOG = LoggerFactory.getLogger(WelcomePage.class);

    @FindBy(how = How.XPATH, using = "//div[@class='T-I J-J5-Ji T-I-KE L3']")
    private WebElement writeLetterButton;

    @FindBy(how = How.XPATH, using = "//a[@href='https://mail.google.com/mail/u/0/#inbox']")
    private WebElement inboxMessages;

    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'a3s aXjCH')]//div[@dir='ltr']")
    private WebElement textOfMessage;

    private WebElement myMessage;

    public WelcomePage() {
        PageFactory.initElements(driver, this);
    }

    @Override
    public boolean isPageLoaded() {
        return waitForElement(writeLetterButton).isDisplayed();
    }

    public WelcomePage clickInboxButton() {
        waitForElement(inboxMessages).click();
        return this;
    }

    public NewMessagePage clickWriteLetterButton() {
        writeLetterButton.click();
        return new NewMessagePage();
    }

    public boolean isMyMessageReceived() {
        String timeOfPreviousMessage = driver.findElement(By.xpath("//table//tr[1]//td[8]/span")).getText().trim();
        String timeOfMyMessage = timeOfPreviousMessage;
        while (timeOfPreviousMessage.equals(timeOfMyMessage)) {
            LOG.debug("Try to find the my last letter in 3 sec...");
            waitForSeconds(3);
            myMessage = driver.findElement(By.xpath("//table//tr[1]//td[8]/span"));
            timeOfMyMessage = myMessage.getText().trim();
        }
        return true;
    }

    public WelcomePage clickOnMyMessage() {
        myMessage.click();
        return this;
    }

    public String getTextOfMyMessage() {
        return textOfMessage.getText();
    }
}
