Feature: Recommendation endpoint

  Background:
    Given url talkAdvisorUrl
    And path 'recommendations'

  Scenario: trying to get recommendations when no profile exists for the user
    Given def userId = 'unknown'

    When call read('recommendation.feature@name=CreateRecommendation')

    Then assert responseStatus == 400
    And match response.message == 'No profile found for the user ' + userId

  Scenario: retrieving recommendations for an user who has stored his profile
    Given def userId = 'recommendMe'
    And def existingProfile = call read('profile.feature@name=CreateProfile')
    * assert existingProfile.responseStatus == 201

    When call read('recommendation.feature@name=CreateRecommendation')

    Then assert responseStatus == 201
    And match response == { id: #notnull, talks: #[18] }
    And match responseHeaders['Location'] contains only (recommendationsUrl + response.id)

  @request
  @name=CreateRecommendation
  Scenario: create a recommendation
    Given header User-Id = userId
    And request ''
    When method post