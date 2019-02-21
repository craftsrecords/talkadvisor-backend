package org.craftsrecords.talkadvisor.recommendation.criteria

import org.assertj.core.api.Assertions.assertThat
import org.craftsrecords.talkadvisor.recommendation.preferences.Topic
import org.craftsrecords.talkadvisor.recommendation.talk.TalkFormat
import org.craftsrecords.talkadvisor.recommendation.talk.TalkFormat.*
import org.junit.jupiter.api.Test

internal class CriteriaTest {

    @Test
    fun `should have talkFormat`() {
        val talksFormats = setOf(CONFERENCE, UNIVERSITY)

        val criteria: Criteria = createCriteria(talksFormats)

        assertThat(criteria.hasTalkFormat(CONFERENCE)).isTrue()
    }

    @Test
    fun `should not have talkFormat`() {
        val talksFormats = setOf(CONFERENCE, UNIVERSITY)

        val criteria: Criteria = createCriteria(talksFormats)

        assertThat(criteria.hasTalkFormat(QUICKIE)).isFalse()
    }

    private fun createCriteria(talksFormats: Set<TalkFormat>): Criteria {
        return GuestCriteria(setOf(Topic("ddd")), talksFormats)
    }

}