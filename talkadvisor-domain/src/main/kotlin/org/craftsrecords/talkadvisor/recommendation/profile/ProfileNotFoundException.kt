package org.craftsrecords.talkadvisor.recommendation.profile

class ProfileNotFoundException(val userId: String) : RuntimeException("No profile found for the user $userId")