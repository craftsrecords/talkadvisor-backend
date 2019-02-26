package org.craftsrecords.talkadvisor.recommendation.profile

class ProfileAlreadyExistsException(userId: String) : RuntimeException("A profile already exists for the user $userId")