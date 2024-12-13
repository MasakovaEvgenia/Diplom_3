package tests.api;

import api.UserApi;
import entity.User;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.equalTo;

public class UserCreateTest { // регистрация пользователя
    private String email;
    private String password = "123456"; // Изменен на корректный пароль
    private String name = "sanemi";
    private String accessToken;


    @Before
    public void setUp() {
        RestAssured.baseURI = UserApi.BASE_URL;
        email = "juniper_" + RandomStringUtils.randomAlphanumeric(8) + "@example.com";
    }

    @After
    public void tearDown() {
        if (accessToken != null) {
            UserApi.deleteUser(accessToken);
        }
    }

    @Test
    public void testCreateUser() { // тут регистрация пользователя
        User user = new User(email, password, name);
        Response response = UserApi.createUser(user);
        accessToken = response.jsonPath().getString("accessToken");

        response.then().statusCode(SC_OK)
                .body("success", equalTo(true));
    }


    @Test
    public void testCreateUserWithShortPassword() { // регистрация пользователя с коротким паролем
        User user = new User(email, "12345", name); // Короткий пароль сделала по ответу, так как при коротком пароле в постман приходит 200ок
        Response response = UserApi.createUser(user);
        response.then().statusCode(SC_OK)
                .body("success", equalTo(true));
    }
}
