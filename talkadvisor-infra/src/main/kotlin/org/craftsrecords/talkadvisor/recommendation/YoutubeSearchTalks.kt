package org.craftsrecords.talkadvisor.recommendation

import com.google.api.services.youtube.YouTube
import com.google.api.services.youtube.model.SearchListResponse
import com.google.api.services.youtube.model.SearchResult
import com.google.common.collect.ImmutableSet
import org.craftsrecords.talkadvisor.recommendation.preferences.Topic
import org.craftsrecords.talkadvisor.recommendation.spi.SearchTalks
import org.craftsrecords.talkadvisor.recommendation.talk.Talk
import org.slf4j.LoggerFactory
import java.time.Duration


class YoutubeSearchTalks(val youtube: YouTube, val apiKey: String) : SearchTalks {

    private val LOG = LoggerFactory.getLogger(YoutubeSearchTalks::class.java)

    private val YOUTUBE_CHANNEL: String = "UCCBVCTuk6uJrN3iFV_3vurg"

    private val NUMBER_OF_VIDEOS_RETURNED: Long = 25

    override fun forTopics(topics: Set<Topic>): Set<Talk> {
        val searchByTopics = buildYoutubeSearchFor(topics)
        val response = searchByTopics.execute()
        return convertResponseToDomainTalks(response)
    }

    private fun convertResponseToDomainTalks(response: SearchListResponse): ImmutableSet<Talk> {
        val talks: MutableList<Talk> = mutableListOf()
        val iterator = response.items.iterator()

        while (iterator.hasNext()) {
            try {
                talks.add(toDomainTalk(iterator.next()))
            } catch (e: IllegalStateException) {
                LOG.warn("Video skipped, cause by uncomplete data received", e)
            }
        }

        return ImmutableSet.copyOf(talks)
    }

    private fun buildYoutubeSearchFor(topics: Set<Topic>): YouTube.Search.List {
        val search = youtube.search().list("id,snippet")
        search.key = apiKey
        search.channelId = YOUTUBE_CHANNEL
        search.maxResults = NUMBER_OF_VIDEOS_RETURNED
        search.q = topics.joinToString(" ") { it.name }
        search.type = "video"
        search.fields = "items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)"
        return search
    }

    private fun toDomainTalk(singleVideo: SearchResult): Talk {
        val videoId = singleVideo.id.videoId
        return Talk.with {
            id = videoId
            title = singleVideo.snippet.title
            duration = retrieveDurationFor(videoId)
        }.build()
    }

    private fun retrieveDurationFor(videoId: String): Duration {
        val searchByVideoId = buildYoutubeSearchFor(videoId)
        val response = searchByVideoId.execute()
        return Duration.parse(response.items[0].contentDetails.duration)
    }

    private fun buildYoutubeSearchFor(videoId: String): YouTube.Videos.List {
        val search = youtube.videos().list("contentDetails")
        search.key = apiKey
        search.id = videoId
        return search
    }
}
