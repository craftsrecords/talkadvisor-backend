package org.craftsrecords.talkadvisor.recommendation.duration

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.Duration.ZERO
import java.time.Duration.ofMinutes


internal class DurationTest {
    @Test
    fun `should be positive when greater than zero`() {
        assertThat(ofMinutes(5).isPositive()).isTrue()
    }

    @Test
    fun `should not be positive with a zero duration`() {
        assertThat(ZERO.isPositive()).isFalse()
    }

    @Test
    fun `should not be positive when lesser than zero`() {
        assertThat(ofMinutes(-5).isPositive()).isFalse()
    }

    @Test
    fun `should be in range`() {
        val range = ofMinutes(-1).rangeTo(ofMinutes(1))
        assertThat(ZERO.isInRange(range)).isTrue()
    }

    @Test
    fun `should not be in range when lesser than start`() {
        val range = ZERO.rangeTo(ofMinutes(1))
        assertThat(ofMinutes(-1).isInRange(range)).isFalse()
    }

    @Test
    fun `should not be in range when greater than end`() {
        val range = ofMinutes(-1).rangeTo(ZERO)
        assertThat(ofMinutes(1).isInRange(range)).isFalse()
    }
}