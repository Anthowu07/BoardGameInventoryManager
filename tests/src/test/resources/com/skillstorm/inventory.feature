@InventoryPageTests
Feature: Display inventory table and allow view, delete, and edit operations on the table. Allow order placements and store that inventory in the table.
  As a user
  I want to be able to view all inventory entries on a single page as well as be able to add, edit, or delete any inventory entries on the page
  so I can keep track of my stock of board games and the warehouses they are stored in as well as keep it updated.

  Scenario: Check if the user can place an order
    Given I am on the orders page
    When the orders page is fully loaded
    And I select the board game "Monopoly" and the warehouse "Warehouse"
    And I press the "Create Order" button to submit the inventory form
    Then I should see a success message

  Scenario: Check if the inventory table is visible and the order is shown
    Given I am on the inventory page
    When the inventory page is fully loaded
    Then I should see a table with inventory entries
    And I should see an inventory entry with board game name "Monopoly" and warehouse name "Warehouse" in the inventory table

  Scenario: Edit an inventory entry
    Given I am on the inventory page
    When I press the "Edit" button in the newly made inventory entry
    And I enter "1" in the Quantity Available field
    And I press the "Update" button to submit the inventory form
    Then I should see an inventory entry with the same ID and "1" quantity available in the table

  Scenario: Delete an inventory entry
    Given I am on the inventory page
    When I press the "Delete" button in the newly made inventory entry
    And I press "OK" on the inventory delete popup
    Then The inventory entry with the same ID should not be in the table
