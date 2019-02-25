package org.craftsrecords.talkadvisor.recommendation.spi.stubs

import org.craftsrecords.hexarch.Stub
import org.craftsrecords.talkadvisor.recommendation.Recommendation
import org.craftsrecords.talkadvisor.recommendation.spi.Recommendations
import java.util.*

@Stub
class InMemoryRecommendations(private val recommendations: MutableMap<UUID, Recommendation> = HashMap()) : Recommendations {
    override fun save(recommendation: Recommendation): Recommendation {
        recommendations[recommendation.id] = recommendation
        return recommendation
    }

}