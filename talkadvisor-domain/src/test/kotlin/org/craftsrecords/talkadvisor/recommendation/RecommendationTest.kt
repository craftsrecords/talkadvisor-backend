package org.craftsrecords.talkadvisor.recommendation

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.craftsrecords.talkadvisor.recommendation.criteria.Criteria
import org.craftsrecords.talkadvisor.recommendation.criteria.GuestCriteria
import org.craftsrecords.talkadvisor.recommendation.criteria.createCriteria
import org.craftsrecords.talkadvisor.recommendation.preferences.Topic
import org.craftsrecords.talkadvisor.recommendation.talk.Talk
import org.craftsrecords.talkadvisor.recommendation.talk.TalkFormat
import org.craftsrecords.talkadvisor.recommendation.talk.createTalk
import org.craftsrecords.talkadvisor.recommendation.talk.createTalks
import org.junit.jupiter.api.Test
import java.time.Duration
import java.util.*

internal class RecommendationTest {
    @Test
    fun `should create a recommendation`() {
        val (criteria, talks) = bootstrap()

        val recommendation = Recommendation(criteria = criteria, talks = talks)

        assertThat(recommendation.id).isNotNull()
        assertThat(recommendation.talks).isEqualTo(talks)
        assertThat(recommendation.criteria).isEqualTo(criteria)
    }

    @Test
    fun `should not create recommendation with criteria that doesn't corresponds to the talks`() {
        val criteria = GuestCriteria(setOf(Topic("topic")), setOf(TalkFormat.UNIVERSITY))
        val talk = Talk.with {
            id = "id"
            title = "something related to the topic"
            duration = Duration.ofMinutes(15)
        }.build()

        assertThatThrownBy { Recommendation(criteria = criteria, talks = setOf(talk)) }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage("Criteria talk formats doesn't correspond to the format of the recommended talks")
    }

    @Test
    fun `should store a copy of the talks`() {

        var (criteria, talks) = bootstrap()
        talks = talks.toMutableSet()
        val recommendation = Recommendation(criteria = criteria, talks = talks)
        val newTalk = createTalk()

        talks.add(newTalk)

        assertThat(recommendation.talks).doesNotContain(newTalk)
    }

    @Test
    fun `should satisfy entity equality`() {
        val id = UUID.randomUUID()
        val criteria = createCriteria()

        val recommendation1 = Recommendation(id, criteria = criteria, talks = createTalks(criteria))
        val recommendation2 = Recommendation(id, criteria = criteria, talks = createTalks(criteria))

        assertThat(recommendation1).isEqualTo(recommendation2)
        assertThat(recommendation1.hashCode()).isEqualTo(recommendation2.hashCode())
    }

    @Test
    fun `should satisfy entity inequality`() {

        val (criteria, talks) = bootstrap()

        val recommendation1 = Recommendation(criteria = criteria, talks = talks)
        val recommendation2 = Recommendation(criteria = criteria, talks = talks)

        assertThat(recommendation1).isNotEqualTo(recommendation2)
        assertThat(recommendation1.hashCode()).isNotEqualTo(recommendation2.hashCode())
    }

    private fun bootstrap(): Pair<Criteria, Set<Talk>> {
        val criteria = createCriteria()
        val talks = createTalks(criteria)
        return Pair(criteria, talks)
    }

}
