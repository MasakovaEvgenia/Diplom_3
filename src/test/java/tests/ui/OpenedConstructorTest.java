package tests.ui;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import page.object.MainPagePO;

import static org.junit.Assert.assertEquals;
@RunWith(Parameterized.class)
public class OpenedConstructorTest { // тест перехода в конструктор
    private String email;
    private String password;
    private String accessToken;
    private WebDriver driver;
    public static final String CHROME = "chrome";
    public static final String YANDEX = "yandex";

    public OpenedConstructorTest(String browser){
        init(browser);
    }
    @Parameterized.Parameters
    public static Object[][] getAccordionData() {
        return new Object[][]{
                {CHROME},{YANDEX}
        };
    }

    public void init(String browser) {
        if (CHROME.equalsIgnoreCase(browser)) { // если браузер хром, тест запуститься на хроме
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (YANDEX.equalsIgnoreCase(browser)) { // если браузер Yandex, то запуститься на Yandex
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\Jenya\\IdeaProjects\\Diplom_3\\yandexdriver-24.7.0.2299-win64\\yandexdriver.exe");
            ChromeOptions options = new ChromeOptions().setBinary("C:\\Users\\Jenya\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
            driver = new ChromeDriver(options);
        }
    }


    @Test
    public void testConstructorNavigation() { // по кнопке конструктор
        MainPagePO mainPage = new MainPagePO(driver);
        // Переход в конструктор
        mainPage.clickConstructorButton();
        // Проверка URL страницы
        verifyCurrentUrl("https://stellarburgers.nomoreparties.site/");
    }

    @Test
    public void testLogoNavigation() {
        // Тест проверяет переход по клику на логотип
        MainPagePO mainPage = new MainPagePO(driver);

        mainPage.clickLogo();
        // Проверка URL страницы
        verifyCurrentUrl("https://stellarburgers.nomoreparties.site/");
    }
    private void verifyCurrentUrl(String expectedUrl) {
        String currentUrl = driver.getCurrentUrl();
        assertEquals("URL не совпадает!", expectedUrl, currentUrl);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
