package org.craftsrecords.talkadvisor.recommendation.profile

import org.craftsrecords.talkadvisor.recommendation.criteria.Criteria
import org.craftsrecords.talkadvisor.recommendation.preferences.Preferences
import org.craftsrecords.talkadvisor.recommendation.preferences.Topic
import org.craftsrecords.talkadvisor.recommendation.talk.TalkFormat
import kotlin.random.Random

fun createPreferences() =
        Preferences(
                setOf(Topic(Random.nextInt().toString()), Topic(Random.nextInt().toString())),
                setOf(TalkFormat.CONFERENCE, TalkFormat.QUICKIE))

fun createCriteria(): Criteria = createPreferences()
