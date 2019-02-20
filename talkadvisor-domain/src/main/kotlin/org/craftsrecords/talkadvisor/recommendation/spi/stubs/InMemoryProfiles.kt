package org.craftsrecords.talkadvisor.recommendation.spi.stubs

import org.craftsrecords.talkadvisor.recommendation.profile.NoProfileFoundException
import org.craftsrecords.talkadvisor.recommendation.profile.Profile
import org.craftsrecords.talkadvisor.recommendation.spi.Profiles

class InMemoryProfiles(private val profiles: MutableMap<String, Profile> = HashMap()) : Profiles {
    override fun fetch(userId: String): Profile {
        return profiles[userId] ?: throw NoProfileFoundException(userId)
    }

    override fun save(profile: Profile) {
        profiles[profile.id] = profile
    }
}