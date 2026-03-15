@tag
Feature: User Registration

  Scenario Outline: Positive scenario of user registration
    Given I am on the registration page
    When I complete registration with first name "<firstName>", last name "<lastName>", email "<email>", phone "<phone>", occupation "<occupation>", gender "<gender>", password "<password>", confirm password "<confirmPassword>"
    Then Registration should be successful
    Examples:
      | firstName   | lastName     | email               | phone      | occupation | gender | password       | confirmPassword |
      | John Eassdw | Doe  dasda   | john.doe@test.com   | 1234567890 | Engineer   | Male   | Passqazwsx123! | Passqazwsx123!  |
      | Jane dsadaa | Smith wdasda | jane.smith@test.com | 0987654321 | Doctor     | Female | Passqazwsx123! | Passqazwsx123!  |
