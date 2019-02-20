package org.craftsrecords.talkadvisor

import cucumber.runtime.java.picocontainer.PicoFactory
import org.craftsrecords.talkadvisor.recommendation.TalksAdvisor
import org.craftsrecords.talkadvisor.recommendation.profile.ProfileCreator
import org.craftsrecords.talkadvisor.recommendation.spi.stubs.HardCodedTalksSearcher
import org.craftsrecords.talkadvisor.recommendation.spi.stubs.InMemoryProfiles
import org.craftsrecords.talkadvisor.recommendation.spi.stubs.InMemoryRecommendations
import org.craftsrecords.talkadvisor.recommendation.stepdefs.TestContext

class CustomPicoFactory : PicoFactory() {
    init {
        addClass(TestContext::class.java)
        addClass(TalksAdvisor::class.java)
        addClass(HardCodedTalksSearcher::class.java)
        addClass(InMemoryRecommendations::class.java)
        addClass(ProfileCreator::class.java)
        addClass(InMemoryProfiles::class.java)
    }
}