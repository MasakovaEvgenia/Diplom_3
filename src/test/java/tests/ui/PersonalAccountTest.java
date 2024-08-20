package tests.ui;


import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.object.LoginPagePO;
import page.object.MainPagePO;
import  static entity.UserCreds.*;
import static entity.UrlConstants.BASE_URL;
import static entity.UrlConstants.PROFILE_URL;


@RunWith(Parameterized.class)
public class PersonalAccountTest extends BaseTest {


    public PersonalAccountTest(String browser) {
        init(browser);
    }

    @Parameterized.Parameters
    public static Object[][] getAccordionData() {
        return new Object[][]{
                {CHROME}, {YANDEX}
        };
    }

    @Test
    public void testPersonalAccountNavigation() {
        driver.get(BASE_URL);
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
        verifyCurrentUrl(PROFILE_URL);
    }

    private void verifyCurrentUrl(String expectedUrl) {
        WebDriverWait wait = new WebDriverWait(driver, 3);
        String currentUrl = driver.getCurrentUrl();
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
    }

}
