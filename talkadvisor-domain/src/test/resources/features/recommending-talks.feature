Feature: As a user,
  In order to effectively learn a subject,
  I want to get the most relevant talks on topics I'm interested in

  Scenario: A busy guest user with no profile only wants to learn with quickies

    Given a guest user who wants to learn DDD
    And he has only time to watch QUICKIE talks
    When he asks for a recommendation given his criteria
    Then talkadvisor recommends some talks
    And the recommended talks are related to DDD
    And all the talks corresponding to the QUICKIE format have a duration between 10 and 20 minutes
    #And the talks are sorted by relevance

  Scenario: A frequent user wants to get recommendations according to his profile

    Given a user
    And he has stored his preferences in his profile
    When he asks for a recommendation
    Then the recommended talks correspond to his preferences

  Scenario: A user tries to get recommendations according to his profile but he didn't have any yet

    Given a user with no profile
    When he asks for a recommendation
    Then he is notified that his profile cannot be found