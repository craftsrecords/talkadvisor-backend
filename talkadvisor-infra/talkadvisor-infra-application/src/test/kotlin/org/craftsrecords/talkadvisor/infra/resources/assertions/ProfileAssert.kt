package org.craftsrecords.talkadvisor.infra.resources.assertions

import org.assertj.core.api.AbstractAssert
import org.craftsrecords.talkadvisor.infra.resources.Profile
import org.craftsrecords.talkadvisor.recommendation.profile.Profile as DomainProfile

class ProfileAssert(actual: Profile) : AbstractAssert<ProfileAssert, Profile>(actual, ProfileAssert::class.java) {
    infix fun `is the resource of`(domainProfile: DomainProfile) {
        matches({ it.id == domainProfile.id }, "Profile resource id corresponds to the one of the DomainProfile")
        satisfies { profile -> profile.preferences.that `is the resource of` domainProfile.preferences }
    }
}