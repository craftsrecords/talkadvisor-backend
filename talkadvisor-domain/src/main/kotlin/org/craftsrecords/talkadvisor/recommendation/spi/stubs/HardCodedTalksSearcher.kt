package org.craftsrecords.talkadvisor.recommendation.spi.stubs

import org.craftsrecords.hexarch.Stub
import org.craftsrecords.talkadvisor.recommendation.preferences.Topic
import org.craftsrecords.talkadvisor.recommendation.spi.SearchTalks
import org.craftsrecords.talkadvisor.recommendation.talk.Talk
import org.craftsrecords.talkadvisor.recommendation.talk.TalkFormat
import java.util.*

@Stub
class HardCodedTalksSearcher : SearchTalks {
    override val maxNumberOfTalks: Int = 5//ignored value

    override fun forTopics(topics: Set<Topic>): Set<Talk> {
        return createTalks(topics)
    }

    private fun createTalks(topics: Set<Topic>): Set<Talk> {
        return topics.flatMap { createTalksForTopic(it.name) }.toSet()
    }

    private fun createTalksForTopic(topicName: String): Set<Talk> {
        return TalkFormat.values().map {
            Talk.with {
                id = UUID.randomUUID().toString()
                title = "${it.name} $topicName"
                duration = it.durationRange.start.plusSeconds(30)
            }.build()
        }.toSet()
    }
}