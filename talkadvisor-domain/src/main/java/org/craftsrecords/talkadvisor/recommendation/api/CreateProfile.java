package org.craftsrecords.talkadvisor.recommendation.api;

import org.craftsrecords.talkadvisor.recommendation.preferences.Preferences;
import org.craftsrecords.talkadvisor.recommendation.profile.Profile;

import javax.annotation.Nonnull;

@FunctionalInterface
public interface CreateProfile {
    @Nonnull
    Profile forUserWithPreferences(@Nonnull String userId, @Nonnull Preferences preferences);
}
