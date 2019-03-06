package org.craftsrecords.talkadvisor

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

interface ValueObjectTest<T> {

    fun createValue(): T
    fun createOtherValue(): T

    @Test
    fun `should satisfy value object equality`() {
        val value1 = createValue()
        val value2 = createValue()
        assertThat(value1).isEqualTo(value2)
        assertThat(value1.hashCode()).isEqualTo(value2.hashCode())
    }

    @Test
    fun `should satisfy value object inequality`() {
        val value1 = createValue()
        val value2 = createOtherValue()
        assertThat(value1).isNotEqualTo(value2)
        assertThat(value1.hashCode()).isNotEqualTo(value2.hashCode())
    }

}