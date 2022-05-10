package com;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.model.Tokens;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static com.Base.BASE_URL;
import static io.restassured.RestAssured.given;

public class UserLoginRequest {
    UserOperations userOperations = new UserOperations();
    public static final String USER_PATH = BASE_URL + "auth/";

    @Step("Регистрация пользователя {userLogin}")
    public static Response login(String userLogin) {
        return given()
                .spec(Base.getBaseSpec())
                .body(userLogin)
                .post(USER_PATH + "login/");
    }

    public void tokenDelete (String email, String password){
        JsonElement accessTokenFull =  login("{\"" +
                        "email\":\"" + email + "\",\"" +
                        "password\":\"" + password + "\"}").thenReturn()
                .body().as(JsonObject.class).get("accessToken");
        if (accessTokenFull != null) {
        String accessToken = accessTokenFull.toString().substring(8, 179);
                    Tokens.setAccessToken(accessToken);
                    userOperations.delete();
        }
    }
}
