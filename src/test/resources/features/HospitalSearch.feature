Feature: Test Hospital search by anonymous user
  As anonymous user I try to search hospital by part of search word

  Scenario:
    When I try to search hospital by 'polik'
    Then I should see 7 hospitals which name, description or address consist search word

#  Scenario Outline:
#    When I try to search hospital by <search_word>
#    Then I should see <expected_number> hospitals which name, description or address consist search word

#    Examples:
#      | search_word | expected_number |
#      | polik       | 1               |
#      | hosp        | 5               |
#      | qwerty      | 0               |