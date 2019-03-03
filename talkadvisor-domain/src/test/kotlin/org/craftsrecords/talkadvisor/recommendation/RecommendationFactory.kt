package org.craftsrecords.talkadvisor.recommendation

import org.craftsrecords.talkadvisor.recommendation.criteria.createCriteria
import org.craftsrecords.talkadvisor.recommendation.talk.createTalks

fun createRecommendation(): Recommendation {
    val criteria = createCriteria()
    return Recommendation(criteria = criteria, talks = createTalks(criteria))
}