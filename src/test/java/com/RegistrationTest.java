package com;

import com.pageObject.LogInPage;
import com.pageObject.MainPage;
import com.pageObject.RegisterPage;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

public class RegistrationTest {
    UserOperations userOperations = new UserOperations();
    Map<String, String> responseData = new HashMap<>();
    String email;
    String password;
    String name;
    MainPage mainPage;
    LogInPage logInPage;
    RegisterPage registerPage;
    UserLoginRequest userLoginRequest = new UserLoginRequest();

    @Before
    public void before() {
        //Создаем нового пользователя
        responseData = userOperations.register();
        name = responseData.get("name");
        email = responseData.get("email");
        password = responseData.get("password");
        //удаляем пользователя через API, чтобы зарегистрироваться через UI
        userOperations.delete();
        //Открываем главную страницу и переходим на страницу регистрации нового пользователя
        mainPage = open(MainPage.URL, MainPage.class);
        logInPage = page(LogInPage.class);
        registerPage = page(RegisterPage.class);
        mainPage.clickLogInAccountButton()
                .clickAuthorizationButton();
    }

    @After
    public void after() {
        userLoginRequest.tokenDelete(email, password); //удаляем пользователя, если он создался
    }

    @Test
    @DisplayName("Проверка регистрации нового пользователя")
    @Description("Тест проверяет возможность регистрации нового пользователя при корректных данных")
    public void testPositiveRegistration() throws InterruptedException {
        registerPage.registrationUser(name, email, password);
        Thread.sleep(1000);//ставим явное ожидание для загрузки ошибки, в случае некорректного ввода данных
        Assert.assertFalse("invalid password", registerPage.errorPasswordIsDisplayed());
        Assert.assertFalse("User is already registered", registerPage.errorUserIsAlreadyRegisteredIsDisplayed());
        //в случае успешной регистрации производим вход в аккаунт и проверку наличия кнопки 'Оформить заказ'
        logInPage.waitLoadPage()
                .loginUser(email, password)
                .waitLoad();
        Assert.assertTrue("there is no 'Place an order' button", mainPage.placeAnOrderButtonIsDisplayed());
    }

    @Test
    @DisplayName("Проверка выхода ошибки при вводе короткого пароля при регистрации пользователя")
    @Description("Тест проверяет выход ошибки при вводе короткого пароля при регистрации нового пользователя")
    public void incorrectPasswordTest() {
        password = RandomStringUtils.randomAlphabetic(5);//заменяем пароль на невалидный
        registerPage.registrationUser(name, email, password);
        Assert.assertTrue("invalid password", registerPage.errorPasswordIsDisplayed());
    }
}
