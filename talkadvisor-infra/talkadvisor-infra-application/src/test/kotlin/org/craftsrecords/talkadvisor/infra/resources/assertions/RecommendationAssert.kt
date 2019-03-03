package org.craftsrecords.talkadvisor.infra.resources.assertions

import org.assertj.core.api.AbstractAssert
import org.craftsrecords.talkadvisor.infra.resources.Recommendation
import org.craftsrecords.talkadvisor.recommendation.Recommendation as DomainRecommendation

class RecommendationAssert(actual: Recommendation) : AbstractAssert<RecommendationAssert, Recommendation>(actual, RecommendationAssert::class.java) {
    infix fun `is the resource of`(domainRecommendation: DomainRecommendation) {
        matches({ it.id == domainRecommendation.id }, "Recommendation resource id corresponds to the one of the DomainRecommendation")
        satisfies { recommendation ->
            recommendation.talks.those `are the resources of` domainRecommendation.talks
        }
    }
}