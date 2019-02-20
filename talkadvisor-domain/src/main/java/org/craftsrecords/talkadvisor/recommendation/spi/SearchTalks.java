package org.craftsrecords.talkadvisor.recommendation.spi;

import org.craftsrecords.talkadvisor.recommendation.preferences.Topic;
import org.craftsrecords.talkadvisor.recommendation.talk.Talk;

import javax.annotation.Nonnull;
import java.util.Set;

@FunctionalInterface
public interface SearchTalks {
    @Nonnull
    Set<Talk> forTopic(@Nonnull Topic topic);
}
