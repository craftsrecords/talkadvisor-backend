package org.craftsrecords.talkadvisor.recommendation

import org.assertj.core.api.Assertions.assertThat
import org.craftsrecords.talkadvisor.recommendation.criteria.createCriteria
import org.craftsrecords.talkadvisor.recommendation.talk.createTalk
import org.craftsrecords.talkadvisor.recommendation.talk.createTalks
import org.junit.jupiter.api.Test
import java.util.*

internal class RecommendationTest {
    @Test
    fun `should create a recommendation`() {
        val talks = createTalks()
        val criteria = createCriteria()

        val recommendation = Recommendation(criteria = criteria, talks = talks)

        assertThat(recommendation.id).isNotNull()
        assertThat(recommendation.talks).isEqualTo(talks)
        assertThat(recommendation.criteria).isEqualTo(criteria)
    }

    @Test
    fun `should store a copy of the talks`() {

        val talks = createTalks().toMutableSet()
        val recommendation = Recommendation(criteria = createCriteria(), talks = talks)
        val newTalk = createTalk()

        talks.add(newTalk)

        assertThat(recommendation.talks).doesNotContain(newTalk)
    }

    @Test
    fun `should satisfy entity equality`() {
        val id = UUID.randomUUID()

        val recommendation1 = Recommendation(id, criteria = createCriteria(), talks = createTalks())
        val recommendation2 = Recommendation(id, criteria = createCriteria(), talks = createTalks())

        assertThat(recommendation1).isEqualTo(recommendation2)
        assertThat(recommendation1.hashCode()).isEqualTo(recommendation2.hashCode())
    }

    @Test
    fun `should satisfy entity inequality`() {

        val talks = createTalks()
        val criteria = createCriteria()

        val recommendation1 = Recommendation(criteria = criteria, talks = talks)
        val recommendation2 = Recommendation(criteria = criteria, talks = talks)

        assertThat(recommendation1).isNotEqualTo(recommendation2)
        assertThat(recommendation1.hashCode()).isNotEqualTo(recommendation2.hashCode())
    }

}
