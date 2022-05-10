package com.pageObject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class ProfilePage {

    @FindBy(how = How.XPATH, using = "//button[text()='Выход']")
    private SelenideElement exitButton;
    @FindBy(how = How.CSS, using = "a[href='/account/profile']")
    private SelenideElement profileButton;


    public LogInPage exitProfile(){
        exitButton.click();
        return page(LogInPage.class);
    }
    public boolean visibleProfileButton(){
        return profileButton.isDisplayed();
    }

    public void waitLoadPage() {
        profileButton.shouldBe(Condition.visible);
    }
}
