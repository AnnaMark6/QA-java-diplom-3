package tests;

import config.WebDriverFactory;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.filter.log.RequestLoggingFilter;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.LoginPage;
import pageobjects.RegistrationPage;
import io.restassured.RestAssured;

import java.time.Duration;


public class RegistrationTest {

    private WebDriverFactory webDriverFactory = new WebDriverFactory();
    RegistrationPage registrationPage = new RegistrationPage(webDriverFactory.getWebDriver());
    LoginPage loginPage = new LoginPage(webDriverFactory.getWebDriver());
    public String genString() {
        return RandomStringUtils.randomAlphabetic(8);
    }

    @Before
    public void setUp() {
        RestAssured.filters(new RequestLoggingFilter());
    }

    @Test
    @DisplayName("Тест: Удачная регистрация пользователя")
    public void successRegistrationTest() {
        registrationPage.open();
        registrationPage.fillUserInfo(genString(), genString() + "@yandex.ru", genString());
        registrationPage.clickRegistrationButton();
        new WebDriverWait(webDriverFactory.getWebDriver(), Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(".//button[text() = 'Войти']")));
        Assert.assertTrue("Удачная регистрация пользователя", loginPage.getHeader().getText().contains("Вход"));
    }

    @Test
    @DisplayName("Тест: Некорректный пароль при регистрации пользователя")
    public void passwordErrorTest() {
        registrationPage.open();
        registrationPage.fillUserInfo(genString(), genString() + "@yandex.ru", "12345");
        Assert.assertTrue("Некорректный пароль", registrationPage.hasError());
    }

    @After
    public void tearDown() {
        webDriverFactory.quitWebDriver();
    }

}