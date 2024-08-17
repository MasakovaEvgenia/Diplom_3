package page.object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MainPagePO {
    private WebDriver driver;

    // Локаторы главной страницы
    private By loginButton = By.xpath("//button[text()='Войти в аккаунт']"); // кнопка Войти
    private By personalAccountButton = By.xpath("//p[contains(@class, 'AppHeader_header__linkText__3q_va') and contains(text(), 'Личный Кабинет')]"); // кнопка Личный кабинет
    private By constructorButton = By.xpath("//p[contains(@class, 'AppHeader_header__linkText__3q_va') and contains(text(), 'Конструктор')]"); // кнопка Конструктор
    private By logo = By.className("AppHeader_header__logo__2D0X2"); // Логтип Stellar Burgers
    public static By bunsTab = By.xpath("//span[text()='Булки']/parent::div");  // разедл Булки
    public static By saucesTab = By.xpath("//span[text()='Соусы']/parent::div");  // Раздел Соусы
    public static By fillingsTab = By.xpath("//span[text()='Начинки']/parent::div"); // Раздел Начинки


    List<By> constructorTabs = List.of(bunsTab, saucesTab, fillingsTab);

    public MainPagePO(WebDriver driver) {
        driver.get("https://stellarburgers.nomoreparties.site/");
        this.driver = driver;
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public void clickPersonalAccountButton() {
        driver.findElement(personalAccountButton).click();
    }

    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
    }

    public void clickLogo() {
        driver.findElement(logo).click();
    }


    @Step("Клик по кнопке Булки")
    public void clickBunsTab() {
        driver.findElement(bunsTab).click();
    }

    @Step("Клик по табу конструктора")
    public void clickTab(By tab) {
        WebDriverWait wait = new WebDriverWait(driver, 3);

        wait.until(ExpectedConditions.elementToBeClickable(tab));
        driver.findElement(tab).click();
    }

    @Step("Клик по кнопке Соусы")
    public void clickSaucesTab() throws InterruptedException {
        driver.findElement(saucesTab).click();
    }

    @Step("Клик по кнопке Начинки")
    public void clickFillingsTab() throws InterruptedException {
        WebElement element = driver.findElement(fillingsTab);
        element.click();
    }

    @Step("Проверка что раздел выбран")
    public boolean isTabSelected(By selected) throws InterruptedException {
        //Ожидаем скролл
        synchronized (driver)
        {
            driver.wait(2000L);
        }

        WebDriverWait wait = new WebDriverWait(driver, 3);
        boolean result = true;
        for (By constructorTab : constructorTabs) {
            boolean contains = driver.findElement(constructorTab).getAttribute("class").contains("tab_tab_type_current__2BEPc");
            if (constructorTab.equals(selected)) {
                result = result && contains;
            } else {
                result = result && !contains;
            }
        }
        return result;
    }

}
