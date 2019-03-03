package org.craftsrecords.talkadvisor.recommendation.assertions

import org.craftsrecords.talkadvisor.recommendation.Recommendation
import org.craftsrecords.talkadvisor.recommendation.profile.Profile
import org.craftsrecords.talkadvisor.recommendation.talk.Talk

val Recommendation.that: RecommendationAssert
    get() = RecommendationAssert(this)

val Talk.that: TalkAssert
    get() = TalkAssert(this)

val Iterable<Talk>.those: TalksAssert
    get() = TalksAssert(this)

val Profile.that: ProfileAssert
    get() = ProfileAssert(this)