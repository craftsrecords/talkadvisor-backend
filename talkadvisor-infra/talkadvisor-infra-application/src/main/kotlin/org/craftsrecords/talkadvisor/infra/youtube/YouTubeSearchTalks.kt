package org.craftsrecords.talkadvisor.infra.youtube

import org.craftsrecords.talkadvisor.infra.configurations.NoSearchTalksAlreadyPresent
import org.craftsrecords.talkadvisor.recommendation.preferences.Topic
import org.craftsrecords.talkadvisor.recommendation.spi.SearchTalks
import org.craftsrecords.talkadvisor.recommendation.talk.Talk
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Conditional
import org.springframework.stereotype.Component
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.util.UriComponentsBuilder
import java.net.URI
import java.time.Duration

@Component
@Conditional(NoSearchTalksAlreadyPresent::class)
class YouTubeSearchTalks(
        restTemplateBuilder: RestTemplateBuilder,
        @Value("\${youtube.api.base-uri}") val youtubeApiBaseUri: String,
        @Value("\${youtube.api.key}") val apiKey: String,
        @Value("\${talks.max-number}") override val maxNumberOfTalks: Int) : SearchTalks {

    private val restTemplate = restTemplateBuilder.build()
    private val devoxxChannelId = "UCCBVCTuk6uJrN3iFV_3vurg"

    override fun forTopics(topics: Set<Topic>): Set<Talk> {

        val videosWithoutDuration = retrieveVideosWithoutDuration(topics)
        val videosDuration = retrieveVideosDuration(videosWithoutDuration)

        val talksWithoutDuration = videosWithoutDuration.toTalkBuilders()
        return talksWithoutDuration.buildWithDurationsFrom(videosDuration)
    }

    private fun retrieveVideosDuration(videosWithoutDuration: Videos): Map<String, Duration> {
        return videosWithoutDuration
                .extractVideoIds()
                .let { toVideosListOptions(it) }
                .let { toVideosListRequest(it) }
                .let { restTemplate.getForObject(it, VideosDetails::class.java)!! }
                .idToDurationMap()
    }

    private fun retrieveVideosWithoutDuration(topics: Set<Topic>): Videos {
        val searchRequest = searchRequest(topics)
        return restTemplate.getForObject(searchRequest, Videos::class.java)!!
    }

    private fun toVideosListRequest(options: MultiValueMap<String, String>): URI {
        return UriComponentsBuilder.fromUriString(youtubeApiBaseUri)
                .path("/videos")
                .queryParams(options)
                .build()
                .toUri()
    }

    private fun toVideosListOptions(videosIds: List<String>): MultiValueMap<String, String> {
        val options: MultiValueMap<String, String> = LinkedMultiValueMap()
        options["part"] = "contentDetails"
        options["id"] = videosIds.joinToString(",")
        options["fields"] = "items(contentDetails/duration,id)"
        options["key"] = apiKey
        return options
    }

    private fun searchRequest(topics: Set<Topic>): URI {
        return UriComponentsBuilder.fromUriString(youtubeApiBaseUri)
                .path("/search")
                .queryParams(searchOptions(topics))
                .build()
                .toUri()
    }

    private fun searchOptions(topics: Set<Topic>): MultiValueMap<String, String> {
        val options: MultiValueMap<String, String> = LinkedMultiValueMap()
        options["part"] = "id,snippet"
        options["channelId"] = devoxxChannelId
        options["maxResults"] = maxNumberOfTalks.toString()
        options["q"] = topics.joinToString("|") { it.name }
        options["type"] = "video"
        options["fields"] = "items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)"
        options["key"] = apiKey
        return options
    }

    private fun Talk.Builder.withDurationFrom(videosDuration: Map<String, Duration>): Talk.Builder {
        return this.apply { duration = videosDuration.getValue(id) }
    }

    private fun Iterable<Talk.Builder>.buildWithDurationsFrom(videosDuration: Map<String, Duration>): Set<Talk> {
        return this.map { it.withDurationFrom(videosDuration) }
                .map(Talk.Builder::build)
                .toSet()
    }

    private fun Videos.extractVideoIds(): List<String> {
        return this.items.map { it.id.videoId }
    }

    private fun VideosDetails.idToDurationMap(): Map<String, Duration> {
        return this.items.map { it.id to it.contentDetails.duration }.toMap()
    }
}
