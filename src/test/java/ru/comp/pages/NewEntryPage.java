package ru.comp.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NewEntryPage extends BasePage {

    @FindBy(how = How.ID, using = "id_title")
    private WebElement txtTitleInput;

    @FindBy(how = How.ID, using = "id_slug")
    private WebElement txtSlugInput;

    @FindBy(how = How.ID, using = "id_text_markdown")
    private WebElement txtTextMarkdownTextarea;

    @FindBy(how = How.ID, using = "id_text")
    private WebElement txtTextTextarea;

    @FindBy(how = How.XPATH, using = "//input[@type='submit' and @class='default']")
    private WebElement submitButton;

    private WebElement getTxtTitleInput() {
        return wait.until(ExpectedConditions.visibilityOf(txtTitleInput));
    }

    private WebElement getTxtSlugInput() {
        return wait.until(ExpectedConditions.visibilityOf(txtSlugInput));
    }

    private WebElement getTxtTextMarkdownTextarea() {
        return wait.until(ExpectedConditions.visibilityOf(txtTextMarkdownTextarea));
    }

    private WebElement getTxtTextTextarea() {
        return wait.until(ExpectedConditions.visibilityOf(txtTextTextarea));
    }

    private WebElement getSubmitButton() {
        return wait.until(ExpectedConditions.visibilityOf(submitButton));
    }

    public NewEntryPage(WebDriver webDriver) {
        super(webDriver);
        this.name = "New entry page";
        this.coreElementXPath = "//h1[contains(text(), 'Добавить entry')]";
    }

    public void addEntry(String title, String slug, String textMarkdown, String text) {
        getTxtTitleInput().sendKeys(title);
        getTxtSlugInput().clear();
        getTxtSlugInput().sendKeys(slug);
        getTxtTextMarkdownTextarea().sendKeys(textMarkdown);
        getTxtTextTextarea().sendKeys(text);
        getSubmitButton().click();
    }
}
