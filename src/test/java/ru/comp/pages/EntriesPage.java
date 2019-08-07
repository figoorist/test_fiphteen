package ru.comp.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class EntriesPage extends BasePage {

    @FindBy(how = How.NAME, using = "action")
    private WebElement actionDropdown;

    @FindBy(how = How.XPATH, using = "//button[text()='Выполнить']")
    private WebElement runActionButton;

    @FindBy(how = How.XPATH, using = "//input[@value='Да, я уверен']")
    private WebElement youSureButton;

    private WebElement getActionDropdown() {
        return wait.until(ExpectedConditions.visibilityOf(actionDropdown));
    }

    private WebElement getRunActionButton() {
        return wait.until(ExpectedConditions.visibilityOf(runActionButton));
    }

    private WebElement getYouSureButton() {
        return wait.until(ExpectedConditions.visibilityOf(youSureButton));
    }

    private String getEntryXPath(String title, String slug) {
        return "//tbody/tr[.//th/a[text()='" + title + "'] and .//td[text()='" + slug + "']]";
    }

    public EntriesPage(WebDriver webDriver) {
        super(webDriver);
        this.name = "Entries page";
        this.coreElementXPath = "//h1[contains(text(), 'Выберите entry для изменения')]";
    }

    @Step("Check if there is an entry we added")
    public boolean containsEntry(String title, String slug) {
        boolean isDisplayed = false;
        try {
            isDisplayed = wait.until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath(getEntryXPath(title, slug))))).isDisplayed();
        }
        catch (NoSuchElementException e) {
        }
        return isDisplayed;
    }

    @Step("Get entries count")
    public int getEntriesCount(String title, String slug) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(getEntryXPath(title, slug)))).size();
    }

    @Step("Click check entry checkbox")
    private void checkFirstEntry(String title, String slug) {
        String entryCheckXPath = "/td/input[contains(@class, 'action-select')]";
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(getEntryXPath(title, slug) + entryCheckXPath))).get(0).click();
    }

    private void setActionDropdown(String value) {
        getDriver().findElement(By.xpath("//select[@name='action']/option[@value='" + value + "']")).click();
    }

    @Step("Delete entry we added and check it")
    public void deleteFirstEntry(String title, String slug) {
        int countAfter = getEntriesCount(title, slug);
        checkFirstEntry(title, slug);
        setActionDropdown("delete_selected");
        getRunActionButton().click();
        getYouSureButton().click();
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath(getEntryXPath(title, slug)),countAfter - 1));
    }
}
