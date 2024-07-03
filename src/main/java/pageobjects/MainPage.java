package pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static config.UriConstant.URL_MAIN_PAGE;

public class MainPage {

    private WebDriver driver;

    private final String url = URL_MAIN_PAGE;
    private final By pageTitle = By.xpath(".//h1[text()='Соберите бургер']");
    private final By personalCabinetButton = By.xpath(".//*[text() = 'Личный Кабинет']");
    private final By enterInAccountButton = By.xpath(".//*[text() = 'Войти в аккаунт']");
    private final By orderButton = By.xpath(".//button[text() = 'Оформить заказ']");
    private final By bunSection = By.xpath(".//span[text() = 'Булки']/parent::div");
    private final By saucesSection = By.xpath(".//span[text() = 'Соусы']/parent::div");
    private final By fillingsSection = By.xpath(".//span[text() = 'Начинки']/parent::div");
    private final By selectedSection = By.xpath(".//div[contains(@class, 'tab_tab_type_current__2BEPc')]");


    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Дождаться загрузки надписи Соберите бургер")
    public void waitForLoadPageTitle() {
        new WebDriverWait(driver, Duration.ofSeconds(8)).until(driver ->
                driver.findElement(pageTitle).isDisplayed());
    }
    @Step("Кликнуть на кнопку Личный кабинет")
    public void clickPersonalCabinetButton() {
        driver.findElement(personalCabinetButton).click();
    }
    @Step("Кликнуть на кнопку Войти в аккаунт")
    public void clickEnterInAccountButton() {driver.findElement(enterInAccountButton).click();}
    @Step("Выбрать секцию Булки")
    public void clickBunSection() {driver.findElement(bunSection).click();}
    @Step("Выбрать секцию Соусы")
    public void clickSaucesSection() {driver.findElement(saucesSection).click();}
    @Step("Выбрать секцию Начинки")
    public void clickFillingsSection() {driver.findElement(fillingsSection).click();}


    @Step("Открыть главную страницу")
    public MainPage open() {
        driver.get(url);
        return this;
    }

    @Step("Дождаться, когда отобразится кнопка Оформить заказ")
    public boolean orderButtonIsDisplayed() {
        waitForLoadPageTitle();
        return driver.findElement(orderButton).isDisplayed();
    }

    @Step("Найти выбранную секцию")
    public String getSelectedSection() {
        return driver.findElement(selectedSection).getText();
    }
    @Step("Найти секцию Булки")
    public WebElement getBunSection() {return driver.findElement(bunSection);};

}