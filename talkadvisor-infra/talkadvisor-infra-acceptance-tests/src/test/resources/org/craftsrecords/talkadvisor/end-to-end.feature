Feature: End-To-End Test

  Background:
    * def generateId = function(){ return Math.random().toString(36).substring(2) }
    # As a prerequisite, TalkAdvisor should be running and healthy
    * call read('health.feature')

  @e2e
  Scenario: Executing the complete workflow
    * def userId = generateId()
    # Trying to get recommendation without having a profile
    * call read('recommendation.feature@name=CreateRecommendation')
    * assert responseStatus == 400
    # Creating a profile for the user
    * call read('profile.feature@name=CreateProfile')
    * assert responseStatus == 201
    # Trying to create again a profile for the same user
    * call read('profile.feature@name=CreateProfile')
    * assert responseStatus == 409
    # Getting recommendations
    * call read('recommendation.feature@name=CreateRecommendation')
    * assert responseStatus == 201