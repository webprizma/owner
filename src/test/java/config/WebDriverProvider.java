package config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.Objects;
import java.util.function.Supplier;

public class WebDriverProvider implements Supplier<WebDriver> {

    private final WebDriverConfig config;

    public WebDriverProvider() {
        this.config = ConfigFactory.create(WebDriverConfig.class, System.getProperties());
    }

    @Override
    public WebDriver get() {
        WebDriver driver = createDriver();
        driver.get(config.getBaseUrl());
        return driver;
    }

    public WebDriver createDriver() {
        if (Objects.isNull(config.getRemoteURL())) {
            switch (config.getBrowser()) {
                case CHROME: {
                    WebDriverManager.chromedriver().driverVersion(config.getVersion()).setup();
                    return new ChromeDriver();
                }
                case FIREFOX: {
                    WebDriverManager.firefoxdriver().driverVersion(config.getVersion()).setup();
                    return new FirefoxDriver();
                }
                default: {
                    throw new RuntimeException("No such driver");
                }
            }
        } else {
            switch (config.getBrowser()) {
                case CHROME: {
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.setCapability("browserName", "chrome");
                    chromeOptions.setCapability("version", config.getVersion());
                    return new RemoteWebDriver(config.getRemoteURL(), chromeOptions);
                }
                case FIREFOX: {
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.setCapability("browserName", "firefox");
                    firefoxOptions.setCapability("version", config.getVersion());
                    return new RemoteWebDriver(config.getRemoteURL(), firefoxOptions);
                }
                default: {
                    throw new RuntimeException("No such driver");
                }
            }
        }
    }
}