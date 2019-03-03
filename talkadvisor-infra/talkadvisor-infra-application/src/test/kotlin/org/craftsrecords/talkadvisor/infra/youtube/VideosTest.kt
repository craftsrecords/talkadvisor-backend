package org.craftsrecords.talkadvisor.infra.youtube

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

internal class VideosTest {

    @Test
    fun `should map to Talk Builder list`() {
        val title = "title"
        val videoId = "id"
        val video = Video(VideoId(videoId), Snippet(title))
        val videos = Videos(listOf(video))

        val talkBuilders = videos.toTalkBuilders().toList()

        assertThat(talkBuilders).hasSize(1)

        val talkBuilder = talkBuilders.first()
        assertThat(talkBuilder.id).isEqualTo(videoId)
        assertThat(talkBuilder.title).isEqualTo(title)
        assertThatThrownBy { talkBuilder.duration }.isInstanceOf(UninitializedPropertyAccessException::class.java)
    }
}