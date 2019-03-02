Feature: Profile endpoint

  Background:
    * url talkAdvisorUrl

    * def userId = 'me'
    * def preferences =
    """
    { "topics": [{"name": "DDD"},{"name": "Hexagonal Architecture"}],"talksFormats": ["QUICKIE","CONFERENCE"] }
    """

  Scenario: creating successfully a profile

    Given path 'profiles'
    And header User-Id = userId
    And request preferences
    When method post
    Then status 201
    * def expectedProfileLocation = talkAdvisorUrl + '/profiles/' + userId
    And match responseHeaders['Location'] contains only expectedProfileLocation
    And match response.id == userId
    And match response.preferences != null

  Scenario: retrieving a profile already exist error when trying to create the same profile again

    Given path 'profiles'
    And header User-Id = userId
    And request preferences
    When method post
    Then status 409
    And match response.message == 'A profile already exists for the user ' + userId