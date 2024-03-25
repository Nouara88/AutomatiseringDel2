Feature: I want register a new user account on Basketball England

  Scenario: Sign up - everything goes as expected and an account is created
    Given I'm on the registration page
    When  I fill all the fields as required
    And   I fill first and last name
    And   I enter a password
    And   I confirm the password with same value
    And   I accept the terms of use
    And   I click the "CONFIRM AND JOIN" button
    Then  I should receive a message that Am a member now


    Scenario: Sign up - last name is missing - registration failed
      Given I'm on the registration page
      When I fill all the fields as required
      When  I fill in first name but forgot last name
      And   I enter a password
      And   I confirm the password with same value
      And   I accept the terms of use
      And   I click the "CONFIRM AND JOIN" button
      Then  I should get a warning message that last name is required

      Scenario: Sign up - password does not match
        Given I'm on the registration page
        When  I fill all the fields as required
        And   I fill first and last name
        And   I accept the terms of use
        And   I enter a password
        And   I confirm the password with a different value
        And   I click the "CONFIRM AND JOIN" button
        Then  I should get a warning message that password does not match

        Scenario: Sign up - terms and conditions are not accepted
          Given I'm on the registration page
          When  I fill all the fields as required
          And   I fill first and last name
          And   I enter a password
          And   I confirm the password with same value
          *     I click the "CONFIRM AND JOIN" button
          Then  I should get a warning message that terms and conditions are not accepted