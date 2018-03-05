package com.gmail.test.scope;

import com.gmail.test.pages.LoginPage;
import com.gmail.test.TestBase;
import com.gmail.test.pages.NewMessagePage;
import com.gmail.test.pages.WelcomePage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SendLetterTest extends TestBase {

    private final static String EMAIL = "hryshyntest@gmail.com";
    private final static String PASSWORD = "hryshyn1";
    private final static String MESSAGE = "Hello, word!";

    LoginPage loginPage;
    WelcomePage welcomePage;
    NewMessagePage newMessagePage;

    @BeforeClass
    public void init() {
        loginPage = new LoginPage();
        Assert.assertTrue(loginPage.isPageLoaded());
    }

    @Test(priority = 1)
    public void loginTest() {
        welcomePage = loginPage
                .typeLogin(EMAIL)
                .clickNext()
                .typePassword(PASSWORD)
                .clickNextToGetMail();
        Assert.assertTrue(welcomePage.isPageLoaded());
    }

    @Test(priority = 2, dependsOnMethods = "loginTest")
    public void sendLetterTest() {
        newMessagePage = welcomePage.clickWriteLetterButton();
        Assert.assertTrue(newMessagePage.isPageLoaded());
        Assert.assertTrue(newMessagePage
                .typeAddress(EMAIL)
                .typeMessage(MESSAGE)
                .clickSendButton()
                .isPageLoaded());
    }

    @Test(priority = 3, dependsOnMethods = "sendLetterTest")
    public void checkIfMyLetterReceivedTest() {
        Assert.assertTrue(welcomePage.isMyMessageReceived());
    }

    @Test(priority = 4, dependsOnMethods = "checkIfMyLetterReceivedTest")
    public void checkTextOfMyLetterTest() {
        Assert.assertEquals(welcomePage.clickOnMyMessage().getTextOfMyMessage(), MESSAGE);
    }

}
