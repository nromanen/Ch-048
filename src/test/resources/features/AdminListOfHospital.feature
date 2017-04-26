  Feature:  As an ADMIN I must can see all hospitals
            Can add new hospital
            Delete hospital
            Find it on the map
            Edit info of hospital

  Background:
    Given ADMIN is on hospital list page

  Scenario: Adding new hospital
    When  ADMIN press button "Add new hospital"
    Then  ADMIN must see page "Hospital add/edit page"

  Scenario: Checking Google POI
    When  ADMIN press button "Check Google POI"
    Then  ADMIN must see page where he can check GooglePOI

  Scenario: Show on map
    When  ADMIN press button "Show on map" in row with certain hospital
    Then  ADMIN must see this hospital on map

  Scenario: Edit hospital
    When  ADMIN press button "Edit" in row with certain hospital
    Then  ADMIN must see page "Hospital add/edit page"

  Scenario: Delete hospital
    When  ADMIN press button "Delete" in row with certain hospital
    Then  ADMIN must see this page again without this hospital





