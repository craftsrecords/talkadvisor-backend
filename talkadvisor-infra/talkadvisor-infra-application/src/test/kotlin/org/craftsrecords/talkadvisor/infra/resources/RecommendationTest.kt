package org.craftsrecords.talkadvisor.infra.resources

import org.craftsrecords.talkadvisor.infra.resources.assertions.it
import org.craftsrecords.talkadvisor.recommendation.createRecommendation
import org.junit.jupiter.api.Test

internal class RecommendationTest {

    @Test
    fun `should convert domain recommendation to resource`() {
        val domainRecommendation = createRecommendation()

        val recommendation = domainRecommendation.toResource()

        recommendation.it `is the resource of` domainRecommendation
    }
}