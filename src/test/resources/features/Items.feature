@crater
Feature: Items
  As a user, I want to be able to manage items

  Background:
    Given User is navigated to crater login page
    When User enters "accounts@crater.com" in the Email input field
    And User enters "primetech@school" for the password input field
    And User clicks on the Login button
    Then User should be logged in successfully
    And User should see the "Settings" page being displayed
@regression
  Scenario: User should be able to add, delete or update items
    When User clicks on the Items Menu Link
    Then User should be navigated to the Items page
    When User clicks on the + Add Item button
    Then User should be navigated to the New Item page
    When User enters "Cell phone" in the Name input field
    And User enters "50000" in the Price input field
    And User selects "Dollars" as one of the Unit
    And User enters "This cell phone is for Diana as a gift." in the Description text field
    When User clicks on the Save Item button
    Then User should see a flash message "Success! Item created successfully" with a close button to the right
    And the flash message should disappear within 5 seconds or less
    And User should be navigated back to the Items page
    And User should be able to view item added within the Items list
    And User should be able to see the entered information in the application database