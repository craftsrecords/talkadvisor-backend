package org.craftsrecords.talkadvisor.infra.youtube

import org.craftsrecords.talkadvisor.recommendation.assertions.those
import org.craftsrecords.talkadvisor.recommendation.preferences.Topic
import org.craftsrecords.talkadvisor.recommendation.talk.Talk
import org.hamcrest.Matchers.startsWith
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest
import org.springframework.core.io.Resource
import org.springframework.http.HttpMethod.GET
import org.springframework.http.MediaType.APPLICATION_JSON_UTF8
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.client.MockRestServiceServer
import org.springframework.test.web.client.match.MockRestRequestMatchers.*
import org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess
import java.net.URLEncoder.encode
import java.nio.charset.StandardCharsets.UTF_8
import java.time.Duration


@ExtendWith(SpringExtension::class)
@RestClientTest(YouTubeSearchTalks::class)
internal class YouTubeSearchTalksTest {

    @Autowired
    private lateinit var youTubeSearchTalks: YouTubeSearchTalks

    @Autowired
    private lateinit var mockServer: MockRestServiceServer

    @Value("classpath:payloads/youtube-search-response.json")
    private lateinit var searchResponse: Resource

    @Value("classpath:payloads/youtube-videos-response.json")
    private lateinit var videosResponse: Resource

    @Test
    fun `should search for youtube video`() {
        configureMockServer()
        val topics = setOf(Topic("DDD"), Topic("TDD"))

        val talks = youTubeSearchTalks.forTopics(topics)

        talks.those `contains exactly in any order elements of` theExpectedTalks()
    }

    private fun theExpectedTalks(): Iterable<Talk> {
        return setOf(
                Talk.with {
                    id = "xZOO_CksS-E"
                    title = "Hexagonal at Scale, with DDD and microservices! (Cyrille Martraire)"
                    duration = Duration.parse("PT45M56S")
                }.build(),

                Talk.with {
                    id = "2vEoL3Irgiw"
                    title = "Improving your Test Driven Development in 45 minutes - Jakub Nabrdalik"
                    duration = Duration.parse("PT1H")
                }.build()
        )
    }

    private fun configureMockServer() {
        this.mockServer
                .expect(method(GET))
                .andExpect(requestTo(startsWith("localhost/search")))
                .andExpect(queryParam("part", "id,snippet"))
                .andExpect(queryParam("channelId", "UCCBVCTuk6uJrN3iFV_3vurg"))
                .andExpect(queryParam("maxResults", "2"))
                .andExpect(queryParam("q", encode("DDD|TDD", UTF_8.toString())))
                .andExpect(queryParam("type", "video"))
                .andExpect(queryParam("fields", "items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)"))
                .andExpect(queryParam("key", "API_KEY"))
                .andRespond(withSuccess(searchResponse, APPLICATION_JSON_UTF8))

        this.mockServer
                .expect(method(GET))
                .andExpect(requestTo(startsWith("localhost/videos")))
                .andExpect(queryParam("part", "contentDetails"))
                .andExpect(queryParam("id", "xZOO_CksS-E,2vEoL3Irgiw"))
                .andExpect(queryParam("fields", "items(contentDetails/duration,id)"))
                .andExpect(queryParam("key", "API_KEY"))
                .andRespond(withSuccess(videosResponse, APPLICATION_JSON_UTF8))
    }
}