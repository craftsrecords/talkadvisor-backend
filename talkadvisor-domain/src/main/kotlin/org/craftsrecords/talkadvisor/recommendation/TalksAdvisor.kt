package org.craftsrecords.talkadvisor.recommendation

import org.craftsrecords.hexarch.DomainService
import org.craftsrecords.talkadvisor.recommendation.api.RecommendTalks
import org.craftsrecords.talkadvisor.recommendation.criteria.Criteria
import org.craftsrecords.talkadvisor.recommendation.criteria.GuestCriteria
import org.craftsrecords.talkadvisor.recommendation.profile.ProfileNotFoundException
import org.craftsrecords.talkadvisor.recommendation.spi.Profiles
import org.craftsrecords.talkadvisor.recommendation.spi.Recommendations
import org.craftsrecords.talkadvisor.recommendation.spi.SearchTalks
import org.craftsrecords.talkadvisor.recommendation.talk.Talk

@DomainService
class TalksAdvisor(private val searchTalks: SearchTalks,
                   private val recommendations: Recommendations,
                   private val profiles: Profiles) : RecommendTalks {

    override fun to(userId: String): Recommendation {
        val profile = profiles.fetch(userId) ?: throw ProfileNotFoundException(userId)
        return recommendTalksSatisfying(profile.preferences)
    }

    override fun satisfying(guestCriteria: GuestCriteria): Recommendation {
        return recommendTalksSatisfying(guestCriteria as Criteria)
    }

    private fun recommendTalksSatisfying(criteria: Criteria): Recommendation {
        val talks = retrieveTalksSatisfying(criteria)
        return recommendations.save(Recommendation(criteria = criteria, talks = talks))
    }

    private fun retrieveTalksSatisfying(criteria: Criteria): Set<Talk> {
        return searchTalks.forTopics(criteria.topics)
                .filter { criteria.hasTalkFormat(it.format) }
                .toSet()
    }
}