package com;

import com.pageObject.LogInPage;
import com.pageObject.MainPage;
import com.pageObject.ProfilePage;
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
        mainPage.clickPersonalAccountButton(); //Переход в личный кабинет
        logInPage.loginUser(email, password) //авторизация пользователя
                .waitLoad()
                .clickPersonalAccountButton(); //Переход в личный кабинет авторизированного пользователя
        profilePage.waitLoadPage(); //ожидание загрузки страницы
    }

    @After
    public void after() {
        userOperations.delete();//удаляем пользователя
    }

    @Test
    @DisplayName("Проверка авторизации пользователя через кнопку 'Личный кабинет'")
    @Description("Тест проверяет возможность авторизации пользователя через кнопку 'Личный кабинет' и проверка авторизации путем входа на страницу профиля")
    public void loginOnPersonalAccountTest() {
        Assert.assertTrue("there is no 'Profile' button", profilePage.visibleProfileButton());
    }

    @Test
    @DisplayName("Проверка перехода в Конструктор из страницы профиля")
    @Description("Тест проверяет возможность перехода в Конструктор из страницы профиля")
    public void clickFromPersonalAccountToConstructorTest() {
        mainPage.clickConstructorHeader() //Переход по кнопке 'Конструктор'
                .waitLoad();
        Assert.assertTrue("there is no 'Place an order' button", mainPage.placeAnOrderButtonIsDisplayed());
    }

    @Test
    @DisplayName("Проверка перехода по нажатию на логотип 'Stellar Burger' из страницы профиля")
    @Description("Тест проверяет возможность перехода по нажатию на логотип 'Stellar Burger' из страницы профиля")
    public void clickFromPersonalAccountToStellarBurgerHeaderTest() {
        mainPage.clickStellarBurgerHeader() //Переход по нажатию на логотип 'Stellar Burger'
                .waitLoad();
        Assert.assertTrue("there is no 'Place an order' button", mainPage.placeAnOrderButtonIsDisplayed());
    }

    @Test
    @DisplayName("Проверка выхода из профиля пользователя")
    @Description("Тест проверяет возможность выхода пользователя из профиля")
    public void exitProfileTest() {
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
