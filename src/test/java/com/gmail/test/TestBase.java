package com.gmail.test;


import com.gmail.test.factory.WebDriverFactory;
import com.gmail.test.util.Browser;
import com.gmail.test.util.PropertyLoader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class TestBase {

    protected WebDriver driver;

    protected String baseUrl;

    protected Browser browser;

    @BeforeSuite
    public void cleanTestData() {
        baseUrl = PropertyLoader.loadProperty("site.url");
        browser = Browser.getInstance();
        driver = WebDriverFactory.INSTANCE.getWebDriver(browser);
        driver.get(baseUrl);
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
