package org.craftsrecords.talkadvisor.recommendation.preferences

import org.assertj.core.api.Assertions.assertThat
import org.craftsrecords.talkadvisor.recommendation.talk.TalkFormat.CONFERENCE
import org.craftsrecords.talkadvisor.recommendation.talk.TalkFormat.QUICKIE
import org.junit.jupiter.api.Test

internal class PreferencesTest {

    @Test
    fun `should store a copy of the talks formats`() {

        val talksFormats = mutableSetOf(CONFERENCE)
        val criteria = Preferences(Topic("ddd"), talksFormats)

        talksFormats.add(QUICKIE)

        assertThat(criteria.talksFormats).containsOnly(CONFERENCE)
    }
}

