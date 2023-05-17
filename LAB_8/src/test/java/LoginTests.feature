Feature: Login

  Scenario Outline: Login
    Given User is on login page
    When User puts <username> and <password>
    And Clicks login
    Then He should see <result>
    Examples:
      | username | password | result |
      |standard_user||Epic sadface: Password is required|
      |standard_user|standard_user|Epic sadface: Username and password do not match any user in this service|
      |secret_sauce|secret_sauce|Epic sadface: Username and password do not match any user in this service|
      |||Epic sadface: Username is required|