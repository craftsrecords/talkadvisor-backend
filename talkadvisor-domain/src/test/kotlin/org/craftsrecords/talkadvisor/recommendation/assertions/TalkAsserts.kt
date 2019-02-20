package org.craftsrecords.talkadvisor.recommendation.assertions

import org.assertj.core.api.AbstractAssert
import org.assertj.core.api.AbstractIterableAssert
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

    @JvmName("areRelatedToTopic")
    infix fun `are related to topic`(topicName: String) {
        allSatisfy { TalkAssert(it) `is related to topic` topicName }
    }

    @JvmName("areRelatedToTopic")

    infix fun `are related to topics`(topics: Set<Topic>) {
        allSatisfy { TalkAssert(it) `is related to a topic in` topics }
    }

    @JvmName("areInTheFormat")
    infix fun `are in the format`(talkFormat: TalkFormat) {
        allSatisfy {
            TalkAssert(it) `is in the format` talkFormat
        }
    }

    @JvmName("haveTheirFormatIn")
    infix fun `have their format in`(talksFormats: Set<TalkFormat>) {
        allSatisfy {
            TalkAssert(it) `has its format in` talksFormats
        }
    }

    @JvmName("haveTheirDurationIn")
    infix fun `have their duration in `(range: ClosedRange<Duration>) {
        allSatisfy {
            TalkAssert(it) `has its duration in` range
        }
    }
}

class TalkAssert(actual: Talk) : AbstractAssert<TalkAssert, Talk>(
        actual,
        TalkAssert::class.java
) {

    @JvmName("isRelatedToTopic")
    infix fun `is related to topic`(topicName: String) {
        matches({ it.title.contains(topicName) }, "is related to topic $topicName")
    }

    @JvmName("isInTheFormat")
    infix fun `is in the format`(talkFormat: TalkFormat) {
        matches({ it.format == talkFormat }, "correspond explicitly to the format $talkFormat")
    }

    @JvmName("hasItsFormatIn")
    infix fun `has its format in`(talksFormats: Set<TalkFormat>) {
        matches({ talksFormats.any { it == actual.format } }, "has its format in the expected ones")

    }

    @JvmName("hasItsDurationIn")
    infix fun `has its duration in`(range: ClosedRange<Duration>) {
        matches({ it.duration.coerceIn(range) == it.duration }, "duration is in the expected range")
    }

    @JvmName("isRelatedToATopicIn")
    infix fun `is related to a topic in`(topics: Set<Topic>) {
        matches({ topics.any { actual.title.contains(it.name) } }, "is related to a topic in")
    }
}