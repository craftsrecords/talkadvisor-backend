package org.craftsrecords.talkadvisor.infra.resources

import org.springframework.hateoas.Identifiable
import java.util.*
import org.craftsrecords.talkadvisor.recommendation.Recommendation as DomainRecommendation
import org.craftsrecords.talkadvisor.recommendation.talk.Talk as DomainTalk

class Recommendation(private val id: UUID, val talks: List<Talk>) : Identifiable<UUID> {
    override fun getId() = id
}

fun DomainRecommendation.toResource() = Recommendation(id = id, talks = talks.map(DomainTalk::toResource).toList())