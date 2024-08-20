package tests.ui;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import page.object.LoginPagePO;
import page.object.MainPagePO;
import page.object.PersonalAccountPO;
import static entity.UrlConstants.*;
import  static entity.UserCreds.*;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class LogoutTest extends BaseTest {

    public LogoutTest(String browser) {
        init(browser);
    }

    @Parameterized.Parameters
    public static Object[][] getBrowserData() {
        return new Object[][]{
                {CHROME}, {YANDEX}
        };
    }

    @Test
    public void testLogout() {
        driver.get(BASE_URL);
        MainPagePO mainPage = new MainPagePO(driver);

        mainPage.clickLoginButton();

        LoginPagePO loginPage = new LoginPagePO(driver);
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();

        mainPage.clickPersonalAccountButton();

        PersonalAccountPO personalAccount = new PersonalAccountPO(driver);
        personalAccount.clickLogoutButton();

        String currentUrl = driver.getCurrentUrl();
        assertEquals("Пользователь не был перенаправлен на страницу логина после выхода", LOGIN_URL, currentUrl);
    }
}
