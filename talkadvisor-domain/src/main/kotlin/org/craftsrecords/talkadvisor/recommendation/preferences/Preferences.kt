package org.craftsrecords.talkadvisor.recommendation.preferences

import org.craftsrecords.talkadvisor.recommendation.talk.TalkFormat

class Preferences(val topic: Topic, talksFormats: Set<TalkFormat>) {
    val talksFormats: Set<TalkFormat> = talksFormats.toSet()
}