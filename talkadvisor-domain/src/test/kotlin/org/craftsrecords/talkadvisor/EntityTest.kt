package org.craftsrecords.talkadvisor

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

interface EntityTest<T> {

    fun createEqualEntities(): Pair<T, T>
    fun createNonEqualEntities(): Pair<T, T>

    @Test
    fun `should satisfy entity equality`() {
        val (entity1, entity2) = createEqualEntities()
        assertThat(entity1).isEqualTo(entity2)
        assertThat(entity1.hashCode()).isEqualTo(entity2.hashCode())
    }

    @Test
    fun `should satisfy entity inequality`() {
        val (entity1, entity2) = createNonEqualEntities()
        assertThat(entity1).isNotEqualTo(entity2)
        assertThat(entity1.hashCode()).isNotEqualTo(entity2.hashCode())
    }

}