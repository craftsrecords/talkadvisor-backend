package org.craftsrecords.talkadvisor.recommendation.api

import org.craftsrecords.talkadvisor.recommendation.Recommendation
import org.craftsrecords.talkadvisor.recommendation.criteria.GuestCriteria

interface RecommendTalks {
    infix fun satisfying(guestCriteria: GuestCriteria): Recommendation
    infix fun to(userId: String): Recommendation
}