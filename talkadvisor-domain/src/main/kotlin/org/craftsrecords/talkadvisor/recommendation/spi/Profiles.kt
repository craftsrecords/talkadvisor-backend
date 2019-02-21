package org.craftsrecords.talkadvisor.recommendation.spi

import org.craftsrecords.talkadvisor.recommendation.profile.Profile

interface Profiles {
    fun save(profile: Profile)

    fun fetch(userId: String): Profile
}
