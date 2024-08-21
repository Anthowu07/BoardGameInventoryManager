package com.skillstorm;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class displayBoardGamesTest {

    WebDriver driver;


    @Before("@displayBoardGames")
    public void before() {
        this.driver = new ChromeDriver();
    }

    @After("@displayBoardGames")
    public void after() {
        if(driver != null) {
            this.driver.quit();
        }
    }

    @Given("I am on the board game page")
    public void iAmOnTheBoardGamePage() {
        driver.get("http://boardgame-inventory-management.s3-website-us-east-1.amazonaws.com/boardgames");
    }

    @When("the page is fully loaded")
    public void thePageIsFullyLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("table")));
    }

    @Then("I should see a table with board games")
    public void iShouldSeeATableWithBoardGames() {
        WebElement table = driver.findElement(By.tagName("table"));
        assertTrue(table.isDisplayed());
        driver.quit();
    }

}
