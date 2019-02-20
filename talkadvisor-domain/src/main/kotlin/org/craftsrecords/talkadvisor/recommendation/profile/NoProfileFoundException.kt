package org.craftsrecords.talkadvisor.recommendation.profile

class NoProfileFoundException(val userId: String) : RuntimeException("No profile found for the user $userId")