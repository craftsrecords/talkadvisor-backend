package org.craftsrecords.talkadvisor.recommendation.spi.stubs

import org.craftsrecords.talkadvisor.recommendation.Recommendation
import org.craftsrecords.talkadvisor.recommendation.spi.Recommendations
import java.util.*

class InMemoryRecommendations(private val recommendations: MutableMap<UUID, Recommendation> = HashMap()) : Recommendations {
    override fun save(recommendation: Recommendation) {
        recommendations[recommendation.id] = recommendation
    }

}