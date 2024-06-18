@AllLogs
Feature: Login and logout

  @LoginCheck
  Scenario: User completes login flow
    Given User is on Welcome page
    When User taps Get Started button
    Then User is on Enter your phone number page
    And User sets country and number phone
    Then User is on Enter the six digit code page
    And User sets six digit code
    Then User is on Permission Request page
    And User grants all permissions
    Then User is on Rides page

  @LogoutCheck
  Scenario: User logs out
    Given User is logged in
    When User taps on Hamburger button
    Then User is on Main Menu page
    And User taps SIGN OUT button
    Then User is on Enter your phone number page