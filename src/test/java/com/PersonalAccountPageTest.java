package com;

import com.pageObject.LogInPage;
import com.pageObject.MainPage;
import com.pageObject.ProfilePage;
import com.pageObject.RegisterPage;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

public class PersonalAccountPageTest {
    UserOperations userOperations = new UserOperations();
    Map<String, String> responseData = new HashMap<>();
    String email;
    String password;
    String name;
    MainPage mainPage;
    LogInPage logInPage;
    ProfilePage profilePage;

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
        profilePage = page(ProfilePage.class);
    }

    @After
    public void after() {
        userOperations.delete();//удаляем пользователя
    }

    @Test
    public void loginOnPersonalAccountTest() {
        mainPage.clickPersonalAccountButton(); //Переход в личный кабинет
        logInPage.loginUser(email, password) //Регистрация пользователя
                .waitLoad()
                .clickPersonalAccountButton(); //Переход в личный кабинет авторизированного пользователя
        profilePage.waitLoadPage();
        Assert.assertTrue("there is no 'Profile' button", profilePage.visibleProfileButton());
    }

    @Test
    public void clickFromPersonalAccountToConstructorTest() {
        mainPage.clickPersonalAccountButton(); //Переход в личный кабинет
        logInPage.loginUser(email, password) //Регистрация пользователя
                .waitLoad()
                .clickPersonalAccountButton(); //Переход в личный кабинет авторизированного пользователя
        profilePage.waitLoadPage();
        mainPage.clickConstructorHeader() //Переход по кнопке 'Конструктор'
                .waitLoad();
        Assert.assertTrue("there is no 'Place an order' button", mainPage.placeAnOrderButtonIsDisplayed());
    }

    @Test
    public void clickFromPersonalAccountToStellarBurgerHeaderTest() {
        mainPage.clickPersonalAccountButton(); //Переход в личный кабинет
        logInPage.loginUser(email, password) //Регистрация пользователя
                .waitLoad()
                .clickPersonalAccountButton(); //Переход в личный кабинет авторизированного пользователя
        profilePage.waitLoadPage();
        mainPage.clickStellarBurgerHeader() //Переход по нажатию на логотип 'Stellar Burger'
                .waitLoad();
        Assert.assertTrue("there is no 'Place an order' button", mainPage.placeAnOrderButtonIsDisplayed());
    }

    @Test
    public void exitProfileTest() {
        mainPage.clickPersonalAccountButton(); //Переход в личный кабинет
        logInPage.loginUser(email, password) //Регистрация пользователя
                .waitLoad()
                .clickPersonalAccountButton(); //Переход в личный кабинет авторизированного пользователя
        profilePage.waitLoadPage();
        //Проверка входа на страницу профиля, путем проверки кнопки 'Профиль'
        Assert.assertTrue("there is no 'Profile' button", profilePage.visibleProfileButton());
        profilePage.exitProfile() //выход из аккаунта
                .waitLoadPage();
        mainPage.clickStellarBurgerHeader(); //переход на главную страницу
        //Проверка, что произошел выход из аккаунта, путем проверки отсутствия кнопки 'Оформить заказ' и наличии кнопки 'Войти в аккаунт'
        Assert.assertFalse("there is no 'Place an order' button", mainPage.placeAnOrderButtonIsDisplayed());
        Assert.assertTrue("there is no 'Login Account' button", mainPage.logInAccountButtonIsDisplayed());
    }

}
