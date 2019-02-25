package org.craftsrecords.talkadvisor.recommendation.spi.stubs

import org.craftsrecords.hexarch.Stub
import org.craftsrecords.talkadvisor.recommendation.profile.Profile
import org.craftsrecords.talkadvisor.recommendation.spi.Profiles

@Stub
class InMemoryProfiles(private val profiles: MutableMap<String, Profile> = HashMap()) : Profiles {
    override fun fetch(userId: String): Profile? {
        return profiles[userId]
    }

    override fun save(profile: Profile): Profile {
        profiles[profile.id] = profile
        return profile
    }
}