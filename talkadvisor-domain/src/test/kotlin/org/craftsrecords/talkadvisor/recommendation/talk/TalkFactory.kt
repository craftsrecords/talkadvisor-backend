package org.craftsrecords.talkadvisor.recommendation.talk

import org.craftsrecords.talkadvisor.recommendation.criteria.Criteria
import java.time.Duration
import java.time.Duration.ofMinutes
import java.util.*
import kotlin.random.Random

fun createTalks(criteria: Criteria) =
        setOf(createTalk(criteria), createTalk(criteria))

fun createTalk(criteria: Criteria): Talk =
        prepareBuilder()
                .apply { duration = durationFrom(criteria) }
                .build()


fun createTalk(): Talk =
        prepareBuilder()
                .apply { duration = ofMinutes(Random.nextLong(2, 120)) }
                .build()

private fun durationFrom(criteria: Criteria) =
        criteria.talksFormats.random().randomDuration()

private fun prepareBuilder(): Talk.Builder =
        Talk.with {
            id = UUID.randomUUID().toString()
            title = "title ${Random.nextInt()}"
        }

private fun TalkFormat.randomDuration(): Duration =
        durationRange.start.plusSeconds(Random.nextLong(1, 59))
