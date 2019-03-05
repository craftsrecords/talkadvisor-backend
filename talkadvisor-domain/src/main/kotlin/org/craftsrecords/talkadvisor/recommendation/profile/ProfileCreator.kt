package org.craftsrecords.talkadvisor.recommendation.profile

import org.craftsrecords.hexarch.DomainService
import org.craftsrecords.talkadvisor.recommendation.api.CreateProfile
import org.craftsrecords.talkadvisor.recommendation.preferences.Preferences
import org.craftsrecords.talkadvisor.recommendation.spi.Profiles

@DomainService
class ProfileCreator(private val profiles: Profiles) : CreateProfile {
    override fun forUserWithPreferences(userId: String, preferences: Preferences): Profile {
        profiles.fetch(userId)?.let { throw ProfileAlreadyExistsException(userId) }
        return profiles.save(Profile(userId, preferences))
    }
}