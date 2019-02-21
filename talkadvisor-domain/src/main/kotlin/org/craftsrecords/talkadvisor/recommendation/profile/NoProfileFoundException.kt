package org.craftsrecords.talkadvisor.recommendation.profile

class NoProfileFoundException(val userId: String) : RuntimeException("No profile found to the user $userId")