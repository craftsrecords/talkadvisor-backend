package org.craftsrecords.talkadvisor.infra.resources

import org.assertj.core.api.Assertions.assertThat
import org.craftsrecords.talkadvisor.recommendation.profile.createProfile
import org.junit.jupiter.api.Test
import org.craftsrecords.talkadvisor.recommendation.profile.Profile as DomainProfile

internal class ProfileTest {

    @Test
    fun `should convert domain profile to resource`() {
        val domainProfile = createProfile()

        val profile = domainProfile.toResource()

        assertThat(profile.id).isEqualTo(domainProfile.id)
        assertThat(profile.preferences).isEqualTo(domainProfile.preferences.toResource())
    }
}