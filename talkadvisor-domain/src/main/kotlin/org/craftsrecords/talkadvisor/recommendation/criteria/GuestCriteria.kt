package org.craftsrecords.talkadvisor.recommendation.criteria

import org.craftsrecords.talkadvisor.recommendation.preferences.Topic
import org.craftsrecords.talkadvisor.recommendation.talk.TalkFormat

class GuestCriteria(topics: Set<Topic>, talksFormats: Set<TalkFormat>) : Criteria {
    override val talksFormats: Set<TalkFormat> = talksFormats.toSet()
    override val topics: Set<Topic> = topics.toSet()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Criteria

        if (talksFormats != other.talksFormats) return false
        if (topics != other.topics) return false

        return true
    }

    override fun hashCode(): Int {
        var result = talksFormats.hashCode()
        result = 31 * result + topics.hashCode()
        return result
    }

}