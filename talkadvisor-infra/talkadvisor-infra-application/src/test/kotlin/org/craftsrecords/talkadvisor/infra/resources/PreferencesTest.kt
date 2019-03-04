package org.craftsrecords.talkadvisor.infra.resources

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.craftsrecords.talkadvisor.infra.resources.assertions.that
import org.craftsrecords.talkadvisor.recommendation.talk.TalkFormat
import org.junit.jupiter.api.Test
import org.craftsrecords.talkadvisor.recommendation.preferences.Preferences as DomainPreferences
import org.craftsrecords.talkadvisor.recommendation.preferences.Topic as DomainTopic

internal class PreferencesTest {

    @Test
    fun `should convert to domain preferences`() {
        val topics = listOf(Topic("topic"))
        val talksFormats = listOf("CONFERENCE")
        val preferences = Preferences(topics, talksFormats)

        val domainPreferences: DomainPreferences = preferences.toDomainObject()

        preferences.that `is the resource of` domainPreferences
    }

    @Test
    fun `should throw IllegalArgumentException when trying to map an unknown TalkFormat`() {
        val topics = listOf(Topic("topic"))
        val talksFormats = listOf("UNKNOWN")
        val preferences = Preferences(topics, talksFormats)

        assertThatThrownBy { preferences.toDomainObject() }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage("No enum constant ${TalkFormat::class.java.name}.UNKNOWN")
    }

}