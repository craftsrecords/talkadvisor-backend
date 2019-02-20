package org.craftsrecords.talkadvisor.recommendation

import org.craftsrecords.talkadvisor.recommendation.api.RecommendTalks
import org.craftsrecords.talkadvisor.recommendation.criteria.Criteria
import org.craftsrecords.talkadvisor.recommendation.criteria.GuestCriteria
import org.craftsrecords.talkadvisor.recommendation.spi.Profiles
import org.craftsrecords.talkadvisor.recommendation.spi.Recommendations
import org.craftsrecords.talkadvisor.recommendation.spi.SearchTalks
import org.craftsrecords.talkadvisor.recommendation.talk.Talk

class TalksAdvisor(private val searchTalks: SearchTalks,
                   private val recommendations: Recommendations,
                   private val profiles: Profiles) : RecommendTalks {

    override fun getRecommendationGivenProfile(userId: String): Recommendation {
        val profile = profiles.fetch(userId)
        return this.getRecommendationSatisfying(profile.preferences)
    }

    override fun getRecommendationSatisfying(guestCriteria: GuestCriteria): Recommendation {
        return this.getRecommendationSatisfying(guestCriteria as Criteria)
    }

    private fun getRecommendationSatisfying(criteria: Criteria): Recommendation {
        val talks = getTalksSatisfying(criteria)
        val recommendation = Recommendation(criteria = criteria, talks = talks)
        recommendations.save(recommendation)
        return recommendation
    }

    private fun getTalksSatisfying(criteria: Criteria): Set<Talk> {
        return searchTalks.forTopics(criteria.topics)
                .filter { criteria.talksFormats.contains(it.format) }
                .toSet()
    }
}