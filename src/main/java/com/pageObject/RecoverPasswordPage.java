package com.pageObject;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class RecoverPasswordPage {

    //Заголовок Восстановление пароля
    @FindBy(how = How.XPATH, using = "//h2[text()='Восстановление пароля']")
    private SelenideElement recoverPasswordHeader;
    //поле ввода Email
    @FindBy(how = How.CSS, using = "input[name='name']")
    private SelenideElement emailField;
    //кнопка Восстановить
    @FindBy(how = How.XPATH, using = "//button[text()='Восстановить']")
    private SelenideElement recoverButton;
    //кнопка Войти
    @FindBy(how = How.CSS, using = ".Auth_link__1fOlj[href='/login']")
    private SelenideElement loginButton;

    public void setEmail(String email) { //ввод email
        emailField.setValue(email);
    }

    public LogInPage clickLogInButton() { //Нажатие кнопки "Войти"
        loginButton.click();
        return page(LogInPage.class);
            }


}
