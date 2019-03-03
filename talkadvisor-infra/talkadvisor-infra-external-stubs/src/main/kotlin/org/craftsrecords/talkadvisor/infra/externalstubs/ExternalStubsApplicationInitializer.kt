package org.craftsrecords.talkadvisor.infra.externalstubs

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.core.env.ConfigurableEnvironment
import org.springframework.core.env.MapPropertySource
import org.springframework.core.env.Profiles

class ExternalStubsApplicationInitializer : ApplicationContextInitializer<ConfigurableApplicationContext> {
    private val youtubeStubProfileName = "YouTubeStub"

    override fun initialize(configurableApplicationContext: ConfigurableApplicationContext) {
        val environment = configurableApplicationContext.environment

        if (environment.acceptsProfiles(Profiles.of(youtubeStubProfileName))) {
            val wiremockserverPort = startWireMock()
            environment.overloadYoutubeUri(wiremockserverPort)
        }
    }

    private fun startWireMock(): Int {
        val wiremockServer = WireMockServer(wireMockConfig().dynamicPort().usingFilesUnderClasspath("stubs"))
        wiremockServer.start()
        return wiremockServer.port()
    }

    private fun ConfigurableEnvironment.overloadYoutubeUri(wiremockserverPort: Int) {
        val properties = mutableMapOf<String, Any>()
        properties["youtube.api.base-uri"] = "http://localhost:$wiremockserverPort/youtube"
        this.propertySources.addFirst(MapPropertySource("external-stub-properties", properties))
    }
}