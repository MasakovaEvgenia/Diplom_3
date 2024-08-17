package tests.ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import page.object.PasswordRecoveryPO;
import page.object.RegistrationPO;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import page.object.LoginPagePO;
import page.object.MainPagePO;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(Parameterized.class)
public class LoginTests { // тест логина пользователя
    private WebDriver driver;
    private String email = "masakova_35@yandex.ru";
    private String password = "123456";
    private String accessToken;
    public static final String CHROME = "chrome";
    public static final String YANDEX = "yandex";

    public LoginTests(String browser){
        init(browser);
    }

    // Метод для получения данных
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

    @Before
    public void setUp() {
    }

    @Test
    @DisplayName("Вход через кнопку Войти в аккаунт на главной странице")
    public void loginThroughMainLoginButton() {
        driver.get("https://stellarburgers.nomoreparties.site/");
        MainPagePO mainPage = new MainPagePO(driver);
        mainPage.clickLoginButton();

        LoginPagePO loginPage = new LoginPagePO(driver);
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();

        assertThat("Пользователь успешно вошел в систему", checkLoginSuccess(), equalTo(true));
    }

    @Test
    @DisplayName("Вход через кнопку Личный кабинет")
    public void loginThroughPersonalAccountButton() {
        driver.get("https://stellarburgers.nomoreparties.site/");
        MainPagePO mainPage = new MainPagePO(driver);
        mainPage.clickPersonalAccountButton();
        LoginPagePO loginPage = new LoginPagePO(driver);
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();

        assertThat("Пользователь успешно вошел в систему", checkLoginSuccess(), equalTo(true));
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void loginThroughRegisterForm() {
        driver.get("https://stellarburgers.nomoreparties.site/register");

        RegistrationPO registrationPage = new RegistrationPO(driver);
        registrationPage.clickLoginLink();
        LoginPagePO loginPage = new LoginPagePO(driver);
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
        assertThat("Пользователь успешно вошел в систему", checkLoginSuccess(), equalTo(true));
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void loginThroughForgotPasswordForm() {
        // Открываем страницу восстановления пароля
        driver.get("https://stellarburgers.nomoreparties.site/forgot-password");
        PasswordRecoveryPO passwordRecoveryPage = new PasswordRecoveryPO(driver);
        passwordRecoveryPage.clickLoginLink();
        LoginPagePO loginPage = new LoginPagePO(driver);
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
        // Проверяем, что пользователь успешно вошел в систему
        assertThat("Пользователь успешно вошел в систему", checkLoginSuccess(), equalTo(true));
    }

        private boolean checkLoginSuccess () {
            // Проверка, что пользователь успешно вошел
            return true;
        }

        @After
        public void tearDown () {
            if (driver != null) {
                driver.quit();
            }
        }

    }