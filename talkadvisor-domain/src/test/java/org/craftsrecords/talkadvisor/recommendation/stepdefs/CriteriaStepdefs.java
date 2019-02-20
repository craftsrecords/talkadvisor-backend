package org.craftsrecords.talkadvisor.recommendation.stepdefs;

import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;
import org.craftsrecords.talkadvisor.recommendation.preferences.Topic;
import org.craftsrecords.talkadvisor.recommendation.talk.TalkFormat;

import java.util.Set;

import static java.util.Collections.singleton;
import static java.util.stream.Collectors.toSet;

public class CriteriaStepdefs implements En {
    public CriteriaStepdefs(TestContext testContext) {
        Given("^a guest user who wants to learn (.+)",
                (String topicName) -> testContext.setRequestedTopics(singleton(new Topic(topicName))));

        Given("^he has only time to watch (.+) talks$",
                (String format) -> {
                    Set<TalkFormat> talkFormats = singleton(TalkFormat.valueOf(format));
                    testContext.setRequestedTalksFormats(talkFormats);
                });

        Given("^he wants to learn$", (DataTable topicNames) -> {
            Set<Topic> topics =
                    topicNames.asList()
                            .stream()
                            .map(Topic::new)
                            .collect(toSet());

            testContext.setRequestedTopics(topics);
        });

        Given("^he only wants to see$", (DataTable formats) -> {
            Set<TalkFormat> talkFormats =
                    formats.asList().stream().map(TalkFormat::valueOf).collect(toSet());
            testContext.setRequestedTalksFormats(talkFormats);
        });
    }
}
