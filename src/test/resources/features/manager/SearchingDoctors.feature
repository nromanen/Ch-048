Feature: Searching doctors on a hospital page table
  As a manager I want to find doctors according to my requirements
  So then i can use on of the fields in searching part of page
  Background:
    Given the manager is on dashboard of particular hospital

  Scenario: Showing respective number of users
    When Manager Select number doctors per page from selector
    Then Number of doctors on page in table equals number

  Scenario: Doctors filtration by specialization
    When Manager select specialization from selector
    Then Show rows in table with doctors that match particular specialization


  Scenario: Searching users by email
    When Manager chose email from search list
    And Type email in text field
    Then Show rows in table with doctors that matched typed email

  Scenario: Searching users by first name
    When Manager chose first name from search list
    And Type name in text field
    Then Show rows in table with doctors that match typed first name

  Scenario: Reset table to default
    When Manager check default number of rows in table
    And Then make simple find
    And Then click on clear button
    Then Show rows in the table in initial condition