package page.object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPO {
    private WebDriver driver;

    // Локаторы элементов на странице регистрации
    private By nameField = By.xpath("//input[@type='text' and @name='name']"); // имя пользователя
    private By emailField = By.xpath("//input[@type='text' and @name='name']"); // почта
    private By passwordField = By.xpath("//input[@type='password' and @name='Пароль']"); // пароль
    private By registerButton = By.xpath("//button[text()='Зарегистрироваться']");
    private By loginLink = By.xpath("//a[text()='Войти']");

    public RegistrationPO(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ввод имени пользователя")
    public void enterName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    @Step("Ввод email")
    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Ввод пароля")
    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Клик по кнопке Зарегистрироваться")
    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    @Step("Клик по ссылке Войти")
    public void clickLoginLink() {
        driver.findElement(loginLink).click();
    }
}
