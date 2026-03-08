@tag
Feature: Validate Table Change

  @Regression
  Scenario Outline: Positive scenario of submitting order
    Given I landed on fruit table page
    And I click download button
    When I upload new data file from <path>
    Then Notification of successful upload is displayed
    And Table data is valid
      | serialNumber | name   | color  | price | season |
      | 1            | Mango  | Green  | 100.5 | Summer |
      | 2            | Apple  | Orange | 5     | Winter |
      | 3            | Papaya | Orange | 187   | Spring |
      | 4            | Banana | Yellow | 69    | All    |
      | 5            | Kivi   | Black  | 1     | Never  |
      | 6            | Orange | Orange | 199   | Summer |
    Examples:
      | path                                       |
      | "C:/Users/Camacho/Downloads/download.xlsx" |
