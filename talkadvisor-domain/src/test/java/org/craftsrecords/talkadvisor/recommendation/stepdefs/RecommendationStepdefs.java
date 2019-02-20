package org.craftsrecords.talkadvisor.recommendation.stepdefs;

import cucumber.api.java8.En;
import kotlin.ranges.ClosedRange;
import org.craftsrecords.talkadvisor.recommendation.Recommendation;
import org.craftsrecords.talkadvisor.recommendation.api.RecommendTalks;
import org.craftsrecords.talkadvisor.recommendation.criteria.GuestCriteria;
import org.craftsrecords.talkadvisor.recommendation.preferences.Preferences;
import org.craftsrecords.talkadvisor.recommendation.profile.Profile;
import org.craftsrecords.talkadvisor.recommendation.talk.TalkFormat;

import java.time.Duration;

import static java.time.Duration.ofMinutes;
import static kotlin.ranges.RangesKt.rangeTo;
import static org.craftsrecords.talkadvisor.recommendation.assertions.TalkAdvisorAssertions.assertThat;

public class RecommendationStepdefs implements En {
    public RecommendationStepdefs(TestContext testContext,
                                  RecommendTalks recommendTalks) {
        When("^he asks for a recommendation given his criteria$",
                () -> {
                    GuestCriteria guestCriteria = createGuestCriteriaFrom(testContext);
                    Recommendation recommendation = recommendTalks.getRecommendationSatisfying(guestCriteria);
                    testContext.setRecommendationResult(recommendation);
                });
        When("^he asks for a recommendation$", () -> {
            try {
                Recommendation recommendation = recommendTalks.getRecommendationGivenProfile(testContext.getUserId());
                testContext.setRecommendationResult(recommendation);
            } catch (Exception e) {
                testContext.setError(e);
            }
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

        Then("^the recommended talks correspond to his preferences$", () -> {
            Recommendation recommendation = testContext.getRecommendationResult();
            Profile profile = testContext.getCreatedProfile();
            Preferences preferences = profile.getPreferences();

            assertThat(recommendation).correspondsToTheCriteria(preferences);
            assertThat(recommendation).hasTalksRelatedTo(preferences.getTopics());
            assertThat(recommendation).hasOnlyTalksInTheFormats(preferences.getTalksFormats());
        });
    }

    private GuestCriteria createGuestCriteriaFrom(TestContext testContext) {
        return new GuestCriteria(testContext.getRequestedTopics(), testContext.getRequestedTalksFormats());
    }
}
