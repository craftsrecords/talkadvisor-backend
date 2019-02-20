package org.craftsrecords.talkadvisor.recommendation

import org.craftsrecords.talkadvisor.recommendation.api.RecommendTalksForTopic
import org.craftsrecords.talkadvisor.recommendation.preferences.Preferences
import org.craftsrecords.talkadvisor.recommendation.spi.Recommendations
import org.craftsrecords.talkadvisor.recommendation.spi.SearchTalks
import org.craftsrecords.talkadvisor.recommendation.talk.Talk

class TalksAdvisor(private val searchTalks: SearchTalks, private val recommendations: Recommendations) : RecommendTalksForTopic {

    override fun getRecommendation(preferences: Preferences): Recommendation {
        val talks = getTalksSatisfying(preferences)
        val recommendation = Recommendation(talks = talks)
        recommendations.save(recommendation)
        return recommendation
    }

    private fun getTalksSatisfying(preferences: Preferences): Set<Talk> {
        return searchTalks.forTopics(preferences.topics)
                .filter { preferences.talksFormats.contains(it.format) }
                .toSet()
    }
}