package com.gmail.test.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.gmail.test.util.Browser;

/*
 * Factory to instantiate a WebDriver object. It returns an instance of the driver (local invocation)
 */
public class WebDriverFactory {

    /* Browsers constants */
    public static final String CHROME = "chrome";
    public static final String FIREFOX = "firefox";
    public static final String INTERNET_EXPLORER = "ie";

    public static final WebDriverFactory INSTANCE = new WebDriverFactory();
    public static WebDriver webDriver = null;
    private static final String DRIVERS_FOLDER = System.getProperty("user.dir")+"\\src\\main\\resources\\";
    public static final String DOWNLOAD_DIR = System.getProperty("user.dir") + "\\target\\downloadFiles\\";

    private WebDriverFactory() {
    }

    public static WebDriver getWebDriver(Browser browser) {

        if (INSTANCE.webDriver != null) {
            return INSTANCE.webDriver;
        }

        if (CHROME.equals(browser.getName())) {
            System.setProperty("webdriver.chrome.driver", DRIVERS_FOLDER+"chromedriver.exe");
            INSTANCE.webDriver = new ChromeDriver(setChromeOptions());

        } else if (FIREFOX.equals(browser)) {
            INSTANCE.webDriver = new FirefoxDriver(setFireFoxCapability());

        } else if (INTERNET_EXPLORER.equals(browser)) {
            INSTANCE.webDriver = new InternetExplorerDriver(setIExplorerCapability());
        }
        return INSTANCE.webDriver;
    }

    private static FirefoxProfile setFirefoxDriverProfile() {
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.download.manager.showWhenStarting", false);
        profile.setPreference("browser.download.dir", DOWNLOAD_DIR);
        profile.setPreference("browser.helperApps.neverAsk.openFile",
                "text/csv,application/x-msexcel,application/excel,application/x-excel,application/vnd.ms-excel,image/png," +
                        "image/jpeg,text/html,text/plain,application/msword,application/xml,application/pdf");
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "text/csv,application/x-msexcel,application/excel," +
                "application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword," +
                "application/xml,application/pdf");
        profile.setPreference("pdfjs.disabled", true);
        profile.setPreference("browser.helperApps.alwaysAsk.force", false);
        profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
        profile.setPreference("browser.download.manager.focusWhenStarting", false);
        profile.setPreference("browser.download.manager.useWindow", false);
        profile.setPreference("browser.download.manager.showAlertOnComplete", false);
        profile.setPreference("browser.download.manager.closeWhenDone", false);
        profile.setPreference("network.http.phishy-userpass-length", 255);
        return profile;
    }

    private static DesiredCapabilities setFireFoxCapability() {
        DesiredCapabilities capability = DesiredCapabilities.firefox();
        capability.setCapability(FirefoxDriver.PROFILE, setFirefoxDriverProfile());
        capability.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
        capability.setCapability("ignoreZoomSetting", true);
        return capability;
    }

    private static DesiredCapabilities setIExplorerCapability() {
        DesiredCapabilities capability = DesiredCapabilities.internetExplorer();
        capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        capability.setCapability("ignoreZoomSetting", true);
        return capability;
    }

    private static ChromeOptions setChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("test-type");
        options.addArguments("disable-infobars");
        options.addArguments("chrome.switches", "--disable-extensions");
        options.addArguments("--start-maximized");
        return options;
    }

}