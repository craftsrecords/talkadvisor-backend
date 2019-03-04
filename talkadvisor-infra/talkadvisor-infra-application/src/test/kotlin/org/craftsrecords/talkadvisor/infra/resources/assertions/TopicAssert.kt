package org.craftsrecords.talkadvisor.infra.resources.assertions

import org.assertj.core.api.AbstractAssert
import org.assertj.core.api.AbstractIterableAssert
import org.craftsrecords.talkadvisor.infra.resources.Topic
import org.craftsrecords.talkadvisor.recommendation.preferences.Topic as DomainTopic

class TopicsAssert(actual: Iterable<Topic>) : AbstractIterableAssert<TopicsAssert, Iterable<Topic>, Topic, TopicAssert>(actual, TopicsAssert::class.java) {

    override fun toAssert(topic: Topic, description: String): TopicAssert {
        return TopicAssert(topic).`as`(description)
    }

    override fun newAbstractIterableAssert(iterable: Iterable<Topic>): TopicsAssert {
        return TopicsAssert(actual)
    }

    infix fun `are the resources of`(domainTopics: Set<DomainTopic>) {
        hasSameSizeAs(domainTopics)
        satisfies {
            it.forEachIndexed { index, topic -> topic.that `is the resource of` domainTopics.elementAt(index) }
        }
    }
}

class TopicAssert(actual: Topic) : AbstractAssert<TopicAssert, Topic>(actual, TopicAssert::class.java) {
    infix fun `is the resource of`(domainTopic: DomainTopic) {
        matches({ it.name == domainTopic.name }, "Resource Topic correponds to the given Domain Topic")
    }
}
