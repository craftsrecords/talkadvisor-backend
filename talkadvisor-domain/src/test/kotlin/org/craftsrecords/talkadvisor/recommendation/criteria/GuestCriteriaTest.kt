package org.craftsrecords.talkadvisor.recommendation.criteria

import org.assertj.core.api.Assertions.assertThat
import org.craftsrecords.talkadvisor.recommendation.preferences.Topic
import org.craftsrecords.talkadvisor.recommendation.talk.TalkFormat
import org.junit.jupiter.api.Test

internal class GuestCriteriaTest {

    @Test
    fun `should store a copy of the talks formats`() {

        val talksFormats = mutableSetOf(TalkFormat.CONFERENCE)
        val guestCriteria = GuestCriteria(setOf(Topic("ddd")), talksFormats)

        talksFormats.add(TalkFormat.QUICKIE)

        assertThat(guestCriteria.talksFormats).containsOnly(TalkFormat.CONFERENCE)
    }

    @Test
    fun `should store a copy of the topics`() {

        val topics = mutableSetOf(Topic("ddd"))
        val guestCriteria = GuestCriteria(topics, setOf(TalkFormat.CONFERENCE))
        val newTopic = Topic("new")
        topics.add(newTopic)

        assertThat(guestCriteria.topics).doesNotContain(newTopic)
    }

    @Test
    fun `should satisfy value object equality`() {
        val guestCriteria = GuestCriteria(setOf(Topic("ddd")), setOf(TalkFormat.CONFERENCE))
        val guestCriteria2 = GuestCriteria(setOf(Topic("ddd")), setOf(TalkFormat.CONFERENCE))

        assertThat(guestCriteria).isEqualTo(guestCriteria2)
        assertThat(guestCriteria.hashCode()).isEqualTo(guestCriteria2.hashCode())
    }

    @Test
    fun `should satisfy value object inequality`() {
        val guestCriteria = GuestCriteria(setOf(Topic("ddd")), setOf(TalkFormat.CONFERENCE))
        val guestCriteria2 = GuestCriteria(setOf(Topic("ddd")), setOf(TalkFormat.QUICKIE))

        assertThat(guestCriteria).isNotEqualTo(guestCriteria2)
        assertThat(guestCriteria.hashCode()).isNotEqualTo(guestCriteria2.hashCode())
    }
}