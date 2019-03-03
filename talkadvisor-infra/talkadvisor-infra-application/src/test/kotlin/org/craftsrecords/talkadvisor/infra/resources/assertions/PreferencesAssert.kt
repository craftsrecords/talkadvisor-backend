package org.craftsrecords.talkadvisor.infra.resources.assertions

import org.assertj.core.api.AbstractAssert
import org.assertj.core.api.Assertions.assertThat
import org.craftsrecords.talkadvisor.infra.resources.Preferences
import org.craftsrecords.talkadvisor.recommendation.talk.TalkFormat
import org.craftsrecords.talkadvisor.recommendation.preferences.Preferences as DomainPreferences

class PreferencesAssert(actual: Preferences) : AbstractAssert<PreferencesAssert, Preferences>(
        actual,
        PreferencesAssert::class.java
) {
    infix fun `is the resource of`(domainPreferences: DomainPreferences) {
        satisfies { preferences ->
            preferences.topics.those `are the resources of` domainPreferences.topics
            preferences.talksFormats.those `are the resources of` domainPreferences.talksFormats
        }
    }

    private val Iterable<String>.those
        get() = object {
            infix fun `are the resources of`(talksFormats: Set<TalkFormat>) {
                forEachIndexed { index, format -> assertThat(format).isEqualTo(talksFormats.elementAt(index).name) }
            }
        }
}
