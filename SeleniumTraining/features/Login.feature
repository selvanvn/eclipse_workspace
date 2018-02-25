#Feature Login
Feature: User Login

  Scenario: User enter should be able login with right credentials
    Given login page opens
    When right Credentials
    Then successfully logged in
    
    Scenario Outline: Used for variables
    Given login page opens
    When enter email <username>
    And enter right pwd <password>
    Then successfully logged in
    
    Examples:
    | username | password |
    | selvanvn@gmail.com | Selva@1331 |
    | 123@dsdsdf.com | ksdsk |
