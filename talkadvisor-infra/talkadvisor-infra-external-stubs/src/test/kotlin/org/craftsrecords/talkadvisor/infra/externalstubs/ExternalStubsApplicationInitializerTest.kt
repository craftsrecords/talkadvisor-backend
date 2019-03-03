package org.craftsrecords.talkadvisor.infra.externalstubs

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.context.support.StaticApplicationContext
import org.springframework.core.env.ConfigurableEnvironment
import org.springframework.core.env.get
import org.springframework.http.HttpStatus
import org.springframework.mock.env.MockEnvironment
import org.springframework.web.client.RestTemplate

class ExternalStubsApplicationInitializerTest {

    private val restTemplate = RestTemplate()
    private val externalStubsApplicationInitializer = ExternalStubsApplicationInitializer()

    @Test
    fun `should stub youtube search`() {
        val context = configureApplicationContext()
        externalStubsApplicationInitializer.initialize(context)

        val overloadedYoutubeUri = context.environment["youtube.api.base-uri"]
        val response =
                restTemplate.getForEntity(
                        "$overloadedYoutubeUri/search?part=id,snippet&channelId=UCCBVCTuk6uJrN3iFV_3vurg&maxResults=25&q=DDD%7Chexagonal%20architecture&type=video&fields=items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)&key=API_KEY",
                        String::class.java)

        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(response.body).isEqualTo(expectedSearchResponse())
    }

    @Test
    fun `should stub youtube video list`() {
        val context = configureApplicationContext()
        externalStubsApplicationInitializer.initialize(context)

        val overloadedYoutubeUri = context.environment["youtube.api.base-uri"]
        val response =
                restTemplate.getForEntity(
                        "$overloadedYoutubeUri/videos?part=contentDetails&id=xZOO_CksS-E%2CXOyyLwyrBQU,ez9GWESKG4I&fields=items(contentDetails/duration,id)&key=API_KEY",
                        String::class.java)

        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(response.body).isEqualTo(expectedVideosListResponse())
    }


    @Test
    fun `should not stub youtube if profile not activated`() {
        val context = configureApplicationContext()
        context.environment.setActiveProfiles("FORCE_RESET_PROFILES")

        externalStubsApplicationInitializer.initialize(context)

        val overloadedYoutubeUri = context.environment["youtube.api.base-uri"]
        assertThat(overloadedYoutubeUri).isNull()
    }

    private fun configureApplicationContext(): StaticApplicationContext {
        val environment: ConfigurableEnvironment = MockEnvironment()
        environment.setActiveProfiles("YouTubeStub")

        val context = StaticApplicationContext()
        context.environment = environment
        return context
    }

    private fun expectedSearchResponse() =
            readResponseFromResources("/stubs/__files/youtube/youtube-search-response.json")

    private fun expectedVideosListResponse() =
            readResponseFromResources("/stubs/__files/youtube/youtube-videos-list-response.json")

    private fun readResponseFromResources(path: String) = this::class.java.getResource(path).readText()
}
