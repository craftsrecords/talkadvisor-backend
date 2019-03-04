package org.craftsrecords.talkadvisor

import com.intuit.karate.junit5.Karate

class IntegrationAcceptanceTest {

    @Karate.Test
    fun tests(): Karate {
        return Karate().relativeTo(this::class.java).tags("~@ignore", "~@request")
    }
}