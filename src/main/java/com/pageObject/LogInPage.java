package com.pageObject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class LogInPage {

    //Заголовок Вход
    @FindBy(how = How.XPATH, using = "//h2[text()='Вход']")
    private SelenideElement entranceHeader;
    //поле ввода Email
    @FindBy(how = How.CSS, using = "input[name='name']")
    private SelenideElement emailField;
    //поле ввода Пароль
    @FindBy(how = How.CSS, using = "input[name='Пароль']")
    private SelenideElement passwordField;
    //кнопка Войти
    @FindBy(how = How.XPATH, using = "//button[text()='Войти']")
    private SelenideElement loginButton;
    //кнопка Зарегистрироваться
    @FindBy(how = How.CSS, using = ".Auth_link__1fOlj[href='/register']")
    private SelenideElement authorizationButton;
    //кнопка Восстановить пароль
    @FindBy(how = How.CSS, using = ".Auth_link__1fOlj[href='/forgot-password']")
    private SelenideElement recoverPasswordButton;


    public void setEmail(String email) { //ввод email
        emailField.setValue(email);
    }

    public void setPassword(String password) { //ввод password
        passwordField.setValue(password);
    }

    public RegisterPage clickAuthorizationButton() { //Нажатие кнопки "Зарегистрироваться"
        authorizationButton.click();
        return page(RegisterPage.class);
    }

    public void clickLogInButton() { //Нажатие кнопки "Войти"
        loginButton.click();
    }

    public RecoverPasswordPage clickRecoverPasswordButton() { //Нажатие кнопки "Восстановить пароль"
        recoverPasswordButton.click();
        return page(RecoverPasswordPage.class);
    }

    public MainPage loginUser(String email, String password) { //регистрация пользователя
        setEmail(email);
        setPassword(password);
        clickLogInButton();
        return page(MainPage.class);
    }

    public LogInPage waitLoadPage() {
        entranceHeader.shouldBe(Condition.visible);
        return page(LogInPage.class);
    }

}
