@tag
Feature: Validate Table Change

  @Regression
  Scenario Outline: Positive scenario of submitting order
    Given I landed on fruit table page
    And I click download button
    When I upload new data file from <path>
    Then Then Notification of successful upload is displayed
    And Table data is valid
    Examples:
      | path                                       |
      | "C:/Users/Camacho/Downloads/download.xlsx" |
