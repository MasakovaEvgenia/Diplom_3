package tests.ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import page.object.MainPagePO;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class SectionsNavigationTest { // переход между разделами в конструкторе
    private WebDriver driver;
    public static final String CHROME = "chrome";
    public static final String YANDEX = "yandex";

    public SectionsNavigationTest(String browser){
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
    @DisplayName("Проверка перехода к разделу Булки")
    public void testSaucesSectionNavigation() throws InterruptedException {
        MainPagePO mainPage = new MainPagePO(driver);
        // Переход в раздел Соусы
        mainPage.clickSaucesTab();
        // Проверка, что раздел Булки виден
        assertTrue(mainPage.isTabSelected(MainPagePO.saucesTab));
        //assertTrue("Раздел 'Булки' не отображается!", mainPage.isBunsSectionVisible());
    }

    @Test
    @DisplayName("Проверка перехода к разделу Соусы")
    public void testBunsSectionNavigation() throws InterruptedException {
        MainPagePO mainPage = new MainPagePO(driver);
        // Переход в раздел Булки невозможен, выбран при открытии страницы. Поэтому соусы, затем булки
        mainPage.clickSaucesTab();
        mainPage.clickBunsTab();
        // Проверка, что раздел Соусы виден
        assertTrue(mainPage.isTabSelected(MainPagePO.bunsTab));
    }

    @Test
    @DisplayName("Проверка перехода к разделу Начинки")
    public void testFillingsSectionNavigation() throws InterruptedException {
        MainPagePO mainPage = new MainPagePO(driver);
        // Переход в раздел Начинки
        mainPage.clickFillingsTab();
        // Проверка, что раздел Начинки виден
        assertTrue(mainPage.isTabSelected(MainPagePO.fillingsTab));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
