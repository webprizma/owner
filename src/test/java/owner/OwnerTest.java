package owner;

import config.WebDriverProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OwnerTest {

    private WebDriver driver;

    @BeforeEach
    void startDriver() {
        driver = new WebDriverProvider().get();
    }

    @Test
    void testGitHub() {
        String title = driver.getTitle();
        assertEquals("GitHub: Where the world builds software · GitHub", title);
    }

    @AfterEach
    void stopDriver() {
        driver.quit();
    }
}
