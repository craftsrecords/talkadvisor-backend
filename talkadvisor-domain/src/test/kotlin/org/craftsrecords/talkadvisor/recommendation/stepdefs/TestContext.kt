package org.craftsrecords.talkadvisor.recommendation.stepdefs

import org.craftsrecords.talkadvisor.recommendation.Recommendation
import org.craftsrecords.talkadvisor.recommendation.preferences.Preferences
import org.craftsrecords.talkadvisor.recommendation.preferences.Topic
import org.craftsrecords.talkadvisor.recommendation.profile.Profile
import org.craftsrecords.talkadvisor.recommendation.talk.TalkFormat

class TestContext {
    lateinit var requestedTopics: Set<Topic>
    lateinit var requestedTalksFormats: Set<TalkFormat>
    lateinit var recommendation: Recommendation
    lateinit var userId: String
    lateinit var createdProfile: Profile
    lateinit var requestedPreferences: Preferences
    var error: Exception? = null
}