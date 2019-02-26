package org.craftsrecords.talkadvisor.recommendation.profile

import org.craftsrecords.talkadvisor.nextString
import org.craftsrecords.talkadvisor.recommendation.criteria.createPreferences
import kotlin.random.Random

fun createProfile() = Profile(Random.nextString(), createPreferences())