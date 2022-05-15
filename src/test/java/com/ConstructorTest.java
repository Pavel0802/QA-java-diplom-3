package com;

import com.pageObject.MainPage;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.closeWindow;
import static com.codeborne.selenide.Selenide.open;

public class ConstructorTest {
    MainPage mainPage = open(MainPage.URL, MainPage.class); //открываем главную страницу

    @After
    public void close() {
        closeWindow();
    }

    @Test
    @DisplayName("Проверка перехода по элементу конструктора 'Булки'")
    @Description("Тест проверяет возможность перехода по элементам конструктора при нажатии кнопки 'Булки' ")
    public void constructorBunClickTest() {
        mainPage.scrollIngredient() //скролим до другого элемента, чтобы нужный не оказался активным
                .clickBunButton() //нажимаем на нужный элемент
                .waitBunHeader(); //ждем перехода и прокрутки до нужного элемента
        Assert.assertEquals("Булки", mainPage.textExtract());
    }

    @Test
    @DisplayName("Проверка перехода по элементу конструктора 'Соусы'")
    @Description("Тест проверяет возможность перехода по элементам конструктора при нажатии кнопки 'Соусы' ")
    public void constructorSauceClickTest() {
        mainPage.scrollIngredient() //скролим до другого элемента, чтобы нужный не оказался активным
                .clickSauceButton() //нажимаем на нужный элемент
                .waitSauceHeader(); //ждем перехода и прокрутки до нужного элемента
        Assert.assertEquals("Соусы", mainPage.textExtract());
    }

    @Test
    @DisplayName("Проверка перехода по элементу конструктора 'Начинки'")
    @Description("Тест проверяет возможность перехода по элементам конструктора при нажатии кнопки 'Начинки' ")
    public void constructorIngredientClickTest() {
        mainPage.scrollSauce()  //скролим до другого элемента, чтобы нужный не оказался активным
                .clickIngredientButton() //нажимаем на нужный элемент
                .waitIngredientHeader(); //ждем перехода и прокрутки до нужного элемента
        Assert.assertEquals("Начинки", mainPage.textExtract());
    }
}
