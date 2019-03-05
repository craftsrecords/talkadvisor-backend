package org.craftsrecords.talkadvisor.recommendation

import org.assertj.core.api.Assertions.assertThat
import org.craftsrecords.talkadvisor.recommendation.criteria.GuestCriteria
import org.craftsrecords.talkadvisor.recommendation.spi.Profiles
import org.craftsrecords.talkadvisor.recommendation.spi.Recommendations
import org.craftsrecords.talkadvisor.recommendation.spi.SearchTalks
import org.craftsrecords.talkadvisor.recommendation.talk.Talk
import org.craftsrecords.talkadvisor.recommendation.talk.TalkFormat.IGNITE
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.anySet
import org.mockito.BDDMockito.given
import org.mockito.Mockito.mock
import java.time.Duration

/**
 * This test is not misplaced, this is there to demonstrate the caveat of mock frameworks.
 * This is just there not to pollute the domain
 */
internal class MockCaveatTest {

    private val searchTalks = mock(SearchTalks::class.java)

    //Have to do it this way since we cannot use any() with kotlin: this return null and recommendation.save doesn't allow it
    private val recommendations: Recommendations = object : Recommendations {
        override fun save(recommendation: Recommendation): Recommendation {
            return recommendation
        }
    }

    private val profiles = mock(Profiles::class.java)

    @Test
    fun `should retrieve talks`() {

        val mockedTalk = mock(Talk::class.java)
        given(mockedTalk.format).willReturn(IGNITE)
        given(mockedTalk.duration).willReturn(Duration.ofHours(1))
        // this resulted Talk Object is invalid, the source of truth is the duration
        // but here with mockito we overload the format computation logic
        given(searchTalks.forTopics(anySet())).willReturn(setOf(mockedTalk))

        val recommendTalks = TalksAdvisor(searchTalks, recommendations, profiles)

        val recommendation = recommendTalks.satisfying(GuestCriteria(setOf(), setOf(IGNITE)))

        assertThat(recommendation.talks).containsExactly(mockedTalk) //it should have been filtered
        assertThat(recommendation.talks.first().format).isEqualTo(IGNITE)
        assertThat(recommendation.talks.first().duration).isEqualTo(Duration.ofHours(1))
        //the test is incorrect but will still pass
    }

}