package org.craftsrecords.talkadvisor.recommendation.preferences

import org.assertj.core.api.Assertions.assertThat
import org.craftsrecords.talkadvisor.recommendation.talk.TalkFormat.CONFERENCE
import org.craftsrecords.talkadvisor.recommendation.talk.TalkFormat.QUICKIE
import org.junit.jupiter.api.Test

internal class PreferencesTest {

    @Test
    fun `should store a copy of the talks formats`() {

        val talksFormats = mutableSetOf(CONFERENCE)
        val preferences = Preferences(setOf(Topic("ddd")), talksFormats)

        talksFormats.add(QUICKIE)

        assertThat(preferences.talksFormats).containsOnly(CONFERENCE)
    }

    @Test
    fun `should store a copy of the topics`() {

        val topics = mutableSetOf(Topic("ddd"))
        val preferences = Preferences(topics, setOf(CONFERENCE))
        val newTopic = Topic("new")
        topics.add(newTopic)

        assertThat(preferences.topics).doesNotContain(newTopic)
    }

    @Test
    fun `should satisfy value object equality`() {
        val preferences = Preferences(setOf(Topic("ddd")), setOf(CONFERENCE))
        val preferences2 = Preferences(setOf(Topic("ddd")), setOf(CONFERENCE))

        assertThat(preferences).isEqualTo(preferences2)
        assertThat(preferences.hashCode()).isEqualTo(preferences2.hashCode())
    }

    @Test
    fun `should satisfy value object inequality`() {
        val preferences = Preferences(setOf(Topic("ddd")), setOf(CONFERENCE))
        val preferences2 = Preferences(setOf(Topic("ddd")), setOf(QUICKIE))

        assertThat(preferences).isNotEqualTo(preferences2)
        assertThat(preferences.hashCode()).isNotEqualTo(preferences2.hashCode())
    }
}

