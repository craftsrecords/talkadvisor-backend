package org.craftsrecords.talkadvisor.recommendation.stepdefs;

import cucumber.api.java8.En;
import org.craftsrecords.talkadvisor.recommendation.api.CreateProfile;
import org.craftsrecords.talkadvisor.recommendation.preferences.Preferences;
import org.craftsrecords.talkadvisor.recommendation.profile.Profile;

import static org.craftsrecords.talkadvisor.recommendation.assertions.TalkAdvisorAssertions.assertThat;

public class ProfileStepdefs implements En {
    public ProfileStepdefs(TestContext testContext, CreateProfile createProfile) {
        When("^he creates his profile$", () -> {
            Preferences preferences = new Preferences(testContext.getRequestedTopics(), testContext.getRequestedTalksFormats());
            Profile profile = createProfile.forUserWithPreferences(testContext.getUserId(), preferences);
            testContext.setRequestedPreferences(preferences);
            testContext.setCreatedProfile(profile);
        });

        Then("^his preferences are stored within$", () -> {
            Profile profile = testContext.getCreatedProfile();
            assertThat(profile).correspondToUser(testContext.getUserId());
            assertThat(profile).hasPreferences(testContext.getRequestedPreferences());
        });
    }
}
