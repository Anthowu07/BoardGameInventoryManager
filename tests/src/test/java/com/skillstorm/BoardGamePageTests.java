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

public class BoardGamePageTests {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass()
    public void before() {
        driver = new ChromeDriver();
    }

    @AfterClass()
    public void after() {
        if(driver != null) {
            driver.quit();
        }
    }
    
    
    @Given("I am on the board game page")
    public void iAmOnTheBoardGamePage() {
        driver = new ChromeDriver();
        //driver.get("http://boardgame-inventory-management.s3-website-us-east-1.amazonaws.com/boardgames");
        driver.get("http://localhost:5173/boardgames");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @When("the page is fully loaded")
    public void the_page_is_fully_loaded() {
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("board-game-table")));
    }

    // @When("the table is fully loaded")
    // public void thePageIsFullyLoaded() {
    //     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    //     wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("boardgame-table-body")));
    // }

    @When("I press the {string} button")
    public void i_press_the_button(String buttonText) {
        WebElement button = driver.findElement(By.id("toggle-form-button"));
        button.click();
    }

    @When("I press the {string} button on the game named {string}")
    public void i_press_the_button_on_the_game_named(String buttonText, String gameName) {
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("boardgame-table-body"), gameName));
        WebElement tableBody = driver.findElement(By.id("boardgame-table-body"));
        List<WebElement> rows = tableBody.findElements(By.id("board-game-table-row"));

        for (WebElement row : rows) {
            WebElement nameCell = row.findElement(By.id("board-game-name"));
            if (nameCell.getText().trim().equalsIgnoreCase(gameName)) {
                WebElement deleteButton = row.findElement(By.id("delete-button"));
                deleteButton.click();
                
                break;
            }
        }
    }

    @When("I enter {string} in the {string} field")
    public void i_enter_in_the_field(String value, String fieldName) {
        WebElement inputField;
        switch (fieldName) {
            case "Name":
                inputField = driver.findElement(By.id("name-field"));
                break;
            case "Publisher":
                inputField = driver.findElement(By.id("publisher-field"));
                break;
            case "Reorder Quantity":
                inputField = driver.findElement(By.id("reorder-quantity-field"));
                break;
            default:
                throw new IllegalArgumentException("Invalid field name: " + fieldName);
        }
        inputField.clear();
        inputField.sendKeys(value);
    }

    @When("I press the {string} button to submit the form")
    public void i_press_the_button_to_submit_the_form(String buttonText) {
        
        WebElement button = driver.findElement(By.id("submit-form-button"));
        button.click();
    }

    @When("I press {string} on the popup")
    public void i_press_on_the_popup(String action) {
        // Wait for the alert popup to appear after clicking delete
        wait.until(ExpectedConditions.alertIsPresent());
        if (action.equalsIgnoreCase("OK")) {
            // Assuming the popup is a JavaScript alert
            driver.switchTo().alert().accept();
        } else if (action.equalsIgnoreCase("Cancel")) {
            driver.switchTo().alert().dismiss();
        } else {
            throw new IllegalArgumentException("Unsupported action: " + action);
        }
    }


    @Then("I should see a table with board games")
    public void iShouldSeeATableWithBoardGames() {
        WebElement table = driver.findElement(By.id("board-game-table"));
        Assert.assertTrue(table.isDisplayed());
        driver.quit();
    }

    @Then("I should see a board game with name {string} in the table")
    public void iShouldSeeABoardGameWithNameInTheTable(String boardgameName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("boardgame-table-body")));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("boardgame-table-body"), boardgameName));
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
        driver.quit();
    }

    @Then("The board game {string} should not be in the table")
    public void the_board_game_should_not_be_in_the_table(String gameName) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("boardgame-table-body")));
        WebElement tableBody = driver.findElement(By.id("boardgame-table-body"));
        List<WebElement> rows = tableBody.findElements(By.id("board-game-table-row"));

        boolean found = false;

        for (WebElement row : rows) {
            WebElement nameCell = row.findElement(By.id("board-game-name"));
            if (nameCell.getText().trim().equalsIgnoreCase(gameName)) {
                found = true;
                break;
            }
        }
        Assert.assertFalse(found, "Board game with name " + gameName + " was found in the table, but it should not be.");
        driver.quit();
    }
}
