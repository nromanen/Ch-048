Feature: As a DOCTOR I can view my working schedule for the week
  As a DOCTOR I can view my working schedule today

  Background:
    Given i sign in as a Doctor and go to Work scheduler page


  Scenario: View working scheduler for the week
    When DOCTOR press button "Week"
    Then Shows the scheduler of the DOCTOR on the week

  Scenario: View working scheduler today
    When DOCTOR press button "Next"
    And DOCTOR press button "Today"
    Then Shows the scheduler of the DOCTOR on today


