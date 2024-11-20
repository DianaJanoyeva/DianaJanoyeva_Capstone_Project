@crater
Feature: Login Functionality
  As a user I want to be able to login to the Crater Application
@Smoke
  Scenario: User should be able to login with valid credentials
  Given User is navigated to crater login page
  When User enters "accounts@crater.com" in the Email input field
  And User enters "primetech@school" for the password input field
  And User clicks on the Login button
  Then User should be logged in successfully
  And User should see the "Settings" page being displayed