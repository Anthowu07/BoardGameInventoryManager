package com.skillstorm;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class InventoryPageTests {
    WebDriver driver;
    WebDriverWait wait;
    static String inventoryID;

    // @Before()
    // public void before() {
    // driver = new ChromeDriver();
    // wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    // }

    // @After()
    // public void after() {
    // if (driver != null) {
    // driver.quit();
    // }
    // }

    @Before
    public void setUp() {
        driver = WebDriverSingleton.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Given("I am on the inventory page")
    public void iAmOnTheInventoryPage() {
        driver.get("http://boardgame-inventory-management.s3-website-us-east-1.amazonaws.com/inventory");
    }

    @Given("I am on the orders page")
    public void iAmOnTheOrdersPage() {
        driver.get("http://boardgame-inventory-management.s3-website-us-east-1.amazonaws.com/orders");
    }

    @When("the inventory page is fully loaded")
    public void theInventoryPageIsFullyLoaded() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("inventory-table")));
    }

    @When("the orders page is fully loaded")
    public void theOrdersPageIsFullyLoaded() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("orders-form")));
    }

    @When("I select the board game {string} and the warehouse {string}")
    public void ISelectTheBoardGameAndTheWarehouse(String boardGameName, String warehouseName) {
        //Wait until the dropdowns are fully loaded and enabled.
        WebElement boardGameDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("boardgame")));
        WebElement warehouseDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("warehouse")));

        //Select the correct option in the boardgame dropdown, ensure desired option is available
        Select boardGameSelect = new Select(boardGameDropdown);
        wait.until(ExpectedConditions.textToBePresentInElement(boardGameDropdown, boardGameName));
        boardGameSelect.selectByVisibleText(boardGameName);

        //Select the correct option in the warehouse dropdown, ensure desired option is available
        Select warehouseSelect = new Select(warehouseDropdown);
        wait.until(ExpectedConditions.textToBePresentInElement(warehouseDropdown, warehouseName));
        warehouseSelect.selectByVisibleText(warehouseName);
    }

    @When("I press the {string} button in the newly made inventory entry")
    public void iPressTheButtonOnTheNewlyMadeInventoryEntry(String buttonText) {


        // Wait until the inventory ID is present in the table body
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("inventory-table-body"), inventoryID));

        
        // Use XPath to find the button based on the inventory ID and button text
        String xpathExpression = String.format(
                "//tr[td[@id='inventory-id' and normalize-space(text())='%s']]//button[normalize-space(text())='%s' or @id='%s-button']",
                inventoryID, buttonText, buttonText.toLowerCase());


        WebElement button = driver.findElement(By.xpath(xpathExpression));
        // Click the button
        button.click();
    }

    @When("I enter {string} in the Quantity Available field")
    public void iEnterInTheQuantityAvailableField(String value) {
        WebElement inputField;
        inputField = driver.findElement(By.id("quantity-field"));
        inputField.clear();
        inputField.sendKeys(value);
    }

    @When("I press the {string} button to submit the inventory form")
    public void iPressTheButtonToSubmitTheInventoryForm(String buttonText) {
        WebElement button = driver.findElement(By.id("submit-form-button"));
        button.click();
    }

    @When("I press {string} on the inventory delete popup")
    public void iPressOnTheInventoryDeletePopup(String action) {
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

    @Then("I should see a success message")
    public void iShouldSeeASuccessMessage(){
        boolean messageVisible = wait.until(
            ExpectedConditions.textToBePresentInElementLocated(By.tagName("div"), "Order Placed!"));
        Assert.assertTrue(messageVisible, "Success Message not shown");
    }

    @Then("I should see an inventory entry with board game name {string} and warehouse name {string} in the inventory table")
    public void iShouldSeeAnInventoryEntry(String boardGameName, String warehouseName) {
        // Wait until the table body is visible
        WebElement tableBody = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inventory-table-body")));
        
        // Wait until the table rows are present in the table body
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("tr")));

        // Retrieve the rows within the table body
        List<WebElement> rows = tableBody.findElements(By.id("inventory-table-row"));
        boolean entryFound = false;
        for (WebElement row : rows) {
            String currentBoardGameName = row.findElement(By.id("board-game-name")).getText();
            String currentWarehouseName = row.findElement(By.id("warehouse-name")).getText();

            if (currentBoardGameName.equals(boardGameName) && currentWarehouseName.equals(warehouseName)) {
                // Entry found, store inventory ID
                inventoryID = row.findElement(By.id("inventory-id")).getText();
                entryFound = true;
                break;
            }
        }

        if (!entryFound) {
            throw new AssertionError("Inventory entry with board game name " + boardGameName + " and warehouse name "
                    + warehouseName + " was not found.");
        }
    }

    @Then("I should see a table with inventory entries")
    public void iShouldSeeATableWithInventoryEntries() {
        WebElement table = driver.findElement(By.id("inventory-table"));
        Assert.assertTrue(table.isDisplayed());
    }

    @Then("I should see an inventory entry with the same ID and {string} quantity available in the table")
    public void iShouldSeeAnInventoryEntryWithTheSameIDAndQuantityAvailableInTheTable(String quantity) {
        // Wait desired inventory entry is located in the table
        boolean isIDPresent = wait.until(
                ExpectedConditions.textToBePresentInElementLocated(By.id("inventory-table-body"), inventoryID));
        boolean isQuantityPresent = wait.until(
                ExpectedConditions.textToBePresentInElementLocated(By.id("inventory-table-body"), quantity));

        // Assert that the desired inventory entry has been found
        Assert.assertTrue(isIDPresent, "Inventory with ID: " + inventoryID + " was not found in the table.");
        Assert.assertTrue(isQuantityPresent,
                "Inventory with ID: " + inventoryID + "and Quantity: " + quantity + "was not found in the table.");
    }

    @Then("The inventory entry with the same ID should not be in the table")
    public void theInventoryEntryWithIDShouldNotBeInTheTable() {
        boolean isNotVisible = wait
                .until(ExpectedConditions.invisibilityOfElementWithText(By.id("inventory-table-body"), inventoryID));

        // If the element is not visible, assert that it's not in the table
        Assert.assertTrue(isNotVisible,
                "Inventory Entry with ID: " + inventoryID + " is still in the table, but it should not be.");
    }
}
