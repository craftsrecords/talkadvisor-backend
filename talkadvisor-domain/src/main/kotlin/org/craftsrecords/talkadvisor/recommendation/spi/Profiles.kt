package org.craftsrecords.talkadvisor.recommendation.spi

import org.craftsrecords.hexarch.Repository
import org.craftsrecords.talkadvisor.recommendation.profile.Profile

@Repository
interface Profiles {
    fun save(profile: Profile): Profile

    fun fetch(userId: String): Profile?
}
