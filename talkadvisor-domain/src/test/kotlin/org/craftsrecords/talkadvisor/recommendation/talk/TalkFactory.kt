package org.craftsrecords.talkadvisor.recommendation.talk

import java.time.Duration.ofMinutes
import java.util.*
import kotlin.random.Random

fun createTalks() = setOf(createTalk(), createTalk())

fun createTalk(): Talk {
    return Talk.with {
        id = UUID.randomUUID().toString()
        title = "title ${Random.nextInt()}"
        duration = ofMinutes(Random.nextLong(2, 120))
    }.build()
}
