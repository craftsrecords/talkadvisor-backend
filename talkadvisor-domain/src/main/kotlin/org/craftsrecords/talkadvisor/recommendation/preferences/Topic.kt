package org.craftsrecords.talkadvisor.recommendation.preferences

import org.apache.commons.lang3.Validate.notBlank

class Topic(name: String) {
    val name: String = notBlank(name, "Cannot create a topic with a blank name")

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Topic

        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }
}
