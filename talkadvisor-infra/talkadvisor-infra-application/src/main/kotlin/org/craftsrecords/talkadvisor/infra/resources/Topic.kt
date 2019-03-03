package org.craftsrecords.talkadvisor.infra.resources

import org.craftsrecords.talkadvisor.recommendation.preferences.Topic as DomainTopic

class Topic(val name: String) {
    fun toDomainObject(): DomainTopic {
        return DomainTopic(this.name)
    }
}

fun DomainTopic.toResource() = Topic(this.name)

fun Iterable<Topic>.toDomainObjects(): Set<DomainTopic> {
    return this.map(Topic::toDomainObject).toSet()
}

fun Iterable<DomainTopic>.toResources(): List<Topic> {
    return this.map(DomainTopic::toResource).toList()
}
