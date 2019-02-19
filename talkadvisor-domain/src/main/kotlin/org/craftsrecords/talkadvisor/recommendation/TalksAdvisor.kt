package org.craftsrecords.talkadvisor.recommendation

import org.craftsrecords.talkadvisor.recommendation.api.RecommendTalksForTopic
import org.craftsrecords.talkadvisor.recommendation.criteria.Criteria

class TalksAdvisor : RecommendTalksForTopic {

    override fun getRecommendation(criteria: Criteria): Recommendation {
        TODO("not implemented")
    }
}