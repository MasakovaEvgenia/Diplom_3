package tests.ui;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import page.object.MainPagePO;

import static entity.UrlConstants.BASE_URL;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class OpenedConstructorTest extends BaseTest {

    public OpenedConstructorTest(String browser) {
        init(browser);
    }

    @Parameterized.Parameters
    public static Object[][] getBrowserData() {
        return new Object[][]{
                {CHROME}, {YANDEX}
        };
    }

    @Test
    public void testConstructorNavigation() {
        driver.get(BASE_URL);
        MainPagePO mainPage = new MainPagePO(driver);
        mainPage.clickConstructorButton();
        verifyCurrentUrl(BASE_URL);
    }

    @Test
    public void testLogoNavigation() {
        driver.get(BASE_URL);
        MainPagePO mainPage = new MainPagePO(driver);
        mainPage.clickLogo();
        verifyCurrentUrl(BASE_URL);
    }

    private void verifyCurrentUrl(String expectedUrl) {
        String currentUrl = driver.getCurrentUrl();
        assertEquals("URL не совпадает!", expectedUrl, currentUrl);
    }
}
