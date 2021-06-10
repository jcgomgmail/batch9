Feature: Dashboard tab functionality

  @dashBoardTabs
  Scenario: Dashboard tab verification
    When user provides valid admin username and password
    And user clicks on login button
    Then user is successfully logged in
    Then verify the following tabs on dashboard
      | Admin | PIM | Leave | Time | Recruitment | Performance | Dashboard | Directory |