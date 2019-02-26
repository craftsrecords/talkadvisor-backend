package org.craftsrecords.talkadvisor.recommendation.criteria

import org.craftsrecords.talkadvisor.nextString
import org.craftsrecords.talkadvisor.recommendation.preferences.Preferences
import org.craftsrecords.talkadvisor.recommendation.preferences.Topic
import org.craftsrecords.talkadvisor.recommendation.talk.TalkFormat
import kotlin.random.Random

fun createPreferences() =
        Preferences(
                setOf(Topic(Random.nextString()), Topic(Random.nextString())),
                setOf(TalkFormat.CONFERENCE, TalkFormat.QUICKIE))

fun createCriteria(): Criteria = createPreferences()
