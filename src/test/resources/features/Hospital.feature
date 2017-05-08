#Feature: Test of 1 Hospital search by anonymous user
#  As anonymous user I try to search hospital by part of search word
#
#  Scenario Outline:
#    Given I open Base Url
#    When I try to search hospital by word <searchWord>
#    Then I should see <expectedNumber> hospitals which name, description or address consist search
#
#    Examples:
#      | searchWord | expectedNumber |
#      | polik       | 1               |
#      | hosp        | 5               |
#      | qwerty      | 0               |