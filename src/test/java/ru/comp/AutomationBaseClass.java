package ru.comp.pages;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import ru.comp.SuiteConfiguration;
import ru.stqa.selenium.factory.WebDriverPool;

/**
 * Base automation class for TestNG-based test classes
 */
public class AutomationBaseClass {

    protected static SuiteConfiguration suiteConfiguration;
    protected static WebDriver driver;
    protected static WebDriverWait wait;
    protected static final int OPTIMIZED_TIMEOUT = 5;

    @BeforeClass
    public void initTestSuite() throws IOException {
        suiteConfiguration = new SuiteConfiguration();
        Capabilities capabilities = suiteConfiguration.getCapabilities();
        driver = WebDriverPool.DEFAULT.getDriver(capabilities);
        driver.manage().window().fullscreen();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }


    @AfterClass(alwaysRun = true)
    public void tearDown() {
        WebDriverPool.DEFAULT.dismissAll();
    }

    protected final static WebDriver getDriver() {
        final WebDriver finalDriver = driver;
        return finalDriver;
    }

    protected void WaitForElementToAppear(WebElement element) {
        wait = new WebDriverWait(driver, OPTIMIZED_TIMEOUT);
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException e) {
            System.out.println("TimeoutException, increase timeout!!!");
        }
    }
}