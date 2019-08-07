package ru.comp.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    private String baseUrl;

    @FindBy(how = How.ID, using = "id_username")
    private WebElement txtLoginInput;

    @FindBy(how = How.ID, using = "id_password")
    private WebElement txtPasswordInput;

    @FindBy(how = How.XPATH, using = "//input[@type='submit']")
    private WebElement submitButton;

    private WebElement getTxtLoginInput() {
        return wait.until(ExpectedConditions.visibilityOf(txtLoginInput));
    }

    private WebElement getTxtPasswordInput() {
        return wait.until(ExpectedConditions.visibilityOf(txtPasswordInput));
    }

    private WebElement getSubmitButton() {
        return wait.until(ExpectedConditions.visibilityOf(submitButton));
    }

    public LoginPage(WebDriver webDriver, String baseUrl) {
        super(webDriver);
        this.baseUrl = baseUrl;
        this.name = "Login page";
        this.coreElementXPath = "//input[@type='submit']";
    }

    @Step("Go to Login page")
    public void goToBaseUrl() {
        getDriver().get(baseUrl);
    }

    @Step("Account login")
    public void login(String login, String password) {
        getTxtLoginInput().sendKeys(login);
        getTxtPasswordInput().sendKeys(password);
        getSubmitButton().click();
    }
}
