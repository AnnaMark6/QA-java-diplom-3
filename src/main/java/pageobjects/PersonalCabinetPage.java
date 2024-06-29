package pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static config.UriConstant.URL_PROFILE_PAGE;

public class PersonalCabinetPage {

    private WebDriver driver;
    private final String url = URL_PROFILE_PAGE;

    private final By personalCabinetButton = By.xpath(".//*[text()='Личный Кабинет']");
    private final By constructorButton = By.xpath(".//p[text() = 'Конструктор']");
    private final By headerLogo = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']");
    private final By logoutButton = By.xpath(".//button[text() = 'Выход']");

    public PersonalCabinetPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Кликнуть на кнопку Личный кабинет")
    public void clickPersonalCabinetButton() {driver.findElement(personalCabinetButton).click();}
    @Step("Кликнуть на Конструктор")
    public void clickConstructorButton() {driver.findElement(constructorButton).click();}
    @Step("Кликнуть на лого в хэдере")
    public void clickHeaderlogo() {driver.findElement(headerLogo);}
    @Step("Найти кнопку Выход")
    public WebElement getLogoutButton() {return driver.findElement(logoutButton);}

    @Step("Кликнуть на кнопку Выход")
    public void logout(){
        driver.findElement(logoutButton).click();
    }

}
