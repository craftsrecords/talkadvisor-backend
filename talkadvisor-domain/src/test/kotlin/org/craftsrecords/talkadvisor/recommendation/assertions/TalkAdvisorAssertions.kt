package org.craftsrecords.talkadvisor.recommendation.assertions

import org.assertj.core.api.Assertions
import org.craftsrecords.talkadvisor.recommendation.Recommendation
import org.craftsrecords.talkadvisor.recommendation.talk.Talk

class TalkAdvisorAssertions : Assertions() {
    companion object Asserter {
        @JvmStatic
        fun assertThat(actual: Recommendation) = RecommendationAssert(actual)

        @JvmStatic
        fun assertThat(actual: Talk) = TalkAssert(actual)

        @JvmStatic
        fun assertThat(actual: Iterable<Talk>) = TalksAssert(actual)
    }
}