package org.craftsrecords.talkadvisor.recommendation.talk

import org.craftsrecords.talkadvisor.recommendation.criteria.Criteria
import java.time.Duration.ofMinutes
import java.util.*
import kotlin.random.Random

fun createTalks() = setOf(createTalk(), createTalk())
fun createTalks(criteria: Criteria) = setOf(createTalk(criteria), createTalk(criteria))

fun createTalk(criteria: Criteria): Talk {
    return prepareBuilder()
            .apply { duration = durationFrom(criteria) }
            .build()
}


fun createTalk(): Talk {
    return prepareBuilder().apply { duration = ofMinutes(Random.nextLong(2, 120)) }.build()
}

private fun durationFrom(criteria: Criteria) = criteria.talksFormats.random().durationRange.start
private fun prepareBuilder(): Talk.Builder {
    return Talk.with {
        id = UUID.randomUUID().toString()
        title = "title ${Random.nextInt()}"
    }
}
