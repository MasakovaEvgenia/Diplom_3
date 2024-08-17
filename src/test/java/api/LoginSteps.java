package api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;
import page.object.LoginPagePO;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class LoginSteps {
    private WebDriver driver;
    private LoginPagePO loginPage;

    public LoginSteps(WebDriver driver) {
        this.driver = driver;
        this.loginPage = new LoginPagePO(driver);
    }

    @Step("Ввод email: {email}")
    public void enterEmail(String email) {
        loginPage.enterEmail(email);
    }

    @Step("Ввод пароля: {password}")
    public void enterPassword(String password) {
        loginPage.enterPassword(password);
    }

    @Step("Клик по кнопке Войти")
    public void clickLoginButton() {
        loginPage.clickLoginButton();
    }

    @Step("Авторизация через API с email: {email} и паролем: {password}")
    public Response loginUser(String email, String password) {
        String endpoint = "https://stellarburgers.nomoreparties.site/api/auth/login";

        return given()
                .header("Content-Type", "application/json")
                .body("{\"email\":\"" + email + "\", \"password\":\"" + password + "\"}")
                .when()
                .post(endpoint)
                .then()
                .assertThat()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("accessToken", notNullValue())
                .body("user.email", equalTo(email))
                .body("user.name", notNullValue())
                .extract()
                .response();
    }
}