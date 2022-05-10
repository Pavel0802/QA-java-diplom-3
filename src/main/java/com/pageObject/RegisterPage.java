package com.pageObject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class RegisterPage {

    /*//URL сайта
    public static final String URL = "https://stellarburgers.nomoreparties.site/register";*/
    //Заголовок Регистрация
    @FindBy(how = How.XPATH, using = "//h2[text()='Регистрация']")
    private SelenideElement registerHeader;
    //поле ввода Имя
    @FindBy(how = How.XPATH, using = "//fieldset[1]/div/div/input")
    private SelenideElement nameField;
    //поле ввода Email
    @FindBy(how = How.XPATH, using = "//fieldset[2]/div/div/input")
    private SelenideElement emailField;
    //поле ввода Пароль
    @FindBy(how = How.XPATH, using = "//fieldset[3]/div/div/input")
    private SelenideElement passwordField;
    //кнопка Зарегистрироваться
    @FindBy(how = How.XPATH, using = "//button[text()='Зарегистрироваться']")
    private SelenideElement authorizationButton;



    //кнопка Войти
    @FindBy(how = How.CSS, using = ".Auth_link__1fOlj[href='/login']")
    private SelenideElement loginButton;
    //поле ошибки при вводе некорректного пароля
    @FindBy(how = How.XPATH, using = "//div/p[text()='Некорректный пароль']")
    private SelenideElement errorPassword;
    //поле ошибки при регистрации существующего пользователя
    @FindBy(how = How.XPATH, using = "//div/p[text()='Такой пользователь уже существует']")
    private SelenideElement errorUserIsAlreadyRegistered;


    public void setName(String name) { //ввод Имени
        nameField.setValue(name);
    }
    public void setEmail(String email) { //ввод email
        emailField.setValue(email);
    }

    public void setPassword(String password) { //ввод password
        passwordField.setValue(password);
    }

    public void clickAuthorizationButton() { //Нажатие кнопки "Зарегистрироваться"
        authorizationButton.click();
    }

    public LogInPage clickLogInButton() { //Нажатие кнопки "Войти"
        loginButton.click();
        return page(LogInPage.class);
    }

   /* public SelenideElement getLoginButton() {
        return loginButton;
    }*/


    public void registrationUser (String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        clickAuthorizationButton();
    }

    public boolean errorUserIsAlreadyRegisteredIsDisplayed() { //проверка наличия ошибки уже существующего пользователя
        return errorUserIsAlreadyRegistered.exists();
    }
    public boolean errorPasswordIsDisplayed() { //проверка наличия ошибки некорректного пароля
        return errorPassword.isDisplayed();
    }
}
