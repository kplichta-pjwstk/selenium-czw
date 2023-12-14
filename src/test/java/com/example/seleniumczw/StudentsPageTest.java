package com.example.seleniumczw;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StudentsPageTest {

    private WebDriver driver;
    private StudentsPage studentsPage;

    @BeforeEach
    void setUp() {
        //System.setProperty("webdriver.chrome.driver", "ścieżka/do/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("http://localhost:8080/students-page");
        studentsPage = new StudentsPage(driver);
    }

    @AfterEach
    void cleanUp() {
        driver.quit();
    }

    @Test
    void shouldDisplayHelloWorld() {
        WebElement helloMessage = driver.findElement(By.xpath("//p[@data-test='hello-message']"));

        assertEquals("Hello World", helloMessage.getText());
        assertEquals("Hello World", studentsPage.helloMessage.getText());
    }

    @Test
    void shouldDisplayHelloName() {
        driver.get("http://localhost:8080/students-page?name=Karola");
        WebElement helloMessage = driver.findElement(By.xpath("//p[@data-test='hello-message']"));

        assertEquals("Hello Karola", helloMessage.getText());
        assertEquals("Hello Karola", studentsPage.helloMessage.getText());
    }

    @Test
    void shouldRedirectToAddStudentPage() {
        assertTrue(studentsPage.addStudentLink.isDisplayed());

        studentsPage.addStudentLink.click();
        assertEquals("http://localhost:8080/students-page/add", driver.getCurrentUrl());
    }
}
