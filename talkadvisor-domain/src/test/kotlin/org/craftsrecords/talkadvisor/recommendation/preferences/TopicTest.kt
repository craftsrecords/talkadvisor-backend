package org.craftsrecords.talkadvisor.recommendation.preferences

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.craftsrecords.talkadvisor.ValueObjectTest
import org.junit.jupiter.api.Test

internal class TopicTest : ValueObjectTest<Topic> {

    override fun createValue() = Topic("topic")
    override fun createOtherValue() = Topic("topic2")

    @Test
    fun `should not create a topic with a blank name`() {
        assertThatThrownBy { Topic("  ") }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage("Cannot create a topic with a blank name")
    }
}