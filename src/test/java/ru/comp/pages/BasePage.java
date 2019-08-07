package ru.comp.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage extends ru.comp.pages.AutomationBaseClass {

    protected String coreElementXPath;
    protected String name;

    @FindBy(how = How.TAG_NAME, using = "h1")
    @CacheLookup
    public WebElement header;

    /*
     * Constructor injecting the WebDriver interface
     * @param webDriver
     */
    public BasePage(WebDriver finalDriver) {
        this.driver = getDriver();
        PageFactory.initElements(new AjaxElementLocatorFactory(finalDriver, OPTIMIZED_TIMEOUT), this);
        wait = new WebDriverWait(driver, OPTIMIZED_TIMEOUT);
    }

    @Step("Check the page has loaded")
    public void IsPresentedCheck() {
        try {
            WaitForElementToAppear(getDriver().findElement(By.xpath(coreElementXPath)));
        }
        catch (NoSuchElementException e) {
            System.out.println("Page " + name + " didn't present!");
            throw e;
        }
    }
}

