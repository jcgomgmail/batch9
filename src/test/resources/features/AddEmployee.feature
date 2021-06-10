Feature: Adding employees

  Background:
    And user is logged in with valid admin credentials
    When user clicks on PIM option
    And user clicks on Add Employee Button

    # 1. Hardcoding
  @smoke
  Scenario: adding employee from add employee page
    And user pass firstname middlename and lastname
    And user clicks on save button
    Then employee added successfully

    # 2. passing
  @regression
  Scenario: adding employee from add employee page
    And user pass firstname "Polina" middlename "A" and lastname "Aponasenko"
    And user clicks on save button
    Then employee added successfully

    # 3. Via feature file  scenario outline
  @example
  Scenario Outline: Adding employee from add employee page via feature file
    And user enters "<FirstName>" "<MiddleName>" and "<LastName>" in the application
    And user clicks on save button
    Then employee added successfully
    Examples:
      | FirstName | MiddleName | LastName |
      | Test12345 | MS         | Test9876 |
      | Test2     | fs         | !@#      |
      | Test3     | fqf12      | sdf      |


  @datatablewithheather
  Scenario: Adding multiple employees in single execution
    When add multiple employees and verify they are added successfully
      | FirstName | MiddleName | LastName |
      | Jon0404   | Ms         | US       |
      | Jack0404  | MS         | US       |


@excel
Scenario: Adding te employees from excel file
  When user adds multiple employees from excel file from "NamesToAdd" sheet and verify they are added
