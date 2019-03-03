package org.craftsrecords.talkadvisor.infra.resources

import org.springframework.hateoas.Identifiable
import org.craftsrecords.talkadvisor.recommendation.profile.Profile as DomainProfile

data class Profile(private val id: String, val preferences: Preferences) : Identifiable<String> {
    override fun getId() = id
}

fun DomainProfile.toResource(): Profile = Profile(this.id, this.preferences.toResource())
