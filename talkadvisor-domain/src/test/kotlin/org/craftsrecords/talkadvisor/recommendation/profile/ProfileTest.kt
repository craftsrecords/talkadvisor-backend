package org.craftsrecords.talkadvisor.recommendation.profile

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.craftsrecords.talkadvisor.EntityTest
import org.craftsrecords.talkadvisor.recommendation.criteria.createPreferences
import org.junit.jupiter.api.Test

internal class ProfileTest : EntityTest<Profile> {

    override fun createEqualEntities() = Pair(
            Profile("id", createPreferences()),
            Profile("id", createPreferences())
    )

    override fun createNonEqualEntities() = Pair(
            Profile("id", createPreferences()),
            Profile("id2", createPreferences())
    )

    @Test
    fun `should not create a profile with a blank id`() {
        assertThatThrownBy { Profile("", createPreferences()) }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage("Cannot create a Profile is a blank id")
    }
}