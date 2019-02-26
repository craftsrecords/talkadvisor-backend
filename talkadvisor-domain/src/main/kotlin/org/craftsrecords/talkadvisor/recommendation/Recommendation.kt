package org.craftsrecords.talkadvisor.recommendation

import org.craftsrecords.hexarch.Aggregate
import org.craftsrecords.talkadvisor.recommendation.criteria.Criteria
import org.craftsrecords.talkadvisor.recommendation.talk.Talk
import java.util.*

@Aggregate
class Recommendation(val id: UUID = UUID.randomUUID(), talks: Set<Talk>, val criteria: Criteria) {

    val talks = talks.toSet()

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