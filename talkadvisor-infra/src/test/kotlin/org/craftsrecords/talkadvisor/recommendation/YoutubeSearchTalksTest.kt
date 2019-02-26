import com.google.api.client.http.HttpRequestInitializer
import com.google.api.client.http.LowLevelHttpRequest
import com.google.api.client.http.LowLevelHttpResponse
import com.google.api.client.json.Json
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.client.testing.http.MockHttpTransport
import com.google.api.client.testing.http.MockLowLevelHttpRequest
import com.google.api.client.testing.http.MockLowLevelHttpResponse
import com.google.api.services.youtube.YouTube
import org.assertj.core.api.Assertions.assertThat
import org.craftsrecords.talkadvisor.recommendation.YoutubeSearchTalks
import org.craftsrecords.talkadvisor.recommendation.preferences.Topic
import org.craftsrecords.talkadvisor.recommendation.talk.Talk
import org.junit.jupiter.api.Test
import java.io.File

internal class YoutubeSearchTalksTest {

    private val apiKey = "NOT_REQUIRED_DURING_TESTING"

    private val youtubeSearchResponse: File = File(javaClass.classLoader.getResource("payloads/youtube-search-response.json").file)

    private val youtubeVideoResponse: File = File(javaClass.classLoader.getResource("payloads/youtube-video-response.json").file)

    private val youtubeSearchUncompleteResponse: File = File(javaClass.classLoader.getResource("payloads/youtube-search-uncomplete-response.json").file)

    @Test
    internal fun `return 25 videos per talks searching`() {
        val transportStub = object : MockHttpTransport() {
            override fun buildRequest(method: String, url: String): LowLevelHttpRequest = buildMockLowLevelHttpRequestBy(url)
        }
        val talks = makeYoutubeSearchFor("DDD", transportStub)

        assertThat(talks).hasSize(25)
    }

    @Test
    internal fun `return only videos for valid talks`() {
        val transportStub = object : MockHttpTransport() {
            override fun buildRequest(method: String, url: String): LowLevelHttpRequest = buildUncompleteResponseMockLowLevelHttpRequestBy(url)
        }
        val talks = makeYoutubeSearchFor("DDD", transportStub)

        assertThat(talks).hasSize(24)
    }

    private fun makeYoutubeSearchFor(topicName: String, transportStub: MockHttpTransport): Set<Talk> {
        val youtube = YouTube.Builder(transportStub, JacksonFactory(), HttpRequestInitializer { })
                .setApplicationName("TalkAdvisor")
                .build()
        val youtubeSearchTalks = YoutubeSearchTalks(youtube, apiKey)
        return youtubeSearchTalks.forTopics(setOf(Topic(topicName)))
    }


    private fun buildUncompleteResponseMockLowLevelHttpRequestBy(url: String): LowLevelHttpRequest {
        return when {
            url.contains("search") -> object : MockLowLevelHttpRequest() {
                override fun execute(): LowLevelHttpResponse {
                    val result = MockLowLevelHttpResponse()
                    result.contentType = Json.MEDIA_TYPE
                    result.setContent(youtubeSearchUncompleteResponse.readBytes())
                    return result
                }
            }
            else -> object : MockLowLevelHttpRequest() {
                override fun execute(): LowLevelHttpResponse {
                    val result = MockLowLevelHttpResponse()
                    result.contentType = Json.MEDIA_TYPE
                    result.setContent(youtubeVideoResponse.readBytes())
                    return result
                }
            }
        }
    }

    private fun buildMockLowLevelHttpRequestBy(url: String): MockLowLevelHttpRequest {
        return when {
            url.contains("search") -> object : MockLowLevelHttpRequest() {
                override fun execute(): LowLevelHttpResponse {
                    val result = MockLowLevelHttpResponse()
                    result.contentType = Json.MEDIA_TYPE
                    result.setContent(youtubeSearchResponse.readBytes())
                    return result
                }
            }
            else -> object : MockLowLevelHttpRequest() {
                override fun execute(): LowLevelHttpResponse {
                    val result = MockLowLevelHttpResponse()
                    result.contentType = Json.MEDIA_TYPE
                    result.setContent(youtubeVideoResponse.readBytes())
                    return result
                }
            }
        }
    }
}