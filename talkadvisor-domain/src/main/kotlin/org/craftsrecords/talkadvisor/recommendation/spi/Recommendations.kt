package org.craftsrecords.talkadvisor.recommendation.spi

import org.craftsrecords.talkadvisor.recommendation.Recommendation

interface Recommendations {
    fun save(recommendation: Recommendation)
}
