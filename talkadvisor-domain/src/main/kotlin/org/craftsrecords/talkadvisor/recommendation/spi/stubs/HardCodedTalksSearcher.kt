package org.craftsrecords.talkadvisor.recommendation.spi.stubs

import org.craftsrecords.talkadvisor.recommendation.preferences.Topic
import org.craftsrecords.talkadvisor.recommendation.spi.SearchTalks
import org.craftsrecords.talkadvisor.recommendation.talk.Talk
import java.time.Duration
import kotlin.random.Random

class HardCodedTalksSearcher : SearchTalks {
    override fun forTopics(topics: Set<Topic>): Set<Talk> {
        return createTalks(topics)
    }

    private fun createTalks(topics: Set<Topic>): Set<Talk> {
        return topics.flatMap { createTalksForTopic(it.name) }.shuffled().toSet()
    }

    private fun createTalksForTopic(topicName: String): Set<Talk> {
        return (1..30)
                .map {
                    Talk.with {
                        id = it.toString()
                        title = "${randomText()} $topicName ${randomText()}"
                        duration = Duration.ofMinutes(Random.nextLong(2, 100))
                    }.build()
                }.toSet()
    }

    private fun randomText() = Random.nextBits(4).toString()
}