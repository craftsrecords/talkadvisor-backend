package org.craftsrecords.talkadvisor.infra.externalstubs

import org.junit.jupiter.api.Test
import org.springframework.context.support.StaticApplicationContext
import org.springframework.web.client.RestTemplate

class ExternalStubsApplicationInitializerTest {

    val restTemplate = RestTemplate()
    private val externalStubsApplicationInitializer = ExternalStubsApplicationInitializer()

    @Test
    fun `should start wiremock`() {
        externalStubsApplicationInitializer.initialize(StaticApplicationContext())
        Thread.sleep(2000)
        val content: String? = restTemplate.getForObject("https://www.googleapis.com/youtube/v3/search", String::class.java)
        println(content)
    }

}