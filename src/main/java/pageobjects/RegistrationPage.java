package pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static config.UriConstant.URL_REGISTER_PAGE;

public class RegistrationPage {
    private WebDriver driver;
    private final String url = URL_REGISTER_PAGE;
    private final By registerationLink = By.xpath(".//*[text() = 'Зарегистрироваться']");
    private final By nameField = By.xpath(".//label[text() = 'Имя']/parent::div/input");
    private final By emailField = By.xpath(".//label[text() = 'Email']/parent::div/input");
    private final By passwordField = By.xpath(".//input[@name = 'Пароль']");
    private final By registrationButton = By.xpath(".//*[text() = 'Зарегистрироваться']");
    private final By passwordError = By.xpath(".//p[contains(@class, 'input__error')]");
    private final By enterButton = By.xpath(".//*[text() = 'Войти']");
    public final static By header = By.xpath(".//h2");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }


    @Step("Кликнуть на слово Зарегистрироваться")
    public void clickRegisterationLink() {driver.findElement(registerationLink).click();}
    @Step("Ввести имя в поле Имя")
    public void enterName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }
    @Step("Ввести email в поле Email")
    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }
    @Step("Ввести пароль в поле Пароль")
    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }
    @Step("Кликнуть на кнопку Зарегистрироваться")
    public void clickRegistrationButton() {
        driver.findElement(registrationButton).click();
    }
    @Step("Кликнуть на кнопку Войти")
    public void clickEnterButton() {driver.findElement(enterButton).click();}


    @Step("Открыть страницу регистрации пользователя")
    public RegistrationPage open() {
        driver.get(url);
        return this;
    }
    @Step("Заполнить данные пользователя и кликнуть кнопку Войти")
    public void fillUserInfo(String name, String email, String password) {
        clickRegisterationLink();
        enterName(name);
        enterEmail(email);
        enterPassword(password);
        clickRegistrationButton();
    }

    @Step("Проверить, что есть ошибка при вводе некорректного пароля")
    public boolean hasError() {
        try {
            driver.findElement(passwordError);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}