package com.skillstorm;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.List;

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
    public void the_page_is_fully_loaded() {
        // Updated to use Duration instead of int
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("table")));
    }

    @When("the table is fully loaded")
    public void thePageIsFullyLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Wait until at least one row is present in the table
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("tbody tr"), 0));
    }

    @Then("I should see a table with board games")
    public void iShouldSeeATableWithBoardGames() {
        WebElement table = driver.findElement(By.tagName("table"));
        assertTrue(table.isDisplayed());
        driver.quit();
    }

    @Then("I should see a board game with name {string} in the table")
    public void iShouldSeeABoardGameWithNameInTheTable(String boardgameName) {
        // Find the table body
        WebElement tableBody = driver.findElement(By.tagName("tbody"));
        
        // Find all rows in the table body
        List<WebElement> rows = tableBody.findElements(By.tagName("tr"));
        
        boolean found = false;
        // Iterate over each row and check the first column (Name)
        for (WebElement row : rows) {
            WebElement nameCell = row.findElement(By.tagName("td"));
            if (nameCell.getText().trim().equalsIgnoreCase(boardgameName.trim())) {
                found = true;
                break;
            }
        }
        
        assertTrue(found, "Board game with name " + boardgameName + " was not found in the table.");
    }

}
