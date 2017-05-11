Feature: As a doctor I can creating a new record to patient card
  and as a patient I can to see new record

   Scenario: Create new record
     When i sign in as a Doctor and go to Create New Record Page
     And doctor fills up all required fields and click button Submit
     Then doctor must see page Patient card Thomas Auginas with one additional record

    Scenario:  Check if new record was created
      When i sign in as a Patient and go to Card Page
      Then patient must see information on the card after a doctor created new record