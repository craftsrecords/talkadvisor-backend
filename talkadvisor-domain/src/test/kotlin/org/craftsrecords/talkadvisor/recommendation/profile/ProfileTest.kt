package org.craftsrecords.talkadvisor.recommendation.profile

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.craftsrecords.talkadvisor.EntityTest
import org.craftsrecords.talkadvisor.recommendation.InjectDomainObjects
import org.craftsrecords.talkadvisor.recommendation.preferences.Preferences
import org.junit.jupiter.api.Test

@InjectDomainObjects
class ProfileTest(private val preferences: Preferences) : EntityTest<Profile> {


    override fun createEqualEntities() = Pair(
            Profile("id", preferences),
            Profile("id", preferences)
    )

    override fun createNonEqualEntities() = Pair(
            Profile("id", preferences),
            Profile("id2", preferences)
    )

    @Test
    fun `should not create a profile with a blank id`() {
        assertThatThrownBy { Profile("", preferences) }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage("Cannot create a Profile is a blank id")
    }
}