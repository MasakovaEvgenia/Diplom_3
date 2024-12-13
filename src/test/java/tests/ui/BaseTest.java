package tests.ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public abstract class BaseTest {
    public static final String YANDEXDRIVER_EXE = "yandexdriver-24.7.0.2299-win64\\yandexdriver.exe";
    public static final String YANDEX_BROWSER_PATH = "C:\\Users\\Jenya\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe";
    protected WebDriver driver;
    public static final String CHROME = "chrome";
    public static final String YANDEX = "yandex";

    public void init(String browser) {
        if (CHROME.equalsIgnoreCase(browser)) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (YANDEX.equalsIgnoreCase(browser)) {
            ChromeOptions options = new ChromeOptions().setBinary(YANDEX_BROWSER_PATH);
            String yandexDriverPath = getClass().getClassLoader().getResource("yandexdriver.exe").getPath();
            System.setProperty("webdriver.chrome.driver", yandexDriverPath);
            driver = new ChromeDriver(options);
        }
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
