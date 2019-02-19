package org.craftsrecords.talkadvisor.recommendation

import org.craftsrecords.talkadvisor.recommendation.api.RecommendTalksForTopic
import org.craftsrecords.talkadvisor.recommendation.criteria.Criteria
import org.craftsrecords.talkadvisor.recommendation.spi.Recommendations
import org.craftsrecords.talkadvisor.recommendation.spi.SearchTalks
import org.craftsrecords.talkadvisor.recommendation.talk.Talk

class TalksAdvisor(private val searchTalks: SearchTalks, private val recommendations: Recommendations) : RecommendTalksForTopic {

    override fun getRecommendation(criteria: Criteria): Recommendation {
        val talks = getTalksSatisfying(criteria)
        val recommendation = Recommendation(talks = talks)
        recommendations.save(recommendation)
        return recommendation
    }

    private fun getTalksSatisfying(criteria: Criteria): Set<Talk> {
        return searchTalks.forTopic(criteria.topic)
                .filter { criteria.talksFormats.contains(it.format) }
                .toSet()
    }
}