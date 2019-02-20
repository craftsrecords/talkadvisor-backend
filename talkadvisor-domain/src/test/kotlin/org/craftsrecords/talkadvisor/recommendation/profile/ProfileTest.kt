package org.craftsrecords.talkadvisor.recommendation.profile

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

internal class ProfileTest {

    @Test
    fun `should not create a profile with a blank id`() {
        assertThatThrownBy { Profile("", createPreferences()) }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage("Cannot create a Profile is a blank id")
    }

    @Test
    fun `should satisfy entity equality`() {
        val profile1 = Profile("id", createPreferences())
        val profile2 = Profile("id", createPreferences())
        assertThat(profile1).isEqualTo(profile2)
        assertThat(profile1.hashCode()).isEqualTo(profile2.hashCode())
    }

    @Test
    fun `should satisfy entity inequality`() {
        val profile1 = Profile("id", createPreferences())
        val profile2 = Profile("id2", createPreferences())

        assertThat(profile1).isNotEqualTo(profile2)
        assertThat(profile1.hashCode()).isNotEqualTo(profile2.hashCode())
    }
}