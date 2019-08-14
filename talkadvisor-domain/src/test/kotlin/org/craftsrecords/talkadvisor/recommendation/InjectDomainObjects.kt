package org.craftsrecords.talkadvisor.recommendation

import org.craftsrecords.talkadvisor.recommendation.criteria.CriteriaParameterResolver
import org.craftsrecords.talkadvisor.recommendation.profile.ProfileParameterResolver
import org.craftsrecords.talkadvisor.recommendation.talk.TalkParameterResolver
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.CLASS
import kotlin.annotation.AnnotationTarget.FILE

@Retention(RUNTIME)
@Target(CLASS, FILE)
@ExtendWith(
        CriteriaParameterResolver::class,
        ProfileParameterResolver::class,
        TalkParameterResolver::class,
        TalksLinkedToCriteriaParameterResolver::class,
        RecommendationParameterResolver::class)
annotation class InjectDomainObjects