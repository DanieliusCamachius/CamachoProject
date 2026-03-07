@tag
Feature: Purchase order from Ecommerce Website

  @ErrorValidation
  Scenario Outline: Negative scenario of login
    Given I landed on Ecommerce Page
    When I am logged in with <name> and password <password>
    Then "Incorrect email or password." message is displayed on Login Page
    Examples:
      | name                     | password         |
      | "dankamacho@hotmail.com" | "32132123213213" |