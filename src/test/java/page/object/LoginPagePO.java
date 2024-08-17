package page.object;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class LoginPagePO { // локаторы страницы логина пользователя
    private WebDriver driver;

    // Локаторы страницы логина
    private By emailField = By.xpath("//input[@name='name']");
    private By passwordField = By.xpath("//input[@name='Пароль']");
    private By loginButton = By.xpath("//button[text()='Войти']");
    private By registerLink = By.xpath("//a[text()='Зарегистрироваться']");
    private By forgotPasswordLink = By.xpath("//a[text()='Восстановить пароль']");

    public LoginPagePO(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ввести email")
    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Ввести пароль")
    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Клик по кнопке Войти")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Клик по ссылке Зарегистрироваться")
    public void clickRegisterLink() {
        driver.findElement(registerLink).click();
    }

    @Step("Клик по ссылке Восстановить пароль")
    public void clickForgotPasswordLink() {
        driver.findElement(forgotPasswordLink).click();
    }
}
