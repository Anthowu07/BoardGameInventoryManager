@BoardGamePageTests
Feature: Display the board game table in the board game page and allow adding, deleting, and editing data on the table
  As a user
  I want to be able to view, edit, add, and delete all board games
  so I can decide which games to stock up on

  Scenario: Check if the board game table is displayed in the board game page
    Given I am on the board game page
    When the board game page is fully loaded
    Then I should see a table with board games

  Scenario: Add a new board game
    Given I am on the board game page
    When I press the "Add Board Game" button
    And I enter "Catan" in the "Name" field
    And I enter "Mayfair Games" in the "Publisher" field
    And I enter "10" in the "Reorder Quantity" field
    And I press the "Create" button to submit the board game form
    Then I should see a board game with name "Catan" in the table

  Scenario: Edit a board game
    Given I am on the board game page
    When I press the "Edit" button on the game named "Catan"
    And I enter "Wingspan" in the "Name" field
    And I enter "Stonemaier Games" in the "Publisher" field
    And I enter "20" in the "Reorder Quantity" field
    And I press the "Update" button to submit the board game form
    Then I should see a board game with name "Wingspan" in the table
    And The board game "Catan" should not be in the table

  Scenario: Delete a board game
    Given I am on the board game page
    When I press the "Delete" button on the game named "Wingspan"
    And I press "OK" on the board game delete popup
    Then The board game "Wingspan" should not be in the table