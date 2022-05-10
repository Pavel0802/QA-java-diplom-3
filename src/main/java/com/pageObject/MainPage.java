package com.pageObject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class MainPage {

    //URL сайта
    public static final String URL = "https://stellarburgers.nomoreparties.site/";
    //кнопка Войти в аккаунт
    @FindBy(how = How.CSS, using = ".button_button_size_large__G21Vg")
    private SelenideElement logInAccountButton;
    //кнопка Оформить заказ
    @FindBy(how = How.XPATH, using = "//div/button[text()='Оформить заказ']")
    private SelenideElement placeAnOrderButton;
    //кнопка Личный Кабинет
    @FindBy(how = How.CSS, using = ".AppHeader_header__link__3D_hX[href='/account']")
    private SelenideElement personalAccountButton;
    //кнопка-логотип Stellar Burger
    @FindBy(how = How.CLASS_NAME, using = "AppHeader_header__logo__2D0X2")
    private SelenideElement stellarBurgerHeader;
    //кнопка-логотип Конструктор
    @FindBy(how = How.CSS, using = ".AppHeader_header__link__3D_hX[href='/']")
    private SelenideElement constructorHeader;
    //кнопка Булки
    @FindBy(how = How.XPATH, using = "//section[1]/div[1]/div[1]")
    private SelenideElement bunButton;
    //название раздела 'Булки' в окне конструктора
    @FindBy(how = How.XPATH, using = "//h2[text()='Булки']")
    private SelenideElement bunHeader;
    //кнопка Соусы
    @FindBy(how = How.XPATH, using = "//section[1]/div[1]/div[2]")
    private SelenideElement sauceButton;
    //название раздела 'Соусы' в окне конструктора
    @FindBy(how = How.XPATH, using = "//h2[text()='Соусы']")
    private SelenideElement sauceHeader;
    //кнопка Начинки
    @FindBy(how = How.XPATH, using = "//section[1]/div[1]/div[3]")
    private SelenideElement ingredientButton;
    //название раздела 'Начинки' в окне конструктора
    @FindBy(how = How.XPATH, using = "//h2[text()='Начинки']")
    private SelenideElement ingredientHeader;
    //выбранный элемент в окне конструктора
    @FindBy(how = How.CLASS_NAME, using = "tab_tab_type_current__2BEPc")
    private SelenideElement constructorInputHeader;

    public LogInPage clickLogInAccountButton() { //Нажатие кнопки "Войти в аккаунт"
        logInAccountButton.click();
        return page(LogInPage.class);
    }

    public void clickPersonalAccountButton() { //Нажатие кнопки "Личный Кабинет"
        personalAccountButton.click();
    }

    public MainPage clickStellarBurgerHeader() { //Нажатие логотипа "Stellar Burger"
        stellarBurgerHeader.click();
        return page(MainPage.class);
    }

    public MainPage clickConstructorHeader() { //Нажатие логотипа "Конструктор"
        constructorHeader.click();
        return page(MainPage.class);
    }

    public void clickBunButton() { //Нажатие кнопки "Булки"
        bunButton.click();
        //return page(MainPage.class);
    }

    public void clickSauceButton() { //Нажатие кнопки "Соусы"
        sauceButton.click();
        //return page(MainPage.class);
    }

    public void clickIngredientButton() { //Нажатие кнопки "Начинки"
        ingredientButton.click();
        //return page(MainPage.class);
    }

    public boolean placeAnOrderButtonIsDisplayed() { //проверка наличия кнопки 'Оформить заказ'
        return placeAnOrderButton.isDisplayed();
    }

    public boolean logInAccountButtonIsDisplayed() { //проверка наличия кнопки Вход в аккаунт
        return logInAccountButton.isDisplayed();
    }

    public MainPage waitLoad() { // ожидание загрузки страницы и появления кнопки 'Оформить заказ'
        placeAnOrderButton.shouldBe(Condition.visible);
        return page(MainPage.class);
    }

    public void bunWait(){
        bunButton.is(Condition.focused);
    }
    public boolean bunHeaderIsDisplayed() { //проверка видимости заголовка 'Булки' в конструкторе
        return bunButton.is(Condition.exactText("Булки"));
    }

    public boolean sauceHeaderIsDisplayed() { //проверка видимости заголовка 'Соусы' в конструкторе
        return sauceButton.is(Condition.exactText("Соусы"));
    }

    public boolean ingredientHeaderIsDisplayed() { //проверка видимости заголовка 'Начинки' в конструкторе
        return ingredientButton.is(Condition.exactText("Начинки"));
    }

    public String textExtract() {
 String textExtract = constructorInputHeader.getText();
 return textExtract;
    }

}