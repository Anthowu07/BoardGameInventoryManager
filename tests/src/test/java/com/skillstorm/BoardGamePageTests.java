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
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("I am on the board game page")
    public void iAmOnTheBoardGamePage() {
        driver = new ChromeDriver();
        // driver.get("http://boardgame-inventory-management.s3-website-us-east-1.amazonaws.com/boardgames");
        driver.get("http://localhost:5173/boardgames");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @When("the page is fully loaded")
    public void thePageIsFullyLoaded() {
        // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("board-game-table")));
    }

    @When("I press the {string} button")
    public void iPressTheButton(String buttonText) {
        WebElement button = driver.findElement(By.id("toggle-form-button"));
        button.click();
    }


    @When("I press the {string} button on the game named {string}")
    public void iPressTheButtonOnTheGameNamed(String buttonText, String gameName) {
        // Wait until the board game name is present in the table body
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("boardgame-table-body"), gameName));

        // Use XPath to find the button based on the game name and button text or ID
        String xpathExpression = String.format(
                "//tr[td[@id='board-game-name' and normalize-space(text())='%s']]//button[normalize-space(text())='%s' or @id='%s-button']",
                gameName, buttonText, buttonText.toLowerCase());
        WebElement button = driver.findElement(By.xpath(xpathExpression));

        // Click the button
        button.click();
    }

    @When("I enter {string} in the {string} field")
    public void iEnterInTheField(String value, String fieldName) {
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
    public void iPressTheButtonToSubmitTheForm(String buttonText) {

        WebElement button = driver.findElement(By.id("submit-form-button"));
        button.click();
    }

    @When("I press {string} on the popup")
    public void iPressOnThePopup(String action) {
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
        // Wait until the board game name is present in the table body
        boolean isTextPresent = wait.until(
                ExpectedConditions.textToBePresentInElementLocated(By.id("boardgame-table-body"), boardgameName));

        // Assert that the board game name is found in the table
        Assert.assertTrue(isTextPresent, "Board game with name " + boardgameName + " was not found in the table.");

        driver.quit();
    }

    @Then("The board game {string} should not be in the table")
    public void theBoardGameShouldNotBeInTheTable(String gameName) {
        boolean isNotVisible = wait
                .until(ExpectedConditions.invisibilityOfElementWithText(By.id("boardgame-table-body"), gameName));

        // If the element is not visible, assert that it's not in the table
        Assert.assertTrue(isNotVisible,
                "Board game with name " + gameName + " is still visible in the table, but it should not be.");

        driver.quit();
    }
}
