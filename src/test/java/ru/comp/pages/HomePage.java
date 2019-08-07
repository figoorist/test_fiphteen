package ru.comp.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Sample page
 */
public class HomePage extends BasePage {

    @FindBy(how = How.XPATH, using = "//div[@class='dashboard-module-content']//li[.//a[text()='Entries']]/ul/li/a[@class='addlink']")
    private WebElement newEntryLink;

    private WebElement getNewEntryLink() {
        return wait.until(ExpectedConditions.visibilityOf(newEntryLink));
    }

    public HomePage(WebDriver webDriver) {
        super(webDriver);
        name = "Home page";
        coreElementXPath = "//a[contains(text(),'Панель управления')]";
    }

    @Step("Go to New entry page")
    public void goToNewEntryPage() {
        getNewEntryLink().click();
    }
}
