package tests.ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import page.object.LoginPagePO;
import page.object.MainPagePO;
import page.object.PersonalAccountPO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class LogoutTest { // тест на нажатие кнопки Выйти
    private WebDriver driver;
    public static final String CHROME = "chrome";
    public static final String YANDEX = "yandex";

    public LogoutTest(String browser){
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

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testLogout() {
        // Открываю главную страницу
        driver.get("https://stellarburgers.nomoreparties.site/");
        MainPagePO mainPage = new MainPagePO(driver);

        // Нажимаю кнопку "Войти в аккаунт"
        mainPage.clickLoginButton();

        // Авторизация на сайте
        LoginPagePO loginPage = new LoginPagePO(driver);
        loginPage.enterEmail("masakova_35@yandex.ru");
        loginPage.enterPassword("123456");
        loginPage.clickLoginButton();

        // Переход в личный кабинет
        mainPage.clickPersonalAccountButton();
        //yandexDriver.get("https://stellarburgers.nomoreparties.site/account/profile");

        // Создаю объект личного кабинета
        PersonalAccountPO personalAccount = new PersonalAccountPO(driver);
        personalAccount.clickLogoutButton();

        // Проверяю, что пользователь перешел на страницу логина
        String currentUrl = driver.getCurrentUrl();
        assertEquals("Пользователь не был перенаправлен на страницу логина после выхода", "https://stellarburgers.nomoreparties.site/login", currentUrl);
    }
    }

