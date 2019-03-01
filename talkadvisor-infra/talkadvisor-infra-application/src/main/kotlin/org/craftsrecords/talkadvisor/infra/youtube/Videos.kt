package org.craftsrecords.talkadvisor.infra.youtube

import org.craftsrecords.talkadvisor.recommendation.talk.Talk

internal class Videos(val items: List<Video>) {
    fun toTalkBuilders(): List<Talk.Builder> {
        return items.map(Video::toTalkBuilder)
    }
}

internal class Video(val id: VideoId, private val snippet: Snippet) {
    internal fun toTalkBuilder(): Talk.Builder {
        val videoId = id.videoId
        return Talk.with {
            id = videoId
            title = snippet.title
        }
    }
}

internal class VideoId(val videoId: String)

internal class Snippet(val title: String)