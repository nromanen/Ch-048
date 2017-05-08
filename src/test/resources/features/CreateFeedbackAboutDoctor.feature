Feature: As a patient i can go to the particular doctor's page
  and write feedback about this doctor

  Scenario: Creating Feedback about particular doctor
      When i sign in as a PATIENT and move to the current doctorInfoPage
      And click on the feedback field  write Feedback and submit it
      Then feedback field should disappear after page refresh

  Scenario: Approving or rejecting patients feedback about current doctor
    When i sign in as a manager and move to the feedbackManagePage
    And approve or disapprove feedback about current doctor
    Then feedback field should disappear after page refresh

  Scenario: Checking if feedback was created
    When i sign in as a patient and move to the current doctorInfoPage
    Then my feedback , wich was written by me early should be added






