package org.craftsrecords.talkadvisor.recommendation.preferences

import org.craftsrecords.talkadvisor.recommendation.criteria.Criteria
import org.craftsrecords.talkadvisor.recommendation.criteria.GuestCriteria
import org.craftsrecords.talkadvisor.recommendation.talk.TalkFormat

data class Preferences constructor(private val criteria: Criteria) : Criteria by criteria {
    constructor(topics: Set<Topic>, talksFormats: Set<TalkFormat>) : this(GuestCriteria(topics, talksFormats))
}