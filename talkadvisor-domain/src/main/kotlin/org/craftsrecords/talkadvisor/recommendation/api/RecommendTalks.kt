package org.craftsrecords.talkadvisor.recommendation.api

import org.craftsrecords.talkadvisor.recommendation.Recommendation
import org.craftsrecords.talkadvisor.recommendation.criteria.GuestCriteria

interface RecommendTalks {
    fun getRecommendationSatisfying(guestCriteria: GuestCriteria): Recommendation
    fun getRecommendationGivenProfile(userId: String): Recommendation
}