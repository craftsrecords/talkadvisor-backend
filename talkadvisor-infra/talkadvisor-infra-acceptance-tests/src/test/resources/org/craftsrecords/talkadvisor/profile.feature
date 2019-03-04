Feature: Profile endpoint

  Background:
    Given url talkAdvisorUrl
    Given path 'profiles'
    Given def preferences =
    """
    { "topics": [{"name": "DDD"},{"name": "Hexagonal Architecture"}],"talksFormats": ["QUICKIE","CONFERENCE"] }
    """

  Scenario: A user wants to store his preferences
    Given def userId = 'me'

    When call read('profile.feature@name=CreateProfile')

    Then assert responseStatus == 201
    And match responseHeaders['Location'] contains only (profilesUrl + userId)
    And match response.id == userId
    And match response.preferences != null

  Scenario: retrieving a profile already exist error when trying to create the same profile again
    Given def userId = 'me'

    When call read('profile.feature@name=CreateProfile')

    Then assert responseStatus == 409
    And match response.message == 'A profile already exists for the user ' + userId

  @request
  @name=CreateProfile
  Scenario: creating a profile
    Given header User-Id = userId
    And request preferences
    When method post