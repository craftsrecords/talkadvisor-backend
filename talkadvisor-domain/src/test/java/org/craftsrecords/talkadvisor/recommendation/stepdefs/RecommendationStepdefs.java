package org.craftsrecords.talkadvisor.recommendation.stepdefs;

import cucumber.api.java8.En;
import kotlin.ranges.ClosedRange;
import org.craftsrecords.talkadvisor.recommendation.Recommendation;
import org.craftsrecords.talkadvisor.recommendation.api.RecommendTalksForTopic;
import org.craftsrecords.talkadvisor.recommendation.criteria.Criteria;
import org.craftsrecords.talkadvisor.recommendation.talk.TalkFormat;

import java.time.Duration;

import static java.time.Duration.ofMinutes;
import static kotlin.ranges.RangesKt.rangeTo;
import static org.craftsrecords.talkadvisor.recommendation.assertions.TalkAdvisorAssertions.assertThat;

public class RecommendationStepdefs implements En {
    public RecommendationStepdefs(TestContext testContext,
                                  RecommendTalksForTopic recommendTalksForTopic) {
        When("^he asks for the related talks$",
                () -> {
                    Criteria criteria = createCriteriaFrom(testContext);
                    Recommendation recommendation = recommendTalksForTopic.getRecommendation(criteria);
                    testContext.setRecommendationResult(recommendation);
                });
        Then("^talkadvisor recommends some talks$", () -> {
            Recommendation recommendation = testContext.getRecommendationResult();
            assertThat(recommendation).hasTalks();
        });
        Then("^the recommended talks are related to (.+)",
                (String topicName) -> {
                    Recommendation recommendation = testContext.getRecommendationResult();
                    assertThat(recommendation).hasTalksRelatedTo(topicName);
                });
        Then("^all the talks corresponding to the (.+) format have a duration between (\\d+) and (\\d+) minutes$",
                (String format, Integer minDuration, Integer maxDuration) -> {
                    Recommendation recommendation = testContext.getRecommendationResult();
                    TalkFormat talkFormat = TalkFormat.valueOf(format);
                    ClosedRange<Duration> range = rangeTo(ofMinutes(minDuration), ofMinutes(maxDuration));
                    assertThat(recommendation).hasOnlyTalksInTheFormat(talkFormat);
                    assertThat(recommendation).hasOnlyTalksHavingTheirDurationIn(range);
                });
    }

    private Criteria createCriteriaFrom(TestContext testContext) {
        return new Criteria(testContext.getRequestedTopic(), testContext.getRequestedTalksFormats());
    }
}
