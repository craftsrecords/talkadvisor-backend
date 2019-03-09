package org.craftsrecords.talkadvisor.recommendation.assertions

import org.assertj.core.api.AbstractIterableAssert
import org.craftsrecords.talkadvisor.recommendation.assertions.generated.AbstractTalkAssert
import org.craftsrecords.talkadvisor.recommendation.preferences.Topic
import org.craftsrecords.talkadvisor.recommendation.talk.Talk
import org.craftsrecords.talkadvisor.recommendation.talk.TalkFormat
import java.time.Duration

class TalksAssert(actual: Iterable<Talk>) : AbstractIterableAssert<TalksAssert, Iterable<Talk>, Talk, TalkAssert>(actual, TalksAssert::class.java) {
    override fun newAbstractIterableAssert(actual: Iterable<Talk>): TalksAssert {
        return TalksAssert(actual)
    }

    override fun toAssert(talk: Talk, description: String): TalkAssert {
        return TalkAssert(talk).`as`(description)
    }

    infix fun `are related to topic`(topicName: String) {
        allSatisfy { TalkAssert(it) `is related to topic` topicName }
    }

    infix fun `are related to topics`(topics: Set<Topic>) {
        allSatisfy { TalkAssert(it) `is related to a topic in` topics }
    }

    infix fun `are in the format`(talkFormat: TalkFormat) {
        allSatisfy {
            TalkAssert(it) `is in the format` talkFormat
        }
    }

    infix fun `have their format in`(talksFormats: Set<TalkFormat>) {
        allSatisfy {
            TalkAssert(it) `has its format in` talksFormats
        }
    }

    infix fun `have their duration in `(range: ClosedRange<Duration>) {
        allSatisfy {
            TalkAssert(it) `has its duration in` range
        }
    }

    infix fun `contains exactly in any order elements of`(talks: Iterable<Talk>) {
        containsExactlyInAnyOrderElementsOf(talks)
    }
}

class TalkAssert(actual: Talk) : AbstractTalkAssert<TalkAssert, Talk>(
        actual,
        TalkAssert::class.java
) {

    infix fun `is related to topic`(topicName: String) {
        matches({ it.title.contains(topicName) }, "is related to topic $topicName")
    }

    infix fun `is in the format`(talkFormat: TalkFormat) {
        matches({ it.format == talkFormat }, "correspond explicitly to the format $talkFormat")
    }

    infix fun `has its format in`(talksFormats: Set<TalkFormat>) {
        matches({ talksFormats.any { it == actual.format } }, "has its format in the expected ones")

    }

    infix fun `has its duration in`(range: ClosedRange<Duration>) {
        matches({ it.duration.coerceIn(range) == it.duration }, "duration is in the expected range")
    }

    infix fun `is related to a topic in`(topics: Set<Topic>) {
        matches({ topics.any { actual.title.contains(it.name) } }, "is related to a topic in")
    }
}