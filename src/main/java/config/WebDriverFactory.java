package config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;


public class WebDriverFactory {

        private static WebDriver webDriver;

        public WebDriver getWebDriver() {
                if (webDriver == null) {
                    String browser = System.getProperty("browser", "chrome");
                    switch (browser) {
                        case "chrome":
                            webDriver = WebDriverManager.chromedriver().create();
                            break;
                        case "yandex":
                            System.setProperty("webdriver.chrome.driver", "src/test/resources/yandexdriver");
                            webDriver = new ChromeDriver();
                            break;
                        default:
                            throw new RuntimeException("Unsupported browser:" + browser);
                    }
                    webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                }
                return webDriver;
            }

        public void quitWebDriver() {
            if (webDriver != null) {
                webDriver.quit();
                webDriver = null;
            }
        }
    }

