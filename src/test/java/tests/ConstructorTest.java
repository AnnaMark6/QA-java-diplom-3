package tests;

import config.WebDriverFactory;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import pageobjects.MainPage;



public class ConstructorTest {

    private WebDriverFactory webDriverFactory = new WebDriverFactory();
    MainPage mainPage = new MainPage(webDriverFactory.getWebDriver());

    @Test
    @DisplayName("Проверка перехода к разделу «Булки»")
    public void bunSectionTest() {
        mainPage.open();
        mainPage.clickSaucesSection();
        mainPage.getBunSection().isEnabled();
        mainPage.clickBunSection();
        Assert.assertTrue("Переход к разделу «Булки»", mainPage.getSelectedSection().contains("Булки"));
    }

    @Test
    @DisplayName("Проверка перехода к разделу «Соусы»")
    public void saucesSectionTest() {
        mainPage.open();
        mainPage.clickSaucesSection();
        Assert.assertTrue("Переход к разделу «Соусы»", mainPage.getSelectedSection().contains("Соусы"));
    }

    @Test
    @DisplayName("Проверка перехода к разделу «Начинки»")
    public void fillingSectionTest() {
        mainPage.open();
        mainPage.clickFillingsSection();
        Assert.assertTrue("Переход к разделу «Начинки»", mainPage.getSelectedSection().contains("Начинки"));
    }

    @After
    public void tearDown() {
        webDriverFactory.quitWebDriver();
    }
}
