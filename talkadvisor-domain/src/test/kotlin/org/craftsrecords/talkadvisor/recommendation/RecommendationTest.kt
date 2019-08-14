package org.craftsrecords.talkadvisor.recommendation

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.craftsrecords.talkadvisor.EntityTest
import org.craftsrecords.talkadvisor.recommendation.criteria.Criteria
import org.craftsrecords.talkadvisor.recommendation.criteria.GuestCriteria
import org.craftsrecords.talkadvisor.recommendation.criteria.createCriteria
import org.craftsrecords.talkadvisor.recommendation.preferences.Topic
import org.craftsrecords.talkadvisor.recommendation.talk.Talk
import org.craftsrecords.talkadvisor.recommendation.talk.TalkFormat
import org.craftsrecords.talkadvisor.recommendation.talk.createTalks
import org.junit.jupiter.api.Test
import java.time.Duration
import java.util.*

@InjectDomainObjects
class RecommendationTest : EntityTest<Recommendation> {

    @Test
    fun `should create a recommendation`(@Linked criteriaAndTalks: Pair<Criteria, Set<Talk>>) {
        val (criteria, talks) = criteriaAndTalks

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
    fun `should store a copy of the talks`(@Linked criteriaAndTalks: Pair<Criteria, Set<Talk>>,
                                           newTalk: Talk) {

        var (criteria, talks) = criteriaAndTalks
        talks = talks.toMutableSet()

        val recommendation = Recommendation(criteria = criteria, talks = talks)

        talks.add(newTalk)

        assertThat(recommendation.talks).doesNotContain(newTalk)
    }


    override fun createEqualEntities(): Pair<Recommendation, Recommendation> {
        val id = UUID.randomUUID()
        val criteria = createCriteria()
        return Pair(
                Recommendation(id, criteria = criteria, talks = createTalks(criteria)),
                Recommendation(id, criteria = criteria, talks = createTalks(criteria))
        )
    }

    override fun createNonEqualEntities(): Pair<Recommendation, Recommendation> {
        val criteria = createCriteria()
        val talks = createTalks(criteria)
        return Pair(
                Recommendation(criteria = criteria, talks = talks),
                Recommendation(criteria = criteria, talks = talks)
        )
    }
}
