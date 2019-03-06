package org.craftsrecords.talkadvisor.recommendation.stepdefs

import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import org.craftsrecords.talkadvisor.recommendation.api.RecommendTalks
import org.craftsrecords.talkadvisor.recommendation.assertions.that
import org.craftsrecords.talkadvisor.recommendation.criteria.GuestCriteria
import org.craftsrecords.talkadvisor.recommendation.talk.TalkFormat
import java.time.Duration.ofMinutes

class RecommendationStepdefs(private val testContext: TestContext,
                             private val recommendTalks: RecommendTalks) {

    @When("^he asks for a recommendation given his criteria$")
    fun `he asks for a recommendation given his criteria`() {
        val guestCriteria = createGuestCriteriaFromContext()

        val recommendation = recommendTalks satisfying guestCriteria

        testContext.recommendation = recommendation
    }

    @When("^he asks for a recommendation$")
    fun `he asks for a recommendation`() {
        try {
            val user = testContext.userId

            val recommendation = recommendTalks to user

            testContext.recommendation = recommendation
        } catch (e: Exception) {
            testContext.error = e
        }
    }

    @Then("^talkadvisor recommends some talks$")
    fun `talkadvisor recommends some talks`() {
        val recommendation = testContext.recommendation

        recommendation.that.hasTalks()
    }

    @Then("^the recommended talks are related to (.+)")
    fun `the recommended talks are related to`(topic: String) {
        val recommendation = testContext.recommendation

        recommendation.that `has talks related to` topic
    }

    @Then("^all the talks corresponding to the (.+) format have a duration between (\\d+) and (\\d+) minutes$")
    fun `all the talks corresponding to the format have a duration in range`(format: String, minDuration: Long, maxDuration: Long) {
        val recommendation = testContext.recommendation
        val talkFormat = TalkFormat.valueOf(format)
        val range = ofMinutes(minDuration).rangeTo(ofMinutes(maxDuration))

        recommendation.that `has only talks in the format` talkFormat
        recommendation.that `has only talks having their duration in` range
    }

    @Then("^the recommended talks correspond to his preferences$")
    fun `the recommended talks correspond to his preferences`() {
        val recommendation = testContext.recommendation
        val profile = testContext.createdProfile
        val preferences = profile.preferences

        recommendation.that `corresponds to the criteria` preferences
        recommendation.that `has talks related to` preferences.topics
        recommendation.that `has only talks in the formats` preferences.talksFormats
    }

    private fun createGuestCriteriaFromContext(): GuestCriteria {
        return GuestCriteria(testContext.requestedTopics, testContext.requestedTalksFormats)
    }
}
