package org.craftsrecords.talkadvisor.recommendation.spi

import org.craftsrecords.hexarch.Repository
import org.craftsrecords.talkadvisor.recommendation.Recommendation

@Repository
interface Recommendations {
    fun save(recommendation: Recommendation): Recommendation
}
