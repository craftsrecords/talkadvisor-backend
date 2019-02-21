package org.craftsrecords.talkadvisor.recommendation.criteria

import org.craftsrecords.talkadvisor.recommendation.preferences.Topic
import org.craftsrecords.talkadvisor.recommendation.talk.TalkFormat

interface Criteria {
    val topics: Set<Topic>
    val talksFormats: Set<TalkFormat>

    fun hasTalkFormat(talkFormat: TalkFormat): Boolean {
        return talksFormats.contains(talkFormat)
    }
}