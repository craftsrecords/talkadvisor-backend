package org.craftsrecords.talkadvisor.recommendation

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.Test

internal class TopicTest{

    @Test
    fun `should not create a test with a blank name`(){
        assertThatThrownBy { Topic("  ") }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage("Cannot create a topic with a blank name")
    }
}