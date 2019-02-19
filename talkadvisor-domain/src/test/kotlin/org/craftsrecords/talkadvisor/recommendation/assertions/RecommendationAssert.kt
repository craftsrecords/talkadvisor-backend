package org.craftsrecords.talkadvisor.recommendation.assertions

import org.assertj.core.api.AbstractAssert
import org.craftsrecords.talkadvisor.recommendation.Recommendation
import org.craftsrecords.talkadvisor.recommendation.assertions.TalkAdvisorAssertions.Asserter.assertThat
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
        assertThat(actual.talks) `are related to topic` topicName
    }

    @JvmName("hasOnlyTalksInTheFormat")
    infix fun `has only talks in the format`(talkFormat: TalkFormat) {
        assertThat(actual.talks) `are in the format` talkFormat
    }

    @JvmName("hasOnlyTalksHavingTheirDurationIn")
    fun `has only talks having their duration in`(range: ClosedRange<Duration>) {
        assertThat(actual.talks) `have their duration in ` range
    }
}