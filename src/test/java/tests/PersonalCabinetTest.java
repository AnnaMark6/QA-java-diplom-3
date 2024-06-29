package tests;

import config.WebDriverFactory;
import io.qameta.allure.internal.shadowed.jackson.core.JsonProcessingException;
import io.qameta.allure.junit4.DisplayName;
import usermodel.User;
import usermodel.UserCreation;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.LoginPage;
import pageobjects.MainPage;
import pageobjects.PersonalCabinetPage;

import java.time.Duration;

public class PersonalCabinetTest {

    private WebDriverFactory webDriverFactory = new WebDriverFactory();
    MainPage mainPage = new MainPage(webDriverFactory.getWebDriver());
    LoginPage loginPage = new LoginPage(webDriverFactory.getWebDriver());
    PersonalCabinetPage personalCabinetPage = new PersonalCabinetPage(webDriverFactory.getWebDriver());
    private String userAccessToken;
    User user = new User(genString() + "@yandex.ru", genString(), "Username");
    public String genString() {
        return RandomStringUtils.randomAlphabetic(8);
    }

    @Before
    public void setUp() throws JsonProcessingException {
        userAccessToken = UserCreation.getResponseCreateUser(user,200).accessToken;
        loginPage.open();
        loginPage.logIn(user.getEmail(),user.getPassword());
    }

    @Test
    @DisplayName("Переход по клику на «Личный кабинет»")
    public void clickToPersonalCabinetButtonTest(){
        personalCabinetPage.clickPersonalCabinetButton();
        Assert.assertTrue("Перешли в Личный кабинет", new WebDriverWait(webDriverFactory.getWebDriver(),
                Duration.ofSeconds(8)).until(driver -> personalCabinetPage.getLogoutButton().isDisplayed()));
    }

    @Test
    @DisplayName("Переход по клику на «Конструктор»")
    public void clickToConstructorButtonTest() {
        personalCabinetPage.clickConstructorButton();
        Assert.assertTrue("Успешно вошли в аккаунт", mainPage.orderButtonIsDisplayed());
    }

    @Test
    @DisplayName("Переход по клику на логотип Stellar Burgers")
    public void clickToStellarBurgersLogoTest(){
        personalCabinetPage.clickHeaderlogo();
        Assert.assertTrue("Успешно вошли в аккаунт", mainPage.orderButtonIsDisplayed());
    }

    @Test
    @DisplayName("Выход по кнопке «Выйти» в личном кабинете")
    public void logoutButtonTest(){
        personalCabinetPage.clickPersonalCabinetButton();
        personalCabinetPage.logout();
        Assert.assertTrue("Вышли из аккаунта", new WebDriverWait(webDriverFactory.getWebDriver(),
                Duration.ofSeconds(8)).until(driver -> loginPage.getLoginButton().isDisplayed()));
    }

    @After
    public void tearDown() {
        webDriverFactory.quitWebDriver();
        if (userAccessToken != null) {
            UserCreation.getResponseUserDeleted(userAccessToken, 202);
        }
    }

}