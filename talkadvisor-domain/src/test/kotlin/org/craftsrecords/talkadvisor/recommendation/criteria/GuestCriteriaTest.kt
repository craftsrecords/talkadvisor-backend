package org.craftsrecords.talkadvisor.recommendation.criteria

import org.assertj.core.api.Assertions.assertThat
import org.craftsrecords.talkadvisor.ValueObjectTest
import org.craftsrecords.talkadvisor.recommendation.preferences.Topic
import org.craftsrecords.talkadvisor.recommendation.talk.TalkFormat.CONFERENCE
import org.craftsrecords.talkadvisor.recommendation.talk.TalkFormat.QUICKIE
import org.junit.jupiter.api.Test

internal class GuestCriteriaTest : ValueObjectTest<GuestCriteria> {

    override fun createValue() = GuestCriteria(setOf(Topic("ddd")), setOf(CONFERENCE))
    override fun createOtherValue() = GuestCriteria(setOf(Topic("ddd")), setOf(QUICKIE))

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
}