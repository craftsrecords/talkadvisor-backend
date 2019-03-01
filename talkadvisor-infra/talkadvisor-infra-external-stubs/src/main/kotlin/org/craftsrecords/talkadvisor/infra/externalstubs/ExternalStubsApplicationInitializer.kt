package org.craftsrecords.talkadvisor.infra.externalstubs

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext

class ExternalStubsApplicationInitializer : ApplicationContextInitializer<ConfigurableApplicationContext> {
    override fun initialize(configurableApplicationContext: ConfigurableApplicationContext) {
        val wiremockserver = WireMockServer(wireMockConfig().httpsPort(9091).usingFilesUnderClasspath("stubs").enableBrowserProxying(true))
        wiremockserver.start()
    }
}