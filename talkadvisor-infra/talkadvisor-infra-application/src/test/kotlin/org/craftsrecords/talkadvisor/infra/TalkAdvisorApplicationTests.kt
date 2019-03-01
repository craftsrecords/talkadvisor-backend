package org.craftsrecords.talkadvisor.infra

import org.assertj.core.api.Assertions.assertThat
import org.craftsrecords.talkadvisor.infra.youtube.YouTubeSearchTalks
import org.craftsrecords.talkadvisor.recommendation.spi.SearchTalks
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
class TalkAdvisorApplicationTests {

    @Autowired
    private lateinit var searchTalks: SearchTalks

    @Test
    fun contextLoads() {
        assertThat(searchTalks).isInstanceOf(YouTubeSearchTalks::class.java)
    }
}

