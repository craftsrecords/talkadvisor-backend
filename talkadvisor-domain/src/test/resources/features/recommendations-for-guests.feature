Feature: As a guest user, In order to effectively learn a subject, I want to get the most relevant talks on this topic

  Scenario: The guest user is busy so he only wants to learn with quickies

    Given a guest user who wants to learn DDD
    And he has only time to watch QUICKIE talks
    When he asks for the related talks
    Then talkadvisor recommends some talks
    And the recommended talks are related to DDD
    And all the talks corresponding to the QUICKIE format have a duration between 10 and 20 minutes
    #And the talks are sorted by relevance
