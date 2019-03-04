package org.craftsrecords.talkadvisor.infra.resources.assertions

import org.craftsrecords.talkadvisor.infra.resources.*

val Preferences.that: PreferencesAssert
    get() = PreferencesAssert(this)

val Iterable<Talk>.those: TalksAssert
    get() = TalksAssert(this)

val Talk.that: TalkAssert
    get() = TalkAssert(this)

val Iterable<Topic>.those: TopicsAssert
    get() = TopicsAssert(this)

val Topic.that: TopicAssert
    get() = TopicAssert(this)

val Profile.that: ProfileAssert
    get() = ProfileAssert(this)

val Recommendation.that: RecommendationAssert
    get() = RecommendationAssert(this)