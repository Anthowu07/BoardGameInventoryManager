package com.skillstorm;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class WarehousePageTests {
    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void setUp() {
        driver = WebDriverSingleton.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @After
    public void tearDown() {
        WebDriverSingleton.quitDriver();
    }

    @Given("I am on the warehouses page")
    public void IAmOnTheWarehousesPage() {
        // driver.get("http://boardgame-inventory-management.s3-website-us-east-1.amazonaws.com/warehouses");
        driver.get("http://localhost:5173/warehouses");
    }

    @When("the warehouses page is fully loaded")
    public void theWarehousesPageIsFullyLoaded() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("warehouses-grid")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("card")));
    }

    @Then("I should see at least one card with a warehouse")
    public void iShouldSeeAtLeastOneCardWithAWarehouse() {
        WebElement card = driver.findElement(By.className("card"));
        Assert.assertTrue(card.isDisplayed());
    }

    @When("I enter {string} in the warehouse {string} field")
    public void iEnterInTheWarehouseField(String value, String fieldName) {
        WebElement inputField;
        switch (fieldName) {
            case "Name":
                inputField = driver.findElement(By.id("name-field"));
                break;
            case "Capacity":
                inputField = driver.findElement(By.id("capacity-field"));
                break;
            default:
                throw new IllegalArgumentException("Invalid field name: " + fieldName);
        }
        inputField.clear();
        inputField.sendKeys(value);
    }

    @When("I press the {string} button to submit the warehouse form")
    public void iPressTheButtonToSubmitTheWarehouseForm(String buttonText) {

        WebElement button = driver.findElement(By.id("submit-form-button"));
        button.click();
    }

    @When("I press the {string} button on the {string} warehouse")
    public void iPressTheButtonOnTheWarehouse(String buttonText, String warehouseName) {
        try {
            // Use XPath to find the card based on the warehouse name
            String cardXPath = String.format(
                    "//div[@class='warehouse-container']//div[@class='card']//div[@class='container'][h2[b[text()='%s']]]",
                    warehouseName);

            WebElement card = driver.findElement(By.xpath(cardXPath));

            // Hover over the card to make the delete button visible
            Actions actions = new Actions(driver);
            actions.moveToElement(card).perform();

            // Now locate the delete button within that container and click it
            WebElement button = card.findElement(By.id(buttonText));
            button.click();

        } catch (NoSuchElementException e) {
            System.out.println("Element not found: " + e.getMessage());
            System.out.println("Page Source: " + driver.getPageSource());
            throw e;
        }
    }

    @When("I press {string} on the warehouse delete popup")
    public void iPressOnTheWarehouseDeletePopup(String action) {
        // Wait for the alert popup to appear after clicking delete
        wait.until(ExpectedConditions.alertIsPresent());
        if (action.equalsIgnoreCase("OK")) {
            driver.switchTo().alert().accept();
        } else if (action.equalsIgnoreCase("Cancel")) {
            driver.switchTo().alert().dismiss();
        } else {
            throw new IllegalArgumentException("Unsupported action: " + action);
        }
    }

    @Then("I should see a warehouse card with the name {string} in the grid")
    public void iShouldSeeAWarehouseCardWithTheNameInTheGrid(String warehouseName) {
        // Wait until the warehouse name is present in the warehouses grid
        boolean isTextPresent = wait.until(
                ExpectedConditions.textToBePresentInElementLocated(By.id("warehouses-grid"), warehouseName));

        // Assert that the warehouse name is found in the grid
        Assert.assertTrue(isTextPresent, "Warehouse with name " + warehouseName + " was not found in the grid.");
    }

    @Then("The warehouse {string} should not be visible")
    public void theWarehouseShouldNotBeVisible(String warehouseName) {
        boolean isNotVisible = wait
                .until(ExpectedConditions.invisibilityOfElementWithText(By.id("warehouses-grid"), warehouseName));

        // If the element is not visible, assert that it's not in the grid
        Assert.assertTrue(isNotVisible,
                "Warehouse with name " + warehouseName + " is still visible grid, but it should not be.");
    }

    @Then("I should see join table page")
    public void iShouldSeeJoinTablePage(){
        WebElement header = driver.findElement(By.id("join-table-header"));
        Assert.assertTrue(header.isDisplayed(), "Board Games in Warehouses header should be visible but is not");
    }
}
