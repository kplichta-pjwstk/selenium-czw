package com.example.seleniumczw;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

public class AddStudentsPage {

    @FindBy(id = "name-input")
    public WebElement nameInput;

    @FindBy(name = "unit")
    public WebElement unitInput;

    @FindBy(tagName = "button")
    public WebElement submitButton;

    public AddStudentsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
