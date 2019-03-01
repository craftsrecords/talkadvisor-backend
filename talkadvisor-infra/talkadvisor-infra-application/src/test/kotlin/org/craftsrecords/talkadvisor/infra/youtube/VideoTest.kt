package org.craftsrecords.talkadvisor.infra.youtube

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

internal class VideoTest {

    @Test
    fun `should be mapped to TalkBuilder`() {
        val title = "title"
        val videoId = "id"
        val video = Video(VideoId(videoId), Snippet(title))

        val talkBuilder = video.toTalkBuilder()

        assertThat(talkBuilder.id).isEqualTo(videoId)
        assertThat(talkBuilder.title).isEqualTo(title)
        assertThatThrownBy { talkBuilder.duration }.isInstanceOf(UninitializedPropertyAccessException::class.java)
    }
}