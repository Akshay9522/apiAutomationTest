Feature: User API Tests

  
  Scenario: Retrieve all users
    When I send a GET request to "USERS" API
    Then the response status code should be 200

  Scenario: Retrieve a user by valid ID
    When I send a GET request for "USER_BY_ID" "1"
    Then the response status code should be 200

  Scenario: Retrieve a user that does not exist
    When I send a GET request for "USER_BY_ID" "9999"
    Then the response status code should be 404

  Scenario: Create a new user
   When I send a POST request to create a user with name "John Doe" and email "john.doe@example.com"
   Then the response status code should be 201
   And the response should contain "John Doe" and "john.doe@example.com"
   And validate the user schema