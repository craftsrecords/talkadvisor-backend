package org.craftsrecords.talkadvisor.recommendation.spi

import org.craftsrecords.talkadvisor.recommendation.preferences.Topic
import org.craftsrecords.talkadvisor.recommendation.talk.Talk

@FunctionalInterface
interface SearchTalks {
    val maxNumberOfTalks: Int
    fun forTopics(topics: Set<Topic>): Set<Talk>
}
