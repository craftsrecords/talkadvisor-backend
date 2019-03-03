package org.craftsrecords.talkadvisor.recommendation

import org.craftsrecords.hexarch.Aggregate
import org.craftsrecords.talkadvisor.recommendation.criteria.Criteria
import org.craftsrecords.talkadvisor.recommendation.talk.Talk
import java.util.*

@Aggregate
class Recommendation(val id: UUID = UUID.randomUUID(), val criteria: Criteria, talks: Set<Talk>) {

    val talks: Set<Talk>

    init {
        checkCriteriaTalkFormatsCorrespondToTheTalksOne(talks)
        this.talks = talks.toSet()
    }

    private fun checkCriteriaTalkFormatsCorrespondToTheTalksOne(talks: Set<Talk>) {
        if (talks.any { !criteria.hasTalkFormat(it.format) }) {
            throw IllegalArgumentException("Criteria talk formats doesn't correspond to the format of the recommended talks")
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Recommendation

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun toString(): String {
        return "Recommendation(id=$id, criteria=$criteria, talks=$talks)"
    }
}