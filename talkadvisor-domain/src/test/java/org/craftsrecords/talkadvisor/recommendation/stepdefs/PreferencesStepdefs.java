package org.craftsrecords.talkadvisor.recommendation.stepdefs;

import cucumber.api.java8.En;
import org.craftsrecords.talkadvisor.recommendation.preferences.Topic;
import org.craftsrecords.talkadvisor.recommendation.talk.TalkFormat;

import java.util.Set;

import static java.util.Collections.singleton;

public class PreferencesStepdefs implements En {
    public PreferencesStepdefs(TestContext testContext) {
        Given("^a guest user who wants to learn (.+)",
                (String topicName) -> testContext.setRequestedTopic(new Topic(topicName)));
        Given("^he has only time to watch (.+) talks$",
                (String format) -> {
                    Set<TalkFormat> talkFormats = singleton(TalkFormat.valueOf(format));
                    testContext.setRequestedTalksFormats(talkFormats);
                });
    }
}
