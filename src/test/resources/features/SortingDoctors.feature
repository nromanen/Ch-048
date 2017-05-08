Feature: Sorting doctors in particular hospital table
  As a manager i want to sort doctors according to my requirements
  So I can push a button at the header of row and sort users

  Background:
    Given the manager is on dashboard of particular hospital in order to sort

  Scenario: Sorting by the email ascending
    When Manager click on the email sorting button one time
    Then Doctors in table is sorted by their emails ascending

  Scenario: Sorting by the email descending
    When Manager click on the email sorting button two times
    Then Doctors in table is sorted by their email descending

  Scenario: Sorting by the first name ascending
    When Manager click on the first name sorting button one time
    Then Doctors in table is sorted by their first name ascending

  Scenario: Sorting by the fist name descending
    When Manager click on the first name sorting button two times
    Then Doctors in table is sorted by their first name descending

  Scenario: Sorting by the last name ascending
    When Manager click on the last name sorting button one time
    Then Doctors in table is sorted by their last name ascending

  Scenario: Sorting by the last name descending
    When Manager click on the last name sorting button two times
    Then Doctors in table is sorted by their last name descending

  Scenario: Sorting by the specialization ascending
    When Manager click on the specialization sorting button one time
    Then Doctors in table is sorted by their specialization ascending

  Scenario: Sorting by the specialization descending
    When Manager click on the specialization sorting button two times
    Then Doctors in table is sorted by their specialization descending

  Scenario: Sorting by the category ascending
    When Manager click on the category sorting button one time
    Then Doctors in table is sorted by their category ascending

  Scenario: Sorting by the category descending
    When Manager click on the category sorting button two times
    Then Doctors in table is sorted by their category descending