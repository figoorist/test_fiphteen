package ru.comp.tests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.comp.pages.*;
import ru.comp.pages.AutomationBaseClass;

public class EntryTestClass extends AutomationBaseClass {
    private static LoginPage loginPage;
    private static HomePage homePage;
    private static NewEntryPage newEntryPage;
    private static EntriesPage entriesPage;

    @BeforeClass
    public static void initPageObjects() {
        String baseUrl = suiteConfiguration.getProperty("site.url");
        String login = suiteConfiguration.getProperty("site.login");
        String password = suiteConfiguration.getProperty("site.password");
        loginPage = new LoginPage(getDriver(), baseUrl);
        loginPage.goToBaseUrl();
        loginPage.IsPresentedCheck();
        loginPage.login(login, password);
        homePage = new HomePage(getDriver());
        homePage.IsPresentedCheck();
    }

    @Test
    @Parameters({"title", "slug", "textMarkdown", "text"})
    public void testEntryAdding(String title, String slug, String textMarkdown, String text) {
        homePage.goToNewEntryPage();
        newEntryPage = new NewEntryPage(getDriver());
        newEntryPage.IsPresentedCheck();
        newEntryPage.addEntry(title, slug, textMarkdown, text);
        entriesPage = new EntriesPage(getDriver());
        entriesPage.IsPresentedCheck();
        Assert.assertTrue(entriesPage.containsEntry(title, slug), "Entry not listed!");
        entriesPage.deleteFirstEntry(title, slug);
    }
}
