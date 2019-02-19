package org.craftsrecords.talkadvisor

import cucumber.runtime.java.picocontainer.PicoFactory
import org.craftsrecords.talkadvisor.recommendation.TalksAdvisor
import org.craftsrecords.talkadvisor.recommendation.stepdefs.TestContext

class CustomPicoFactory : PicoFactory() {
    init {
        addClass(TestContext::class.java)
        addClass(TalksAdvisor::class.java)
    }
}