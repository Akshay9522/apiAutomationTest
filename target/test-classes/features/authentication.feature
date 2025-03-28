Feature: Google Drive API Authentication & Authorization

@Auth
  Scenario: Fetch files from Google Drive
    Given I have a valid OAuth 2.0 token
    When I send a GET request to fetch Google Drive files
    Then the response status code should be 200
    And the response should contain a list of files
    And the response should match the "schema.json"
