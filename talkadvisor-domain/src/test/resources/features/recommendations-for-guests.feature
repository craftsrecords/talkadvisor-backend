Feature: As a guest user, In order to effectively learn a subject, I want to get the most relevant talks on this topic

  Scenario: The guest user is busy so he only wants to learn with quickies
    Given a guest user who wants to learn DDD
    When he asks for the related talks
    Then the selected talks are related to DDD
    And the talks are sorted by relevance
    And all the talks are quickies