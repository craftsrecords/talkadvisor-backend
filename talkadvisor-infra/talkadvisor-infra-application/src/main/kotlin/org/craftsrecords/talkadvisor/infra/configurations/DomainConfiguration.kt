package org.craftsrecords.talkadvisor.infra.configurations

import org.craftsrecords.hexarch.DomainService
import org.craftsrecords.talkadvisor.recommendation.Recommendation
import org.craftsrecords.talkadvisor.recommendation.spi.stubs.HardCodedTalksSearcher
import org.craftsrecords.talkadvisor.recommendation.spi.stubs.InMemoryProfiles
import org.craftsrecords.talkadvisor.recommendation.spi.stubs.InMemoryRecommendations
import org.springframework.context.annotation.*

@Configuration
@ComponentScan(
        basePackageClasses = [Recommendation::class],
        includeFilters = [ComponentScan.Filter(type = FilterType.ANNOTATION, value = [DomainService::class])])
class DomainConfiguration {

    @Bean
    @Profile("searchTalksStub")
    fun searchTalksStub() = HardCodedTalksSearcher()

    @Bean
    fun profiles() = InMemoryProfiles()

    @Bean
    fun recommendations() = InMemoryRecommendations()
}