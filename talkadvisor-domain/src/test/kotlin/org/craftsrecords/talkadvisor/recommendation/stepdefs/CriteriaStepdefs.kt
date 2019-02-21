package org.craftsrecords.talkadvisor.recommendation.stepdefs

import cucumber.api.java.en.Given
import org.craftsrecords.talkadvisor.recommendation.preferences.Topic
import org.craftsrecords.talkadvisor.recommendation.talk.TalkFormat

class CriteriaStepdefs(private val testContext: TestContext) {

    @Given("^a guest user who wants to learn (.+)")
    fun `a guest user who wants to learn`(topicName: String) {
        testContext.requestedTopics = setOf(Topic(topicName))
    }

    @Given("^he has only time to watch (.+) talks$")
    fun `he has only time to watch talks on`(format: String) {
        val talkFormats = setOf(TalkFormat.valueOf(format))
        testContext.requestedTalksFormats = talkFormats
    }

    @Given("^he wants to learn$")
    fun `he wants to learn`(topicNames: List<String>) {
        val topics = topicNames.map { Topic(it) }.toSet()
        testContext.requestedTopics = topics
    }

    @Given("^he only wants to see$")
    fun `he only wants to see`(formats: List<String>) {
        val talkFormats = formats.map { TalkFormat.valueOf(it) }.toSet()
        testContext.requestedTalksFormats = talkFormats
    }
}
