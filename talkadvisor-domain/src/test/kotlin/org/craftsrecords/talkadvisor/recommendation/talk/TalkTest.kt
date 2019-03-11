package org.craftsrecords.talkadvisor.recommendation.talk

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.craftsrecords.talkadvisor.EntityTest
import org.craftsrecords.talkadvisor.recommendation.assertions.that
import org.craftsrecords.talkadvisor.recommendation.talk.TalkFormat.CONFERENCE
import org.junit.jupiter.api.Test
import java.time.Duration

internal class TalkTest : EntityTest<Talk> {

    override fun createEqualEntities() = Pair(
            talk().build(),
            talk().apply { title = "new title" }.build()
    )

    override fun createNonEqualEntities() = Pair(
            talk().build(),
            talk().apply { id = "new id" }.build()
    )

    @Test
    fun `should create a conference`() {
        val talk = talk().build()

        talk.that `has id` "id"
        talk.that `has title` "title"
        talk.that `has duration` Duration.ofMinutes(45)
        talk.that `is in the format` CONFERENCE
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