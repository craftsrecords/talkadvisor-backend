package org.craftsrecords.talkadvisor.recommendation.profile

import org.apache.commons.lang3.Validate.notBlank
import org.craftsrecords.talkadvisor.recommendation.preferences.Preferences


class Profile(id: String, val preferences: Preferences) {
    val id: String = notBlank(id, "Cannot create a Profile is a blank id")

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Profile

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}