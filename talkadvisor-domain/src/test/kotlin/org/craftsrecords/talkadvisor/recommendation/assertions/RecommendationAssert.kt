package org.craftsrecords.talkadvisor.recommendation.assertions

import org.assertj.core.api.AbstractAssert
import org.craftsrecords.talkadvisor.recommendation.Recommendation
import org.craftsrecords.talkadvisor.recommendation.criteria.Criteria
import org.craftsrecords.talkadvisor.recommendation.preferences.Topic
import org.craftsrecords.talkadvisor.recommendation.talk.TalkFormat
import java.time.Duration

class RecommendationAssert(actual: Recommendation) : AbstractAssert<RecommendationAssert, Recommendation>(
        actual,
        RecommendationAssert::class.java
) {

    fun hasTalks() {
        matches({ it.talks.isNotEmpty() }, "has talks")
    }

    infix fun `has talks related to`(topicName: String) {
        matches({
            it.criteria.topics.any { topic -> topic.name == topicName }
        }, "recommendations criteria has the topic $topicName")

        actual.talks.those `are related to topic` topicName
    }

    infix fun `has talks related to`(topics: Set<Topic>) {
        matches({
            it.criteria.topics.all { topic -> topics.contains(topic) }
        }, "recommendations criteria has the given topics")

        actual.talks.those `are related to topics` topics
    }

    infix fun `has only talks in the format`(talkFormat: TalkFormat) {
        matches({
            it.criteria.talksFormats.all { format -> format == talkFormat }
        }, "recommendations criteria are only of format $talkFormat")

        actual.talks.those `are in the format` talkFormat
    }

    infix fun `has only talks having their duration in`(range: ClosedRange<Duration>) {
        actual.talks.those `have their duration in ` range
    }

    infix fun `has only talks in the formats`(talksFormats: Set<TalkFormat>) {
        matches({
            it.criteria.talksFormats.all { talkFormat -> talksFormats.contains(talkFormat) }
        }, "recommendations criteria are talks format in the expected ones")
        actual.talks.those `have their format in` talksFormats
    }

    infix fun `corresponds to the criteria`(criteria: Criteria) {
        matches({ it.criteria == criteria }, "corresponds to the given criteria")
    }
}