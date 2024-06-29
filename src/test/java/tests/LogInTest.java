package tests;

import config.WebDriverFactory;
import io.qameta.allure.internal.shadowed.jackson.core.JsonProcessingException;
import io.qameta.allure.junit4.DisplayName;
import usermodel.User;
import usermodel.UserCreation;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.*;
import pageobjects.LoginPage;
import pageobjects.MainPage;
import pageobjects.RegistrationPage;


public class LogInTest {

    private WebDriverFactory webDriverFactory = new WebDriverFactory();
    MainPage mainPage = new MainPage(webDriverFactory.getWebDriver());
    RegistrationPage registrationPage = new RegistrationPage(webDriverFactory.getWebDriver());
    LoginPage loginPage = new LoginPage(webDriverFactory.getWebDriver());
    private String userAccessToken;
    User user = new User(genString() + "@yandex.ru", genString(), "Username");
    public String genString() {
        return RandomStringUtils.randomAlphabetic(8);
    }

    @Before
    public void setUp() throws JsonProcessingException {
        userAccessToken = UserCreation.getResponseCreateUser(user,200).accessToken;
    }

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной странице")
    public void loginFromEnterInAccountButtonTest() {
        mainPage.open().clickEnterInAccountButton();
        loginPage.logIn(user.getEmail(),user.getPassword());
        Assert.assertTrue("Successful login", mainPage.orderButtonIsDisplayed());
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет» на главной странице»")
    public void loginFromPersonalCabinetButtonTest() {
        mainPage.open().clickPersonalCabinetButton();
        loginPage.logIn(user.getEmail(),user.getPassword());
        Assert.assertTrue("Successful login", mainPage.orderButtonIsDisplayed());
    }

    @Test
    @DisplayName("Вход через кнопку «Войти» в форме регистрации")
    public void loginFromEnterButtonInRegistrationFormTest() {
        registrationPage.open();
        registrationPage.clickRegistrationButton();
        registrationPage.clickEnterButton();
        loginPage.logIn(user.getEmail(),user.getPassword());
        Assert.assertTrue("Successful login", mainPage.orderButtonIsDisplayed());
    }

    @Test
    @DisplayName("Вход через кнопку «Войти» в форме восстановления пароля")
    public void loginFromForgotPasswordButtonTest() {
        loginPage.open();
        loginPage.clickForgotPasswordButton();
        loginPage.clickEnterLink();
        loginPage.logIn(user.getEmail(),user.getPassword());
        Assert.assertTrue("Successful login", mainPage.orderButtonIsDisplayed());
    }

    @After
    public void tearDown() {
        webDriverFactory.quitWebDriver();
        if (userAccessToken != null) {
            UserCreation.getResponseUserDeleted(userAccessToken, 202);
        }
    }
}
