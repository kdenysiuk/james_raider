@AllEditProfile
Feature: EditProfile

  @ChangeNameCheck
  Scenario: Change name
    Given User is logged in
    When User taps on Hamburger button
    Then User is on Main Menu page
    And User changes profile name
    Then User sees profile name has changed