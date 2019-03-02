Feature: health endpoint

  Background:
    * url talkAdvisorUrl

  Scenario: asking for the health of the application should return UP

    Given path 'actuator/health'
    When method get
    Then status 200
    Then match response.status == 'UP'