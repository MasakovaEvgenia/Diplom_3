package page.object;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PasswordRecoveryPO {
    private WebDriver driver;

    // Локаторы элементов на странице восстановления пароля
    private By emailField = By.xpath("//input[@type='text' and @name='name']");
    private By restoreButton = By.xpath("//button[text()='Восстановить']");
    private By loginLink = By.xpath("//a[text()='Войти']");

    public PasswordRecoveryPO(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ввод email для восстановления пароля")
    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Клик по кнопке Восстановить")
    public void clickRestoreButton() {
        driver.findElement(restoreButton).click();
    }

    @Step("Клик по ссылке Войти")
    public void clickLoginLink() {
        driver.findElement(loginLink).click();
    }
}
