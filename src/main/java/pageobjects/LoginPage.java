package pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static config.UriConstant.URL_LOGIN_PAGE;

public class LoginPage {

    private WebDriver driver;
    private final String url = URL_LOGIN_PAGE;
    private final By emailField = By.xpath(".//label[text() = 'Email']/parent::div/input");
    private final By passwordField = By.xpath(".//input[@name = 'Пароль']");
    private final By enterButton = By.xpath(".//button[text() = 'Войти']");
    private final By forgotPasswordButton = By.xpath(".//*[text() = 'Восстановить пароль']");
    private final By enterLink = By.xpath(".//*[text() = 'Войти']");
    public final static By header = By.xpath(".//h2");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Кликнуть на кнопку Восстановить пароль")
    public void clickForgotPasswordButton() {driver.findElement(forgotPasswordButton).click();}
    @Step("Кликнуть на слово Войти")
    public void clickEnterLink() {driver.findElement(enterLink).click();}
    @Step("Найти кнопку Войти")
    public WebElement getLoginButton() {return driver.findElement(enterButton);}
    @Step("Найти хэдер]")
    public WebElement getHeader() {
        return driver.findElement(header);
    }

    @Step("Открыть страницу логина пользователя")
    public LoginPage open() {
        driver.get(url);
        return this;
    }

    @Step("Заполнить поля для логина пользователя и кликнуть на кнопку Войти")
    public void logIn(String email, String password) {
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(enterButton).click();
    }
}