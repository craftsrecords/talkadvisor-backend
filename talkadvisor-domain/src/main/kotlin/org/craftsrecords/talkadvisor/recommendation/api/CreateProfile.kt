package org.craftsrecords.talkadvisor.recommendation.api

import org.craftsrecords.talkadvisor.recommendation.preferences.Preferences
import org.craftsrecords.talkadvisor.recommendation.profile.Profile

@FunctionalInterface
interface CreateProfile {
    fun forUserWithPreferences(userId: String, preferences: Preferences): Profile
}
