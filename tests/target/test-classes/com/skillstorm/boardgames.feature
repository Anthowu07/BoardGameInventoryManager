@displayBoardGames
Feature: Display the board game table in the board game page
  As a user
  I want to be able to view all board games available for order
  so I can decide which games to stock up on

  Scenario: Check if the board game table is displayed in the board game page
    Given I am on the board game page
    When the page is fully loaded
    Then I should see a table with board games


