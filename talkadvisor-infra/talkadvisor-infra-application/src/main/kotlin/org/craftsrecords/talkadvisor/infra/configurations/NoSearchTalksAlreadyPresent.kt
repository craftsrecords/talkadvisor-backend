package org.craftsrecords.talkadvisor.infra.configurations

import org.craftsrecords.talkadvisor.recommendation.spi.SearchTalks
import org.springframework.context.annotation.Condition
import org.springframework.context.annotation.ConditionContext
import org.springframework.core.type.AnnotatedTypeMetadata

//Not using @ConditionalOnMissingBean since this is reserved for AutoConfigurations
class NoSearchTalksAlreadyPresent : Condition {
    override fun matches(conditionContext: ConditionContext, annotatedTypeMetadata: AnnotatedTypeMetadata): Boolean {
        return !conditionContext.beanFactory!!.containsBean(SearchTalks::class.qualifiedName!!)
    }

}
