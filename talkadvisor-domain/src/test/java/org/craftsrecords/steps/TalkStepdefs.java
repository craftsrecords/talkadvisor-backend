package org.craftsrecords.steps;

import cucumber.api.java8.En;
import org.craftsrecords.steps.context.TopicToLearn;
import org.craftsrecords.talkadvisor.recommendation.api.RecommendTalksForTopic;

public class TalkStepdefs implements En {
    public TalkStepdefs(TopicToLearn topicToLearn, RecommendTalksForTopic recommendTalksForTopic) {

        When("^he asks for the related talks$",
                () -> recommendTalksForTopic.getRecommendation(topicToLearn.getTopic()));
    }
}
