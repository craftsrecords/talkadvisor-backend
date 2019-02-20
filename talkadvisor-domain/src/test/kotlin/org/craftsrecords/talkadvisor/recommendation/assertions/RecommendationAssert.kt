package org.craftsrecords.talkadvisor.recommendation.assertions

import org.assertj.core.api.AbstractAssert
import org.craftsrecords.talkadvisor.recommendation.Recommendation
import org.craftsrecords.talkadvisor.recommendation.assertions.TalkAdvisorAssertions.Asserter.assertThat
import org.craftsrecords.talkadvisor.recommendation.criteria.Criteria
import org.craftsrecords.talkadvisor.recommendation.preferences.Topic
import org.craftsrecords.talkadvisor.recommendation.talk.TalkFormat
import java.time.Duration

class RecommendationAssert(actual: Recommendation) : AbstractAssert<RecommendationAssert, Recommendation>(
        actual,
        RecommendationAssert::class.java
) {

    @JvmName("hasTalks")
    fun `has talks`() {
        matches({ it.talks.isNotEmpty() }, "has talks")
    }

    @JvmName("hasTalksRelatedTo")
    infix fun `has talks related to`(topicName: String) {
        matches({
            it.criteria.topics.any { topic -> topic.name == topicName }
        }, "recommendations criteria has the topic $topicName")

        assertThat(actual.talks) `are related to topic` topicName
    }

    @JvmName("hasTalksRelatedTo")
    infix fun `has talks related to`(topics: Set<Topic>) {
        matches({
            it.criteria.topics.all { topic -> topics.contains(topic) }
        }, "recommendations criteria has the given topics")

        assertThat(actual.talks) `are related to topics` topics
    }

    @JvmName("hasOnlyTalksInTheFormat")
    infix fun `has only talks in the format`(talkFormat: TalkFormat) {
        matches({
            it.criteria.talksFormats.all { format -> format == talkFormat }
        }, "recommendations criteria are only of format $talkFormat")

        assertThat(actual.talks) `are in the format` talkFormat
    }

    @JvmName("hasOnlyTalksHavingTheirDurationIn")
    infix fun `has only talks having their duration in`(range: ClosedRange<Duration>) {
        assertThat(actual.talks) `have their duration in ` range
    }

    @JvmName("hasOnlyTalksInTheFormats")
    infix fun `has only talks in the formats`(talksFormats: Set<TalkFormat>) {
        matches({
            it.criteria.talksFormats.all { talkFormat -> talksFormats.contains(talkFormat) }
        }, "recommendations criteria are talks format in the expected ones")
        assertThat(actual.talks) `have their format in` talksFormats
    }

    @JvmName("correspondsToTheCriteria")
    infix fun `corresponds to the criteria`(criteria: Criteria) {
        matches({ it.criteria == criteria }, "corresponds to the given criteria")
    }
}