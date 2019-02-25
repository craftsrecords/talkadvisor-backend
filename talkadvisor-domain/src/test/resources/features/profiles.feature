Feature: As a frequent user,
  In order not repeat my preferences at each request,
  I want to create my profile with my preferences

  Scenario: The user is creating his profile with his preferences

    Given a user
    And he wants to learn
      | DDD | hexagonal architecture |
    And he only wants to see
      | QUICKIE | CONFERENCE |
    When he creates his profile
    Then his preferences are stored within

  Scenario: A user is trying to create a profile which already exists
    Given a user
    And he already has a profile
    When he tries to create again his profile
    Then he is notified that his profile already exists
