package org.craftsrecords.talkadvisor.recommendation.api;

import org.craftsrecords.talkadvisor.recommendation.Recommendation;
import org.craftsrecords.talkadvisor.recommendation.Topic;

@FunctionalInterface
public interface RecommendTalksForTopic{
    Recommendation getRecommendation(Topic topic);
}

