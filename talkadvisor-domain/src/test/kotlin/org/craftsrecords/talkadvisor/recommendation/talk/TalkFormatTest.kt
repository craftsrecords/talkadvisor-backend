package org.craftsrecords.talkadvisor.recommendation.talk

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.craftsrecords.talkadvisor.recommendation.talk.TalkFormat.CONFERENCE
import org.craftsrecords.talkadvisor.recommendation.talk.TalkFormat.QUICKIE
import org.junit.jupiter.api.Test
import java.time.Duration.ZERO
import java.time.Duration.ofMinutes

internal class TalkFormatTest {
    @Test
    fun `should return conference when duration is 45min`() {
        val fortyFiveMinutes = ofMinutes(45)
        assertThat(TalkFormat.ofDuration(fortyFiveMinutes)).isEqualTo(CONFERENCE)
    }

    @Test
    fun `should return quickie when duration is 15min`() {
        val fifteenMinutes = ofMinutes(15)
        assertThat(TalkFormat.ofDuration(fifteenMinutes)).isEqualTo(QUICKIE)
    }

    @Test
    fun `should not give a duration when less than the minimum of the smallest format`() {
        assertThatThrownBy { TalkFormat.ofDuration(ZERO) }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage("Duration is less than expected")
    }

    @Test
    fun `should return university when duration is more than the maximum`() {
        val fifteenMinutes = ofMinutes(15)
        assertThat(TalkFormat.ofDuration(fifteenMinutes)).isEqualTo(QUICKIE)
    }

}