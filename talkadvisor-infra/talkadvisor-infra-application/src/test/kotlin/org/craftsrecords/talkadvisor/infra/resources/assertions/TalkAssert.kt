package org.craftsrecords.talkadvisor.infra.resources.assertions

import org.assertj.core.api.AbstractAssert
import org.assertj.core.api.AbstractIterableAssert
import org.craftsrecords.talkadvisor.infra.resources.Talk
import org.craftsrecords.talkadvisor.recommendation.talk.Talk as DomainTalk

class TalksAssert(actual: Iterable<Talk>) : AbstractIterableAssert<TalksAssert, Iterable<Talk>, Talk, TalkAssert>(actual, TalksAssert::class.java) {

    override fun toAssert(topic: Talk, description: String): TalkAssert {
        return TalkAssert(topic).`as`(description)
    }

    override fun newAbstractIterableAssert(iterable: Iterable<Talk>): TalksAssert {
        return TalksAssert(actual)
    }

    infix fun `are the resources of`(domainTalks: Set<DomainTalk>) {
        hasSameSizeAs(domainTalks)
        satisfies {
            it.forEachIndexed { index, topic -> topic.that `is the resource of` domainTalks.elementAt(index) }
        }
    }
}

class TalkAssert(actual: Talk) : AbstractAssert<TalkAssert, Talk>(actual, TalkAssert::class.java) {
    infix fun `is the resource of`(domainTalk: DomainTalk) {
        matches({ it.id == domainTalk.id }, "Resource Talk id corresponds to the Domain Talk one")
        matches({ it.title == domainTalk.title }, "Resource Talk title corresponds to the Domain Talk one")
        matches({ it.duration == domainTalk.duration }, "Resource Talk duration corresponds to the Domain Talk one")
        matches({ it.format == domainTalk.format.name }, "Resource Talk format corresponds to the Domain Talk one")
    }
}
