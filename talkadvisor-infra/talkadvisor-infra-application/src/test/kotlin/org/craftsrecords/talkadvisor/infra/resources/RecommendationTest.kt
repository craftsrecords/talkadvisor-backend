package org.craftsrecords.talkadvisor.infra.resources

import org.craftsrecords.talkadvisor.infra.resources.assertions.that
import org.craftsrecords.talkadvisor.recommendation.InjectDomainObjects
import org.junit.jupiter.api.Test
import org.craftsrecords.talkadvisor.recommendation.Recommendation as DomainRecommendation

@InjectDomainObjects
class RecommendationTest {

    @Test
    fun `should convert domain recommendation to resource`(domainRecommendation: DomainRecommendation) {

        val recommendation = domainRecommendation.toResource()

        recommendation.that `is the resource of` domainRecommendation
    }
}