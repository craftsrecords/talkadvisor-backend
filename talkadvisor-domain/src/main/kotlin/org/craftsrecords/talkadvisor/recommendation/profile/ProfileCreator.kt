package org.craftsrecords.talkadvisor.recommendation.profile

import org.craftsrecords.talkadvisor.recommendation.api.CreateProfile
import org.craftsrecords.talkadvisor.recommendation.preferences.Preferences
import org.craftsrecords.talkadvisor.recommendation.spi.Profiles

class ProfileCreator(val profiles: Profiles) : CreateProfile {
    override fun forUserWithPreferences(userId: String, preferences: Preferences): Profile {
        val profile = Profile(userId, preferences)
        profiles.save(profile)
        return profile
    }
}