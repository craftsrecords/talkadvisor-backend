package org.craftsrecords.talkadvisor.recommendation.api;

import org.craftsrecords.talkadvisor.recommendation.Recommendation;
import org.craftsrecords.talkadvisor.recommendation.criteria.Criteria;

@FunctionalInterface
public interface RecommendTalksForTopic {
    Recommendation getRecommendation(Criteria criteria);
}