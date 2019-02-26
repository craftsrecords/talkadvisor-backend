package org.craftsrecords.talkadvisor.infra.resources

import org.craftsrecords.talkadvisor.recommendation.preferences.Topic
import org.craftsrecords.talkadvisor.recommendation.talk.TalkFormat
import org.craftsrecords.talkadvisor.recommendation.preferences.Preferences as DomainPreferences

data class Preferences(val topics: Set<Topic>, val talksFormats: Set<TalkFormat>) {
    fun toDomainObject(): DomainPreferences {
        return DomainPreferences(topics, talksFormats)
    }
}

fun DomainPreferences.toResource() = Preferences(this.topics, this.talksFormats)