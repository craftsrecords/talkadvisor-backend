package org.craftsrecords.talkadvisor.recommendation.preferences

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

internal class TopicTest {

    @Test
    fun `should not create a test with a blank name`() {
        assertThatThrownBy { Topic("  ") }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage("Cannot create a topic with a blank name")
    }

    @Test
    fun `should satisfy value object equality`() {
        val topic = Topic("topic")
        val topic2 = Topic("topic")

        assertThat(topic).isEqualTo(topic2)
        assertThat(topic.hashCode()).isEqualTo(topic2.hashCode())
    }

    @Test
    fun `should satisfy value object inequality`() {
        val topic = Topic("topic")
        val topic2 = Topic("topic2")

        assertThat(topic).isNotEqualTo(topic2)
        assertThat(topic.hashCode()).isNotEqualTo(topic2.hashCode())
    }
}