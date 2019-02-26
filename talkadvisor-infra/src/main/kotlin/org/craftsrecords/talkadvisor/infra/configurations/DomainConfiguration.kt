package org.craftsrecords.talkadvisor.infra.configurations

import org.craftsrecords.hexarch.DomainService
import org.craftsrecords.hexarch.Stub
import org.craftsrecords.talkadvisor.recommendation.Recommendation
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.FilterType

@Configuration
@ComponentScan(
        basePackageClasses = [Recommendation::class],
        includeFilters = [ComponentScan.Filter(type = FilterType.ANNOTATION,
                value = [DomainService::class, Stub::class])])
class DomainConfiguration