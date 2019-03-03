package org.craftsrecords.talkadvisor.infra.resources.assertions

import org.craftsrecords.talkadvisor.infra.resources.*

val Preferences.it: PreferencesAssert
    get() = PreferencesAssert(this)

val Iterable<Talk>.those: TalksAssert
    get() = TalksAssert(this)

val Talk.it: TalkAssert
    get() = TalkAssert(this)

val Iterable<Topic>.those: TopicsAssert
    get() = TopicsAssert(this)

val Topic.it: TopicAssert
    get() = TopicAssert(this)

val Profile.it: ProfileAssert
    get() = ProfileAssert(this)

val Recommendation.it: RecommendationAssert
    get() = RecommendationAssert(this)