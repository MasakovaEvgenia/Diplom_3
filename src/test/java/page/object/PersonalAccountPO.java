package page.object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PersonalAccountPO {
    private WebDriver driver;
    private WebDriverWait wait;

    // Локатор для кнопки "Выход"
    private By logoutButton = By.xpath("//button[contains(@class, 'Account_button__14Yp3') and text()='Выход']");

    public PersonalAccountPO(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10000);
    }

    @Step("Нажатие на кнопку Выйти")
    public void clickLogoutButton() {
        // Ожидание видимости кнопки "Выход"
        wait.until(ExpectedConditions.visibilityOfElementLocated(logoutButton));
        driver.findElement(logoutButton).click();
        // Ожидание перехода на страницу логина
        wait.until(ExpectedConditions.urlToBe("https://stellarburgers.nomoreparties.site/login"));
    }

    @Step("Проверка, что пользователь находится на странице логина")
    public boolean isOnLoginPage() {
        return driver.getCurrentUrl().equals("https://stellarburgers.nomoreparties.site/login");
    }
}
