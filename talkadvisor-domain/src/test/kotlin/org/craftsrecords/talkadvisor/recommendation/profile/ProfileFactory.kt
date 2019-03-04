package org.craftsrecords.talkadvisor.recommendation.profile

import org.craftsrecords.talkadvisor.recommendation.criteria.createPreferences
import kotlin.random.Random

fun createProfile() = createProfileFor(Random.nextInt().toString())

fun createProfileFor(userId: String) = Profile(userId, createPreferences())