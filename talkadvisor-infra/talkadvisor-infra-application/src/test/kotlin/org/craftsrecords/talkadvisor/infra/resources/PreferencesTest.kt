package org.craftsrecords.talkadvisor.infra.resources

import org.assertj.core.api.Assertions.assertThat
import org.craftsrecords.talkadvisor.infra.resources.Preferences
import org.craftsrecords.talkadvisor.recommendation.criteria.createPreferences
import org.craftsrecords.talkadvisor.recommendation.preferences.Topic
import org.craftsrecords.talkadvisor.recommendation.talk.TalkFormat.CONFERENCE
import org.junit.jupiter.api.Test
import org.craftsrecords.talkadvisor.recommendation.preferences.Preferences as DomainPreferences

internal class PreferencesTest {

    @Test
    fun `should convert to domain preferences`() {
        val topics = setOf(Topic("topic"))
        val talksFormats = setOf(CONFERENCE)
        val preferences = Preferences(topics, talksFormats)

        val domainPreferences: DomainPreferences = preferences.toDomainObject()

        assertThat(domainPreferences.topics).isEqualTo(topics)
        assertThat(domainPreferences.talksFormats).isEqualTo(talksFormats)
    }

    @Test
    fun `should convert domain preferences to resource`() {
        val domainPreferences = createPreferences()

        val preferences = domainPreferences.toResource()

        assertThat(preferences.topics).isEqualTo(domainPreferences.topics)
        assertThat(preferences.talksFormats).isEqualTo(domainPreferences.talksFormats)
    }
}