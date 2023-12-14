package com.example.seleniumczw;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class StudentsPage {

    @FindBy(xpath = "//p[@data-test='hello-message']")
    public WebElement helloMessage;

    @FindBy(linkText = "Dodaj Studenta")
    public WebElement addStudentLink;

    @FindBy(tagName = "tr")
    public List<WebElement> studentsRows;

    public StudentsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
