package org.craftsrecords.steps;

import cucumber.api.java8.En;
import org.craftsrecords.steps.context.TopicToLearn;

public class UserStepdefs implements En {
    public UserStepdefs(TopicToLearn topicToLearn) {
        Given("^a guest user who wants to learn (.*)", topicToLearn::setTopic);
    }
}
