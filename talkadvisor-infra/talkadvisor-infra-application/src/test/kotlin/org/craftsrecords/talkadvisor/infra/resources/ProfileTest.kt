package org.craftsrecords.talkadvisor.infra.resources

import org.craftsrecords.talkadvisor.infra.resources.assertions.that
import org.craftsrecords.talkadvisor.recommendation.InjectDomainObjects
import org.junit.jupiter.api.Test
import org.craftsrecords.talkadvisor.recommendation.profile.Profile as DomainProfile

@InjectDomainObjects
class ProfileTest {

    @Test
    fun `should convert domain profile to resource`(domainProfile : DomainProfile) {

        val profile = domainProfile.toResource()

        profile.that `is the resource of` domainProfile
    }
}