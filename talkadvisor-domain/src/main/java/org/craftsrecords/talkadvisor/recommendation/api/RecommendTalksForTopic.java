package org.craftsrecords.talkadvisor.recommendation.api;

import org.craftsrecords.talkadvisor.recommendation.Recommendation;
import org.craftsrecords.talkadvisor.recommendation.preferences.Preferences;

import javax.annotation.Nonnull;

@FunctionalInterface
public interface RecommendTalksForTopic {
    @Nonnull
    Recommendation getRecommendation(@Nonnull Preferences preferences);
}