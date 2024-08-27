@WarehousePageTests
Feature: Display a grid of warehouse cards with information on it's current and max capacity. Allow adding and deleting warehouses as well as viewing inventory in the warehouse.
  As a user
  I want to be able to view all warehouses in an organized way and view the amount of itmes in each
  so I can keep track of the current capacity of all my warehouses and keep their information updated.

  Scenario: Check if warehouses are displayed on the warehouses page
    Given I am on the warehouses page
    When the warehouses page is fully loaded
    Then I should see at least one card with a warehouse

  Scenario: Add a new warehouse
    Given I am on the warehouses page
    When I press the "Add Warehouse" button
    And I enter "Catan" in the warehouse "Name" field
    And I enter "1200" in the warehouse "Capacity" field
    And I press the "Create Warehouse" button to submit the warehouse form
    Then I should see a warehouse card with the name "Catan" in the grid

  Scenario: View Inventory in warehouse
    Given I am on the warehouses page
    When the warehouses page is fully loaded
    And I press the "view_inventory_button" button on the "Catan" warehouse
    Then I should see join table page

  Scenario: Delete a warehouse
    Given I am on the warehouses page
    When the warehouses page is fully loaded
    And I press the "delete_button" button on the "Catan" warehouse
    And I press "OK" on the warehouse delete popup
    Then The warehouse "Catan" should not be visible
