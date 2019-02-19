package org.craftsrecords.talkadvisor.recommendation.criteria

import org.craftsrecords.talkadvisor.recommendation.talk.TalkFormat

class Criteria(val topic: Topic, talksFormats: Set<TalkFormat>) {
    val talksFormats: Set<TalkFormat> = talksFormats.toHashSet()
}