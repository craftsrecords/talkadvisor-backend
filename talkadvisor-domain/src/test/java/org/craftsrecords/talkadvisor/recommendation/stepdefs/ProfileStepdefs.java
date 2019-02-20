package org.craftsrecords.talkadvisor.recommendation.stepdefs;

import cucumber.api.java8.En;
import org.craftsrecords.talkadvisor.recommendation.api.CreateProfile;
import org.craftsrecords.talkadvisor.recommendation.preferences.Preferences;
import org.craftsrecords.talkadvisor.recommendation.profile.CriteriaFactoryKt;
import org.craftsrecords.talkadvisor.recommendation.profile.NoProfileFoundException;
import org.craftsrecords.talkadvisor.recommendation.profile.Profile;
import org.craftsrecords.talkadvisor.recommendation.spi.Profiles;
import org.jetbrains.annotations.NotNull;

import static org.craftsrecords.talkadvisor.recommendation.assertions.TalkAdvisorAssertions.assertThat;

public class ProfileStepdefs implements En {
    public ProfileStepdefs(TestContext testContext, CreateProfile createProfile, Profiles profiles) {
        Given("^a user$", () -> {
            testContext.setUserId("frequentUser");
        });
        Given("^a user with no profile$", () -> {
            testContext.setUserId("noProfileUser");
        });
        Given("^he has stored his preferences in his profile$", () -> {
            Profile profile = createProfile(testContext.userId);
            profiles.save(profile);
            testContext.setCreatedProfile(profile);
        });

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
        Then("^he is notified that his profile cannot be found$", () -> {
            assertThat(testContext.getError())
                    .isNotNull()
                    .isInstanceOf(NoProfileFoundException.class)
                    .hasMessage(String.format("No profile found for the user %s", testContext.userId));
        });
    }

    @NotNull
    private Profile createProfile(String userId) {
        Preferences preferences = CriteriaFactoryKt.createPreferences();
        return new Profile(userId, preferences);
    }
}
