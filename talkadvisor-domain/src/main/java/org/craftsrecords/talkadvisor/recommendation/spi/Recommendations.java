package org.craftsrecords.talkadvisor.recommendation.spi;

import org.craftsrecords.talkadvisor.recommendation.Recommendation;

import javax.annotation.Nonnull;

public interface Recommendations {
    void save(@Nonnull Recommendation recommendation);
}
