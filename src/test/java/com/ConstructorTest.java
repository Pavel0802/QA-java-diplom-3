package com;

import com.pageObject.MainPage;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.open;

public class ConstructorTest {
    MainPage mainPage = new MainPage();
    String textExtract;

    @Test
    public void constructorClickTest() throws InterruptedException {
        mainPage = open(MainPage.URL, MainPage.class); //открываем главную страницу
        textExtract = mainPage.textExtract(); //извлекаем текст активной кнопки конструктора
        //проверяем, что кнопка неактивна и нажимаем на нее, затем на другие по очереди, если активна, то начинаем с другой неактивной кнопки
        if (textExtract.equals("Булки") || textExtract.equals("Начинки")) {
            mainPage.clickSauceButton();
            Thread.sleep(2000);
            Assert.assertEquals("Соусы", mainPage.textExtract());
            mainPage.clickBunButton();
            Thread.sleep(2000);
            Assert.assertEquals("Булки", mainPage.textExtract());
            mainPage.clickIngredientButton();
            Thread.sleep(2000);
            Assert.assertEquals("Начинки", mainPage.textExtract());
        } else {
            mainPage.clickBunButton();
            Thread.sleep(2000);
            Assert.assertEquals("Булки", mainPage.textExtract());
            mainPage.clickSauceButton();
            Thread.sleep(2000);
            Assert.assertEquals("Соусы", mainPage.textExtract());
            mainPage.clickIngredientButton();
            Thread.sleep(2000);
            Assert.assertEquals("Начинки", mainPage.textExtract());
        }
    }

}
