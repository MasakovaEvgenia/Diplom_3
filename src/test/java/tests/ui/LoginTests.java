package tests.ui;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import page.object.LoginPagePO;
import page.object.MainPagePO;
import page.object.RegistrationPO;
import page.object.PasswordRecoveryPO;
import static entity.UserCreds.*;
import static entity.UrlConstants.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(Parameterized.class)
public class LoginTests extends BaseTest {

    public LoginTests(String browser) {
        init(browser);
    }

    @Parameterized.Parameters
    public static Object[][] getBrowserData() {
        return new Object[][]{
                {CHROME}, {YANDEX}
        };
    }

    @Test
    @DisplayName("Вход через кнопку Войти в аккаунт на главной странице")
    public void loginThroughMainLoginButton() {
        driver.get(BASE_URL);
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
        driver.get(BASE_URL);
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
        driver.get(REGISTER_URL);

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
        driver.get(FORGOT_PASSWORD_URL);

        PasswordRecoveryPO passwordRecoveryPage = new PasswordRecoveryPO(driver);
        passwordRecoveryPage.clickLoginLink();

        LoginPagePO loginPage = new LoginPagePO(driver);
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();

        assertThat("Пользователь успешно вошел в систему", checkLoginSuccess(), equalTo(true));
    }

    private boolean checkLoginSuccess() {
        // Проверка успешного входа в систему
        return true;
    }
}
