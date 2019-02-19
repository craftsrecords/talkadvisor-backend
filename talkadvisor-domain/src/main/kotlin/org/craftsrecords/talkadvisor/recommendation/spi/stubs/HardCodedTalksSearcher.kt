package org.craftsrecords.talkadvisor.recommendation.spi.stubs

import org.craftsrecords.talkadvisor.recommendation.criteria.Topic
import org.craftsrecords.talkadvisor.recommendation.spi.SearchTalks
import org.craftsrecords.talkadvisor.recommendation.talk.Talk
import java.time.Duration
import kotlin.random.Random

class HardCodedTalksSearcher : SearchTalks {
    override fun forTopic(topic: Topic): Set<Talk> {
        return createTalks(topic)
    }

    private fun createTalks(topic: Topic): Set<Talk> {
        return (1..20)
                .map {
                    Talk.with {
                        id = it.toString()
                        title = "${randomText()} ${topic.name} ${randomText()}"
                        duration = Duration.ofMinutes(Random.nextLong(2, 100))
                    }.build()
                }.toSet()
    }

    private fun randomText() = Random.nextBits(4).toString()
}