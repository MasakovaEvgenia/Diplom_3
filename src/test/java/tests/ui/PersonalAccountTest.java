package tests.ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.object.LoginPagePO;
import page.object.MainPagePO;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class PersonalAccountTest { // переход в личный кабинет

    private WebDriver driver;
    private String email = "masakova_35@yandex.ru";
    private String password = "123456";
    public static final String CHROME = "chrome";
    public static final String YANDEX = "yandex";

    public PersonalAccountTest(String browser){
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
    public void testPersonalAccountNavigation() {
        driver.get("https://stellarburgers.nomoreparties.site/");
        MainPagePO mainPage = new MainPagePO(driver);
        // Нажатие кнопки "Войти в аккаунт"
        mainPage.clickLoginButton();
        // Авторизация
        LoginPagePO loginPage = new LoginPagePO(driver);
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
        // Переход в личный кабинет
        mainPage.clickPersonalAccountButton();
        // Проверка URL страницы
        verifyCurrentUrl("https://stellarburgers.nomoreparties.site/account/profile");
    }

    private void verifyCurrentUrl(String expectedUrl) {
        WebDriverWait wait = new WebDriverWait(driver, 3);
        String currentUrl = driver.getCurrentUrl();
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
