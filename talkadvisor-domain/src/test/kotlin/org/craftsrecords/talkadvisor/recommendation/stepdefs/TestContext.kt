package org.craftsrecords.talkadvisor.recommendation.stepdefs

import org.craftsrecords.talkadvisor.recommendation.Recommendation
import org.craftsrecords.talkadvisor.recommendation.preferences.Topic
import org.craftsrecords.talkadvisor.recommendation.talk.TalkFormat

class TestContext {
    lateinit var requestedTopic: Topic
    lateinit var requestedTalksFormats: Set<TalkFormat>
    lateinit var recommendationResult: Recommendation
}