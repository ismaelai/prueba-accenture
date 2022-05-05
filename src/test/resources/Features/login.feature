Feature: User login functionality

  Scenario Outline: Check if the user is logged successfully

    Given user is on the website
    When user enters <username> and <password>
    And clicks on login button
    Then logged as user in home items directory

    Examples:
      | username | | password |
      | standard_user | | secret_sauce |


