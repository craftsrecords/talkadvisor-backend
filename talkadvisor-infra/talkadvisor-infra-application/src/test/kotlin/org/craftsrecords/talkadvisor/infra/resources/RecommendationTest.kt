package org.craftsrecords.talkadvisor.infra.resources

import org.craftsrecords.talkadvisor.infra.resources.assertions.that
import org.craftsrecords.talkadvisor.recommendation.createRecommendation
import org.junit.jupiter.api.Test

internal class RecommendationTest {

    @Test
    fun `should convert domain recommendation to resource`() {
        val domainRecommendation = createRecommendation()

        val recommendation = domainRecommendation.toResource()

        recommendation.that `is the resource of` domainRecommendation
    }
}