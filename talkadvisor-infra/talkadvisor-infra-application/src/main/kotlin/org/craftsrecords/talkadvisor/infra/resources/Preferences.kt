package org.craftsrecords.talkadvisor.infra.resources

import org.craftsrecords.talkadvisor.recommendation.preferences.Preferences as DomainPreferences
import org.craftsrecords.talkadvisor.recommendation.preferences.Topic as DomainTopic
import org.craftsrecords.talkadvisor.recommendation.talk.TalkFormat as DomainTalkFormat

data class Preferences(val topics: List<Topic>, val talksFormats: List<String>) {
    fun toDomainObject(): DomainPreferences {
        return DomainPreferences(topics.toDomainObjects(), talksFormats.toDomainTalkFormats())
    }
}

fun DomainPreferences.toResource() = Preferences(this.topics.toResources(), this.talksFormats.toResources())

private fun Iterable<String>.toDomainTalkFormats(): Set<DomainTalkFormat> {
    return this.map(DomainTalkFormat::valueOf).toSet()
}

private fun Iterable<DomainTalkFormat>.toResources(): List<String> {
    return this.map(DomainTalkFormat::name).toList()
}