package org.craftsrecords.talkadvisor.recommendation.preferences

import org.assertj.core.api.Assertions.assertThat
import org.craftsrecords.talkadvisor.ValueObjectTest
import org.craftsrecords.talkadvisor.recommendation.talk.TalkFormat.CONFERENCE
import org.craftsrecords.talkadvisor.recommendation.talk.TalkFormat.QUICKIE
import org.junit.jupiter.api.Test

class PreferencesTest : ValueObjectTest<Preferences> {

    override fun createValue() = Preferences(setOf(Topic("ddd")), setOf(CONFERENCE))
    override fun createOtherValue() = Preferences(setOf(Topic("ddd")), setOf(QUICKIE))

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

}

