package org.craftsrecords.talkadvisor.recommendation.assertions

import org.assertj.core.api.AbstractAssert
import org.craftsrecords.talkadvisor.recommendation.preferences.Preferences
import org.craftsrecords.talkadvisor.recommendation.profile.Profile

class ProfileAssert(actual: Profile) : AbstractAssert<ProfileAssert, Profile>(
        actual,
        ProfileAssert::class.java
) {
    infix fun `corresponds to user`(userId: String) {
        matches({ actual.id == userId }, "profile id corresponds to userId")
    }

    infix fun `has preferences`(preferences: Preferences) {
        matches({ actual.preferences == preferences }, "matching expected preferences")
    }
}
