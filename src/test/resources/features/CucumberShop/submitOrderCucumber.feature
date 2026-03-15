@tag @Regression
Feature: Purchase order from Ecommerce Website

  Background:
    Given I landed on Ecommerce Page

  Scenario Outline: Positive scenario of submitting order
    Given I am logged in with <name> and password <password>
    When I add the product <productName> to Cart
    And I Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on Confirmation Page
    Examples:
      | name                     | password          | productName       |
      | "dankamacho@hotmail.com" | "1Danielcamacho!" | "ZARA COAT 3"     |
      | "dankamacho@hotmail.com" | "1Danielcamacho!" | "ADIDAS ORIGINAL" |