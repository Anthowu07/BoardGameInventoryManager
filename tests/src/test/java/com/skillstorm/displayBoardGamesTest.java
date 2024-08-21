package com.skillstorm;


import io.cucumber.java.en.*;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;

public class displayBoardGamesTest {

    static WebDriver driver;
    static WebDriverWait wait;

    @BeforeClass()
    public void before() {
        driver = new ChromeDriver();
    }

    @AfterClass()
    public void after() {
        if(driver != null) {
            
        }
        driver.quit();
    }
    
    
    @Given("I am on the board game page")
    @Test
    public void iAmOnTheBoardGamePage() {
        //driver = new ChromeDriver();
        //driver.get("http://boardgame-inventory-management.s3-website-us-east-1.amazonaws.com/boardgames");
        driver.get("http://localhost:5173/boardgames");
    }

    @Test
    @When("the page is fully loaded")
    public void the_page_is_fully_loaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("board-game-table")));
    }

    @Test
    @When("the table is fully loaded")
    public void thePageIsFullyLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("boardgame-table-body")));
    }

    @Test
    @Then("I should see a table with board games")
    public void iShouldSeeATableWithBoardGames() {
        WebElement table = driver.findElement(By.id("board-game-table"));
        Assert.assertTrue(table.isDisplayed());
    }

    @Test
    @Then("I should see a board game with name {string} in the table")
    public void iShouldSeeABoardGameWithNameInTheTable(String boardgameName) {
        // Find the table body
        WebElement tableBody = driver.findElement(By.id("boardgame-table-body"));
        
        // Find all rows in the table body
        List<WebElement> rows = tableBody.findElements(By.id("board-game-table-row"));
        
        boolean found = false;
        // Iterate over each row and check the first column (Name)
        for (WebElement row : rows) {
            
            WebElement nameCell = row.findElement(By.id("board-game-name"));
            System.out.println(nameCell.getText());
            if (nameCell.getText().trim().equalsIgnoreCase(boardgameName.trim())) {
                found = true;
                break;
            }
        }
        
        Assert.assertTrue(found, "Board game with name " + boardgameName + " was not found in the table.");
    }

}
