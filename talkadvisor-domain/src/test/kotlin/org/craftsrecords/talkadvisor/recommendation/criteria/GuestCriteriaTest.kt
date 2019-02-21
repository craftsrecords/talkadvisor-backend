package org.craftsrecords.talkadvisor.recommendation.criteria

import org.assertj.core.api.Assertions.assertThat
import org.craftsrecords.talkadvisor.recommendation.preferences.Topic
import org.craftsrecords.talkadvisor.recommendation.talk.TalkFormat.CONFERENCE
import org.craftsrecords.talkadvisor.recommendation.talk.TalkFormat.QUICKIE
import org.junit.jupiter.api.Test

internal class GuestCriteriaTest {

    @Test
    fun `should store a copy of the talks formats`() {

        val talksFormats = mutableSetOf(CONFERENCE)
        val guestCriteria = GuestCriteria(setOf(Topic("ddd")), talksFormats)

        talksFormats.add(QUICKIE)

        assertThat(guestCriteria.talksFormats).containsOnly(CONFERENCE)
    }

    @Test
    fun `should store a copy of the topics`() {

        val topics = mutableSetOf(Topic("ddd"))
        val guestCriteria = GuestCriteria(topics, setOf(CONFERENCE))
        val newTopic = Topic("new")
        topics.add(newTopic)

        assertThat(guestCriteria.topics).doesNotContain(newTopic)
    }

    @Test
    fun `should satisfy value object equality`() {
        val guestCriteria = GuestCriteria(setOf(Topic("ddd")), setOf(CONFERENCE))
        val guestCriteria2 = GuestCriteria(setOf(Topic("ddd")), setOf(CONFERENCE))

        assertThat(guestCriteria).isEqualTo(guestCriteria2)
        assertThat(guestCriteria.hashCode()).isEqualTo(guestCriteria2.hashCode())
    }

    @Test
    fun `should satisfy value object inequality`() {
        val guestCriteria = GuestCriteria(setOf(Topic("ddd")), setOf(CONFERENCE))
        val guestCriteria2 = GuestCriteria(setOf(Topic("ddd")), setOf(QUICKIE))

        assertThat(guestCriteria).isNotEqualTo(guestCriteria2)
        assertThat(guestCriteria.hashCode()).isNotEqualTo(guestCriteria2.hashCode())
    }
}