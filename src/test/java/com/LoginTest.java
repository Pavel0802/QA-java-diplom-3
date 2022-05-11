package com;

import com.pageObject.LogInPage;
import com.pageObject.MainPage;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

public class LoginTest {
    UserOperations userOperations = new UserOperations();
    Map<String, String> responseData = new HashMap<>();
    String email;
    String password;
    String name;
    MainPage mainPage;
    LogInPage logInPage;

    @Before
    public void before() {
        //Создаем нового пользователя
        responseData = userOperations.register();
        name = responseData.get("name");
        email = responseData.get("email");
        password = responseData.get("password");
        //Открываем главную страницу
        mainPage = open(MainPage.URL, MainPage.class);
        logInPage = page(LogInPage.class);
    }

    @After
    public void after() {
        userOperations.delete();//удаляем пользователя
    }

    @Test
    @DisplayName("Авторизация пользователя через кнопку 'Войти в аккаунт'")
    @Description("Тест проверяет возможность авторизации пользователя через кнопку 'Войти в аккаунт' на главной странице")
    public void loginAccountButtonTest() {
        mainPage.clickLogInAccountButton()
                .waitLoadPage()
                .loginUser(email, password)
                .waitLoad();
        Assert.assertTrue("there is no 'Place an order' button", mainPage.placeAnOrderButtonIsDisplayed());
    }

    @Test
    @DisplayName("Авторизация пользователя через кнопку 'Личный кабинет'")
    @Description("Тест проверяет возможность авторизации пользователя через кнопку 'Личный кабинет' на главной странице")
    public void loginPersonalAccountButtonTest() {
        mainPage.clickPersonalAccountButton();
        logInPage.waitLoadPage()
                .loginUser(email, password)
                .waitLoad();
        Assert.assertTrue("there is no 'Place an order' button", mainPage.placeAnOrderButtonIsDisplayed());
    }

    @Test
    @DisplayName("Авторизация пользователя через кнопку 'Войти'")
    @Description("Тест проверяет возможность авторизации пользователя через кнопку 'Войти' на странице регистрации")
    public void loginRegistrationPageButtonTest() {
        mainPage.clickLogInAccountButton()
                .waitLoadPage()
                .clickAuthorizationButton()
                .clickLogInButton()
                .waitLoadPage()
                .loginUser(email, password)
                .waitLoad();
        Assert.assertTrue("there is no 'Place an order' button", mainPage.placeAnOrderButtonIsDisplayed());
    }

    @Test
    @DisplayName("Авторизация пользователя через кнопку 'Войти'")
    @Description("Тест проверяет возможность авторизации пользователя через кнопку 'Войти' на странице восстановления пароля")
    public void loginRecoverPasswordButtonTest() {
        mainPage.clickPersonalAccountButton();
        logInPage.waitLoadPage()
                .clickRecoverPasswordButton()
                .clickLogInButton()
                .loginUser(email, password)
                .waitLoad();
        Assert.assertTrue("there is no 'Place an order' button", mainPage.placeAnOrderButtonIsDisplayed());
    }

}

