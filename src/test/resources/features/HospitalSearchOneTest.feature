Feature: Test Hospital search by anonymous user
  As anonymous user I try to search hospital by part of search word

  Scenario Outline:
    Given I opened Base Url
    When I try to search hospital by 'polik'
    Then I should see <expectedNumber> hospital which name, description or address consist search word

    Examples:
     | expectedNumber |
     | 1              |
     | 5              |
     | 0              |