package com.example.seleniumczw;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddStudentsPageTest {

    private WebDriver driver;
    private AddStudentsPage addStudentsPage;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("http://localhost:8080/students-page/add");
        addStudentsPage = new AddStudentsPage(driver);
    }

    @AfterEach
    void cleanUp() {
        driver.quit();
    }

    @Test
    void shouldRedirectToStudentsPageAfterAddStudent() {
        driver.get("http://localhost:8080/students-page");
        var initStudentPage = new StudentsPage(driver);
        var initStudentRows = getStudentsRows(initStudentPage);
        driver.get("http://localhost:8080/students-page/add");
        addStudentsPage.nameInput.sendKeys("Karola");
        var unitSelect = new Select(addStudentsPage.unitInput);
        unitSelect.selectByValue("GDANSK");
        addStudentsPage.submitButton.click();

        assertEquals("http://localhost:8080/students-page", driver.getCurrentUrl());
        var studentPage = new StudentsPage(driver);
        var addedStudentRows = getStudentsRows(studentPage);
        assertEquals(addedStudentRows.size(), initStudentRows.size() + 1);

    }

    private static List<WebElement> getStudentsRows(StudentsPage studentPage) {
        return studentPage.studentsRows.stream()
                .filter(row -> {
                    var cells = row.findElements(By.tagName("td")).stream()
                            .map(WebElement::getText)
                            .toList();
                    return cells.contains("Karola") && cells.contains("GDANSK");
                })
                .toList();
    }
}
