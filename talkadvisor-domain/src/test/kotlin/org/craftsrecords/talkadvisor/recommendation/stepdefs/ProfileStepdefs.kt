package org.craftsrecords.talkadvisor.recommendation.stepdefs

import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import org.assertj.core.api.Assertions.assertThat
import org.craftsrecords.talkadvisor.recommendation.api.CreateProfile
import org.craftsrecords.talkadvisor.recommendation.assertions.that
import org.craftsrecords.talkadvisor.recommendation.criteria.createPreferences
import org.craftsrecords.talkadvisor.recommendation.preferences.Preferences
import org.craftsrecords.talkadvisor.recommendation.profile.Profile
import org.craftsrecords.talkadvisor.recommendation.profile.ProfileAlreadyExistsException
import org.craftsrecords.talkadvisor.recommendation.profile.ProfileNotFoundException
import org.craftsrecords.talkadvisor.recommendation.spi.Profiles

class ProfileStepdefs(private val testContext: TestContext,
                      private val createProfile: CreateProfile,
                      private val profiles: Profiles) {

    @Given("^a user$")
    fun `a user`() {
        testContext.userId = "frequentUser"
    }

    @Given("^a user with no profile$")
    fun `a user with no profile`() {
        testContext.userId = "noProfileUser"
    }

    @Given("^he already has a profile$")
    fun `he already has a profile`() {
        val profile = Profile(testContext.userId, createPreferences())
        profiles.save(profile)
    }

    @Given("^he has stored his preferences in his profile$")
    fun `he has stored his preferences in his profile`() {
        val profile = createProfile(testContext.userId)
        profiles.save(profile)

        testContext.createdProfile = profile
    }

    @When("^he creates his profile$")
    fun `he creates his profile`() {
        val preferences = preferencesFromContext()

        val profile = createProfile.forUserWithPreferences(testContext.userId, preferences)

        testContext.requestedPreferences = preferences
        testContext.createdProfile = profile
    }

    @When("^he tries to create again his profile$")
    fun `he tries to create again his profile`() {
        try {
            createProfile.forUserWithPreferences(testContext.userId, createPreferences())
        } catch (e: Exception) {
            testContext.error = e
        }
    }

    @Then("^his preferences are stored within$")
    fun `his preferences are stored within`() {
        val profile = testContext.createdProfile
        val frequentUser = testContext.userId
        val hisPreferences = testContext.requestedPreferences

        profile.that `corresponds to user` frequentUser
        profile.that `has preferences` hisPreferences
    }

    @Then("^he is notified that his profile cannot be found$")
    fun `he is notified that his profile cannot be found`() {
        assertThat(testContext.error)
                .isNotNull()
                .isInstanceOf(ProfileNotFoundException::class.java)
                .hasMessage(String.format("No profile found for the user %s", testContext.userId))
    }

    @Then("^he is notified that his profile already exists$")
    fun `he is notified that his profile already exists`() {
        assertThat(testContext.error)
                .isNotNull()
                .isInstanceOf(ProfileAlreadyExistsException::class.java)
                .hasMessage(String.format("A profile already exists for the user %s", testContext.userId))
    }

    private fun createProfile(userId: String): Profile {
        return Profile(userId, createPreferences())
    }

    private fun preferencesFromContext(): Preferences {
        return Preferences(testContext.requestedTopics, testContext.requestedTalksFormats)
    }
}
