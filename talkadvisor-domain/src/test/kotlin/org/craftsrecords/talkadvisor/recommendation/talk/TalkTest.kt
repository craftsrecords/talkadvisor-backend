package org.craftsrecords.talkadvisor.recommendation.talk

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.craftsrecords.talkadvisor.recommendation.talk.TalkFormat.CONFERENCE
import org.junit.jupiter.api.Test
import java.time.Duration
import kotlin.IllegalArgumentException
import kotlin.apply
import org.craftsrecords.talkadvisor.recommendation.assertions.TalkAdvisorAssertions.Asserter.assertThat as assert

internal class TalkTest {


    @Test
    fun `should create a conference`() {
        val talk = talk().build()

        assertThat(talk.id).isEqualTo("id")
        assertThat(talk.title).isEqualTo("title")
        assertThat(talk.duration).isEqualTo(Duration.ofMinutes(45))
        assert(talk) `is in the format` CONFERENCE
    }

    @Test
    fun `should not create a talk with a blank id`() {
        assertThatThrownBy { talk().apply { id = "" }.build() }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage("Cannot create a Talk is a blank id")
    }

    @Test
    fun `should not create a talk with a blank title`() {
        assertThatThrownBy { talk().apply { title = "" }.build() }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage("Cannot create a Talk is a blank title")
    }

    @Test
    fun `should not create a talk with a negative duration`() {
        assertThatThrownBy { talk().apply { duration = Duration.ofMinutes(-10) }.build() }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage("Talk duration must be strictly positive")
    }

    fun talk() =
            Talk.with {
                id = "id"
                title = "title"
                duration = Duration.ofMinutes(45)
            }
}