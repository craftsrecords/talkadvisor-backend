package org.craftsrecords.talkadvisor.recommendation.criteria

import org.craftsrecords.talkadvisor.recommendation.preferences.Preferences
import org.craftsrecords.talkadvisor.recommendation.preferences.Topic
import org.craftsrecords.talkadvisor.recommendation.talk.TalkFormat

fun createPreferences() =
        Preferences(
                setOf(Topic("DDD"), Topic("Hexagonal Architecture")),
                setOf(TalkFormat.values().random(), TalkFormat.QUICKIE, TalkFormat.IGNITE))

fun createCriteria(): Criteria = createPreferences()
